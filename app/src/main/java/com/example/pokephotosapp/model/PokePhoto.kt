package com.example.pokephotosapp.model

import kotlinx.serialization.Serializable

@Serializable
data class PokePhoto (
    val id:String,
    val url:String,
    val width: Int,
    val height:Int
)