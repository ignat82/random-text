package com.example.randomtext.web;

import com.example.randomtext.RandomTextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.URISyntaxException;

import static com.example.randomtext.Constants.BASE_PATH;
import static com.example.randomtext.Constants.MAIN_TEMPLATE;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainPageController {
    private final RandomTextService service;

    @GetMapping(BASE_PATH)
    public String doGet(MainPageForm form) {
        return MAIN_TEMPLATE;
    }

    @PostMapping(BASE_PATH)
    public String doPost(@Valid MainPageForm mainPageForm, BindingResult bindingResult) throws URISyntaxException {
        log.info("got form data {}", mainPageForm.toString());
        if (!bindingResult.hasErrors()) {
            log.info("see no errors :E");
            log.info(bindingResult.toString());
            log.info("sourceFile {}", mainPageForm.getSourceFile().getFileName());
            mainPageForm.setSourceText(service.readSourceString(mainPageForm.getSourceFile().getFileName()));
        } else {
            bindingResult.getFieldErrors()
                         .forEach(e -> log.error("got validation error {} for field {}",
                                                 e.getDefaultMessage(),
                                                 e.getField()));
        }
        return MAIN_TEMPLATE;
    }
}