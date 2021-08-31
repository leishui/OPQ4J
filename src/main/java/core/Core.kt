package core

import io.socket.client.IO
import io.socket.client.Socket
import listener.OPQBaseListener
import net.CoreRequest


class Core(private val url: String, private val port: Int, currentQQ: Long) {
    private var socket: Socket? = null
    private val options = IO.Options()

    companion object {
        //当前QQ号
        var currentQQ: Long = 0
        //发送请求的间隔时间
        var delay: Long = 1000
    }

    /**
     * 设置发送请求的间隔时间
     * @param delay Long 间隔时间ms
     */
    fun setDelay(delay: Long) {
        Companion.delay = delay
    }

    /**
     * 初始化
     */
    init {
        Companion.currentQQ = currentQQ
        options.transports = arrayOf("websocket")
        options.reconnectionAttempts = 2
        //失败重连的时间间隔
        options.reconnectionDelay = 1000
        //连接超时时间(ms)
        options.timeout = 500
    }

    /**
     * 发送私聊消息
     * @param toQQ Long 对方QQ号
     * @param content String 消息内容
     */
    fun sendPrivateMsg(toQQ: Long, content: String) {
        CoreRequest.sendPrivateMsg(toQQ, content)
    }
    /**
     * 发送群聊消息
     * @param toGroup Long 群号
     * @param content String 消息内容
     */
    fun sendGroupMsg(toGroup: Long, content: String) {
        CoreRequest.sendGroupMsg(toGroup, content)
    }

    /**
     * 发送带@的群聊消息
     * @param atQQ Array<Long> 要@的QQ号集合
     * @param toGroup Long 群号
     * @param content String 消息内容
     */
    fun sendGroupMsgWithAt(atQQ: Array<Long>, toGroup: Long, content: String) {
        CoreRequest.sendGroupMsgWithAt(atQQ, toGroup, content)
    }

    /**
     * 发送json格式信息到服务器
     * @param url String api路径，参考文档，例如：/v1/LuaApiCaller?qq=111&funcname=SendMsg&timeout=10
     * @param jsonBody String json消息体
     */
    fun sendMsg(url:String, jsonBody:String){
        CoreRequest.sendMsg(url,jsonBody)
    }
    fun start(listener: OPQBaseListener) {
        CoreRequest.init("$url:$port", listener)
        socket = IO.socket("$url:$port", options)
        socket!!.on(Socket.EVENT_CONNECT) {
            listener.onConnected()
        }.on("OnGroupMsgs") { s ->
            listener.onGroupMsgRaw(s[0].toString())
        }.on("OnFriendMsgs") { s ->
            listener.onPrivateMsgRaw(s[0].toString())
        }.on("OnEvents") { s ->
            listener.onEvent(s[0].toString())
        }.on(Socket.EVENT_DISCONNECT) {
            listener.onDisconnected()
        }.on(Socket.EVENT_CONNECT_ERROR) { args ->
            listener.onConnectionError(args[0].toString())
        }
        socket!!.connect()
    }

    fun stop() {
        socket?.disconnect()
        socket?.close()
    }

}