package constant

object MsgType {
    //接受的消息类型 图片/Json/文本
    const val PIC = "PicMsg"
    const val JSON = "JsonMsg"
    const val TEXT = "TextMsg"
    const val FRIEND_FILE = "FriendFileMsg"

    //发送消息用 私聊/群聊
    /*为1时为好友会话,这时的toUser为对方的QQ号,groupid保持0就行
    为2时是群聊,这时的toUser为群号,groupid填不填貌似都可以
    为3时是临时会话,这时的toUser为对方的QQ号,groupid为发起临时会话的群号*/
    const val MSG_PRIVATE = 1
    const val MSG_GROUP = 2
    const val MSG_TEMP = 3
}