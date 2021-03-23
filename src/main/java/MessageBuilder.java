import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageBuilder {

    private int day;
    private int hour;
    private String message;
    private List<MessageBuilder> list = new ArrayList<>();

    protected static final int DOMINGO = Calendar.SUNDAY;
    protected static final int SEGUNDA_FEIRA = Calendar.MONDAY;
    protected static final int TERCA_FEIRA = Calendar.TUESDAY;
    protected static final int QUARTA_FEIRA = Calendar.WEDNESDAY;
    protected static final int QUINTA_FEIRA = Calendar.THURSDAY;
    protected static final int SEXTA_FEIRA = Calendar.FRIDAY;
    protected static final int SABADO = Calendar.SATURDAY;

    public MessageBuilder() {
        this.day = -1;
        this.hour = -1;
        this.message = "No message created";
    }

    public MessageBuilder(int day, int hour, String message) {
        this.day = day;
        this.hour = hour;
        this.message = message;
    }

    public void createMessage(int day, int hour, String message) {
        list.add(new MessageBuilder(day, hour, message));
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getMessage() {
        return message;
    }

    public List<MessageBuilder> getList() {
        return list;
    }
}