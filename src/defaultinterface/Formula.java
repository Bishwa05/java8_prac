package defaultinterface;
interface Formula1 {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

class OverridableImpl implements Formula1 {
    //@Override
    public String sqrt() {
        return "Overridden implementation";
    }

    @Override
    public double calculate(int a){
       return a*a;
    }

}

/**
 * Besides the abstract method calculate the interface Formula also
 * defines the default method sqrt. Concrete classes only have to
 * implement the abstract method calculate. The default method sqrt
 * can be used out of the box.
 */
public class Formula {

    static Formula1 formula = new Formula1() {
        @Override
        public double calculate(int a) {
            return sqrt(a * 100);
        }
    };

    public static void main(String arg[]){
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }
}
