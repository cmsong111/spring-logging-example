package io.github.cmsong111.spring_logging_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringLoggingExampleApplication

fun main(args: Array<String>) {
	runApplication<SpringLoggingExampleApplication>(*args)
}
