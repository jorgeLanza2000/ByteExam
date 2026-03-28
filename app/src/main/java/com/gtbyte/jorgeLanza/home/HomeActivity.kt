package com.gtbyte.jorgeLanza.home

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.gtbyte.jorgeLanza.R


class HomeActivity : ComponentActivity() {

    private var dialogShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onStart() {
        super.onStart()

        if (!dialogShown) {
            val username = intent.getStringExtra("USERNAME") ?: ""
            showUsernameDialog(username)
            dialogShown = true
        }
    }

    private fun showUsernameDialog(username: String) {

        val view = layoutInflater.inflate(R.layout.dialog_username, null)
        val message = view.findViewById<TextView>(R.id.tvMessage)
        val btnContinuar = view.findViewById<TextView>(R.id.btnContinue)

        message.text = "Nos alegra que hayas vuelo $username, esperamos que tu experiencia sea lo mejor"
        btnContinuar.text = "Continuar"

        val dialog = MaterialAlertDialogBuilder(this)
            .setView(view)
            .create()

        btnContinuar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
