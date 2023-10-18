package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResultProcessorConfig.class);

        InMemoryResultParser parser = new InMemoryResultParser(new ArrayList<>(), new ArrayList<>());
        List<String> strings = parser.fileToList(new File("C:\\Users\\letke\\IdeaProjects\\Exam\\src\\main\\resources\\Right-answers.txt"));

        System.out.println(strings);
    }
}
