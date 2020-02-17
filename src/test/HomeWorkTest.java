package test;

import main.HomeWork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
    @MethodSource("argumentsSourceForAdditionTest")
    public <T extends Comparable> void bubbleSortTestShouldReturnSortedArrayListofComparables(List<T> inputList, List<T> expected) {
        List<T> result = homeWork.bubbleSortArrayList(inputList);
        assertEquals(expected, result);
    }

//    @Test
//    public void bubbleSortTestShouldThrowIllegalArgumentException(){
//        assertThrows(IllegalArgumentException.class, ()->homeWork.bubbleSortArrayList(new));
//    }


    static Stream<Arguments> argumentsSourceForAdditionTest() throws FileNotFoundException {
        List<String> list = getFileAsList("src/unsortedStrings");
        List<String> sotredList = new ArrayList<>(list);
        sotredList.sort(String::compareTo);

        return Stream.of(
                Arguments.arguments(list, sotredList),
                Arguments.arguments(Arrays.asList(""), Arrays.asList("")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "", "zzzasdf", "qwerqw", "asdfgsa"), Arrays.asList("", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "zzzasdf", "0", "qwerqw", "asdfgsa"), Arrays.asList("0", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf")),
                Arguments.arguments(Arrays.asList("asdf", "ssfg", "zzzasdf", "-654", "qwerqw", "999", "asdfgsa"), Arrays.asList("-654", "999", "asdf", "asdfgsa", "qwerqw", "ssfg", "zzzasdf"))
        );
    }

    public static List<String> getFileAsList(String fileUri) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileUri)))) {
            result = reader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}