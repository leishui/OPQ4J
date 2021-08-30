package msg

class PrivateMsg {
    /**
     * CurrentPacket : {"WebConnId":"","Data":{"Content":"1","MsgSeq":4355,"FromUin":1111,"constant.MsgType":"TextMsg","ToUin":1111,"RedBaginfo":null}}
     * CurrentQQ : 1111
     */
    var CurrentPacket: CurrentPacketEntity? = null
    var CurrentQQ:Long = 0

    inner class CurrentPacketEntity {
        /**
         * WebConnId :
         * Data : {"Content":"1","MsgSeq":4355,"FromUin":1111,"constant.MsgType":"TextMsg","ToUin":1111,"RedBaginfo":null}
         */
        var WebConnId: String? = null
        var Data: PrivateDataEntity? = null

        inner class PrivateDataEntity {
            /**
             * Content : 1
             * MsgSeq : 4355
             * FromUin : 1111
             * constant.MsgType : TextMsg
             * ToUin : 1111
             * RedBaginfo : null
             */
            var Content: String? = null
            var MsgSeq:Long = 0
            var FromUin:Long = 0
            var MsgType: String? = null
            var ToUin:Long = 0
            var DedBaginfo: String? = null
        }
    }
}