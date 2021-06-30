package com.leon.service;

import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class ValidationServiceImpl implements ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Autowired
    FileReaderService fileReaderService;

    @Override
    public ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration)
    {
        Flux<String[]> rows = fileReaderService.readFile(filePath, validationConfiguration.getDelimiter());

        rows.parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(row  -> validateRow(row, validationConfiguration))
            .subscribe();

        return new ValidationResult();
    }

    private boolean validateRow(String[] row, ValidationConfiguration validationConfiguration)
    {
        return false;
    }
}
