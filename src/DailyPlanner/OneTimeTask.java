package DailyPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends DailyPlanner {
    public OneTimeTask(Repeatability repeatability, Type type, String title, String description, LocalDateTime executionDateTime) {
        setRepetitionFrequency(repeatability);
        setType(type);
        setTitle(title);
        setDescription(description);
        setExecutionDateTime(executionDateTime);
        setId(idGenerator());
        setTaskRepeatPeriod(0);
        setTaskCreationDateTime(LocalDateTime.now().withNano(0));
    }
    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        LocalDate executionDate = localDateTime.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if(executionDate.isEqual(currentDate.plusDays(1))) {
            return true;
        }
        return false;
    }
}
