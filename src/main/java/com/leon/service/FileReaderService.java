package com.leon.service;

import com.leon.model.DataRow;
import reactor.core.publisher.Flux;

public interface FileReaderService
{
    Flux<DataRow> readFile(String filePath, char delimiter);
}
