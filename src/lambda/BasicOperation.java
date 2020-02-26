package lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Lambdas may reference the class members and local variables
 * (implicitly making them effectively final if they are not).
 */
public class BasicOperation {

    public static void main(String arg[]) {
        //Print all the array elements
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println(e));

        //Prior to java 8
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(names);

        // Instead of creating anonymous objects all day long, Java 8 comes with a much shorter syntax, lambda expressions:
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        //For one line method bodies you can skip both the braces {} and the return keyword. But it gets even shorter:

        names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.sort((a, b) -> b.compareTo(a));
        System.out.println(names);
    }
}
