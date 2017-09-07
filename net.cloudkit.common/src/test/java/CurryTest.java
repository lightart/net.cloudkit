import java.util.function.Function;

/**
 * Java 8 Currying tutorial
 * http://www.tuicool.com/articles/hit/vQBFrm
 */
public class CurryTest {

    public static void main(String[] args) {

        Function<Integer, Function<Integer, Integer>> add = (a) -> (b) -> a + b;

        Function<Integer, Integer> addOne = add.apply(1);
        Function<Integer, Integer> addFive = add.apply(5);
        Function<Integer, Integer> addTen = add.apply(10);

        Integer result1 = addOne.apply(2); // returns 3
        Integer result2 = addFive.apply(2); // returns 7
        Integer result3 = addTen.apply(2); // returns 12

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);

    }
}
