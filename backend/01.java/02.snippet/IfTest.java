import java.util.ArrayList;
import java.util.List;

public class IfTest {
    private static List<String> listOfStrings = new ArrayList<>();


    public static boolean hasValue(String value) {
        return listOfStrings.contains(value);
    }

    public static void main(String[] args) {

        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add("C");
        
        if (listOfStrings.contains("D")) {
            System.out.println("D is in the list");
        } else {
            System.out.println("D is not in the list");
        }

        if (hasValue("C")) {
            System.out.println("C is in the list");
        } else {
            System.out.println("C is not in the list");
        }

    }
    
}
