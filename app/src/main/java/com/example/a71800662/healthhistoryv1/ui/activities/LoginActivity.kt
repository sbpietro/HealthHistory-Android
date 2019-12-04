package com.example.a71800662.healthhistoryv1.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a71800662.healthhistoryv1.R
import com.example.a71800662.healthhistoryv1.models.Account
import com.example.a71800662.healthhistoryv1.services.RetrofitInitializer
import com.example.a71800662.healthhistoryv1.ui.activities.CadastroActivity
import com.example.a71800662.healthhistoryv1.ui.activities.ForgotPasswordActivity
import com.example.a71800662.healthhistoryv1.ui.activities.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            auth()
        }

        cadastrar.setOnClickListener{
            goToCadastro()
        }

        esqueceuSenha.setOnClickListener{
            goToPasswordRecovery()
        }
    }

    fun goToPasswordRecovery(){
        var intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
        //finish()
    }

    fun goToCadastro(){
        var intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
        //finish()
    }

    fun goToHomePage(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun auth() {

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = inputUser.text.toString()
        account.password = inputPwd.text.toString()

        var call = s.auth(account)

        call.enqueue(object : Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                response?.let {

                    if (it.code() == 200) {
                        Toast.makeText(this@LoginActivity, "Autenticado", Toast.LENGTH_LONG).show()
                        goToHomePage()
                    } else {
                        Toast.makeText(this@LoginActivity, "Usuário ou senha inválido", Toast.LENGTH_LONG).show()
                    }

                }


            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@LoginActivity, "Ocorreu um erro na aplicação. Tente mais tarde", Toast.LENGTH_LONG).show()
            }

        })


    }
}
