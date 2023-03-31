package DailyPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public abstract class DailyPlanner {
    private Repeatability repetitionFrequency;
    private Type type;
    private String title;
    private String description;
    private LocalDateTime executionDateTime;
    private int id;
    private int taskRepeatPeriod;
    private LocalDateTime taskCreationDateTime;

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    public int getTaskRepeatPeriodDays() {
        return taskRepeatPeriod;
    }

    public void setTaskRepeatPeriod(int taskRepeatPeriod) {
        this.taskRepeatPeriod = taskRepeatPeriod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTaskCreationDateTime() {
        return taskCreationDateTime;
    }

    public void setTaskCreationDateTime(LocalDateTime dateTime) {
        this.taskCreationDateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Repeatability getRepetitionFrequency() {
        return repetitionFrequency;
    }

    public void setRepetitionFrequency(Repeatability repetitionFrequency) {
        this.repetitionFrequency = repetitionFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyPlanner that = (DailyPlanner) o;
        return id == that.id && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }

    @Override
    public String toString() {
        return "DailyPlanner{" +
                "repetitionFrequency=" + repetitionFrequency +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", executionDateTime=" + executionDateTime +
                ", id=" + id +
                ", taskRepeatPeriodDays=" + taskRepeatPeriod +
                ", taskCreationDateTime=" + taskCreationDateTime +
                '}';
    }

    public abstract boolean appearsIn(LocalDateTime localDateTime);
    public static Integer idGenerator() {
        LocalDate localDate = LocalDate.now();
        String localDateToString = "" + localDate.getDayOfMonth() + localDate.getMonthValue();
        LocalTime localTime = LocalTime.now();
        String localTimeToString = "" + localTime.getHour() + localTime.getMinute() + localTime.getSecond();
        String idStringFormat = localDateToString + localTimeToString;
        Integer idIntegerFormat = Integer.parseInt(idStringFormat);
        return idIntegerFormat;
    }
}
