package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class InMemoryResultParser implements ResultParser {

    @Value("classpath:Right-answers.txt")
    private Resource rightFile;

    @Value("classpath:Student1-answers.txt")
    private Resource studentFile;

    private List<String> rightAnswers;
    private List<String> studentsAnswers;

    public InMemoryResultParser(List<String> rightAnswers, List<String> studentsAnswers) {
        this.rightAnswers = rightAnswers;
        this.studentsAnswers = studentsAnswers;
    }

    public List<String> getRightAnswers() {
        return rightAnswers;
    }

    public List<String> getStudentsAnswers() {
        return studentsAnswers;
    }

    private void parseData() {
        try {
            rightAnswers = fileToList(rightFile.getFile());
            studentsAnswers = fileToList(studentFile.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> fileToList(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\d+ - ([АБВГ])");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    String answerLetter = matcher.group(1);
                    list.add(answerLetter);
                } else list.add(null);
            }
            return list;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
