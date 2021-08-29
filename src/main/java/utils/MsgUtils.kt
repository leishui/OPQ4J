package utils

import com.google.gson.Gson
import msg.AtMsg
import msg.PicMsg

object MsgUtils {
    fun parseAtMsg(content: String?): AtMsg {
        return Gson().fromJson(content?.replace("\\", ""), AtMsg::class.java)
    }
    fun parsePicMsg(content: String?): PicMsg {
        return Gson().fromJson(content?.replace("\\", ""), PicMsg::class.java)
    }
}