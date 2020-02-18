package test;

import main.HomeWork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {
    public static HomeWork homeWork;

    /**
     * test sorting with no arguments
     */

    @BeforeAll
    public static void setup() {
        homeWork = new HomeWork();
    }


    @ParameterizedTest
    @MethodSource("streamOfArgumentsArrayListFromFiles")
    public <T extends Comparable<? super T>> void bubbleSortTestShouldReturnSortedArrayListofComparables(
            List<T> inputList, List<T> expected) {
        List<T> result = homeWork.bubbleSortArrayList(inputList);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("streamOfArgumentsArrayListFromFiles")
    public void selectionSortTestShouldReturnSortedList(List<String> inputList, List<String> expected) {
        List<String> result = homeWork.selectionSort(inputList);
        assertEquals(expected, result);
    }

    static Stream<Arguments> argumentsSourceForSortTest() {
        return Stream.of(
                Arguments.arguments(getFileAsList("src/unsortedStrings"),
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
        getFileAsList("src/listOfTextFiles").forEach(s -> {
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
}