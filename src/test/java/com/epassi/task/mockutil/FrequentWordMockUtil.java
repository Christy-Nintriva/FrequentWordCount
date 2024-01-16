package com.epassi.task.mockutil;

import java.util.HashMap;
import java.util.Map;

public class FrequentWordMockUtil {
    // Method to get mock file content
    public static String GetFileContent(){
        String fileContent="This is a test file. This file contains some words to test";
        return fileContent;
    }
    // Method to get mock tok K entries
    public static Map<String, Long> getTopKEntries() {
        Map<String, Long> topKEntries = new HashMap<>();
        topKEntries.put("test", 2L);
        topKEntries.put("this", 2L);
        return topKEntries;
    }
}
