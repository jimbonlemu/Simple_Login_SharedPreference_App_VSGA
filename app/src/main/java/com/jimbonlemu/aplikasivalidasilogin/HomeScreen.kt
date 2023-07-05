package com.jimbonlemu.aplikasivalidasilogin

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import com.jimbonlemu.aplikasivalidasilogin.Get.GetToast

class HomeScreen : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var fullName: EditText
    private lateinit var campusName: EditText
    private lateinit var address: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        username = findViewById(R.id.usernameHomeController)
        email = findViewById(R.id.emailHomeController)
        fullName = findViewById(R.id.nameHomeController)
        campusName = findViewById(R.id.campusHomeController)
        address = findViewById(R.id.addressHomeController)

        supportActionBar?.apply {
            title = "Halaman Home"
        }

        setData()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                willPopScope()
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                showLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLogoutDialog() {
        val options = arrayOf("Logout", "Logout & Hapus Data")

        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> logout()
                    1 -> logoutAndClearData()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun logout() {
        GetToast("Logout berhasil")
        Get.offAll(this, LoginScreen::class.java)
    }

    private fun logoutAndClearData() {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        GetToast("Logout berhasil dan data dihapus")
        Get.offAll(this, LoginScreen::class.java)
    }


    private fun setData() {
        username.isEnabled = false
        email.isEnabled = false
        fullName.isEnabled = false
        campusName.isEnabled = false
        address.isEnabled = false

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

    private fun getEmail(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", "") ?: ""
    }

    private fun getFullName(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("fullName", "") ?: ""
    }

    private fun getCampusName(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("campusName", "") ?: ""
    }

    private fun getAddress(): String {
        val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        return sharedPreferences.getString("address", "") ?: ""
    }

    private fun willPopScope() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Peringatan!")
        dialogBuilder.setMessage("Apakah anda yakin ingin keluar dari aplikasi ?")
        dialogBuilder.setPositiveButton("Yes") { _, _ ->
            finish()
        }
        dialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

}