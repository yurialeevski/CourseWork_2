package DailyPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends DailyPlanner{
    public YearlyTask(Repeatability repeatability, Type type, String title, String description, LocalDateTime executionDateTime) {
        setRepetitionFrequency(repeatability);
        setType(type);
        setTitle(title);
        setDescription(description);
        setExecutionDateTime(executionDateTime);
        setId(idGenerator());
        setTaskRepeatPeriod(365);
        setTaskCreationDateTime(LocalDateTime.now().withNano(0));
    }
    @Override
    public boolean appearsIn(LocalDateTime localDateTime) {
        LocalDate executionDate = localDateTime.toLocalDate();
        if(executionDate.isEqual(LocalDate.now())) {
            return false;
        }
        LocalDate newExecutionDate = executionDate;
        LocalDate currentDate = LocalDate.now();
        int i = 1;
        while(newExecutionDate.isBefore(currentDate) || newExecutionDate.isEqual(currentDate)) {
            newExecutionDate = executionDate.plusYears(i);
            System.out.println(newExecutionDate + " i " + i);
            i++;
        }
        if(newExecutionDate.isEqual(currentDate.plusDays(1))) {
            return true;
        }
        return false;
    }
}
