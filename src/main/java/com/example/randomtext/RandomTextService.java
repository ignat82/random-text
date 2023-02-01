package com.example.randomtext;

import com.example.randomtext.generator.Markov;
import edu.duke.FileResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static com.example.randomtext.Constants.DATA_FOLDER;

@Component
@Slf4j
@RequiredArgsConstructor
public class RandomTextService {
    private final Markov markov;
    public String readSourceString(String fileName) throws URISyntaxException {
        log.info("got fileName {}", fileName);
        URL res = getClass().getClassLoader().getResource(DATA_FOLDER + fileName);
        File sourceFile = Paths.get(res.toURI()).toFile();
        FileResource fileResource = new FileResource(sourceFile);
        return fileResource.asString();
    }

    public String generateOutputString(String inputString, int length, Integer seed) {
        if (seed != null) {
            setRandom(seed);
        }
        inputString = inputString.replace('\n', ' ');
        markov.setTraining(inputString);
        return markov.getRandomText(length);
    }

    private void setRandom(int seed) {
        markov.setRandom(seed);
    }
}
