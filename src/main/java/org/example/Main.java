package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResultProcessorConfig.class);

        ResultParser resultParser = context.getBean(ResultParser.class);
        resultParser.parseData();

        ResultProcessor resultProcessor = context.getBean(ResultProcessor.class);
        resultProcessor.getResult();



    }
}
