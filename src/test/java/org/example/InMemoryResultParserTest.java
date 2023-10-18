package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class InMemoryResultParserTest {
    AnnotationConfigApplicationContext context;
    ResultParser resultParser;
    Resource rightFileTest;
    Resource studentFileTest;

    @Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(ResultProcessorConfig.class);
        resultParser = context.getBean(ResultParser.class);

        rightFileTest = new ClassPathResource("RightTest-answers.txt");
        studentFileTest = new ClassPathResource("StudentTest-answers.txt");
    }

    @After
    public void tearDown() throws Exception {
        context = null;
        resultParser = null;
        rightFileTest = null;
        studentFileTest = null;
    }

    @Test
    public void getRightAnswers() throws NoSuchFieldException, IllegalAccessException {
        Field rightFile = resultParser.getClass().getDeclaredField("rightFile");
        rightFile.setAccessible(true);
        rightFile.set(resultParser, rightFileTest);
        resultParser.parseData();

        List<String> actual = resultParser.getRightAnswers();
        List<String> expected = List.of("А","А","А","А","А","А","А","А","А","А");
        assertEquals(expected, actual);
    }

    @Test
    public void getStudentsAnswers() throws NoSuchFieldException, IllegalAccessException {
        Field studentFile = resultParser.getClass().getDeclaredField("studentFile");
        studentFile.setAccessible(true);
        studentFile.set(resultParser, studentFileTest);
        resultParser.parseData();

        List<String> actual = resultParser.getStudentsAnswers();
        List<String> expected = List.of("А","Б","В","А","А","В","А","Б","Г","А");
        assertEquals(expected, actual);
    }

    @Test
    public void parseData() throws NoSuchFieldException, IllegalAccessException {
        assertEquals(Collections.emptyList(), resultParser.getRightAnswers());
        assertEquals(Collections.emptyList(), resultParser.getStudentsAnswers());

        Field rightFile = resultParser.getClass().getDeclaredField("rightFile");
        Field studentFile = resultParser.getClass().getDeclaredField("studentFile");
        rightFile.setAccessible(true);
        studentFile.setAccessible(true);
        rightFile.set(resultParser, rightFileTest);
        studentFile.set(resultParser, studentFileTest);
        resultParser.parseData();

        List<String> expectedRight = List.of("А","А","А","А","А","А","А","А","А","А");
        List<String> expectedStudents = List.of("А","Б","В","А","А","В","А","Б","Г","А");
        assertEquals(expectedRight, resultParser.getRightAnswers());
        assertEquals(expectedStudents, resultParser.getStudentsAnswers());
    }
}