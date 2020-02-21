package test;

import jdk.jfr.Description;
import main.HomeWork;
import main.Start;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayNameGeneration(HomeWorkTest.IndicativeSentences.class)
class HomeWorkTest {
    public static HomeWork homeWork;
    public static Start start;

    @BeforeAll
    public static void setup() {
        homeWork = new HomeWork();
        start = new Start();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "../resources/numbers", numLinesToSkip = 1)
    public void test_multiplication_from_StartTest(int expected, int numbers, int numbers2, int numbers3){
        int result = start.multiplication(numbers, numbers2, numbers3);
        assumeTrue(expected == result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../resources/listOfTextFiles")
    public void testing_implicit_conversion_to_File(File file){
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            result = reader.lines().collect(Collectors.toList());
        }catch(IOException ignored){}
        System.out.print(result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../resources/listOfTextFiles")
    public void bubbleSort_Test_testing_with_CSV_Source_and_custom_converter(@ConvertWith(ToListArgumentsConverter.class) File file){

    }

    @ParameterizedTest
//    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
//    @DisabledForJreRange(min = JAVA_8)
//    @CsvFileSource(resources = "../resources/listOfTextFiles", numLinesToSkip = 1)
    @ArgumentsSource(ListArgumentsProvider.class)
    public <T extends Comparable<? super T>> void bubble_Sort_Test_Should_Return_Sorted_ArrayList_of_Comparables(
            List<T> inputList, List<T> expected) {
        List<T> result = homeWork.bubbleSortArrayList(inputList);
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("heading text",
                () -> assertNotNull(result),
                () -> assertEquals(expected, result, "the default error message goes in as the last parameter")
        );
    }

    @ParameterizedTest
//    @ArgumentsSource(ListArgumentsProvider.class)
    @MethodSource("test.ListArgumentsProvider#streamOfArgumentsArrayListFromFiles")
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