package com.jimbonlemu.aplikasivalidasilogin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.jimbonlemu.aplikasivalidasilogin.Get.GetToast
import android.content.SharedPreferences
import android.preference.PreferenceManager


class RegisterScreen : AppCompatActivity() {

    private lateinit var regUsername: EditText
    private lateinit var regPassword: EditText
    private lateinit var regEmail: EditText
    private lateinit var regFullName: EditText
    private lateinit var regCampusName: EditText
    private lateinit var regAddress: EditText
    private lateinit var regBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_screen)

        customActionBar()

        regUsername = findViewById(R.id.usernameRegController)
        regPassword = findViewById(R.id.passwordRegController)
        regEmail = findViewById(R.id.emailRegController)
        regFullName = findViewById(R.id.nameRegController)
        regCampusName = findViewById(R.id.campusRegController)
        regAddress = findViewById(R.id.addressRegController)
        regBtn = findViewById(R.id.btnRegRegister)

        regBtn.setOnClickListener {
            onSubmitRegister()
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        Get.back(this)
        return true
    }

    private fun customActionBar() {
        supportActionBar?.apply {
            title = "Halaman Register"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun onSubmitRegister() {
        var validator = true

        if (regUsername.text.isNullOrEmpty()) {
            GetToast("Username wajib diisi")
            validator = false
        } else if (regPassword.text.isNullOrEmpty()) {
            GetToast("Password wajib diisi")
            validator = false
        } else if (regEmail.text.isNullOrEmpty()) {
            GetToast("Email wajib diisi")
            validator = false
        } else if (regFullName.text.isNullOrEmpty()) {
            GetToast("Nama Lengkap wajib diisi")
            validator = false
        } else if (regCampusName.text.isNullOrEmpty()) {
            GetToast("Nama Kampus Wajib diisi")
            validator = false
        } else if (regAddress.text.isNullOrEmpty()) {
            GetToast("Alamat wajib diisi")
            validator = false
        }

        if (validator) {
            saveHandler(
                username = regUsername.text.toString(),
                password = regPassword.text.toString(),
                email = regEmail.text.toString(),
                fullName = regFullName.text.toString(),
                campusName = regCampusName.text.toString(),
                address = regAddress.text.toString()
            )
            GetToast("Data anda berhasil tersimpan silahkan login ")
            Get.back(this)

        } else {
            GetToast("Harap lengkapi seluruh data")
        }


    }

    private fun saveHandler(
        username: String,
        password: String,
        email: String,
        fullName: String,
        campusName: String,
        address: String
    ) {
        val sharedPreference = getSharedPreferences("data", Context.MODE_PRIVATE)
        var saver = sharedPreference.edit()
        saver.putString("username", username)
        saver.putString("password", password)
        saver.putString("email", email)
        saver.putString("fullName", fullName)
        saver.putString("campusName", campusName)
        saver.putString("address", address)
        saver.commit()


    }

}