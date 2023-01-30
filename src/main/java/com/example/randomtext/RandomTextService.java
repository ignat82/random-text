package com.example.randomtext;

import edu.duke.FileResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static com.example.randomtext.Constants.DATA_FOLDER;

@Component
@Slf4j
public class RandomTextService {
    //private final
    public String readSourceString(String fileName) throws URISyntaxException {
        log.info("got fileName {}", fileName);
        URL res = getClass().getClassLoader().getResource(DATA_FOLDER + fileName);
        File sourceFile = Paths.get(res.toURI()).toFile();
        FileResource fileResource = new FileResource(sourceFile);
        return fileResource.asString();
    }

    public String generateOutputString(String inputString, int length) {
        inputString = inputString.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setTraining(inputString);
        return markov.getRandomText(length);
    }
}
