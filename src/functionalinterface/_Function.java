package functionalinterface;

import sun.awt.geom.AreaOp;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function
{
    public static void main (String[] args)
    {
        int increment = incrementByOneFn.apply(1);
        System.out.println(increment);
        int multiply = multiplyBy10Fn.apply(increment);
        System.out.println(multiply);

        Function<Integer, Integer> addByOneMultiplyBy10 =
            incrementByOneFn.andThen(multiplyBy10Fn);

        System.out.println(addByOneMultiplyBy10.apply(1));

        // BiFunction takes 2 argument and produces 1 result
        System.out.println(incrementByOneAndMultiplyBiFn.apply(4, 100));

    }

    static Function<Integer, Integer> incrementByOneFn = number -> number +1;

    static Function<Integer, Integer> multiplyBy10Fn = number -> number *10;

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFn =
        (numberToIncrement, numberToMultiplyBy) ->
            (numberToIncrement +1)* numberToMultiplyBy;
}
