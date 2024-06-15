package org.d3if0088.miniproject1.model

data class OpStatus(
    var status: String,
    var message: String?,
    var data: List<Comic> = emptyList()
)