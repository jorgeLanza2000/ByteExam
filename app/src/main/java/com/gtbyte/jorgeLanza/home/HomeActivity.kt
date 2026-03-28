package com.gtbyte.jorgeLanza.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.gtbyte.jorgeLanza.R
import com.gtbyte.jorgeLanza.auth.LoginActivity
import com.gtbyte.jorgeLanza.home.api.HomeViewModel
import com.gtbyte.jorgeLanza.home.components.DonutListFragment


class HomeActivity : AppCompatActivity() {
    private var dialogShown = false
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnNext = findViewById<ImageButton>(R.id.btnNext)

        btnNext.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, DonutListFragment())
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.fetchDonuts()

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
