package io.github.cmsong111.spring_logging_example.common

sealed class DomainException(
    override val message: String,
    open val code: String,
) : RuntimeException(message)

data class NotFoundException(
    override val message: String = "Resource not found"
) : DomainException(message, code = "NOT_FOUND")

data class InvalidInputException(
    override val message: String = "Invalid input"
) : DomainException(message, code = "INVALID_INPUT")

data class UnauthorizedException(
    override val message: String = "Unauthorized"
) : DomainException(message, code = "UNAUTHORIZED")

data class ForbiddenException(
    override val message: String = "Forbidden"
) : DomainException(message, code = "FORBIDDEN")

data class ConflictException(
    override val message: String = "Conflict occurred"
) : DomainException(message, code = "CONFLICT")

data class InternalServerErrorException(
    override val message: String = "Internal server error"
) : DomainException(message, code = "INTERNAL_SERVER_ERROR")
