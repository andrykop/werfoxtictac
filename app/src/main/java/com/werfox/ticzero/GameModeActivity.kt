package com.werfox.ticzero

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_modecheck.*

class GameModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modecheck)
        easy1.setOnClickListener {
            val intent= Intent(this, SecondGamaActivity::class.java)
            startActivity(intent)
        }
        medium1.setOnClickListener {
            val intent= Intent(this, ThierdActivity::class.java)
            startActivity(intent)
        }
        hard1.setOnClickListener {
            val intent= Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
    }
}
