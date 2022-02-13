package education;

import education.model.Lesson;
import education.model.Student;
import education.model.User;
import education.model.UserType;
import education.storage.LessonStorage;
import education.storage.StudentStorage;
import education.storage.UserStorage;
import education.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StudentLessonTest implements StudentLessonCommands {

    static Scanner scanner = new Scanner(System.in);
    static StudentStorage studentStorage = new StudentStorage();
    static LessonStorage lessonStorage = new LessonStorage();
    static UserStorage userStorage = new UserStorage();

    public static void main(String[] args) {
        initData();
        boolean isRun = true;

        while (isRun) {
            StudentLessonCommands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOG_IN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void login() {
        System.out.println("Please, input email");
        String email = scanner.nextLine();
        User byEmail = userStorage.getByEmail(email);
        if (byEmail != null) {
            System.out.println("Please, input password");
            String password = scanner.nextLine();
            if (byEmail.getPassword().equals(password)) {
                if (byEmail.getType() == UserType.ADMIN) {
                    adminLogin();
                } else if (byEmail.getType() == UserType.USER) {
                    userLogin();
                }
            } else {
                System.out.println("Wrong password!");
            }
        } else {
            System.err.println("User with " + email + " doesn't exist");
        }
    }

    private static void adminLogin() {
        boolean isRun = true;
        while (isRun) {
            StudentLessonCommands.printAdminCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printByLesson();
                    break;
                case DELETE_LESSON_BY_NAME:
                    deleteByName();
                    break;
                case DELETE_STUDENT_BY_EMAIL:
                    deleteByEmail();
                    break;
                case LOG_OUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void userLogin() {
        boolean isRun = true;
        while (isRun) {
            StudentLessonCommands.printUserCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printByLesson();
                    break;
                case LOG_OUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void register() {
        System.out.println("Please, input email");
        String email = scanner.nextLine();
        User byEmail = userStorage.getByEmail(email);
        if (byEmail == null) {
            System.out.println("Please, input name");
            String name = scanner.nextLine();

            System.out.println("Please, input surname");
            String surname = scanner.nextLine();

            System.out.println("Please, input password");
            String password = scanner.nextLine();

            System.out.println("Please, input type (ADMIN, USER)");
            String type = scanner.nextLine();
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(password);
            user.setType(UserType.valueOf(type.toUpperCase()));
            userStorage.add(user);
            System.out.println("User was registered!");
        } else {
            System.err.println("User with " + email + " already exist");
        }
    }

    private static void initData() {
        studentStorage.initData();
        lessonStorage.initData();
        userStorage.initData();
    }

    private static void deleteByEmail() {
        System.out.println("Please, choose student's email");
        studentStorage.print();
        System.out.println("---------");
        String email = scanner.nextLine();
        Student student = studentStorage.getByEmail(email);
        if (student != null) {
            studentStorage.delete(student);
        } else {
            System.err.println("Student with this email doesn't exist.");
        }
    }

    private static void deleteByName() {
        System.out.println("Please, input lesson's name");
        lessonStorage.print();
        System.out.println("---------");
        String name = scanner.nextLine();
        Lesson lesson = lessonStorage.getByName(name);
        if (lesson != null) {
            lessonStorage.deleteByName(lesson);
        } else {
            System.err.println("Lesson with this name doesn't exist.");
        }
    }

    private static void printByLesson() {
        System.out.println("Please, input lesson's name");
        String lessonName = scanner.nextLine();
        Lesson lesson = lessonStorage.getByName(lessonName);
        if (lesson != null) {
            studentStorage.printByLesson(lesson);
        } else {
            System.err.println("Student does not exist");
        }
    }

    private static void addStudent() {
        System.out.println("Please input student's name,surname,age,email,phone,lesson,date of birth");
        String studentDataStr = scanner.nextLine();
        String[] studentData = studentDataStr.split(",");
        String lessonNameStr = scanner.nextLine();
        String[] lessonName = lessonNameStr.split(",");
        if (studentData.length == 7) {
            int age = Integer.parseInt(studentData[3]);
            Date date;
            try {
                date = DateUtil.stringToDate(studentData[6]);
            } catch (ParseException e) {
                System.out.println("Invalid date format!");
                return;
            }
            System.out.println("Please, input lesson's name: name1, name2, name3");
            if (lessonName.length == 0) {
                System.out.println("Please, choose lesson's name");
                return;
            }
            Set<Lesson> lessons = new HashSet<>();
            for (int i = 0; i < lessonName.length; i++) {
                Lesson lesson = lessonStorage.getByName(lessonName[i]);
                if (lesson != null) {
                    lessons.add(lesson);
                } else {
                    System.out.println("invalid lesson");
                    return;
                }
            }
            Student student = new Student(studentData[0], studentData[1], age, studentData[2],
                    studentData[3], lessons, date);
            studentStorage.add(student);
            System.out.println("Thank you, student was added!");
        } else {
            System.err.println("invalid email! Please, try again!");
            addStudent();
        }
    }

    private static void addLesson() {
        System.out.println("Please, input lesson's name.");
        String name = scanner.nextLine();
        if (lessonStorage.getByName(name) == null) {
            System.out.println("Please, input lesson's duration");
            String duration = scanner.nextLine();
            System.out.println("Please, input lesson's lecturer name");
            String lecturerName = scanner.nextLine();
            System.out.println("Please, input lesson's price");
            double price = Double.parseDouble(scanner.nextLine());
            Student student = new Student();
            Lesson lesson = new Lesson(name, duration, lecturerName, price,student);
            lessonStorage.add(lesson);
            System.out.println("Thank you, lesson was added");
        } else {
            System.err.println("Lesson with this email already exists");
        }
    }
}
