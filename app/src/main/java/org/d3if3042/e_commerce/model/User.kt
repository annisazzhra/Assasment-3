package org.d3if3042.e_commerce.model

import retrofit2.http.Url

data class User(
    val name: String = "",
    val email: String = "",
    val photoUrl: String = ""
)