package com.example.a71800662.healthhistoryv1.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a71800662.healthhistoryv1.R
import com.example.a71800662.healthhistoryv1.activities.LoginActivity
import com.example.a71800662.healthhistoryv1.models.Account
import com.example.a71800662.healthhistoryv1.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro.inputPwd
import kotlinx.android.synthetic.main.activity_cadastro.inputUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btnCadastrar.setOnClickListener{
            signUp()
        }
    }

    fun goToLogin(){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun signUp(){
        var service = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.name = inputName.text.toString()
        account.email = inputUser.text.toString()
        account.password = inputPwd.text.toString()

        var call = service.signUp(account)

        call.enqueue(object : Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                response?.let {

                    if (it.code() == 200) {
                        Toast.makeText(this@CadastroActivity, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show()
                        goToLogin()

                    } else {
                        Toast.makeText(this@CadastroActivity, "Dados inválidos", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@CadastroActivity, "Ocorreu um erro na aplicação. Tente mais tarde", Toast.LENGTH_LONG).show()
            }

        })
    }
}
