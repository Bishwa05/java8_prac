package stream;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
    int no;
    String name;
    Double sal;

    Employee(int no, String name, double sal){
       this.no = no;
       this.name = name;
       this.sal = sal;
    }

    public Long getNo(){
        return new Long(this.no);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee [no=" + no + ", name=" + name + ", sal=" + sal + "]";
    }
}

/**
 * A java.util.Stream represents a sequence of elements on which one or
 * more operations can be performed. Stream operations are either
 * intermediate or terminal. While terminal operations return a result
 * of a certain type, intermediate operations return the stream itself
 * so you can chain multiple method calls in a row. Streams are created
 * on a source, e.g. a java.util.Collection like lists or sets
 * (maps are not supported). Stream operations can either be
 * executed sequentially or parallely.
 */
public class BasicOperations {

    public static void main(String arg[]) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        String separator = "*****************************";


/**
 * Filter accepts a predicate to filter all elements of the stream.
 * This operation is intermediate which enables us to call another
 * stream operation (forEach) on the result. ForEach accepts a
 * consumer to be executed for each element in the filtered stream.
 * ForEach is a terminal operation. It's void, so we cannot call
 * another stream operation.
 */
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println(separator);

/**
 * Sorted is an intermediate operation which returns a sorted view of
 * the stream. The elements are sorted in natural order unless you
 * pass a custom Comparator.
 */
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach(System.out::println);


/**
 * Keep in mind that sorted does only create a sorted view of the
 * stream without manipulating the ordering of the backed collection.
 * The ordering of stringCollection is untouched:
 */

        System.out.println(stringCollection);
        System.out.println(separator);

/**
 * This is a stateful intermediate operation which returns a new stream.
 * Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator..
 * For ordered streams, the sort is stable.
 * For unordered streams, no stability guarantees are made.
 */
        List<String> sortedList = stringCollection.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(sortedList);
        System.out.println(separator);


/**
 * The intermediate operation map converts each element into another
 * object via the given function. The following example converts
 * each string into an upper-cased string. But you can also use map
 * to transform each object into another type. The generic type of
 * the resulting stream depends on the generic type of the function
 * you pass to map.
 */
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        System.out.println(separator);


/**
 * Various matching operations can be used to check whether a certain
 * predicate matches the stream. All of those operations are terminal
 * and return a boolean result.
 */

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);     // true

        System.out.println(separator);


/**
 * Count is a terminal operation returning the number of elements
 * in the stream as a long.
 */

        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);

        System.out.println(separator);

/**
 * Reduce terminal operation performs a reduction on the elements of
 * the stream with the given function. The result is an Optional
 * holding the reduced value.
 */

        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "," + s2);

        reduced.ifPresent(System.out::println);

        System.out.println(separator);

        /**
         * Sequential Sort
         */

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        /**
         * Parallel Sort
         */
         t0 = System.nanoTime();
         count = values.parallelStream().sorted().count();
        System.out.println(count);

        t1 = System.nanoTime();

        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

        System.out.println(separator);


        /**
         * Use collect method to form list out of stream.
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers);

        System.out.println(separator);

        /**
         * After filter map can be used on top of it.
         */
        List<Integer> squaredEvenNumbers = list.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println(squaredEvenNumbers);

        System.out.println(separator);


        List<Employee> employeesList = Arrays.asList(
                new Employee(1, "Alex", 100),
                new Employee(2, "Brian", 100),
                new Employee(3, "Charles", 200),
                new Employee(4, "David", 200),
                new Employee(5, "Edward", 300),
                new Employee(6, "Frank", 300)
        );

        List<Double> distinctSalaries = employeesList.stream()
                .map( e -> e.sal )
                .distinct()
                .collect(Collectors.toList());

        System.out.println(distinctSalaries);
        System.out.println(separator);

        /**
         * Stream.flatMap() helps in converting Collection<Collection<T>> to Collection<T>.
         *
         * flatMap() = map() + Flattening
         *
         * Before flattening   : [[1, 2, 3], [4, 5], [6, 7, 8]]
         * After flattening    : [1, 2, 3, 4, 5, 6, 7, 8]
         *
         * It is an intermediate operation and return another stream as method output return value.
         * Returns a stream consisting of the results of replacing each element of the given stream
         * with the contents of a mapped stream produced by applying the provided mapping function to each element.
         * The function used for transformation in flatMap() is a stateless function and returns only a stream of new values.
         * Each mapped stream is closed after its contents have been placed into new stream.
         */
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        List<Integer> list3 = Arrays.asList(7,8,9);

        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> listOfAllIntegers = listOfLists.stream()
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        System.out.println(listOfAllIntegers);
        System.out.println(separator);


        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};

        List<String> listOfAllChars = Arrays.stream(dataArray)
                .flatMap(x -> Arrays.stream(x))
                .collect(Collectors.toList());

        System.out.println(listOfAllChars);
        System.out.println(separator);


        /**
         * Use of stream.distinct()
         */
        Collection<String> list4 = Arrays.asList("A", "B", "C", "D", "A", "B", "C");

        // Get collection without duplicate i.e. distinct only
        List<String> distinctElements = list4.stream().distinct().collect(Collectors.toList());

        //Let's verify distinct elements
        System.out.println(distinctElements);
        System.out.println(separator);

        Employee lokesh = new Employee(1, "Lokesh", 100);
        Employee brian = new Employee(2, "Brian", 300);
        Employee alex = new Employee(3, "Alex", 200);

        //Add some random persons
        Collection<Employee> list5 = Arrays.asList(lokesh,brian,alex,lokesh,brian,lokesh);

        // Get distinct objects by key
        List<Employee> distinctElements2 = list5.stream()
                .filter( distinctByKey(p -> p.no) )
                .collect( Collectors.toList() );

        // Let's verify distinct elements
        System.out.println( distinctElements2 );
        System.out.println(separator);


