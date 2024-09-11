package com.xcheko51x.googlesheetapp

data class PostResponse(
    val rows: List<List<String>>,
    val sheet: String
)
