import io.socket.client.IO
import io.socket.client.Socket
import net.CoreRequest


class Core(private val url: String, private val port: Int,private val currentQQ:Long) {
    private var socket: Socket? = null
    private val options = IO.Options()

    companion object{
        var currentQQ:Long = 0
    }
    init {
        Core.currentQQ = currentQQ
        options.transports = arrayOf("websocket")
        options.reconnectionAttempts = 2
        //失败重连的时间间隔
        options.reconnectionDelay = 1000
        //连接超时时间(ms)
        options.timeout = 500
    }

    fun sendPrivateMsg(toQQ: Long, content: String) {
        CoreRequest.sendPrivateMsg(currentQQ,toQQ, content)
    }

    fun sendGroupMsg(toGroup: Long, content: String) {
        CoreRequest.sendGroupMsg(currentQQ,toGroup, content)
    }

    fun sendGroupMsgWithAt(atQQ: Array<Long>, toGroup: Long, content: String) {
        CoreRequest.sendGroupMsgWithAt(currentQQ,atQQ, toGroup, content)
    }

    fun start(listener: OPQListener) {
        socket = IO.socket("$url:$port", options)
        socket!!.on(Socket.EVENT_CONNECT) {
            println("[Client Connect!]")
        }.on("OnGroupMsgs") { s ->
            listener.onGroupMsg(s[0].toString())
        }.on("OnFriendMsgs") { s ->
            listener.onPrivateMsg(s[0].toString())
        }.on("OnEvents") { s ->
            println("[事件]${s?.get(0)}")
        }.on(Socket.EVENT_DISCONNECT) {
            println("[Client Disconnect!]")
        }.on(Socket.EVENT_CONNECT_ERROR) { args ->
            println("[Connect Error]:$args")
        }
        socket!!.connect()
    }

    fun stop() {
        socket?.disconnect()
        socket?.close()
    }
}