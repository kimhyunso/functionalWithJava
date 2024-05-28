import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;

public class ComplexTest {

    @Test
    void 합성함수테스트_ANDTHEN(){
        Function<String, String> replace = (input) -> input.replace("a", "");
        Function<String, String> toUpperCase = String::toUpperCase;

        String result = replace.andThen(toUpperCase).apply("helalao");
        assertThat(result).isEqualTo("HELLO");
    }

    @Test
    void 합성함수테스트_COMPOSE(){
        Function<String, String> replace = (input) -> input.replace("a", "");
        Function<String, String> toUpperCase = String::toUpperCase;

        String result = toUpperCase.compose(replace).apply("helalao");
        assertThat(result).isEqualTo("HELLO");
    }
}
