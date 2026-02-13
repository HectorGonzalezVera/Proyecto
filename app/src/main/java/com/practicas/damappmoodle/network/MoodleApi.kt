package com.practicas.damappmoodle.network

import com.practicas.damappmoodle.data.Course
import com.practicas.damappmoodle.data.SiteInfo
import com.practicas.damappmoodle.data.TokenResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoodleApi {

    @GET("webservice/rest/server.php")
    fun getSiteInfo(
        @Query("wstoken") token: String,
        @Query("wsfunction") function: String = "core_webservice_get_site_info",
        @Query("moodlewsrestformat") format: String = "json"
    ): Call<SiteInfo>

    @GET("webservice/rest/server.php")
    fun getUserCourses(
        @Query("wstoken") token: String,
        @Query("wsfunction") function: String = "core_enrol_get_users_courses",
        @Query("userid") userId: Int,
        @Query("moodlewsrestformat") format: String = "json"
    ): Call<List<Course>>

    @GET("login/token.php")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("service") service: String
    ): Call<TokenResponse>

}
