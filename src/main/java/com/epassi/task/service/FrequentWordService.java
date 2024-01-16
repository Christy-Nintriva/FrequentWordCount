package com.epassi.task.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

// Service class to find top K frequent words
@Service
@RequiredArgsConstructor
public class FrequentWordService {
    private static final long MAX_FILE_SIZE = 2 * 1024;//2 KB, adjust as needed
    public Map<String, Long> getTopKFrequentWords(MultipartFile file, int count) throws IOException {
// To check if the input file is greater than the allowed limit
    if (file.getSize() > MAX_FILE_SIZE) {
        throw new FileSizeLimitExceededException("File size exceeds the allowed limit", file.getSize(), MAX_FILE_SIZE);
    }
        InputStream inputStream = file.getInputStream();
        String content = readFromInputStream(inputStream);
        Map<String, Long> wordCounts = countWordOccurrences(content);
        Map<String, Long> sortedWordCounts=getFrequentWordsSorted(wordCounts);
        return getTopKEntries(sortedWordCounts , count);

    }
    // method to get the top K entries form the sorted Map
    private Map<String, Long> getTopKEntries(Map<String, Long> sortedWordCounts, int count) {
        return sortedWordCounts.entrySet().stream()
                .limit(count)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (newValue, oldValue) -> oldValue,
                        LinkedHashMap::new));
    }
    // method to read the content from the InputStream
    private String readFromInputStream(InputStream inputStream) throws IOException {
        try (Scanner scanner = new Scanner(inputStream)) {
            StringBuilder contentBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine()).append("\n");
            }
            return contentBuilder.toString();
        }
    }
    // method to count word occurrences from the input file
    private Map<String, Long> countWordOccurrences(String content) {
        return Arrays.stream(content.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
    }
    // method to sort the Map in descending order of wordcounts
    private Map<String, Long> getFrequentWordsSorted(Map<String, Long> wordCounts) {
        return wordCounts.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (newValue, oldValue) -> oldValue,
                        LinkedHashMap::new));
    }
}
