import interfaceTest.Lambda;

import java.time.LocalDate;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Lambda<Integer> lambda = x -> x * 2;

        int result = lambda.squared(10);

        System.out.println(result);

        LocalDate now = LocalDate.now();

        Predicate<LocalDate> isAfterNowAsLambda = date -> date.isAfter(now);
        Predicate<LocalDate> isAfterNowAsRef = now::isAfter;

        boolean result1 = isAfterNowAsLambda.test(LocalDate.now());
        boolean result2 = isAfterNowAsRef.test(LocalDate.now());
        System.out.println(result1 + " " + result2);
    }
}