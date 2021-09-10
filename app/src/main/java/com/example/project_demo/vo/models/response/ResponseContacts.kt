package com.example.project_demo.vo.models.response

data class ResponseContacts(
    val `data`: List<DataContacts>
)

data class DataContacts(
    val id: Int,
    val link: String,
    val type: String
)