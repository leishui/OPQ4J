import org.jetbrains.annotations.NotNull;

public class JMain {
    public static void main(String[] args) {
        Core core = new Core("http://118.178.178.211",2233,2056378490);
        core.start(new OPQListener(){
            @Override
            public void onPrivateTextMsg(long toQQ, @NotNull String textContent) {
                super.onPrivateTextMsg(toQQ, textContent);
                core.sendPrivateMsg(toQQ, textContent);
            }
        });
    }
}
