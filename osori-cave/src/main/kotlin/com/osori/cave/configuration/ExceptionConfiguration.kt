package com.osori.cave.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(annotations = [RestController::class])
class ExceptionConfiguration @Autowired constructor(private val env: Environment) {

    @ExceptionHandler(value = [RuntimeException::class])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleGlobalException(e: RuntimeException, request: HttpServletRequest): Map<String, Any> {
        val requestAttributes = ServletRequestAttributes(request)
        val errorAttributes = DefaultErrorAttributes().getErrorAttributes(requestAttributes, isIncludeStackTrace())
        errorAttributes.put("status", 500)
        return errorAttributes
    }

    @ExceptionHandler(value = [IllegalValidateException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleValidateException(e: IllegalValidateException, request: HttpServletRequest): Map<String, Any> {
        val requestAttributes = ServletRequestAttributes(request)
        val errorAttributes = DefaultErrorAttributes().getErrorAttributes(requestAttributes, isIncludeStackTrace())
        errorAttributes.put("status", 400)

        e.fieldError?.let {
            errorAttributes.put("error", "field=${it.field} arguments=[${it.arguments?.joinToString(",")}]")
            if (it.defaultMessage.isNullOrBlank().not())
                errorAttributes.put("message", it.defaultMessage)
        }

        return errorAttributes
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFoundException(e: NotFoundException, request: HttpServletRequest): Map<String, Any> {
        val requestAttributes = ServletRequestAttributes(request)
        val errorAttributes = DefaultErrorAttributes().getErrorAttributes(requestAttributes, isIncludeStackTrace())
        errorAttributes.put("status", 404)
        errorAttributes.put("message", e.message)
        return errorAttributes
    }

    private fun isIncludeStackTrace(): Boolean {
        val profiles = env.activeProfiles
        return (profiles.isEmpty() || profiles.contains("dev"))
    }
}

class IllegalValidateException(message: String = "Validation failed") : RuntimeException(message) {
    var fieldError: FieldError? = null

    constructor(message: String = "Validation failed", fieldError: FieldError? = null) : this(message) {
        this.fieldError = fieldError
    }
}

class NotFoundException(resourceName: String) : RuntimeException(resourceName + " not found.")
