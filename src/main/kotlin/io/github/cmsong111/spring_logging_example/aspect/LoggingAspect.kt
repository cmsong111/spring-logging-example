package io.github.cmsong111.spring_logging_example.aspect

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    @Around("@within(org.springframework.web.bind.annotation.RestController) && within(io.github.cmsong111.spring_logging_example..*)")
    fun logControllerExecution(joinPoint: ProceedingJoinPoint): Any? {
        val className = joinPoint.target.javaClass.simpleName
        val methodName = joinPoint.signature.name
        val start = System.currentTimeMillis()

        return try {
            val result = joinPoint.proceed()
            val duration = System.currentTimeMillis() - start
            logger.info { "Called $className.$methodName - took ${duration}ms" }
            result
        } catch (e: Throwable) {
            val duration = System.currentTimeMillis() - start
            logger.warn { "Failed $className.$methodName - took ${duration}ms" }
            throw e
        }
    }

    companion object {
        private val logger: KLogger = KotlinLogging.logger {}
    }
}
