package io.mikael.poc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.filter.CommonsRequestLoggingFilter

@SpringBootApplication
open class Application {

    @Bean
    open fun logFilter() = CommonsRequestLoggingFilter().apply {
        setIncludeQueryString(true);
        setIncludePayload(true);
        setMaxPayloadLength(20480);
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
