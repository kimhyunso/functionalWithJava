import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.example.record.Container;
import org.example.record.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    @DisplayName("레코드 테스트")
    void 레코드(){
        User user = new User("test", false, LocalDateTime.now());
        assertThat(user.username()).isEqualTo("test");
        assertThat(user.active()).isFalse();
    }

    @Test
    @DisplayName("레코드 Null 테스트")
    void 레코드_NotNull(){
        assertThatThrownBy(() -> {
            User user = new User(null, false, LocalDateTime.now());
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("레코드 동일성 체크")
    void 레코드_동일성(){
        User user1 = new User("test", false, LocalDateTime.now());
        User user2 = new User("test", false, LocalDateTime.now());
        assertThat(user1.equals(user2)).isTrue();
    }


    @Test
    @DisplayName("레코드 제네릭")
    void 레코드_제네릭(){
        Container<String> container = new Container<>("hello ", " World!");
        assertThat(container.content()).isEqualTo("hello ");
        assertThat(container.identifier()).isEqualTo(" World!");
    }

}
