import core.Core;
import listener.OPQListener;
import org.jetbrains.annotations.NotNull;

public class JMain {
    public static void main(String[] args) {
        //服务器地址、端口、机器人QQ
        Core core = new Core("http://xxx.xxx.xxx.xxx", 2233, 1000000000);
        /*设置发送消息的最小间隔时间，默认为1000ms
        由于服务器响应速度等问题，间隔时间过小可能导致消息丢失*/
        //core.setDelay(500);
        //1.通过重写OPQListener中的方法进行监听
        //我只实现了部分消息监听
        core.start(new OPQListener() {
            @Override
            public void onPrivateTextMsg(long toQQ, @NotNull String textContent) {
                super.onPrivateTextMsg(toQQ, textContent);
                /*
                Core提供了4个发送消息的方法,详见源码中的注释
                1.sendPrivateMsg
                2.sendGroupMsg
                3.sendGroupMsgWithAt
                4.sendMsg 通过路径和参数调用所有服务端的接口
                 */
                core.sendPrivateMsg(toQQ, textContent);
                //使用sendMsg方法时需要注意带'\'的需要将'\'转义，例如:
                //'\n'->'\\n'         '\"'->'\\\"'
                String nContent = "\\\"a\\nb\\n";
                core.sendMsg("/v1/LuaApiCaller?qq=" + Core.Companion.getCurrentQQ() + "&funcname=SendMsg&timeout=10",
                        "{\"toUser\":" + toQQ + ",\"sendToType\":1,\"sendMsgType\":\"TextMsg\",\"content\":\"" + nContent + "\",\"groupid\":0,\"atUser\":0}");
            }
        });
        //2.可参考OPQListener通过继承OPQBaseListener实现自己的Listener进行监听
//        core.start(new OPQBaseListener(){
//            @Override
//            public void onPrivateMsg(@NotNull String rawMsg) {
//                System.out.println(rawMsg);
//            }
//        });
    }
}
