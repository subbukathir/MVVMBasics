package com.netfresh.mvvm_basics.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.netfresh.mvvm_basics.data.repository.UserRepository
import com.netfresh.mvvm_basics.util.ApiException
import com.netfresh.mvvm_basics.util.Coroutines
import com.netfresh.mvvm_basics.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
):ViewModel() {
    var email:String?=null
    var password: String? = null

    var authListener: AuthListener? =null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginClicked(view :View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            //show failure
            authListener?.onFailure("Input should not be empty")
            return
        }

        //Handle success
        Coroutines.main {
            try {
                val loginResponse = repository.userLogin(email!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(loginResponse.user!!)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailure(loginResponse.message!!)
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }

    }
}