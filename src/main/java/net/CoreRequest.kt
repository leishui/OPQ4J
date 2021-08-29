package net

import constant.MsgType
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object CoreRequest {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://qq.entergx.cn/").addConverterFactory(GsonConverterFactory.create()).build()
    private val api: CoreApi = retrofit.create(CoreApi::class.java)
    fun sendPrivateMsg(currentQQ: Long, toQQ: Long, content: String) {
        sendMsg(currentQQ, null, toQQ, content, MsgType.MSG_PRIVATE)
    }

    fun sendGroupMsg(currentQQ: Long, toGroup: Long, content: String) {
        sendMsg(currentQQ, null, toGroup, content, MsgType.MSG_GROUP)
    }

    fun sendGroupMsgWithAt(currentQQ: Long, atQQ: Array<Long>, toGroup: Long, content: String) {
        sendMsg(currentQQ, atQQ, toGroup, content, MsgType.MSG_GROUP)
    }

    @Synchronized
    fun sendMsg(currentQQ: Long, atQQ: Array<Long>?, group: Long, content: String, type: Int) {
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
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), """{
                "toUser":$group,
                "sendToType":$type,
                "sendMsgType":"TextMsg",
                "content":"$nContent",
                "groupid":0,
                "atUser":0
            }""")
        try {
            api.sendMsg(currentQQ, body).execute()
            println("[$body]")
            Thread.sleep(1000)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}