import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



class StartTest {

    static Start start;

    @BeforeAll
    static void setup() {
        start = new Start();
    }


    @ParameterizedTest
    @MethodSource("testMethod")
    void convert(int ball, int expectedResult) {
        int factResult = start.convert(ball);
        assertEquals(expectedResult, factResult);
    }

    // 59 60 61  74 75 76  89 90 91  -55  128 35 67 83 94
    static Stream<Arguments> testMethod() {
        return Stream.of(
                Arguments.arguments(59, 2),
                Arguments.arguments(60, 3),
                Arguments.arguments(61, 3),
                Arguments.arguments(74, 3),
                Arguments.arguments(75, 4),
                Arguments.arguments(76, 4),
                Arguments.arguments(89, 4),
                Arguments.arguments(90, 5),
                Arguments.arguments(91, 5),
                Arguments.arguments(-55, 2),
                Arguments.arguments(128, 5),
                Arguments.arguments(35, 2),
                Arguments.arguments(67, 3),
                Arguments.arguments(83, 4),
                Arguments.arguments(94, 5)

        );
    }

    @ParameterizedTest
    @MethodSource("testSumArguments")
    void additionTest(int[] arguments, int expected) {
        int result = start.summa(arguments);
        assertEquals(expected, result);
    }

    static Stream<Arguments> testSumArguments() {
        return Stream.of(
                Arguments.arguments(new int[]{}, 0),
                Arguments.arguments(new int[]{6}, 6),
                Arguments.arguments(new int[]{6, 4, 5}, 15)
        );
    }

    @ParameterizedTest
    @MethodSource("testMultiplicationArguments")
    public void multiplicationTest(int[] arguments, int expected) {
        int result = start.multiplication(arguments);
        assertEquals(expected, result);
    }

    @Test
    public void testing_multiplication_should_throw_IllegalArgumentException(){
//        exception
        Assertions.assertThrows(IllegalArgumentException.class, ()->start.multiplication());
    }

    private static Stream<Arguments> testMultiplicationArguments() {
        return Stream.of(
//                Arguments.arguments(new int[]{}, new IllegalArgumentException()),
                Arguments.arguments(new int[]{1}, 1),
                Arguments.arguments(new int[]{3, 7, 2}, 42),
                Arguments.arguments(new int[]{-5, 2, 4}, -40),
                Arguments.arguments(new int[]{-5, 2, 4, 0}, 0)
        );
    }
    
    
    

    @ParameterizedTest
    @MethodSource("argumentsSourceForAdditionTest")
    public <T extends Comparable> void bubbleSortTest(List<T> inputList, List<T> expected) {
        List<T> result = Start.bubbleSortArrayList(inputList);
        assertEquals(expected, result);
    }
    
    
    static Stream<Arguments> argumentsSourceForAdditionTest() throws FileNotFoundException {

        FileReader ff = new FileReader(new File("src/unsortedStrings"));
        BufferedReader reader = new BufferedReader(ff);
        List<String> list = reader.lines().collect(Collectors.toList());
        List<String> sotredList = new ArrayList<>(list);
        sotredList.sort(String::compareTo);

        return Stream.of(
                Arguments.arguments(list, sotredList),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "", "zzzasdf", "qwerqw", "asdfgsa"), Arrays.asList("", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "zzzasdf", "0", "qwerqw", "asdfgsa"), Arrays.asList("0", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "zzzasdf", "-654", "qwerqw", "999", "asdfgsa"), Arrays.asList("-654", "999", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf"))
        );
    }


}
