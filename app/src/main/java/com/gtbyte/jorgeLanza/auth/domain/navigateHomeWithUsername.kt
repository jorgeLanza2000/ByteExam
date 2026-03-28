package com.gtbyte.jorgeLanza.auth.domain

import android.content.Context
import android.content.Intent
import com.gtbyte.jorgeLanza.home.HomeActivity

fun navigateHomeWithUsername (context: Context, username: String) {
    val intent = Intent(context, HomeActivity::class.java)
    intent.putExtra("USERNAME", username)
    context.startActivity(intent)
}