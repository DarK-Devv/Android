package com.example.e09_employeesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.employee_item.view.*
import org.json.JSONArray
import org.json.JSONObject

// Employees Adapter, used in RecyclerView in MainActivity
class EmployeesAdapter(private val employees: JSONArray)
    : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {
    // Create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.employee_item, parent, false)
        return ViewHolder(view)
    }

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val titleTextView: TextView = view.titleTextView
        val emailTextView: TextView = view.emailTextView
        val phoneTextView: TextView = view.phoneTextView
        val departmentTextView: TextView = view.departmentTextView
        val employeeImageView: ImageView = view.employeeImageView
        // Add a item click listener
        init {
            itemView.setOnClickListener {
                // create an explicit intent
                val intent = Intent(view.context, EmployeeActivity::class.java)
                // add selected employee JSON as a string data
                intent.putExtra("employee",employees[adapterPosition].toString())
                // start a new activity
                view.context.startActivity(intent)
            }
        }
    }


    // Bind data to UI View Holder
    override fun onBindViewHolder(
        holder: EmployeesAdapter.ViewHolder,
        position: Int)
    {
        // employee to bind UI
        val employee: JSONObject = employees.getJSONObject(position)
        val url = employee["image"].toString()
        // employee image
        Glide
            .with(holder.employeeImageView.context)
            .load(url)
            .circleCrop()
            .apply(RequestOptions().override(250, 250))
            .placeholder(R.drawable.ic_android_black_24dp)
            .into(holder.employeeImageView)
        // employee last name and first name
        holder.nameTextView.text = employee["lastName"].toString() + " " + employee["firstName"].toString()
        // employee title
        holder.titleTextView.text = employee["title"].toString()
        // employee email
        holder.emailTextView.text = employee["email"].toString()
        // employee phone
        holder.phoneTextView.text = employee["phone"].toString()
        // employee department
        holder.departmentTextView.text = employee["department"].toString()

    }
    // Return item count in employees
    override fun getItemCount(): Int = employees.length()
}

