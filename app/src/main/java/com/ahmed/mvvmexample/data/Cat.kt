package com.ahmed.mvvmexample.data

import kotlinx.serialization.SerialName

data class Cat(
    @SerialName("createdAt")
    val createdAt: String = "",
    @SerialName("_id")
    val id: String,
    @SerialName("owner")
    val owner: String = "",
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("updatedAt")
    val updatedAt: String = ""
)