package io.github.cmsong111.spring_logging_example.config

import io.github.cmsong111.spring_logging_example.filter.LoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {
    /**
     * 해당 프로젝트에서는 Spring Security를 사용하지 않기 때문에
     * SecurityFilterChain을 사용하지 않고 LoggingFilter를 직접 등록합니다.
     */
    @Bean
    fun loggingFilter(): FilterRegistrationBean<LoggingFilter> {
        val registrationBean = FilterRegistrationBean<LoggingFilter>()
        registrationBean.filter = LoggingFilter()
        registrationBean.addUrlPatterns("/*")
        registrationBean.order = 1
        return registrationBean
    }
}
