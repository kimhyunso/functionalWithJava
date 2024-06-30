import org.example.domain.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class StreamTest {
    private List<Shape> shapes;

    @BeforeEach
    void setUp(){
        shapes = Arrays.asList(new Shape(3), new Shape(4), new Shape(3), new Shape(0), new Shape(4), new Shape(0));
    }


    @Test
    @DisplayName("스트림 필터 테스트")
    void filterTest(){

        List<Shape> result = shapes.stream()
                .filter(Shape::hasCorners)
                .collect(Collectors.toList());
        assertThat(result).isNotIn(Arrays.asList(new Shape(0)));
    }

    @Test
    @DisplayName("스트림 dropWhile 테스트 :: 요소 검사중 조건이 참이나오기 전까지 전부 버림")
    void dropWhileTest(){
        List<Shape> result = shapes.stream()
                .dropWhile(Shape::hasCorners)
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(new Shape(0),new Shape(4), new Shape(0)));
    }

    @Test
    @DisplayName("스트림 takeWhile 테스트 :: dropWhile의 반대 개념 조건이 거짓이 나오기 전까지 전부 버림")
    void takeWhileTest(){
        List<Shape> result = shapes.stream()
                .takeWhile(Shape::hasCorners)
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(new Shape(3),new Shape(4), new Shape(3)));
    }

    @Test
    @DisplayName("스트림 limit :: 요소의 최대 갯수 제한")
    void limitTest(){
        List<Shape> result = shapes.stream()
                .limit(2)
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(new Shape(3),new Shape(4)));
    }

    @Test
    @DisplayName("스트림 skip :: 요소를 n만큼 건너뜀")
    void skipTest(){
        List<Shape> result = shapes.stream()
                .skip(2)
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(new Shape(3), new Shape(0), new Shape(4), new Shape(0)));
    }

    @Test
    @DisplayName("스트림 distinct :: 중복요소 제거")
    void distinctTest(){
        List<Shape> result = shapes.stream()
                .distinct()
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(new Shape(3), new Shape(4), new Shape(0)));
    }

    @Test
    @DisplayName("스트림 sorted :: 오름차순 정렬")
    void sortedTest(){
        List<Shape> result = shapes.stream()
                .sorted()
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(new Shape(0), new Shape(0), new Shape(3), new Shape(3), new Shape(4), new Shape(4)));
    }

    @Test
    @DisplayName("스트림 map :: 요소 매핑 및 변환")
    void mapTest(){
        List<Integer> result = shapes.stream()
                .map(Shape::cornerCount)
                .collect(Collectors.toList());

        assertThat(result).isEqualTo(Arrays.asList(3, 4, 3, 0, 4, 0));
    }

    @Test
    @DisplayName("스트림 flatMap :: map 연산 후 컨테이너 형태의 요소를 펼친다.")
    void flatMapTest(){
        List<Shape> shapes = Arrays.asList(new Shape(4), new Shape(3), new Shape(0));

        List<Shape> result = shapes.stream()
                .map(Shape::twice)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat(result).isEqualTo(Arrays.asList(new Shape(4), new Shape(4), new Shape(3), new Shape(3), new Shape(0), new Shape(0)));
    }

    @Test
    @DisplayName("스트림 peek :: 디버깅용")
    void peekTest(){
        List<Shape> result = Stream.of(Shape.square(), Shape.triangle(), Shape.circle())
                .map(Shape::twice)
                .flatMap(List::stream)
                .peek(shape -> System.out.println(shape))
                .filter(shape -> shape.corners() < 4)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("스트림 reduce 인수 2개")
    void reduceTest(){
        int reduceOnly = Stream.of("apple", "orange", "banana")
                .peek(str -> System.out.println(str))
                .reduce(0, (acc, str) -> acc + str.length(), Integer::sum);

        int mapReduce = Stream.of("apple", "orange", "banana")
                .mapToInt(String::length)
                .peek(length -> System.out.println(length))
                .reduce(0, (acc, length) -> acc + length);

        System.out.println(mapReduce);


    }
}