/**
 * Stream.peek() method is an intermediate operation.
 * It returns a stream consisting of the elements of current stream.
 * It additionally perform the provided action on each element as elements.
 * For parallel stream pipelines, the action may be called at whatever time and in whatever thread the element is made available by the upstream operation.
 * If the action modifies shared state, it is itself responsible for providing the required synchronization.
 * This method exists mainly to support debugging, where we want to see the elements as they flow past a certain point in a pipeline.
 */
        List<Integer> list6 = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> newList = list6.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println(newList);
        System.out.println(separator);


/**
 * Stream.limit() method is short-circuiting intermediate operation. An intermediate operation is short-circuiting if, when presented with infinite input, it may produce a finite stream as a result. Please note that a terminal operation is short-circuiting if, when presented with infinite input, it may terminate in finite time.
 * It returns a stream consisting of the maximum elements, no longer than given size in length, of current stream.
 * Generally, limit() is cheap operation but may sometimes be expensive if maxSize has a large value and stream is parallely processed.
 * Using an unordered stream source (such as generate(Supplier)) or removing the ordering constraint with BaseStream.unordered() may result in significant speedups of limit() in parallel pipelines.
 * limit() returns the first n elements in the encounter order.
 */

        Stream<Integer> evenNumInfiniteStream = Stream.iterate(0, n -> n + 2);

        List<Integer> newList2 = evenNumInfiniteStream.limit(10)
                .collect(Collectors.toList());
        System.out.println(newList2);
        System.out.println(separator);
/**
 * Stream.skip() method is stateful intermediate operation. Stateful operations, such as distinct and sorted, may incorporate state from previously seen elements when processing new elements.
 * Returns a stream consisting of the remaining elements of the stream after discarding the first n elements of the stream.
 * If the stream contains fewer than n elements then an empty stream will be returned.
 * Generally skip() is a cheap operation, it can be quite expensive on ordered parallel pipelines, especially for large values of n.
 * Using an unordered stream source (such as generate(Supplier)) or removing the ordering constraint with BaseStream.unordered() may result in significant speedups of skip() in parallel pipelines.
 * skip() skips the first n elements in the encounter order.
 */

        Stream<Integer> evenNumInfiniteStream1 = Stream.iterate(0, n -> n + 2);

        List<Integer> newList3 = evenNumInfiniteStream1
                .skip(5)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(newList3);
        System.out.println(separator);


        /**
         * Stream to Map
         */
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
                new Employee(1, "A", 100),
                new Employee(2, "A", 200),
                new Employee(3, "B", 300),
                new Employee(4, "B", 400),
                new Employee(5, "C", 500),
                new Employee(6, "C", 600)));

        Map<Long, Employee> employeesMap = employeeList.stream()
                .collect( Collectors.toMap(Employee::getNo,
                        Function.identity()));

        System.out.println(employeesMap);
        System.out.println(separator);

    /**
     * If the stream elements have elements where map keys are duplicate
     * the we can use Collectors.groupingBy() to collect elements to map
     * in Map<keyObj, List<Element>> format. Here for each map key, we will
     * store all elements in a list as map value.
     */
        List<Employee> employeeList1 = new ArrayList<>(Arrays.asList(
                new Employee(1, "A", 100),
                new Employee(2, "A", 200),
                new Employee(3, "B", 300),
                new Employee(4, "B", 400),
                new Employee(5, "C", 500),
                new Employee(6, "C", 600)));

        Map<String, List<Employee>> employeesMap1 = employeeList1.stream()
                .collect(Collectors.groupingBy(Employee::getName));

        System.out.println(employeesMap1);
        System.out.println(separator);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
