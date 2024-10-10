package com.sunaa.callmedady.protojson

import kotlinx.serialization.Serializable

@Serializable
data class DadyDetails(
    val profession : String,
    val nameOfProfessional : String,
    val liking : Boolean
)
