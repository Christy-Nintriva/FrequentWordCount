package com.epassi.task.service;

import com.epassi.task.mockutil.FrequentWordMockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FrequentWordServiceTest {
    @InjectMocks
    private FrequentWordService frequentWordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Method to test getTopKFrequentWords method in FrequentWordService
    @Test
    void testGetTopKFrequentWords() throws IOException {
        // call Mock file content from FrequentWordMockUtil class
        String fileContent = FrequentWordMockUtil.GetFileContent();
        MultipartFile file = new MockMultipartFile("test.txt", fileContent.getBytes());
        Map<String, Long> result = frequentWordService.getTopKFrequentWords(file, 2);
        // Verify the result
        assertEquals(FrequentWordMockUtil.getTopKEntries(), result);
    }

}
