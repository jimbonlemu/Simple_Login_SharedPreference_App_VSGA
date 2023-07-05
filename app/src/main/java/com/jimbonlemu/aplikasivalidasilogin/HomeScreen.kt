package com.jimbonlemu.aplikasivalidasilogin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class HomeScreen : AppCompatActivity() {

    private lateinit var username : EditText
    private lateinit var email:EditText
    private lateinit var fullName:EditText
    private lateinit var campusName:EditText
    private lateinit var address : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        username = findViewById(R.id.usernameHomeController)
        email = findViewById(R.id.emailHomeController)
        fullName = findViewById(R.id.nameHomeController)
        campusName = findViewById(R.id.campusHomeController)
        address = findViewById(R.id.addressHomeController)
        setData()

    }

    private fun setData(){
        username.isEnabled = false
        email.isEnabled = false
        fullName.isEnabled = false
        campusName.isEnabled = false
        address.isEnabled=false

        username.setText(getUsername())
        email.setText(getEmail())
        fullName.setText(getFullName())
        campusName.setText(getCampusName())
        address.setText(getAddress())
    }

    private fun getUsername(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }

    private fun getEmail():String{
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", "") ?: ""
    }
    private fun getFullName():String{
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("fullName", "") ?: ""
    }
    private fun getCampusName():String{
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("campusName", "") ?: ""
    }
    private fun getAddress():String{
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("address", "") ?: ""
    }

}