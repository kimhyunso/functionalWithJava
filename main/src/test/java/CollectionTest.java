import org.example.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectionTest {

    @DisplayName("요소 변환")
    @Test
    void 요소변환_테스트() {


        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(), "student", LocalDateTime.now(), null));
        users.add(new User(UUID.randomUUID(), "student", LocalDateTime.now(), null));
        users.add(new User(UUID.randomUUID(), "student", LocalDateTime.now(), null));
        users.add(new User(UUID.randomUUID(), "admin", LocalDateTime.now(), null));
        users.add(new User(UUID.randomUUID(), "admin", LocalDateTime.now(), null));

        Map<String, Set<UUID>> lookup = users.stream()
                .collect(groupingBy(User::group, mapping(User::id, toSet())));

        for (String group : lookup.keySet()) {
            System.out.println(lookup.get(group));
        }


    }

}
