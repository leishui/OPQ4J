package listener

import core.Core.Companion.currentQQ
import com.google.gson.Gson
import msg.GroupMsg
import msg.PrivateMsg

open class OPQBaseListener {
    /**
     * 原始消息的处理，忽略机器人自身发送消息的监听，避免死锁
     * @param raw String json格式原始私聊信息
     */
    fun onPrivateMsgRaw(raw: String) {
        val msg = Gson().fromJson(raw, PrivateMsg::class.java).CurrentPacket?.Data!!
        val toQQ = msg.FromUin
        if (toQQ == currentQQ)
            return
        onPrivateMsg(raw)
        onPrivateMsg(msg)
    }

    /**
     * 原始消息的处理，忽略机器人自身发送消息的监听，避免死锁
     * @param raw String json 格式原始群聊信息
     */
    fun onGroupMsgRaw(raw: String) {
        val msg = Gson().fromJson(raw, GroupMsg::class.java).CurrentPacket?.Data!!
        val toQQ = msg.FromUserId
        if (toQQ == currentQQ)
            return
        onGroupMsg(raw)
        onGroupMsg(msg)
    }

    /**
     * 监听事件信息
     * @param raw String json事件信息，需要自己进行解析
     */
    open fun onEvent(raw: String) {}

    /**
     * 监听私聊消息（Json）
     * @param rawMsg String json格式原始私聊信息，需要自己进行解析
     */
    open fun onPrivateMsg(rawMsg: String) {}

    /**
     * 监听私聊消息（PrivateDataEntity）
     * @param msg PrivateDataEntity 已经解析好的私聊消息对象
     */
    open fun onPrivateMsg(msg: PrivateMsg.CurrentPacketEntity.PrivateDataEntity) {}

    /**
     * 监听群聊消息（Json）
     * @param rawMsg String json格式原始群聊信息，需要自己进行解析
     */
    open fun onGroupMsg(rawMsg: String) {}

    /**
     * 监听群聊消息（GroupDataEntity）
     * @param msg GroupDataEntity 已经解析好的私聊消息对象
     */
    open fun onGroupMsg(msg: GroupMsg.CurrentPacketEntity.GroupDataEntity) {}

    /**
     * 监听IOSocket连接成功
     */
    open fun onConnected() {}

    /**
     * 监听IOSocket连接断开
     */
    open fun onDisconnected() {}

    /**
     * 监听IOSocket连接异常
     */
    open fun onConnectionError(e: String) {}

    /**
     * 监听消息发送成功
     * @param jsonBody String 所发送的消息体
     * @param ret String 接口所返回的信息
     */
    open fun onMsgSent(jsonBody: String, ret: String) {}

    /**
     * 监听消息发送失败
     * @param jsonBody String 所发送的消息体
     * @param throwable Throwable? 异常
     */
    open fun onSendMsgError(jsonBody: String, throwable: Throwable?) {}

}