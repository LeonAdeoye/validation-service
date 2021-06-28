package com.leon.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService
{
    private static final Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String[]> readFile(String filePath)
    {
        List<String[]> result = new ArrayList<>();
        try(java.io.FileReader fileReader = new java.io.FileReader(filePath); CSVReader reader = new CSVReader(fileReader))
        {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                result.add(nextLine);
            }
        }
        catch(FileNotFoundException fnfe)
        {
            logger.error(fnfe.getMessage());
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
            return Flux.fromIterable(result);
        }
    }
}
