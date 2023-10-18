package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class InMemoryResultProcessorTest {
    AnnotationConfigApplicationContext context;
    ResultParser resultParser;
    ResultProcessor resultProcessor;
    Resource rightFileTest;
    Resource studentFileTest;

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(ResultProcessorConfig.class);
        rightFileTest = new ClassPathResource("RightTest-answers.txt");
        studentFileTest = new ClassPathResource("StudentTest-answers.txt");
        resultParser = context.getBean(ResultParser.class);
        resultProcessor = context.getBean(ResultProcessor.class);

        Field rightFile = resultParser.getClass().getDeclaredField("rightFile");
        Field studentFile = resultParser.getClass().getDeclaredField("studentFile");
        rightFile.setAccessible(true);
        studentFile.setAccessible(true);
        rightFile.set(resultParser, rightFileTest);
        studentFile.set(resultParser, studentFileTest);
        resultParser.parseData();
    }

    @After
    public void tearDown() throws Exception {
        context = null;
        resultParser = null;
        resultProcessor = null;
        rightFileTest = null;
        studentFileTest = null;
    }

    @Test
    public void getResult() throws NoSuchFieldException, IllegalAccessException {
        PrintStream defaultOut = System.out;
        ByteArrayOutputStream systemOutCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutCaptor));

        resultProcessor.getResult();

        String actual = systemOutCaptor.toString().trim();
        String expected = "Получено 9 баллов из 18 возможных\n" +
                          "Задания в которых были допущены ошибки: 2, 3, 6, 8, 9".trim();

        assertEquals(expected, actual);
    }
}