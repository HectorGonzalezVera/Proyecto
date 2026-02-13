package com.practicas.damappmoodle.viewmodel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practicas.damappmoodle.R
import com.practicas.damappmoodle.data.Course
import com.practicas.damappmoodle.ui.CourseDetailActivity

class CourseAdapter(
    private val courses: List<Course>,
    private val token: String
) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvCourseName)
        val tvShort: TextView = view.findViewById(R.id.tvCourseShort)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = courses[position]

        holder.tvName.text = course.fullname
        holder.tvShort.text = course.shortname

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, CourseDetailActivity::class.java)

            intent.putExtra("id", course.id)
            intent.putExtra("token", token)

            context.startActivity(intent)
        }
    }
}
