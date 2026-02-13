package com.practicas.damappmoodle.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practicas.damappmoodle.R
import com.practicas.damappmoodle.data.TokenResponse
import com.practicas.damappmoodle.network.MoodleApi
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private val BASE_URL = "http://172.17.57.46/moodle/"

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var api: MoodleApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 🔹 Referencias correctas
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MoodleApi::class.java)

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        api.login(username, password, "damapp")
            .enqueue(object : Callback<TokenResponse> {

                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>
                ) {
                    if (response.isSuccessful) {

                        val token = response.body()?.token
                        val sharedPref = getSharedPreferences("DAM_PREF", MODE_PRIVATE)
                        sharedPref.edit().putString("TOKEN", token).apply()

                        if (!token.isNullOrEmpty()) {

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("TOKEN", token)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Credenciales incorrectas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error en el servidor",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error de conexión",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
