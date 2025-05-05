package io.github.cmsong111.spring_logging_example.config


import io.github.cmsong111.spring_logging_example.common.NotFoundException
import io.github.cmsong111.spring_logging_example.filter.LoggingFilter
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(
            HttpStatus.BAD_REQUEST
        ).body(
            ErrorResponse(
                code = "INVALID_ARGUMENT",
                message = ex.message ?: "Invalid argument",
            )
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        ex: NotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(
            HttpStatus.NOT_FOUND
        ).body(
            ErrorResponse(
                code = ex.code,
                message = ex.message,
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        ex: Exception,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(
            HttpStatus.INTERNAL_SERVER_ERROR
        ).body(
            ErrorResponse(
                code = "INTERNAL_SERVER_ERROR",
                message = ex.message ?: "An unexpected error occurred",
            )
        )
    }

    data class ErrorResponse(
        val code: String,
        val message: String,
    )
}

