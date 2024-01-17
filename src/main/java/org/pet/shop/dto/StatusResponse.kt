package org.pet.shop.dto

enum class StatusResponse constructor(private val value : String) {
    OK("OK"),
    NOT_FOUND("NOT_FOUND"),
    BAD_REQUEST("BAD_REQUEST")
}