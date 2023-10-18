package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryResultProcessor implements ResultProcessor {

    ResultParser resultParser;

    @Value("#{'${pointsForTasks}'.split(',')}")
    private List<Integer> taskPoints;

    public InMemoryResultProcessor(ResultParser resultParser) {
        this.resultParser = resultParser;
    }

    @Override
    public void getResult() {
        int result = 0;
        int maxPoints = 0;
        List<Integer> tasksWithMistakes = new ArrayList<>();

        List<String> rightAnswers = resultParser.getRightAnswers();
        List<String> studentsAnswers = resultParser.getStudentsAnswers();

        for (int i = 0; i < rightAnswers.size(); i++) {
            maxPoints += taskPoints.get(i);
            if (rightAnswers.get(i).equals(studentsAnswers.get(i))) {
                result += taskPoints.get(i);
            } else tasksWithMistakes.add(i + 1);
        }

        System.out.printf("Получено %d баллов из %d возможных\n", result, maxPoints);
        if (result != maxPoints) {
        System.out.println("Задания в которых были допущены ошибки: " +
                tasksWithMistakes.toString().replaceAll("[\\[\\]]", ""));
        }
    }
}
