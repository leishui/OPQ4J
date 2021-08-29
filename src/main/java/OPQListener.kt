import Core.Companion.currentQQ
import com.google.gson.Gson
import constant.MsgType
import msg.GroupMsg
import msg.PicMsg
import msg.PrivateMsg
import utils.*

open class OPQListener {

    open fun onPrivateMsg(raw: String) {
        val msg = Gson().fromJson(raw, PrivateMsg::class.java).CurrentPacket?.Data!!
        val toQQ = msg.FromUin
        if (toQQ == currentQQ)
            return
        val content = msg.Content!!
        when (val msgType = msg.MsgType!!) {
            MsgType.TEXT -> onPrivateTextMsg(toQQ, content)
            MsgType.PIC -> onPrivatePicMsg(toQQ, MsgUtils.parsePicMsg(content))
            else -> onPrivateOtherMsg(toQQ, content, msgType)
        }
    }

    open fun onGroupMsg(raw: String) {
        val msg = Gson().fromJson(raw, GroupMsg::class.java).CurrentPacket?.Data!!
        val toQQ = msg.FromUserId
        if (toQQ == currentQQ)
            return
        val toGroup = msg.FromGroupId
        val content = msg.Content!!
        when (val msgType = msg.MsgType!!) {
            MsgType.TEXT -> onGroupTextMsg(toQQ, toGroup, content)
            else -> onGroupOtherMsg(toQQ, toGroup, content, msgType)
        }
    }

    open fun onPrivateTextMsg(toQQ: Long, textContent: String) {
        println("[PrivateMsg<${MsgType.TEXT}>:$toQQ]"+ textContent)
    }

    open fun onPrivatePicMsg(toQQ: Long, picContent: PicMsg) {
        println("[私聊消息<${MsgType.PIC}>:$toQQ]${picContent.FriendPic?.get(0)?.Url}")
    }

    open fun onPrivateOtherMsg(toQQ: Long, content: String, msgType: String) {
        println("[私聊消息<$msgType>:$toQQ]$content")
    }

    open fun onGroupTextMsg(toQQ: Long, toGroup: Long, content: String) {
        println("[群消息<${MsgType.TEXT}>:$toQQ:$toGroup]$content")
    }

    open fun onGroupOtherMsg(toQQ: Long, toGroup: Long, content: String, msgType: String) {
        println("[群消息<$msgType>:$toQQ:$toGroup]$content")
    }
}