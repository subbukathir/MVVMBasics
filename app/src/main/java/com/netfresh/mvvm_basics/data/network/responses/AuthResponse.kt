package com.netfresh.mvvm_basics.data.network.responses

import com.netfresh.mvvm_basics.data.db.entities.User

data class AuthResponse(
    var isSuccessful: Boolean?,
    var message:String?,
    var user:User?
)