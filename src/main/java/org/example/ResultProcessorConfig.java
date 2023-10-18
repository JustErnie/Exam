package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("example")
@Import(PropertiesConfiguration.class)
public class ResultProcessorConfig {
}
