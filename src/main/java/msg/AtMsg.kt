package msg

class AtMsg {
    /**
     * UserExt : [{"QQNick":"Enter","QQUid":1111}]
     * Content : @Enter
     * UserID : [1111]
     */
    var UserExt: List<UserExtEntity>? = null
    var Content: String? = null
    var UserID: List<Long>? = null
    val RealContent: String? = null

    inner class UserExtEntity {
        /**
         * QQNick : Enter
         * QQUid : 1111
         */
        var QQNick: String? = null
        var QQUid:Long = 0
    }
}