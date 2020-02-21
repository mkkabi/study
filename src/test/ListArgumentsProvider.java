package test;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
//        return Stream.of("apple", "banana").map(Arguments::of);
        return streamOfArgumentsArrayListFromFiles();
    }


    static Stream<Arguments> streamOfArgumentsArrayListFromFiles() {
        // небольшой эксперимент с внешними ресурсами для тестирования в целях повторения потоков
        // текстовый файл "src/resources/listOfTextFiles", содержит в себе список файлов для тестирования,

        return getFileAsList("src/resources/listOfTextFiles").stream().map((line) -> {
            // проходим по каждой строчке файла listOfTextFiles и возвращаем поток аргументов
            // из несортированного и сортированного списка
            // сохраняем содержимое файла в переменную, чтобы дважды его не парсить
            List<String> listFromFile = getFileAsList(line);
            return Arguments.arguments(listFromFile, listFromFile
                    .parallelStream()
                    .sorted(String::compareTo)
                    .collect(Collectors.toList()));
        });
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