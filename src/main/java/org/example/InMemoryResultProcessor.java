package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryResultProcessor implements ResultProcessor {

    ResultParser resultParser;

    public InMemoryResultProcessor(ResultParser resultParser) {
        this.resultParser = resultParser;
    }

    @Override
    public int getResult() {
        int result = 0;
        List<String> rightAnswers = resultParser.getRightAnswers();
        List<String> studentsAnswers = resultParser.getStudentsAnswers();

        for (int i = 0; i < rightAnswers.size(); i++) {

        }

        return result;
    }
}
