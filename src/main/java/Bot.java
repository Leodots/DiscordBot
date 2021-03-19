import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class Bot {

    private static final String idChannel = "790661190174507018";
    private static final String ruleProdutores = "<@&790656053792079903>";

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
                int hour = calendar.get(Calendar.HOUR_OF_DAY);

                //Quarta, primeiro dia de sugestão de lista
                if(dayOfWeek == Calendar.WEDNESDAY && hour == 8) {
                    channel.ifPresent(textChannel -> textChannel.sendMessage(sentences.get(0)));
                } else if (dayOfWeek == Calendar.THURSDAY && hour == 8) { //Quinta, segundo dia
                    channel.ifPresent(textChannel -> textChannel.sendMessage(sentences.get(1)));
                } else if(dayOfWeek == Calendar.FRIDAY && hour == 8) { //Sexta, último dia
                    channel.ifPresent(textChannel -> textChannel.sendMessage(sentences.get(2)));
                }

                try {
                    Thread.sleep(3600000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        }
    }

