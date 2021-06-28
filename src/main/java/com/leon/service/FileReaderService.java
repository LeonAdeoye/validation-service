package com.leon.service;

import reactor.core.publisher.Flux;

public interface FileReaderService
{
    Flux<String[]> readFile(String filePath);
}
