import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class Bot {

    private static final String idChannel = "790661190174507018"; //Channel Text - Produtores
    private static final String ruleProdutores = "<@&790656053792079903>"; //Rule - Produtores
    private static AtomicBoolean botState = new AtomicBoolean(true);

    public static void main(String[] args) {

        String token = new PropertyFileReader().getPropValues();
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        Optional<TextChannel> channel = api.getTextChannelById(idChannel);

        long leodotsId = 247165500986097664L;
        long botId = 821834201086689301L;

        List<String> sentences = new ArrayList<>();

        sentences.add(ruleProdutores + " salve galera! Hoje é dia de mandar sugestão de tema pro próximo vídeo de lista! Vocês tem até sexta-feira pra mandar, valeu!");
        sentences.add("Passando só pra lembrar que ainda dá tempo de mandar a sugestão de tema!");
        sentences.add("Último dia pra sugerir o tema pro próximo vídeo de lista rapaziada, amanhã já começa a votação!");

        MessageBuilder messages = new MessageBuilder();

        messages.createMessage(MessageBuilder.QUARTA_FEIRA, 10, sentences.get(0));
        messages.createMessage(MessageBuilder.QUINTA_FEIRA, 10, sentences.get(1));
        messages.createMessage(MessageBuilder.SEXTA_FEIRA, 10, sentences.get(2));

        api.addMessageCreateListener(event -> {
            if(event.getMessageAuthor().getId() == leodotsId || event.getMessageAuthor().getId() == botId) {

                if(event.getMessageContent().equalsIgnoreCase("#shutdown")) {
                    if(botState.get() == true) {
                        botState.set(false);
                        event.getChannel().sendMessage("Bot is now dead.");
                    } else {
                        event.getChannel().sendMessage("It is already dead.");
                    }
                }

                if(event.getMessageContent().equalsIgnoreCase("#turnOn")) {
                    if(botState.get() == false) {
                        botState.set(true);
                        event.getChannel().sendMessage("Bot came back to life.");
                    } else {
                        event.getChannel().sendMessage("Bot is already working.");
                    }
                }

                if (event.getMessageContent().equalsIgnoreCase("#commands")) {
                    event.getChannel().sendMessage("Commands: \n1. (#add) Add new message. " +
                            "\n2. (#remove) Remove message." +
                            "\n3. (#show) Show all messages." +
                            "\n4. (#shutDown) Stop bot execution." +
                            "\n5. (#turnOn) Get bot to work.");
                }

                if(event.getMessageContent().startsWith("#add")) {
                    String messageReceived = event.getMessageContent().replaceFirst("#add", "");
                    try {
                        String[] messageParameters = messageReceived.split("/");
                        messages.createMessage(Integer.parseInt(messageParameters[0].trim()), Integer.parseInt(messageParameters[1].trim()), messageParameters[2].trim());
                        event.getChannel().sendMessage("Message created.");
                    } catch (Exception e) {
                        event.getChannel().sendMessage("Notei que você é meio lezado, vou desenhar pra você entender como que cria uma mensagem" +
                                " Ex: (Dia) 1-7 / (Hora) 1-23 / Mensagem que você quer criar");
                    }
                }

                if(event.getMessageContent().equalsIgnoreCase("#show")) {
                    for (int i = 0; i < messages.getList().size(); i++) {
                        int day = messages.getList().get(i).getDay();
                        int hour = messages.getList().get(i).getHour() - 3;
                        String message = messages.getList().get(i).getMessage();

                        event.getChannel().sendMessage("ID: " + (i+1) + ". Dia: " + day + "\t Hora: " + hour + "\t Mensagem: " + message);

                        System.out.println("autor: " + event.getMessageAuthor().getId());
                    }
                }

                if(event.getMessageContent().startsWith("#remove")) {
                    try {
                        String index = event.getMessageContent().replaceFirst("#remove", "").trim();
                        int id = Integer.parseInt(index);
                        messages.getList().remove(id - 1);
                        event.getChannel().sendMessage("Message removed.");
                    } catch (Exception e) {
                        event.getChannel().sendMessage("Você precisa mandar um ID de mensagem válido, vacilão!");
                    }
                }
            } else {
                event.getChannel().sendMessage("QUEM É VOCÊ? Cai fora que você não tem permissão de me usar, só o Leodots tem!");
            }

        });

        Thread thread = new Thread(() -> {
            while (true) {
                Calendar calendar = Calendar.getInstance();

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                int hourSystem = calendar.get(Calendar.HOUR_OF_DAY);
                int horaMensagem = -1;
                int diaMensagem = -1;
                String message = "";

                for (int i = 0; i < messages.getList().size(); i++) {
                    diaMensagem = messages.getList().get(i).getDay();
                    horaMensagem = messages.getList().get(i).getHour();
                    message = messages.getList().get(i).getMessage();

                    sendMessage(channel, dayOfWeek, hourSystem, horaMensagem, diaMensagem, message);

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

    private static void sendMessage(Optional<TextChannel> channel, int dayOfWeek, int hourSystem, int horaMensagem, int diaMensagem, String message) {
        if (dayOfWeek == diaMensagem && hourSystem == horaMensagem && botState.get() == true) {
            channel.ifPresent(textChannel -> textChannel.sendMessage(message));
        }
    }
}

