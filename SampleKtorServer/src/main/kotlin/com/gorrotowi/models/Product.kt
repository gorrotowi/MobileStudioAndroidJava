package com.gorrotowi.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val description: String,
    val category: String? = "Daily grocery"
)