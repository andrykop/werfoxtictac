package com.werfox.ticzero

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chooseleve.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooseleve)
        easy.setOnClickListener {
            val intent= Intent(this, GameModeActivity::class.java)
            startActivity(intent)
        }
        hard.setOnClickListener {
            val intent= Intent(this, GameForBothActivity::class.java)
            startActivity(intent)
        }


    }
}
