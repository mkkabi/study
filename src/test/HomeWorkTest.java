package test;

import main.HomeWork;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(HomeWorkTest.IndicativeSentences.class)
class HomeWorkTest {
    public static HomeWork homeWork;

    @BeforeAll
    public static void setup() {
        homeWork = new HomeWork();
    }


    @ParameterizedTest
    @MethodSource("streamOfArgumentsArrayListFromFiles")
    public <T extends Comparable<? super T>> void bubble_Sort_Test_Should_Return_Sorted_ArrayList_of_Comparables(
            List<T> inputList, List<T> expected) {
        List<T> result = homeWork.bubbleSortArrayList(inputList);

        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
                () -> assertNotNull(result),
                () -> assertEquals(expected, result, "the default error message goes in as the last parameter")
        );
    }

    @ParameterizedTest
    @MethodSource("streamOfArgumentsArrayListFromFiles")
    <T extends Comparable<? super T>> void  timeout_Not_Exceeded_With_Result(List<T> inputList, List<T> expected) {

        // The following assertion succeeds, and returns the supplied object.
//        () -> assertEquals(expected, result)

        List<T> result = assertTimeout(ofMillis(2, ()->{
            return homeWork.bubbleSortArrayList(inputList);
        }));

     }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> { return "a result";

        });
        assertEquals("a result", actualResult);
    }

    @ParameterizedTest
    @DisplayName("\uD83D\uDE31 Display name параметр имеет преимущество над DisplayNameGeneration")
    @MethodSource("streamOfArgumentsArrayListFromFiles")
    public void selectionSort_Test_Should_Return_Sorted_List(List<String> inputList, List<String> expected) {
        List<String> result = homeWork.selectionSort(inputList);
        assertEquals(expected, result);
    }

    @Test
    public void selectionSort_Test_Should_Return_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> homeWork.selectionSort(null));
    }

    static Stream<Arguments> argumentsSourceForSortTest() {
        return Stream.of(
                Arguments.arguments(getFileAsList("resources/src/unsortedStrings"),
                        getFileAsList("src/unsortedStrings").stream().sorted(String::compareTo).collect(Collectors.toList())),
                Arguments.arguments(Arrays.asList(""), Arrays.asList("")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "", "zzzasdf", "qwerqw", "asdfgsa"), Arrays.asList("", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "zzzasdf", "0", "qwerqw", "asdfgsa"), Arrays.asList("0", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "zzzasdf", "-654", "qwerqw", "999", "asdfgsa"), Arrays.asList("-654", "999", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf"))
        );
    }

    static Stream<Arguments> streamOfArgumentsArrayListFromFiles() {
        List<Arguments> argList = new ArrayList<>();

        // небольшой эксперимент с внешними ресурсами для тестирования
        // текстовый файл "src/listOfTextFiles", содержит в себе список файлов для тестирования,
        // добавляем содержимого каждого файла из списка в виде
        // List<String> в переменную argList  и затем возвращаем ее в виде потока
        getFileAsList("src/resources/listOfTextFiles").forEach(s -> {
            argList.add(Arguments.arguments(getFileAsList(s), getFileAsList(s).parallelStream().sorted(String::compareTo).collect(Collectors.toList())));
        });

        return argList.stream();
    }


    public static List<String> getFileAsList(String fileUri) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileUri)))) {
            result = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    static class IndicativeSentences extends DisplayNameGenerator.ReplaceUnderscores {

        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return super.generateDisplayNameForClass(testClass);
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return super.generateDisplayNameForNestedClass(nestedClass) + "...";
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            String name = testClass.getSimpleName() + ' ' + testMethod.getName();
            return name.replace('_', ' ') + '.';
        }

    }
}