package com.netfresh.mvvm_basics.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.netfresh.mvvm_basics.R
import com.netfresh.mvvm_basics.data.db.AppDatabase
import com.netfresh.mvvm_basics.data.db.entities.User
import com.netfresh.mvvm_basics.data.network.MyApi
import com.netfresh.mvvm_basics.data.network.NetworkConnectionInterceptor
import com.netfresh.mvvm_basics.data.repository.UserRepository
import com.netfresh.mvvm_basics.databinding.ActivitySignInBinding
import com.netfresh.mvvm_basics.ui.home.HomeActivity
import com.netfresh.mvvm_basics.util.hide
import com.netfresh.mvvm_basics.util.show
import com.netfresh.mvvm_basics.util.snackbar
import com.netfresh.mvvm_basics.util.toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignInActivity : AppCompatActivity() , AuthListener, KodeinAware{

    override val kodein: Kodein by kodein()
    private val factory: AuthViewmodelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding:ActivitySignInBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        dataBinding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {
            if(it!=null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(user: User) {
        progressBar.hide()
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        root_view.snackbar("Login failed : $message")
    }
}
