package msg

class GroupMsg {
    /**
     * CurrentPacket : {"WebConnId":"","Data":{"FromNickName":"xxx","MsgRandom":1807348683,"Content":"0","MsgSeq":2410,"FromGroupName":"Enter、1、此心也曾渴慕","FromUserId":1111,"constant.MsgType":"TextMsg","RedBaginfo":null,"MsgTime":1630026418,"FromGroupId":1111}}
     * CurrentQQ : 1111
     */
    var CurrentPacket: CurrentPacketEntity? = null
    var CurrentQQ: Long = 0

    inner class CurrentPacketEntity {
        /**
         * WebConnId :
         * Data : {"FromNickName":"xxx","MsgRandom":1807348683,"Content":"0","MsgSeq":2410,"FromGroupName":"Enter、1、此心也曾渴慕","FromUserId":1111,"constant.MsgType":"TextMsg","RedBaginfo":null,"MsgTime":1630026418,"FromGroupId":1111}
         */
        var WebConnId: String? = null
        var Data: DataEntity? = null

        inner class DataEntity {
            /**
             * FromNickName : xxx
             * MsgRandom : 1807348683
             * Content : 0
             * MsgSeq : 2410
             * FromGroupName : Enter、1、此心也曾渴慕
             * FromUserId : 1111
             * constant.MsgType : TextMsg
             * RedBaginfo : null
             * MsgTime : 1630026418
             * FromGroupId : 1111
             */
            var FromNickName: String? = null
            var MsgRandom: Long = 0
            var Content: String? = null
            var MsgSeq: Long = 0
            var FromGroupName: String? = null
            var FromUserId: Long = 0
            var MsgType: String? = null
            var RedBaginfo: String? = null
            var MsgTime: Long = 0
            var FromGroupId: Long = 0
        }
    }
}