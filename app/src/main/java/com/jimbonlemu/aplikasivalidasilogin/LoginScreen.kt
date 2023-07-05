package com.jimbonlemu.aplikasivalidasilogin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.jimbonlemu.aplikasivalidasilogin.Get.GetToast

class LoginScreen : AppCompatActivity() {

    private lateinit var usernameController:EditText
    private lateinit var passwordController:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        usernameController = findViewById(R.id.usernameController)
        passwordController = findViewById(R.id.passwordController)

        supportActionBar?.apply {
            title = "Halaman Login"
        }

        findViewById<MaterialButton>(R.id.btnRegister).setOnClickListener {
            Get.to(this,RegisterScreen::class.java)
        }

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            onLogin()
        }
    }
    private fun onLogin() {
        val savedUsername = getSavedUsername()
        val savedPassword = getSavedPassword()

        val username = usernameController.text.toString().trim()
        val password = passwordController.text.toString().trim()

        if (username == savedUsername && password == savedPassword) {
            setLoggedIn(true)
            GetToast("Login berhasil")
            Get.off(this,HomeScreen::class.java)

        } else {
            GetToast("Username atau password salah")
        }
    }

    private fun getSavedUsername(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }

    private fun getSavedPassword(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("password", "") ?: ""
    }

    private fun setLoggedIn(loggedIn: Boolean) {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("loggedIn", loggedIn)
        editor.apply()
    }

    private fun isLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("loggedIn", false)
    }
}