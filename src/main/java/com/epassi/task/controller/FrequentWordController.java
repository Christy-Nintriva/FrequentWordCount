package com.epassi.task.controller;

import com.epassi.task.service.FrequentWordService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/api/text")
@RequiredArgsConstructor
public class FrequentWordController {

    private final FrequentWordService frequentWordService;
    @PostMapping("/frequent-words-count")
    public ResponseEntity<Map<String, Long>> topKFrequentWords(@RequestParam(value = "file") MultipartFile file,
                                                               @RequestParam("count") int count)  {
        try {
            return ResponseEntity.ok(frequentWordService.getTopKFrequentWords(file, count));
        }catch (FileSizeLimitExceededException e) {
            // Convert the exception into a ResponseEntity with a map containing an error message
            Map<String, Long> errorResponse = new HashMap<>();
            errorResponse.put(e.getMessage(), file.getSize());
            return ResponseEntity.badRequest().body(errorResponse);
        }catch (Exception e) {
            // Handle other exceptions
            Map<String, Long> errorResponse = new HashMap<>();
            errorResponse.put(e.getMessage(), 500L);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
