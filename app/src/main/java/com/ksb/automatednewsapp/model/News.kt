package com.ksb.automatednewsapp.model


import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("descp")
    val descp: List<String>,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("title")
    val title: List<String>,
)