package functionalinterface;

/**
 * Language designers put a lot of thought on how to make already
 * existing functionality lambda-friendly. As a result, the concept of
 * functional interfaces has emerged. The function interface is an
 * interface with just one single method. As such, it may be implicitly
 * converted to a lambda expression. The java.lang.Runnable and
 * java.util.concurrent.Callable are two great examples of functional
 * interfaces. In practice, the functional interfaces are fragile:
 * if someone adds just one another method to the interface definition,
 * it will not be functional anymore and compilation process will fail.
 * To overcome this fragility and explicitly declare the intent of the
 * interface as being functional, Java 8 adds special annotation
 * @FunctionalInterface (all existing interfaces in Java library have
 * been annotated with @FunctionalInterface as well). Let us take a
 * look on this simple functional interface definition:
 *
 * One thing to keep in mind: default and static methods do not break
 * the functional interface contract and may be declared:
 */

/**
 * As discussed above, only one abstract method is allowed in any
 * functional interface. Second abstract method is not not permitted
 * in a functional interface. If we remove @FunctionInterface
 * annotation then we are allowed to add another abstract method,
 * but it will make the interface non-functional interface.
 *
 * A functional interface is valid even if the @FunctionalInterface
 * annotation would be omitted. It is only for informing the compiler
 * to enforce single abstract method inside interface.
 *
 * Conceptually, a functional interface has exactly one abstract
 * method. Since default methods have an implementation, they are not
 * abstract. Since default methods are not abstract you’re free to
 * add default methods to your functional interface as many as you
 * like.
 */
@FunctionalInterface
interface Functional {
    void method();
}

@FunctionalInterface
interface FunctionalDefaultMethods {
    void method();

    default void defaultMethod() {
    }
}

@FunctionalInterface
interface MyFunctionalInterface
{
    public void firstWork();

    @Override
    public String toString();                //Overridden from Object class

    @Override
    public boolean equals(Object obj);        //Overridden from Object class
}

public class BasicOperation {

    public static void main(String[] args)
    {
        Functional checkFnI = new Functional() {
            @Override
            public void method() {
                System.out.println("Hello Functional");
            }
        };

        checkFnI.method();

        Functional intMultiplier = () -> { System.out.println("Hi Again");};
        intMultiplier.method();
    }
}
