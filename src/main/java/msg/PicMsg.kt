package msg

class PicMsg {
    /**
     * FriendPic : [{"FileMd5":"vgTxlmELhpP0hh+/1/X+BA==","Path":"/1223812424-2641369294-BE04F196610B8693F4861FBFD7F5FE04","Url":"http://c2cpicdw.qpic.cn/offpic_new/2056378490/1223812424-2641369294-BE04F196610B8693F4861FBFD7F5FE04/0","FileSize":88070}]
     * Tips : [好友图片]
     */
    var FriendPic: List<FriendPicEntity>? = null
    var Tips: String? = null

    inner class FriendPicEntity {
        /**
         * FileMd5 : vgTxlmELhpP0hh+/1/X+BA==
         * Path : /1223812424-2641369294-BE04F196610B8693F4861FBFD7F5FE04
         * Url : http://c2cpicdw.qpic.cn/offpic_new/2056378490/1223812424-2641369294-BE04F196610B8693F4861FBFD7F5FE04/0
         * FileSize : 88070
         */
        var FileMd5: String? = null
        var Path: String? = null
        var Url: String? = null
        var FileSize:Long = 0
    }
}