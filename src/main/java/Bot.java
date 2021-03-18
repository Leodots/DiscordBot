import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Bot {

    private static String token = "ODIxODM0MjAxMDg2Njg5MzAx.YFJevg.c25qbj7K1T_o1uDfjak4fAiAoe0";

    private static DiscordApi api = new DiscordApiBuilder()
            .setToken(token)
            .login().join();

    public static void main(String[] args) {

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }

}