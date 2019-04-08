package com.tduweai.jemal.kuiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_netije.*
// netijäni görkezjek esasy klas
class NetijeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_netije)
        val b = this.intent.extras
        val ulanyjy_jogaplary = b!!.getBooleanArray("ulanyjy_jogaplary")
        var sana=1
        val dogry_sany=b!!.getString("dogry_jogap_sany")
        val dogrylar_sany_=dogry_sany.toInt()

        dogrylar_sany.text=dogry_sany+"%"
        netije_yazgysy.text=when{

            dogrylar_sany_<50->{
                netije_suraty.setImageResource(R.drawable.yykyldy)
                "Siz ýykyldyňyz!"}
            dogrylar_sany_>=50 && dogrylar_sany_<65->{
                netije_suraty.setImageResource(R.drawable.ok)
                "Gowy!"
            }
            dogrylar_sany_>=65 && dogrylar_sany_<85->{
                netije_suraty.setImageResource(R.drawable.very_good_)
                "Has gowy!"
            }
            dogrylar_sany_>=85 && dogrylar_sany_<=100->{
                netije_suraty.setImageResource(R.drawable.best)
                "Berekella!"}
            else->"Yalňyşlyk!"
        }
        taze_oyun.setOnClickListener {
            val i = Intent(this@NetijeActivity, TestActivity::class.java)
            finish()  //Kill the activity from which you will go to next activity
            startActivity(i)
        }
            cykmak.setOnClickListener {
            finish()
        }

    }
}
