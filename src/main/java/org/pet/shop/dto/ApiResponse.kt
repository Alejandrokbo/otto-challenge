package org.pet.shop.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity

data class ApiResponse (
    @JsonProperty("message")
    val message: String,

    @JsonProperty("status")
    val status: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    val data: Any? = null
)

fun response(apiResponse: ApiResponse): ResponseEntity<ApiResponse> {
    return when (apiResponse.status) {
        StatusResponse.OK.name -> {
            ResponseEntity.ok(apiResponse)
        }
        StatusResponse.NOT_FOUND.name -> {
            ResponseEntity<ApiResponse>(apiResponse, org.springframework.http.HttpStatus.NOT_FOUND)
        }
        StatusResponse.BAD_REQUEST.name -> {
            ResponseEntity.badRequest().body(apiResponse)
        }
        else -> {
            ResponseEntity.badRequest().body(apiResponse)
        }
    }
}