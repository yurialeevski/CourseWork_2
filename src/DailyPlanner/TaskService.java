package DailyPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private static HashMap<Integer, DailyPlanner> taskHashMap = new HashMap<>();
    private static Set<DailyPlanner> archivedTasks = new HashSet<>();

    public static HashMap<Integer, DailyPlanner> getTaskHashMap() {
        return taskHashMap;
    }

    public static Set<DailyPlanner> getArchivedTasks() {
        return archivedTasks;
    }
    public static void addElementToTaskHashMap(HashMap<Integer, DailyPlanner> hashMap, DailyPlanner dailyPlanner) {
        hashMap.put(dailyPlanner.getId(), dailyPlanner);
    }

    public static Repeatability setInputRepetitionFrequency(Scanner scanner) {
        System.out.print("Создание новой задачи - \n" +
                "1 - однократная задача\n" +
                "2 - ежедневная задача\n" +
                "3 - еженедельная задача\n" +
                "4 - ежемесячная задача\n" +
                "5 - ежегодная задача\n" +
                "Введите цифру для выбора задачи или exit для выхода: ");
        Repeatability inputRepetitionFrequency;
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("Вид задачи по умолчанию - ежегодная задача");
            return Repeatability.YEARLY_TASK;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return null;
        } else {
            switch (inputString) {
                case "1": inputRepetitionFrequency = Repeatability.ONE_TIME_TASK;
                    break;
                case "2": inputRepetitionFrequency = Repeatability.DAILY_TASK;
                    break;
                case "3": inputRepetitionFrequency = Repeatability.WEEKLY_TASK;
                    break;
                case "4": inputRepetitionFrequency = Repeatability.MONTHLY_TASK;
                    break;
                case "5": inputRepetitionFrequency = Repeatability.YEARLY_TASK;
                    break;
                default: System.out.println("Вид задачи по умолчанию - ежегодная задача");
                    inputRepetitionFrequency = Repeatability.YEARLY_TASK;
            }
        }
        return inputRepetitionFrequency;
    }

    /*public static Type getInputType() {
        return inputType;
    }*/

    public static Type setInputType(Scanner scanner) {
        System.out.print("1 - рабочая задача\n" +
                        "2 - личная задача\n" +
                        "Введите цифру для выбора типа задачи или exit для выхода: ");
        Type inputType;
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("Тип задачи: по умолчанию - рабочая задача");
            return Type.WORK;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return null;
        } else {
            switch (inputString) {
                case "1": inputType = Type.WORK;
                    break;
                case "2": inputType = Type.PERSONAL;
                    break;
                default: System.out.println("Тип задачи: по умолчанию - рабочая задача");
                    inputType = Type.WORK;
            }
        }
        return inputType;
    }

    /*public static String getInputTitle() {
        return inputTitle;
    }*/

    public static String setInputTitle(Scanner scanner) {
        System.out.print("Введите название задачи или exit для выхода: ");
        String inputTitle = scanner.nextLine();
        if(inputTitle.isEmpty()) {
            System.out.println("Задача без названия");
            inputTitle = "Задача без названия";
        } else if (inputTitle.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return null;
        }
        return inputTitle;
    }

    /*public static String getInputDescription() {
        return inputDescription;
    }*/

    public static String setInputDescription(Scanner scanner) {
        System.out.print("Введите описание задачи или exit для выхода: ");
        String inputDescription = scanner.nextLine();
        if(inputDescription.isEmpty()) {
            System.out.println("Задача без описания");
            return  "Задача без описания";
        } else if (inputDescription.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return null;
        }
        return inputDescription;
    }

    /*public static int getYear() {
        return year;
    }*/

    public static int setYear(Scanner scanner) {
        System.out.print("Введите год в формате yyyy или exit для выхода: ");
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("Год по умолчанию: " + year);
            return year;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return  -1;
        } else if(inputString.matches("[0-9]{4}")) {
            year = Integer.parseInt(inputString);
            if(year >= today.getYear() && year <= 2030) {
                return year;
            }
        }
        year = today.getYear();
        System.out.println("Год по умолчанию: " + year);
        return year;
    }

    /*public static int getMonth() {
        return month;
    }*/

    public static int setMonth(Scanner scanner) {
        int month = 12;
        System.out.print("Введите номер месяца в формате ММ или exit для выхода: ");
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("Месяц по умолчанию: " + month);
            return month;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return -1;
        } else if(inputString.matches("[0-9]{1,2}")) {
            month = Integer.parseInt(inputString);
            if(month >=1 && month <= 12) {
                return month;
            }
        }
        month = 12;
        System.out.println("Месяц по умолчанию: " + month);
        return month;
    }

    /*public static int getDayOfMonth() {
        return dayOfMonth;
    }*/

    public static int setDayOfMonth(Scanner scanner) {
        int dayOfMonth = 31;
        System.out.print("Введите день месяца в формате dd или exit для выхода: ");
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("День месяца по умолчанию: " + dayOfMonth);
            return dayOfMonth;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return -1;
        } else if(inputString.matches("[0-9]{1,2}")) {
            dayOfMonth = Integer.parseInt(inputString);
            if(dayOfMonth >=1 && dayOfMonth <= 31) {
                return dayOfMonth;
            }
        }
        dayOfMonth = 31;
        System.out.println("День месяца по умолчанию: " + dayOfMonth);
        return dayOfMonth;
    }

    /*public static int getHour() {
        return hour;
    }*/

    public static int setHour(Scanner scanner) {
        int hour = 21;
        System.out.print("Введите значение часа в формате hh или exit для выхода: ");
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("Значение часа по умолчанию: " + hour);
            return hour;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return -1;
        } else if(inputString.matches("[0-9]{1,2}")) {
            hour = Integer.parseInt(inputString);
            if(hour >=0 && hour <= 23) {
                return hour;
            }
        }
        hour = 21;
        System.out.println("Значение часа по умолчанию: " + hour);
        return hour;
    }

    /*public static int getMinute() {
        return minute;
    }*/

    public static int setMinute(Scanner scanner) {
        int minute = 0;
        System.out.print("Введите значение минут в формате mm или exit для выхода: ");
        String inputString = scanner.nextLine();
        if(inputString.isEmpty()) {
            System.out.println("Значение минут по умолчанию: " + minute);
            return minute;
        } else if (inputString.equalsIgnoreCase("exit")) {
            System.out.println("Отмена создания задачи");
            return -1;
        } else if(inputString.matches("[0-9]{1,2}")) {
            minute = Integer.parseInt(inputString);
            if(minute >=0 && minute <= 59) {
                return minute;
            }
        }
        minute = 0;
        System.out.println("Значение минут по умолчанию: " + minute);
        return minute;
    }

    /*public static int getSecond() {
        return second;
    }*/

    public static int setSecond() {
        return 0;
    }

    /*public static LocalDateTime getInputExecutionDateTime() {
        return inputExecutionDateTime;
    }*/

    public static LocalDateTime setInputExecutionDateTime(Scanner scanner) {
        System.out.println("Установите дату и время выполнения задачи - ");
        String exitString;
        int year = setYear(scanner);
        if(year < 0) {
            return null;
        }
        int month = setMonth(scanner);
        if(month < 0) {
            return null;
        }
        int dayOfMonth = setDayOfMonth(scanner);
        if(dayOfMonth < 0) {
            return null;
        }
        int hour = setHour(scanner);
        if(hour < 0) {
            return null;
        }
        int minute = setMinute(scanner);
        if(minute < 0) {
            return null;
        }
        int second = setSecond();
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }

    public static void addTask(Scanner scanner) {
        Repeatability inputRepetitionFrequency = setInputRepetitionFrequency(scanner);
        if(inputRepetitionFrequency == null) {
            return;
        }
        Type inputType = setInputType(scanner);
        if(inputType == null) {
            return;
        }
        String inputTitle = setInputTitle(scanner);
        if(inputTitle == null) {
            return;
        }
        String inputDescription = setInputDescription(scanner);
        if(inputDescription == null) {
            return;
        }
        LocalDateTime inputExecutionDateTime = setInputExecutionDateTime(scanner);
        if(inputExecutionDateTime == null) {
            return;
        }
            switch (inputRepetitionFrequency) {
                case ONE_TIME_TASK:
                    createOneTimeTask(scanner, inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
                    break;
                case DAILY_TASK:
                    createDailyTask(scanner, inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
                    break;
                case WEEKLY_TASK:
                    createWeeklyTask(scanner, inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
                    break;
                case MONTHLY_TASK:
                    createMonthlyTask(scanner, inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
                    break;
                case YEARLY_TASK:
                    createYearlyTask(scanner, inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
                    break;
                default:
                    System.out.println("Task has not been created");
            }
    }

    public static void createOneTimeTask(Scanner scanner, Repeatability inputRepetitionFrequency, Type inputType, String inputTitle, String inputDescription, LocalDateTime inputExecutionDateTime) {
        OneTimeTask oneTimeTask = new OneTimeTask(inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
        if(printTaskSaveOrCancelMenu(scanner, oneTimeTask)) {
            taskHashMap.put(oneTimeTask.getId(), oneTimeTask);
            System.out.println("Создана однократная задача");
        }
    }
    public static void createDailyTask(Scanner scanner, Repeatability inputRepetitionFrequency, Type inputType, String inputTitle, String inputDescription, LocalDateTime inputExecutionDateTime) {
        DailyTask dailyTask = new DailyTask(inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
        if(printTaskSaveOrCancelMenu(scanner, dailyTask)) {
            taskHashMap.put(dailyTask.getId(), dailyTask);
            System.out.println("Создана ежедневная задача");
        }
    }
    public static void createWeeklyTask(Scanner scanner, Repeatability inputRepetitionFrequency, Type inputType, String inputTitle, String inputDescription, LocalDateTime inputExecutionDateTime) {
        WeeklyTask weeklyTask = new WeeklyTask(inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
        if(printTaskSaveOrCancelMenu(scanner, weeklyTask)) {
            taskHashMap.put(weeklyTask.getId(), weeklyTask);
            System.out.println("Создана еженедельная задача");
        }
    }
    public static void createMonthlyTask(Scanner scanner, Repeatability inputRepetitionFrequency, Type inputType, String inputTitle, String inputDescription, LocalDateTime inputExecutionDateTime) {
        MonthlyTask monthlyTask = new MonthlyTask(inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
        if(printTaskSaveOrCancelMenu(scanner, monthlyTask)) {
            taskHashMap.put(monthlyTask.getId(), monthlyTask);
            System.out.println("Создана ежемесячная задача");
        }
    }
    public static void createYearlyTask(Scanner scanner, Repeatability inputRepetitionFrequency, Type inputType, String inputTitle, String inputDescription, LocalDateTime inputExecutionDateTime) {
        YearlyTask yearlyTask = new YearlyTask(inputRepetitionFrequency, inputType, inputTitle, inputDescription, inputExecutionDateTime);
        if(printTaskSaveOrCancelMenu(scanner, yearlyTask)) {
            taskHashMap.put(yearlyTask.getId(), yearlyTask);
            System.out.println("Создана ежегодная задача");
        }
    }
    public static boolean printTaskSaveOrCancelMenu(Scanner scanner, DailyPlanner dailyPlanner) {
        System.out.println("Создана новая задача");
        printTaskBeforeSave(dailyPlanner);
        System.out.print("Введите Y - сохранить или N - отменить создание задачи и выйти: ");
        boolean testYorN = false;
        while(true) {
            if(scanner.hasNextLine()) {
                String exitString = scanner.nextLine();
                if(exitString.equalsIgnoreCase("Y")) {
                    System.out.println("Задача сохранена");
                    testYorN = true;
                    break;
                } else if(exitString.equalsIgnoreCase("N")) {
                    System.out.println("Создание задачи отменено");
                    break;
                }
            }
            System.out.print("Введите Y - сохранить или N - отменить создание задачи и выйти: ");
        }
        return testYorN;
    }
    public static void printTaskBeforeSave(DailyPlanner dailyPlanner) {
        System.out.println(
                "Вид задачи: " + "\t\t\t\t" + dailyPlanner.getRepetitionFrequency() +
                "\nтип: " + "\t\t\t\t\t\t" + dailyPlanner.getType() +
                "\nназвание: " + "\t\t\t\t\t" + dailyPlanner.getTitle() +
                "\nописание: " + "\t\t\t\t\t" + dailyPlanner.getDescription() +
                "\nвремя выполнения: " + "\t\t\t" + dailyPlanner.getExecutionDateTime() +
                "\nid : " + "\t\t\t\t\t\t" + dailyPlanner.getId() +
                "\nповтор выполнения через: " + "\t" + dailyPlanner.getTaskRepeatPeriodDays() + " дней" +
                "\nвремя создания задачи: " + "\t\t" + dailyPlanner.getTaskCreationDateTime());
    }
    public static void printAllActiveTasks(HashMap<Integer, DailyPlanner> hashMap) {
        if(hashMap.isEmpty()) {
            System.out.println("Список активных задач пустой");
            return;
        }
        System.out.println("Список активных задач: ");
        Set<Map.Entry<Integer, DailyPlanner>> entries = hashMap.entrySet();
        for(Map.Entry<Integer, DailyPlanner> entry: entries) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
            System.out.println("Задача коротко - id задачи: " + entry.getKey() +
                    " заголовок задачи: " + entry.getValue().getTitle() +
                    " описание задачи: " + entry.getValue().getDescription());
        }
    }
    public static void removeFromMapPlaceToSet(Scanner scanner, HashMap<Integer, DailyPlanner> hashMap) {
        System.out.println("Удаление задачи по ID");
        int id = -1;
        try {
            id = checkIfIdIsCorrect(scanner, hashMap);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if(id < 0) {
            return;
        } else {
            System.out.println("Задача ID: " + id + " название " + hashMap.get(id).getTitle() +
                    " будет удалена");
        }
        System.out.print("Введите Y - удалить задачу или N - отменить операцию и выйти: ");
        while(true) {
            if(scanner.hasNextLine()) {
                String exitString = scanner.nextLine();
                if(exitString.equalsIgnoreCase("Y")) {
                    System.out.println("Задача удалена из активных задач и помещена в архив");
                    archivedTasks.add(hashMap.remove(id));
                    return;
                } else if(exitString.equalsIgnoreCase("N")) {
                    System.out.println("Удаление задачи отменено");
                    return;
                }
            }
            System.out.print("Введите Y - удалить задачу или N - отменить операцию и выйти: ");
        }
    }
    public static void printAllArchivedTasks(Set<DailyPlanner> hashSet) {
        if(hashSet.isEmpty()) {
            System.out.println("Список архивных задач - пустой");
            return;
        }
        System.out.println("Список архивных задач - ");
        for (DailyPlanner element: hashSet) {
            System.out.println("ID: " + element.getId() + " описание задачи: " + element.getTitle());
        }
    }
    public static int checkIfIdIsCorrect(Scanner scanner, HashMap<Integer, DailyPlanner> hashMap) throws TaskNotFoundException{
        if (hashMap.isEmpty()) {
            System.out.println("В ежедневнике нет активных задач");
            throw new TaskNotFoundException("TaskNotFoundException");
        }
        int idInput = 0;
        while(true) {
            System.out.print("Введите ID задачи или exit для выхода: ");
            String inputString = scanner.nextLine();
            if (inputString.isEmpty()) {
                System.out.println("ID не введен");
                continue;
            } else if (inputString.equalsIgnoreCase("exit")) {
                System.out.println("Операция отменена");
                return -1;
            }
            if (inputString.matches("[0-9]{1,10}")) {
                idInput = Integer.parseInt(inputString);
                if(!hashMap.containsKey(idInput)) {
                    System.out.println("Введен неверный ID - " + idInput);
                    continue;
                } else {
                    break;
                }
            }
            System.out.print("Введите ID задачи или exit для выхода: ");
        }

        return idInput;
    }
    public static void updateDescription(Scanner scanner, HashMap<Integer, DailyPlanner> hashMap) {
        System.out.println("Редактирование описания задачи");
        int id = -1;
        try {
            id = checkIfIdIsCorrect(scanner, hashMap);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if(id < 0) {
            return;
        } else {
            System.out.println("Описание задачи " + id + " " + hashMap.get(id).getDescription() +
                    " будет изменено");
        }
        System.out.print("Введите Y - редактировать описание или N - отменить операцию и выйти: ");
        while(true) {
            if(scanner.hasNextLine()) {
                String exitString = scanner.nextLine();
                if(exitString.equalsIgnoreCase("Y")) {
                    System.out.print("Введите новое описание задачи или exit для выхода: ");
                    String inputDescription = scanner.nextLine();
                    if(inputDescription.isEmpty()) {
                        System.out.println("Задача без описания");
                        hashMap.get(id).setDescription("Задача без описания");
                        return;
                    } else if (inputDescription.equalsIgnoreCase("exit")) {
                        System.out.println("Отмена изменения описания задачи");
                        return;
                    } else {
                        hashMap.get(id).setDescription(inputDescription);
                        System.out.println("Описание задачи изменено");
                        return;
                    }
                } else if(exitString.equalsIgnoreCase("N")) {
                    System.out.println("Отмена изменения описания задачи");
                    return;
                }
            }
            System.out.print("Введите Y - редактировать описание задачи или N - отменить операцию и выйти: ");
        }
    }
    public static void updateTitle(Scanner scanner, HashMap<Integer, DailyPlanner> hashMap) {
        System.out.println("Редактирование названия задачи");
        int id = -1;
        try {
            id = checkIfIdIsCorrect(scanner, hashMap);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if(id < 0) {
            return;
        } else {
            System.out.println("Название задачи " + id + " " + hashMap.get(id).getTitle() +
                    " будет изменено");
        }
        System.out.print("Введите Y - редактировать заголовок или N - отменить операцию и выйти: ");
        while(true) {
            if(scanner.hasNextLine()) {
                String exitString = scanner.nextLine();
                if(exitString.equalsIgnoreCase("Y")) {
                    System.out.print("Введите новое название задачи или exit для выхода: ");
                    String inputTitle = scanner.nextLine();
                    if(inputTitle.isEmpty()) {
                        System.out.println("Задача без названия");
                        hashMap.get(id).setTitle("Задача без названия");
                        return;
                    } else if (inputTitle.equalsIgnoreCase("exit")) {
                        System.out.println("Отмена изменения заголовка");
                        return;
                    } else {
                        hashMap.get(id).setTitle(inputTitle);
                        System.out.println("Заголовок задачи изменен");
                        return;
                    }
                } else if(exitString.equalsIgnoreCase("N")) {
                    System.out.println("Отмена изменения заголовка");
                    return;
                }
            }
            System.out.print("Введите Y - редактировать заголовок или N - отменить операцию и выйти: ");
        }
    }
    public static void getTasksBySpecificDate(Scanner scanner, HashMap<Integer, DailyPlanner> hashMap) {
        if(hashMap.isEmpty()) {
            System.out.println("В ежедневнике нет активных задач");
            return;
        }
        LocalDate inputLocalDate = null;
        try {
            inputLocalDate = inputDate(scanner);
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(inputLocalDate == null) {
            return;
        }
        Set<Map.Entry<Integer, DailyPlanner>> entries = hashMap.entrySet();
        for(Map.Entry<Integer, DailyPlanner> entry : entries) {
            LocalDate executionDate = entry.getValue().getExecutionDateTime().toLocalDate();
            if(executionDate.equals(inputLocalDate)) {
                System.out.println("Список задач на дату " + executionDate);
                System.out.println(entry);
                System.out.println("ID: " + entry.getValue().getId() + " название задачи: " + entry.getValue().getTitle());
            } else {
                System.out.println("Задач на дату " + inputLocalDate + " не существует");
            }
        }
    }
    public static LocalDate inputDate(Scanner scanner) throws IncorrectArgumentException{
        System.out.println("Установите дату для получения списка задач в формате yyyy-MM-dd - ");
        LocalDate today = LocalDate.now();
        int year;
        int month;
        int dayOfMonth;
        while(true) {
            System.out.print("Введите год в формате yyyy или exit для выхода: ");
            String inputString = scanner.nextLine();
            if (inputString.isEmpty()) {
                System.out.println("Неверный формат года. Год не указан");
                throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
            } else if (inputString.equalsIgnoreCase("exit")) {
                System.out.print("Отмена получения списка задач на дату yyyy-MM-dd ");
                return null;
            } else if (inputString.matches("[0-9]{4}")) {
                year = Integer.parseInt(inputString);
                if (year >= today.getYear() && year <= 2030) {
                    break;
                } else {
                    System.out.println("Значение года должно быть от " + today.getYear() + " до 2030");
                    throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
                }
            }
            System.out.println("Значение года должно быть от " + today.getYear() + " до 2030");
            throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
        }
        while (true) {
            System.out.print("Введите номер месяца в формате ММ или exit для выхода: ");
            String inputString = scanner.nextLine();
            if(inputString.isEmpty()) {
                System.out.println("Неверный формат месяца. Месяц не указан");
                throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
            } else if (inputString.equalsIgnoreCase("exit")) {
                System.out.println("Отмена получения списка задач на дату yyyy-MM-dd ");
                return null;
            } else if(inputString.matches("[0-9]{1,2}")) {
                month = Integer.parseInt(inputString);
                if(month >=1 && month <= 12) {
                    break;
                } else {
                    System.out.println("Значение месяца указано неверно");
                    throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
                }
            } else {
                System.out.println("Значение месяца указано неверно");
                throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
            }
        }
        while (true) {
            System.out.print("Введите день месяца в формате dd или exit для выхода: ");
            String inputString = scanner.nextLine();
            if(inputString.isEmpty()) {
                System.out.println("Неверный формат дня месяца. День не указан");
                throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
            } else if (inputString.equalsIgnoreCase("exit")) {
                System.out.println("Отмена получения списка задач на дату yyyy-MM-dd");
                return null;
            } else if(inputString.matches("[0-9]{1,2}")) {
                dayOfMonth = Integer.parseInt(inputString);
                if(dayOfMonth >=1 && dayOfMonth <= 31) {
                    break;
                } else {
                    System.out.println("Значение дня указано неверно");
                    throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
                }
            }
            System.out.println("Значение дня указано неверно");
            throw new IncorrectArgumentException("IncorrectArgumentException: Некорректно введенные данные");
        }
        return LocalDate.of(year, month, dayOfMonth);
    }
    public static Map<LocalDate, List<DailyPlanner>> getAllGroupedByDate(HashMap<Integer, DailyPlanner> hashMap) {
        List<DailyPlanner> dailyPlannerList = new ArrayList<>();
        dailyPlannerList.addAll(hashMap.values());
        Map<LocalDate, List<DailyPlanner>> result = dailyPlannerList.stream()
                .collect(Collectors.groupingBy(s -> s.getExecutionDateTime().toLocalDate()));
        return result;
    }
    public static void printGetAllGroupedByDate(Map<LocalDate, List<DailyPlanner>> result) {
        System.out.println("Задачи, сгруппированные по датам выполнения");
        Set<Map.Entry<LocalDate, List<DailyPlanner>>> entries = result.entrySet();
        for(Map.Entry<LocalDate, List<DailyPlanner>> entry: entries) {
            System.out.println("На дату: " + entry.getKey() + " запланированы задачи - ");
            for(int i = 0; i < entry.getValue().size(); i++) {
                System.out.println("ID задачи: " + entry.getValue().get(i).getId() +
                        " заголовок задачи: " + entry.getValue().get(i).getTitle() +
                        " описание задачи: " + entry.getValue().get(i).getDescription());
            }
        }
    }
    public static List<DailyPlanner> getAllByDate(HashMap<Integer, DailyPlanner> hashMap) {
        System.out.println("Список активных задач на следующий день -");
        LocalDate localDate = LocalDate.now();
        List<DailyPlanner> dailyPlannerList = new ArrayList<>();
        Set<Map.Entry<Integer, DailyPlanner>> entries = hashMap.entrySet();
        System.out.println("На дату: " + localDate.plusDays(1) + " активны задачи - ");
        for(Map.Entry<Integer, DailyPlanner> entry: entries) {
            LocalDateTime localDateTime = entry.getValue().getExecutionDateTime();
            boolean ifAppears = entry.getValue().appearsIn(localDateTime);
            if(ifAppears) {
                dailyPlannerList.add(entry.getValue());
            }
        }
        return dailyPlannerList;
    }
    public static void printGetAllByDate(List<DailyPlanner> dailyPlannerList) {
        for(DailyPlanner dailyPlanner: dailyPlannerList) {
            System.out.println("ID задачи: " + dailyPlanner.getId() +
                    " заголовок задачи: " + dailyPlanner.getTitle() +
                    " описание задачи: " + dailyPlanner.getDescription());
        }
    }

}
