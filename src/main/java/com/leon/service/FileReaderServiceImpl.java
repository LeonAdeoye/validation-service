package com.leon.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class FileReaderServiceImpl implements FileReaderService
{
    private static final Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String[]> readFile(String filePath, char delimiter)
    {
        try
        {
            CSVParser parser = new CSVParserBuilder().withSeparator(delimiter).build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).build();

            return Flux.generate(() -> null, (state, sink) ->
            {
                try
                {
                    String[] nextLine = reader.readNext();
                    if(nextLine != null && nextLine.length > 0)
                        sink.next(nextLine);
                    else
                        sink.complete();


                }
                catch(IOException ioe)
                {
                    logger.error(ioe.getMessage());
                }
                catch(CsvValidationException cve)
                {
                    logger.error(cve.getMessage());
                }
                finally
                {
                    return state;
                }
            });
        }
        catch(FileNotFoundException fnfe)
        {
            logger.error(fnfe.getMessage());
        }
        finally
        {
            return Flux.empty();
        }
    }
}
