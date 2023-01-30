package com.example.randomtext.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class MainPageForm {
    @NotEmpty(message = "choose the source file")
    private SourceFile sourceFile;
    private String sourceText;
    private boolean sourceTextSet;
    private String generatedText;
    @Pattern(regexp = "[0-9]+", message = "input the whole number")
    private String lengthOfOutput;
    private String seed;

    @RequiredArgsConstructor
    @Getter
    public enum SourceFile {
        ALICE("alice.txt"),
        CONFUCIUS("confucius.txt"),
        PUTIN("putin.txt");
        private static final SourceFile[] ALL_VALUES = SourceFile.values();
        private final String fileName;
    }
}
