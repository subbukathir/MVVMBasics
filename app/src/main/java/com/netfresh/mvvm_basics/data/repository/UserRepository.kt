package com.netfresh.mvvm_basics.data.repository

import com.netfresh.mvvm_basics.data.db.AppDatabase
import com.netfresh.mvvm_basics.data.db.entities.User
import com.netfresh.mvvm_basics.data.network.MyApi
import com.netfresh.mvvm_basics.data.network.responses.AuthResponse
import com.netfresh.mvvm_basics.data.network.SafeApiRequest

class UserRepository(
    val api:MyApi,
    val db:AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email:String, password:String) : AuthResponse{
        return apiRequest {
            api.userLogin(email, password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}