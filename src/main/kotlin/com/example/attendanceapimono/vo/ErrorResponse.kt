package com.example.attendanceapimono.vo

import com.fasterxml.jackson.annotation.JsonInclude

data class ErrorResponse(
    val errorMsg: String,
    @JsonInclude(JsonInclude.Include.NON_NULL) val errorCode: Int? = null,
)