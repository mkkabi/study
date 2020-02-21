package test;

import jdk.jfr.Description;
import main.HomeWork;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
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
import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;
import static test.ListArgumentsProvider.getFileAsList;

@DisplayNameGeneration(HomeWorkTest.IndicativeSentences.class)
class HomeWorkTest {
    public static HomeWork homeWork;

    @BeforeAll
    public static void setup() {
        homeWork = new HomeWork();
    }


    @ParameterizedTest
//    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
//    @DisabledForJreRange(min = JAVA_8)
//    @CsvFileSource(resources = "../resources/listOfTextFiles", numLinesToSkip = 1)
    @ArgumentsSource(ListArgumentsProvider.class)
    public <T extends Comparable<? super T>> void bubble_Sort_Test_Should_Return_Sorted_ArrayList_of_Comparables(
            List<T> inputList) {
        List<T> result = homeWork.bubbleSortArrayList(inputList);
        List<T> expected = result.stream().sorted(T::compareTo).collect(Collectors.toList());
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("heading text",
                () -> assertNotNull(result),
                () -> assertEquals(expected, result, "the default error message goes in as the last parameter")
        );
    }

    @ParameterizedTest
    @ArgumentsSource(ListArgumentsProvider.class)
    <T extends Comparable<? super T>> void bubbleSort_does_not_exceed_duration(List<T> inputList, List<T> expected) {

        List<T> result = assertTimeout(ofMillis(100), () -> homeWork.bubbleSortArrayList(inputList));
        assertNotNull(result);

        assumeTrue(false);
    }

    @ParameterizedTest
    @Description("description annotation")
    @DisplayName("\uD83D\uDE31 Display name параметр имеет преимущество над DisplayNameGeneration")
    @ArgumentsSource(ListArgumentsProvider.class)
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