package com.aifunny.exceptions

import com.aifunny.model.error.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ResponseEntityExceptionHandler {

    @ExceptionHandler
    fun handleNullPointers(ex: NullPointerException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message.orEmpty()
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
}