import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class PrimitiveStreamTest {

    @DisplayName("원시스트림")
    @Test
    void 원시스트림_테스트() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        Stream<Long> longStream = Stream.of(1L, 2L, 3L, 4L);
        Stream<Double> doubleStream = Stream.of(1.1, 2.1, 3.3, 4.2);
    }

    @DisplayName("UUID")
    @Test
    void UUID_Generated_테스트() {
        Stream.generate(UUID::randomUUID)
                .limit(10L);
    }

    @DisplayName("깊이 우선 디렉토리 순회")
    @Test
    void 깊이우선디렉토리순회() {
        Path start = Paths.get("D:\\functionalTest\\main\\src\\main\\java");

        try (Stream<Path> stream = Files.walk(start)) {
             stream.map(Path::toFile)
                     .filter(Predicate.not(File::isFile))
                     .sorted()
                     .forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("파일찾기 스트림")
    @Test
    void 파일찾기() {
        Path start = Paths.get("D:\\functionalTest\\main\\src\\main\\java");

        BiPredicate<Path, BasicFileAttributes> matcher = (path, attr) -> attr.isRegularFile();

        try (Stream<Path> stream = Files.find(start, Integer.MAX_VALUE, matcher)) {
            stream.sorted()
                    .forEach(System.out::println);
        } catch (IOException e) {

        }
    }

    @DisplayName("날짜 쿼리 테스트")
    @Test
    void 날짜쿼리테스트() {
        boolean isItTeaTime = LocalDateTime.now()
                .query(temporal -> {
                    LocalTime time = LocalTime.from(temporal);
                    return time.getHour() >= 16;
                });

        assertThat(isItTeaTime).isTrue();

        LocalTime time = LocalDateTime.now().query(LocalTime::from);
    }
}
