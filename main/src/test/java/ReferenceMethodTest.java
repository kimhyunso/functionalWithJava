import net.bytebuddy.asm.Advice;
import org.example.domain.SubClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReferenceMethodTest {
    @DisplayName("정적 메소드 참조")
    @Test
    void staticMethodRef(){
        // 람다
        Function<Integer, String> asLambda = i -> Integer.toHexString(i);
        
        // 정적 메소드 참조
        Function<Integer, String> asRef = Integer::toHexString;

        assertThat(asLambda.apply(10)).isEqualTo("a");
        assertThat(asRef.apply(10)).isEqualTo("a");
    }

    @DisplayName("바운드 비정적 메소드 참조")
    @Test
    void boundMethodRef(){
        // 람다
        Predicate<LocalDate> isAfterNowAsLambda = date -> date.isAfter(LocalDate.now());
        // 바운드 비정적 메소드 참조
        Predicate<LocalDate> isAfterNowAsRef = LocalDate.now()::isAfter;

        assertThat(isAfterNowAsLambda.test(LocalDate.now())).isFalse();
        assertThat(isAfterNowAsRef.test(LocalDate.now())).isFalse();

        assertThat(new SubClass().superWork("hello, world")).isEqualTo("super: hello, world");
        assertThat(new SubClass().thisWork("hello, world")).isEqualTo("this: hello, world");

    }

    @DisplayName("언바운드 비정적 메소드 참조")
    @Test
    void unboundMethodRef(){
        // 람다
        Function<String, String> toLowerCaseLambda = str -> str.toLowerCase();
        // 언바운드 비정적 메소드 참조
        Function<String, String> toLowerCaseRef = String::toLowerCase;

        assertThat(toLowerCaseLambda.apply("ReFeReNce")).isEqualTo("reference");
        assertThat(toLowerCaseRef.apply("ReFeReNce")).isEqualTo("reference");
    }

    @DisplayName("생성자 참조")
    @Test
    void constructor(){
        // 람다
        Function<String, Locale> newLocaleLambda = language -> new Locale(language);

        // 생성자 참조
        Function<String, Locale> newLocaleRef = Locale::new;

        assertThat(newLocaleLambda.apply("test")).hasToString("test");
        assertThat(newLocaleRef.apply("test")).hasToString("test");
    }

}
