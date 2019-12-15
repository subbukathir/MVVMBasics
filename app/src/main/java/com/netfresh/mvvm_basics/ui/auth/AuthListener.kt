package com.netfresh.mvvm_basics.ui.auth

import androidx.lifecycle.LiveData
import com.netfresh.mvvm_basics.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)
}