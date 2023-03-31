import DailyPlanner.*;
import DailyPlanner.OneTimeTask;
import DailyPlanner.DailyTask;
import DailyPlanner.WeeklyTask;
import DailyPlanner.Type;
import DailyPlanner.Repeatability;
import DailyPlanner.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static DailyPlanner.DailyPlanner.idGenerator;
import static DailyPlanner.TaskService.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //simpleExecution(scanner);
        executionWithMenu(scanner);
    }
    public static void simpleExecution(Scanner scanner) {
        OneTimeTask task1 = new OneTimeTask(Repeatability.ONE_TIME_TASK, Type.PERSONAL, "test 1", "t1111", LocalDateTime.of(2023, 3, 29, 13, 45, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task1.appearsIn(task1.getExecutionDateTime()));
        OneTimeTask task2 = new OneTimeTask(Repeatability.ONE_TIME_TASK, Type.WORK, "test 2", "T >>>>>>", LocalDateTime.of(2023, 3, 29, 15, 4, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task2.appearsIn(task2.getExecutionDateTime()));
        OneTimeTask task3 = new OneTimeTask(Repeatability.ONE_TIME_TASK, Type.WORK, "test 3", "OneDay >>>>>>", LocalDateTime.of(2023, 3, 30, 12, 40, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task3.appearsIn(task3.getExecutionDateTime()));
        DailyTask task4 = new DailyTask(Repeatability.DAILY_TASK, Type.WORK, "test 4", "Daily >>>>>>", LocalDateTime.of(2023, 3, 30, 16, 45, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task4.appearsIn(task4.getExecutionDateTime()));
        WeeklyTask task5 = new WeeklyTask(Repeatability.WEEKLY_TASK, Type.PERSONAL, "test 5", "Weekly >>>>>", LocalDateTime.of(2023, 3, 23, 10, 45, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task5.appearsIn(task5.getExecutionDateTime()));
        MonthlyTask task6 = new MonthlyTask(Repeatability.MONTHLY_TASK, Type.PERSONAL, "test 6", "Monthly >>>>>", LocalDateTime.of(2023, 2, 16, 10, 45, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task6.appearsIn(task6.getExecutionDateTime()));
        YearlyTask task7 = new YearlyTask(Repeatability.YEARLY_TASK, Type.PERSONAL, "test 7", "Yearly >>>>>", LocalDateTime.of(2023, 3, 30, 10, 45, 0));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println("Таймер завис");
        }
        System.out.println(task7.appearsIn(task7.getExecutionDateTime()));
        addElementToTaskHashMap(getTaskHashMap(), task1);
        addElementToTaskHashMap(getTaskHashMap(), task2);
        addElementToTaskHashMap(getTaskHashMap(), task3);
        addElementToTaskHashMap(getTaskHashMap(), task4);
        addElementToTaskHashMap(getTaskHashMap(), task5);
        addElementToTaskHashMap(getTaskHashMap(), task6);
        addElementToTaskHashMap(getTaskHashMap(), task7);
        //printAllActiveTasks(getTaskHashMap());
        //getAllGroupedByDate(getTaskHashMap());
        //printGetAllGroupedByDate(getAllGroupedByDate(getTaskHashMap()));
        //printGetAllByDate(getAllByDate(getTaskHashMap()));

        getTasksBySpecificDate(scanner, getTaskHashMap());
        //updateTitle(scanner, getTaskHashMap());
        //updateDescription(scanner, getTaskHashMap());
        //printAllArchivedTasks(getArchivedTasks());
        //removeFromMapPlaceToSet(scanner, getTaskHashMap());
        //printAllActiveTasks(getTaskHashMap());
        //printAllArchivedTasks(getArchivedTasks());
        //addTask(scanner);
        //addTask(scanner);
        //addTask(scanner);
        //boolean test = printTaskSaveOrCancelMenu(scanner);
        //System.out.println(test);
    }
    public static void executionWithMenu(Scanner scanner) {
        printTaskMenu();
        while(true) {
            if(scanner.hasNextLine()) {
                String exitString = scanner.nextLine();
                switch (exitString) {
                    case "1":
                        System.out.println("Добавить задачу ");
                        addTask(scanner);
                        break;
                    case "2":
                        System.out.println("Удалить задачу по ID ");
                        removeFromMapPlaceToSet(scanner, getTaskHashMap());
                        break;
                    case "3":
                        System.out.println("Получить список задач на следующий день(Исп. appearsIn())");
                        printGetAllByDate(getAllByDate(getTaskHashMap()));
                        break;
                    case "4":
                        System.out.println("Получить задачи на день по указанной дате(С IncorrectArgumentException)");
                        getTasksBySpecificDate(scanner, getTaskHashMap());
                        break;
                    case "5":
                        System.out.println("Получить список всех удаленных задач");
                        printAllArchivedTasks(getArchivedTasks());
                        break;
                    case "6":
                        System.out.println("Редактировать название задачи");
                        updateTitle(scanner, getTaskHashMap());
                        break;
                    case "7":
                        System.out.println("Редактировать описание задачи");
                        updateDescription(scanner, getTaskHashMap());
                        break;
                    case "8":
                        System.out.println("Получить задачи, сгруппированные по датам выполнения");
                        printGetAllGroupedByDate(getAllGroupedByDate(getTaskHashMap()));
                        break;
                    case "9":
                        System.out.println("Напечатать все активные задачи");
                        printAllActiveTasks(getTaskHashMap());
                        break;
                    default:
                        System.out.println("Не выбрана ни одна из задач");
                }
                if(exitString.equalsIgnoreCase("exit")) {
                    System.out.println("Работа программы завершена");
                    break;
                }
            }
            printTaskMenu();
            //System.out.print("Введите номер задачи или exit - чтобы выйти: ");
        }
    }
    public static void printTaskMenu() {
        System.out.println("Выберите задачу из списка - ");
        System.out.print("" +
                "1 - Добавить задачу\n" +
                "2 - Удалить задачу по ID\n" +
                "3 - Получить список задач на следующий день(Исп. appearsIn())\n" +
                "4 - Получить задачи на день по указанной дате(Без appearsIn())\n" +
                "5 - Получить список всех удаленных задач\n" +
                "6 - Редактировать название задачи\n" +
                "7 - Редактировать описание задачи\n" +
                "8 - Получить задачи, сгруппированные по датам выполнения\n" +
                "9 - Напечатать все активные задачи\n" +
                "Для продолжения введите номер задачи, для выхода - exit: ");
    }


}