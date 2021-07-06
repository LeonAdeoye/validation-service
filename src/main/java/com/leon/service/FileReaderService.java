package com.leon.service;

import com.leon.model.DataRow;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;

public interface FileReaderService
{
    Flux<DataRow> readFile(String filePath, String delimiter) throws FileNotFoundException;
}
