package use_case.constants;

public class Constants {
    public static final int REVIEW_PROFICIENCY_MAX = 3;
    public static final int REVIEW_PROFICIENCY_MIN = 0;
    public static final int REVIEW_FAIL_NEG = -2;
    public static final int REVIEW_SUCCEED_POS = 1;
//    public static final int PROFICIENCY_MAX = 3;
//    public static final int PROFICIENCY_MIN = 0;

    public static final int CLEAR = 1;
    public static final int BLUR = -1;
    public static final int FORGET = -2;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN

    public static final String CLEAR_CONSOLE = "\033[H\033[2J";

    public static final String PRO_HIGHER_THAN_MAX = "Proficiency is higher than maximum";
    public static final String PRO_LOWER_THAN_MIN = "Proficiency is lower than minimum";

    //GUI.login_register
    public static final String PASSWORD_NOT_MATCH = "Passwords didn't match. Try again.";
    public static final String REG_FAIL = "Registration Fails";
    public static final String USER_NAME_TAKEN = "This username is taken. Choose another one please~";
    public static final String LOGIN_FAIL_MSG = "Wrong password  OR  the username doesn't exist";
    public static final String LOGIN_FAIL = "Login Fails";


    //label
    public static final int EXTRA_X = 150;
    public static final int EXTRA_Y = 30;
    public static final int COLUMNS1 = 20;
    public static final int PW_WIDTH = 130;

    public static final String PW_AGAIN = "Password Again: ";

    //GUI.start
    public static final String WELCOME_MSG = "Welcome to Recaller!";

    //GUI.user
    public static final String CHANGE_PW = "Change password";
    public static final String NEW_PW_MSG = "New password: ";
    public static final String REPEAT_PW_MSG = "Repeat new password: ";
    public static final String CHANGE_USERNAME = "Change username";
    public static final String NEW_USERNAME_MSG = "new username: ";

    public static final String PW_CHANGED_SUCCEED = "Password changed successfully.";
    public static final String USERNAME_CHANGED_SUCCEED = "Username changed successfully";
    public static final String USERNAME_CHANGED_FAILED = "Changing username fails";

    //Button
    public static final String RECALLER_BTN = "Recaller";
    public static final String DONE_BTN = "done";
    public static final String LOGIN_BTN = "Login";
    public static final String REG_BTN = "Register";
    public static final String SIGN_OUT_BTN = "Sign off";
    public static final String CHANGE_NAME_BTN = "change name";
    public static final String CHANGE_PW_BTN = "change password";
    public static final String CREATE_NEW_PACKAGE = "Create a package";
    public static final String CHECK_OUT_PACKAGE = "Checkout my packages";

    //Presenter
    public static final String NAME_EXISTS = "Name already exists. Try a new name.";
    public static final String SUC_VIEW = "Successfully added.";
}