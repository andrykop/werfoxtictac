package com.werfox.ticzero

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

class start : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        single.setOnClickListener {
            val intent=Intent(this, ChooseActivity::class.java)
            startActivity(intent)
        }
    }
}
