package listener

import constant.MsgType
import msg.GroupMsg
import msg.PicMsg
import msg.PrivateMsg
import utils.*

open class OPQListener : OPQBaseListener(){

    override fun onPrivateMsg(msg: PrivateMsg.CurrentPacketEntity.PrivateDataEntity) {
        val toQQ = msg.FromUin
        val content = msg.Content!!
        when (val msgType = msg.MsgType!!) {
            MsgType.TEXT -> onPrivateTextMsg(toQQ, content)
            MsgType.PIC -> onPrivatePicMsg(toQQ, MsgUtils.parsePicMsg(content))
            else -> onPrivateOtherMsg(toQQ, content, msgType)
        }
    }

    override fun onGroupMsg(msg: GroupMsg.CurrentPacketEntity.GroupDataEntity) {
        val toQQ = msg.FromUserId
        val toGroup = msg.FromGroupId
        val content = msg.Content!!
        when (val msgType = msg.MsgType!!) {
            MsgType.TEXT -> onGroupTextMsg(toQQ, toGroup, content)
            else -> onGroupOtherMsg(toQQ, toGroup, content, msgType)
        }
    }
    override fun onConnected(){
        println("--[Client Connect!]")
    }
    override fun onDisconnected(){
        println("--[Client Disconnect!]")
    }

    override fun onConnectionError(e:String) {
        println("XX[Connection Error]:$e")
    }
    override fun onMsgSent(jsonBody: String, ret: String) {
        print("↑↑[SendMsg]$jsonBody\n↓↓[ret]$ret")
    }
    override fun onSendMsgError(jsonBody: String,throwable: Throwable?){
        println("XX[ERROR][Msg]$jsonBody")
        println("XX[ERROR]$throwable")
    }

    override fun onEvent(raw: String) {
        println("↓↓[Event]$raw")
    }

    open fun onPrivateTextMsg(toQQ: Long, textContent: String) {
        println("↓↓[PrivateMsg<${MsgType.TEXT}>:$toQQ]"+ textContent)
    }

    open fun onPrivatePicMsg(toQQ: Long, picContent: PicMsg) {
        println("↓↓[PrivateMsg<${MsgType.PIC}>:$toQQ]${picContent.FriendPic?.get(0)?.Url}")
    }

    open fun onPrivateOtherMsg(toQQ: Long, content: String, msgType: String) {
        println("↓↓[PrivateMsg<$msgType>:$toQQ]$content")
    }

    open fun onGroupTextMsg(toQQ: Long, toGroup: Long, content: String) {
        println("↓↓[GroupMsg<${MsgType.TEXT}>:$toQQ:$toGroup]$content")
    }

    open fun onGroupOtherMsg(toQQ: Long, toGroup: Long, content: String, msgType: String) {
        println("↓↓[GroupMsg<$msgType>:$toQQ:$toGroup]$content")
    }
}