package education;

public interface StudentLessonCommands {

    String EXIT = "0";
    String ADD_LESSON = "1";
    String PRINT_LESSONS = "2";
    String ADD_STUDENT = "3";
    String PRINT_STUDENTS = "4";
    String PRINT_STUDENTS_BY_LESSON = "5";
    String DELETE_LESSON_BY_NAME = "6";
    String DELETE_STUDENT_BY_EMAIL = "7";

    // user commands
    String LOG_OUT = "8";
    String LOG_IN = "1";
    String REGISTER = "2";
    String PRINT_USER = "3";

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOG_IN + " for logging in");
        System.out.println("Please input " + REGISTER + " for registering");
        System.out.println("Please input " + PRINT_USER + " for printing user");
    }

    static void printAdminCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_LESSON + " for adding lesson");
        System.out.println("Please input " + PRINT_LESSONS + " for printing lessons");
        System.out.println("Please input " + ADD_STUDENT + " for adding student");
        System.out.println("Please input " + PRINT_STUDENTS + " for printing students");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for printing students by lesson");
        System.out.println("Please input " + DELETE_LESSON_BY_NAME + " for deleting lesson my name");
        System.out.println("Please input " + DELETE_STUDENT_BY_EMAIL + " for deleting student by email");
        System.out.println("Please input " + LOG_OUT + " for logging out");

    }

    static void printUserCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_LESSON + " for adding lesson");
        System.out.println("Please input " + PRINT_LESSONS + " for printing lessons");
        System.out.println("Please input " + ADD_STUDENT + " for adding student");
        System.out.println("Please input " + PRINT_STUDENTS + " for printing students");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for printing students by lesson");
        System.out.println("Please input " + LOG_OUT + " for logging out");

    }
}