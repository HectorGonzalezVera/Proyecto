package com.practicas.damappmoodle.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.practicas.damappmoodle.R

class CourseDetailActivity : AppCompatActivity() {

    private val BASE_URL = "http://172.17.57.46/moodle/"
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        webView = findViewById(R.id.webView)

        val courseId = intent.getIntExtra("id", 0)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // URL directa al curso
        val url = "${BASE_URL}course/view.php?id=$courseId"

        webView.loadUrl(url)
    }
}
