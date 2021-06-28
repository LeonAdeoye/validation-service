package com.leon.service;

import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ValidationServiceImpl implements ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Autowired
    FileReaderService fileReaderService;

    @Override
    public ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration)
    {
        Flux<String[]> rows = fileReaderService.readFile(filePath);
        int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfProcessors);

        rows.parallel(numberOfProcessors)
            .runOn(Schedulers.fromExecutor(executor))
            .map(System.out::println);

        return new ValidationResult();
    }

    private boolean validateRow(String[] row, ValidationConfiguration validationConfiguration)
    {
        return false;
    }
}
