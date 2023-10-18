package org.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:Answers.properties")
public class PropertiesConfiguration {
}
