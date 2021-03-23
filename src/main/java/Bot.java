import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class Bot {

    private static final String idChannel = "790661190174507018"; //Channel Text - Produtores
    private static final String ruleProdutores = "<@&790656053792079903>"; //Rule - Produtores

    public static void main(String[] args) {

        String token = new PropertyFileReader().getPropValues();
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        Optional<TextChannel> channel = api.getTextChannelById(idChannel);

        List<String> sentences = new ArrayList<>();

        sentences.add(ruleProdutores + " salve galera! Hoje é dia de mandar sugestão de tema pro próximo vídeo de lista! Vocês tem até sexta-feira pra mandar, valeu!");
        sentences.add("Passando só pra lembrar que ainda dá tempo de mandar a sugestão de tema!");
        sentences.add("Último dia pra sugerir o tema pro próximo vídeo de lista rapaziada, amanhã já começa a votação!");

        Thread thread = new Thread(() -> {
            while (true) {
                Calendar calendar = Calendar.getInstance();

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int hourSystem = calendar.get(Calendar.HOUR_OF_DAY);
                int horaMensagem = -1;
                int diaMensagem = -1;
                String message = "";

                MessageBuilder messages = new MessageBuilder();

                messages.createMessage(MessageBuilder.QUARTA_FEIRA, 9, sentences.get(0));
                messages.createMessage(MessageBuilder.QUINTA_FEIRA, 9, sentences.get(1));
                messages.createMessage(MessageBuilder.SEXTA_FEIRA, 9, sentences.get(2));

                for (int i = 0; i < messages.getList().size(); i++) {
                    diaMensagem = messages.getList().get(i).getDay();
                    horaMensagem = messages.getList().get(i).getHour();
                    message = messages.getList().get(i).getMessage();

                    switch (diaMensagem) {
                        case MessageBuilder.DOMINGO:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                        case MessageBuilder.SEGUNDA_FEIRA:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                        case MessageBuilder.TERCA_FEIRA:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                        case MessageBuilder.QUARTA_FEIRA:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                        case MessageBuilder.QUINTA_FEIRA:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                        case MessageBuilder.SEXTA_FEIRA:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                        case MessageBuilder.SABADO:
                            if (hourSystem == horaMensagem) {
                                String finalMessage = message;
                                channel.ifPresent(textChannel -> textChannel.sendMessage(finalMessage));
                            }
                            break;
                    }
                }
                try {
                    Thread.sleep(3600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}

