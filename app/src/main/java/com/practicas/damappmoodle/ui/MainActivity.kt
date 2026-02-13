package com.practicas.damappmoodle.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicas.damappmoodle.R
import com.practicas.damappmoodle.data.Course
import com.practicas.damappmoodle.data.SiteInfo
import com.practicas.damappmoodle.network.MoodleApi
import com.practicas.damappmoodle.viewmodel.CourseAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "http://172.17.57.46/moodle/"

    private lateinit var recycler: RecyclerView
    private lateinit var tvWelcome: TextView
    private lateinit var btnLogout: ImageView
    private lateinit var api: MoodleApi
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        tvWelcome = findViewById(R.id.tvWelcome)
        btnLogout = findViewById(R.id.btnLogout)

        recycler.layoutManager = LinearLayoutManager(this)

        // 🔥 Recibir token
        token = intent.getStringExtra("TOKEN") ?: ""

        if (token.isEmpty()) {
            cerrarSesion()
            return
        }

        Log.d("TOKEN_DEBUG", token)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MoodleApi::class.java)

        btnLogout.setOnClickListener {
            cerrarSesion()
        }

        cargarUsuario()
    }

    // =============================
    // 🔹 CARGAR INFORMACIÓN USUARIO
    // =============================
    private fun cargarUsuario() {

        api.getSiteInfo(token).enqueue(object : Callback<SiteInfo> {

            override fun onResponse(
                call: Call<SiteInfo>,
                response: Response<SiteInfo>
            ) {

                if (response.isSuccessful) {

                    val site = response.body()

                    if (site != null) {
                        tvWelcome.text = site.fullname
                        cargarCursos(site.userid)
                    } else {
                        tokenInvalido()
                    }

                } else {
                    tokenInvalido()
                }
            }

            override fun onFailure(call: Call<SiteInfo>, t: Throwable) {
                Log.e("ERROR_USER", t.message ?: "Error")
                Toast.makeText(
                    this@MainActivity,
                    "Error de conexión",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    // =============================
    // 🔹 CARGAR CURSOS
    // =============================
    private fun cargarCursos(userId: Int) {

        api.getUserCourses(
            token = token,
            userId = userId
        ).enqueue(object : Callback<List<Course>> {

            override fun onResponse(
                call: Call<List<Course>>,
                response: Response<List<Course>>
            ) {

                if (response.isSuccessful) {

                    val courses = response.body() ?: emptyList()
                    recycler.adapter = CourseAdapter(courses, token)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error cargando cursos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.e("ERROR_COURSES", t.message ?: "Error")
            }
        })
    }

    // =============================
    // 🔹 TOKEN INVÁLIDO
    // =============================
    private fun tokenInvalido() {
        Toast.makeText(
            this,
            "Sesión expirada",
            Toast.LENGTH_SHORT
        ).show()
        cerrarSesion()
    }

    // 🔹 CERRAR SESIÓN
    private fun cerrarSesion() {

        val sharedPref = getSharedPreferences("DAM_PREF", MODE_PRIVATE)
        sharedPref.edit().clear().apply()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
