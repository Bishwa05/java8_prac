package foreach;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ForEachImpl {

    static List<String> names = Arrays.asList("Alex", "Brian", "Charles");

    static Consumer<String> makeUpperCase = new Consumer<String>() {
        @Override
        public void accept(String t)
        {
            System.out.println(t.toUpperCase());
        }
    };


    static BiConsumer<String, Integer> action = (a, b) ->
    {
        System.out.println("Key is : " + a);
        System.out.println("Value is : " + b*b);
    };





    public static void main(String arg[])  {
        names.forEach(makeUpperCase);


        Map<String, String> map = new HashMap<String, String>();

        map.put("A", "Alex");
        map.put("B", "Brian");
        map.put("C", "Charles");

        map.forEach((k, v) ->
                System.out.println("Key = " + k + ", Value = " + v));


        //Using custom BiConsumer
        Map<String, Integer> customMap = new HashMap<>();

        customMap.put("A", 1);
        customMap.put("B", 2);
        customMap.put("C", 3);
        customMap.forEach(action);


        //Printing even numbers
        List<Integer> numberList = Arrays.asList(1,2,3,4,5);

        Consumer<Integer> action1 = System.out::println;

        numberList.stream()
                .filter(n -> n%2  == 0)
                .forEach( action1 );


        //One more layer of functional abstraction.
        HashMap<String, Integer> map2 = new HashMap<>();

        map2.put("A", 1);
        map2.put("B", 2);
        map2.put("C", 3);

        //1. Map entries
        Consumer<Map.Entry<String, Integer>> action2 = System.out::println;

        map2.entrySet().forEach(action2);

        //2. Map keys
        Consumer<String> actionOnKeys = System.out::println;

        map2.keySet().forEach(actionOnKeys);

        //3. Map values
        Consumer<Integer> actionOnValues = System.out::println;

        map2.values().forEach(actionOnValues);
    }



}
