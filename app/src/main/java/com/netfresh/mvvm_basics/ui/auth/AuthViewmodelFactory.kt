package com.netfresh.mvvm_basics.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.netfresh.mvvm_basics.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewmodelFactory(
    private val repository: UserRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}