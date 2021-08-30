package net

import listener.OPQBaseListener
import constant.MsgType
import core.Core
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoreRequest {
    private lateinit var retrofit: Retrofit
    private lateinit var api: CoreApi
    private lateinit var listener: OPQBaseListener
    fun init(baseUrl:String,listener: OPQBaseListener) {
        this.listener = listener
        retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(CoreApi::class.java)
    }

    fun sendPrivateMsg(toQQ: Long, content: String) {
        sendMsg(null, toQQ, content, MsgType.MSG_PRIVATE)
    }

    fun sendGroupMsg(toGroup: Long, content: String) {
        sendMsg(null, toGroup, content, MsgType.MSG_GROUP)
    }

    fun sendGroupMsgWithAt(atQQ: Array<Long>, toGroup: Long, content: String) {
        sendMsg(atQQ, toGroup, content, MsgType.MSG_GROUP)
    }

    private fun sendMsg(atQQ: Array<Long>?, group: Long, content: String, type: Int) {
        var nContent = content.replace("\n", "\\n").replace("\"", "\\\"")
        atQQ?.let {
            var atContent = "[ATUSER("
            var i = 1
            for (l in it) {
                atContent += l
                if (i != it.size)
                    atContent += ","
                i++
            }
            nContent = "$atContent)] $nContent"
        }
        sendMsg("/v1/LuaApiCaller?qq=${Core.currentQQ}&funcname=SendMsg&timeout=10","""{"toUser":$group,"sendToType":$type,"sendMsgType":"TextMsg","content":"$nContent","groupid":0,"atUser":0}""")
    }

    @Synchronized
    fun sendMsg(url:String,jsonContent: String) {
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonContent)
        try {
            api.sendMsg(url, body).enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(p0: Call<ResponseBody?>, p1: Response<ResponseBody?>) {
                    p1.body()?.string()?.let { listener.onMsgSent(jsonContent, it) }
                }

                override fun onFailure(p0: Call<ResponseBody?>, p1: Throwable) {
                    listener.onSendMsgError(jsonContent, p1)
                }

            })
            Thread.sleep(Core.delay)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


}
