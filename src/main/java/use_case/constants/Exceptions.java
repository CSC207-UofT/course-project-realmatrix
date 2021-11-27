package use_case.constants;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Exceptions{
    public static final String PackNotInListError = "Pack not in user's Pack list";
    public static final String TermUsed = "Term is used in the system";
    public static final String ProficiencyHigherThanMax = "Proficiency is higher than max";
    public static final String ProficiencyLowerThanMin = "Proficiency is lower than min";
    public static final String NullUserName = "User name is null";
    public static final String InvalidObject = "Invalid Object";
//    public static final String ArchivePathExist = "Archive path exists";
    public static final String WritePathExist = "Write path exists";
    public static final String InvalidPath = "Path is invalid";

//    public static ArrayList<String> getErrors() {
//        ArrayList<String> errorMessages = new ArrayList<String>();
//        Field[] fields = Exceptions.class.getFields();
//        for (Field field : fields) {
//            try {
//                String errorMessage = (String) field.get(Exceptions.class);
//                errorMessages.add(errorMessage);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return errorMessages;
//    }

}