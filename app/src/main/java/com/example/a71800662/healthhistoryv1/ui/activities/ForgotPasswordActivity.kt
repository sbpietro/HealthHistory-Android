package com.example.a71800662.healthhistoryv1.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a71800662.healthhistoryv1.R
import com.example.a71800662.healthhistoryv1.activities.LoginActivity
import com.example.a71800662.healthhistoryv1.models.Account
import com.example.a71800662.healthhistoryv1.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.btnLogin
import kotlinx.android.synthetic.main.activity_forgot_password.inputUser
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnLogin.setOnClickListener{
            recoverPassword()
        }
    }

    fun goToLogin(){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun recoverPassword(){
        var service = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = inputUser.text.toString()

        var call = service.forgotPassword(account)

        call.enqueue(object : Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                response?.let {

                    if (it.code() == 204) {
                        Toast.makeText(this@ForgotPasswordActivity, "Nova senha enviada para o e-mail cadastrado", Toast.LENGTH_LONG).show()
                        goToLogin()
                    } else {
                        Toast.makeText(this@ForgotPasswordActivity, "Usuário inexistente", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@ForgotPasswordActivity,
                    "Ocorreu um erro na aplicação. Tente mais tarde", Toast.LENGTH_LONG).show()
            }

        })
    }
}
