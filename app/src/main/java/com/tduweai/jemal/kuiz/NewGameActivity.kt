package com.tduweai.jemal.kuiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_game.*

class NewGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)
        oyuna_bashla.setOnClickListener {
            val i = Intent(this@NewGameActivity,
                    TestActivity::class.java)
            // beýleki penjiräni görkezeli
            startActivity(i)

            // häzirki peniräni tutuş ýok edeli
            finish()
        }
        cykmak.setOnClickListener {
            finish()
        }
        biz_barada.setOnClickListener {
            val i = Intent(this@NewGameActivity,
                    AboutActivity::class.java)
            // beýleki penjiräni görkezeli
            startActivity(i)

        }
    }
}
