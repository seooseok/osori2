package com.osori.cave.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.osori.cave.utils.Jackson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import spring.trace.configuration.EnableTrace
import spring.trace.web.TraceLogFilter


@EnableTrace(basePackages = arrayOf("com.osori.cave.domain"), proxyTargetClass = true)
@Configuration
class WebConfiguration {

    @Bean
    fun objectMapper(): ObjectMapper {
        return Jackson.getMapper()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedMethods("PUT", "DELETE", "OPTION", "GET", "POST")
            }
        }
    }

    @Bean
    fun traceLogFilter(): TraceLogFilter {
        return TraceLogFilter()
    }

    @Bean
    fun logFilter(): CommonsRequestLoggingFilter =
            CommonsRequestLoggingFilter().apply {
                setIncludeQueryString(true)
                setIncludePayload(true)
                isIncludeHeaders = false
                setBeforeMessagePrefix("[REQ]")
                setAfterMessagePrefix("[RES]")
            }
}
