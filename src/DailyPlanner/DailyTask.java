package DailyPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends DailyPlanner {
    public DailyTask(Repeatability repeatability, Type type, String title, String description, LocalDateTime executionDateTime) {
        setRepetitionFrequency(repeatability);
        setType(type);
        setTitle(title);
        setDescription(description);
        setExecutionDateTime(executionDateTime);
        setId(idGenerator());
        setTaskRepeatPeriod(1);
        setTaskCreationDateTime(LocalDateTime.now().withNano(0));
    }
    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        return true;
    }
}
