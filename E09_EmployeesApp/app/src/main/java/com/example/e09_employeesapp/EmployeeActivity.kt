package com.example.e09_employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_employee.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val employeeString = bundle!!.getString("employee")
            val employee = JSONObject(employeeString)
            val name = employee["firstName"].toString()+ " " + employee["lastName"].toString()
            val title = employee["title"].toString()
            val department = employee["department"].toString()
            val email = "Email: " + employee["email"].toString()
            val phone = "Phone: " + employee["phone"].toString()
            val image = employee["image"].toString()

            // employee image
            Glide
                .with(this)
                .load(image)
                .circleCrop()
                .apply(RequestOptions().override(500, 500))
                .placeholder(R.drawable.ic_android_black_24dp)
                .into(infoPortraitImageView)
            // employee last name and first name
            infoNameTextView.text = name
            // employee title
            infoTitleTextView.text = title
            // employee email
            infoEmailTextView.text = email
            // employee phone
            infoPhoneTextView.text = phone
            // employee department
            infoDepartmentTextView.text = department


        }

    }
}