package com.tduweai.jemal.kuiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_test.*
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import java.util.*
import android.media.MediaPlayer.OnCompletionListener
import android.media.AudioManager
import android.media.AudioAttributes
import android.os.Build





// Test funkisýasyny ýerine ýetirjek esasy klas
class TestActivity : AppCompatActivity() {
    //val dogry_sazy = MediaPlayer.create(this@TestActivity,R.raw.correct_new)
    //val yalnysh_sazy = MediaPlayer.create(this@TestActivity,R.raw.wrong)

    val sorag_sany=10
    var dogry_sany=0
    //soraljak soragyn nomeri, ulnyjy her gezek jogap saylanda 1 artdyrylyar
    var sorag_nomeri=0
    // soraglaryn saklanyan listi
    val soraglar= mutableListOf<TestQuestionModel>()
    var umumy_soraglar=mutableListOf<TestQuestionModel>()
    //ulanyjynyn saylan jogablaryny saklamak ucin list
   lateinit var ulanyjyn_saylan_jogaby_dogrymy:BooleanArray
    // android programmasy ilkinji ishlap bashlanda 1 gezek ishlejek funksiya
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        umumy_soraglary_gosh()
        // soraglary goshyan funksiyany cagyr
        soraglaryGosh()
        //ilkinji soragy soramak ucin soragSora funksiyany cagyraly
        soragSora()
    }
fun umumy_soraglary_gosh(){
    umumy_soraglar.add(TestQuestionModel("Adam näme?", mutableListOf("hiçzat","adam","haýwan",
            "eşek"),
    "adam"))
    umumy_soraglar.add(TestQuestionModel("Bill Geýts haçan doguldy?", mutableListOf("1922","1122","322",
    "1988"), "1922"))
    umumy_soraglar.add(TestQuestionModel("Türkemistan haçan Bitarap boldy?", mutableListOf
    ("1995","1122","322","1999"),
            "1995"))
    umumy_soraglar.add(TestQuestionModel("Türkmenistan haçan garaşsyz boldy?", mutableListOf
    ("1991","1122","322","1988"),
            "1991"))
    umumy_soraglar.add(TestQuestionModel("Türkmenistanda naçe welaýat bar?", mutableListOf("9","3",
            "4","5"),
            "5"))
    umumy_soraglar.add(TestQuestionModel("Türkmenistanyň paýtagty", mutableListOf("Aşgabat",
            "Kerki","Türkmenabat","Mary"),
            "Aşgabat"))
    umumy_soraglar.add(TestQuestionModel("Lebap welaýatynyň merkezi.", mutableListOf
    ("Türkmenabat","Balkan",
            "Kerki","Halaç"),"Türkmenabat"))
    umumy_soraglar.add(TestQuestionModel("Bir ýylda ortaça näçe gün bar?", mutableListOf("369",
            "360","365","300"),
            "365"))
    umumy_soraglar.add(TestQuestionModel("Bir günde naçe sagat bar?", mutableListOf("23","20","12",
            "24"),
            "24"))
    umumy_soraglar.add(TestQuestionModel(" 0/30 amalynyň jogaby naçe? ", mutableListOf
    ("tukeniksiz","0","30","nabelli"),
            "0"))
    umumy_soraglar.add(TestQuestionModel("Gökdepe urşy haçan boldy?", mutableListOf("1881","1122","322",
    "1988"),
            "1881"))
    umumy_soraglar.add(TestQuestionModel("Beýik watançylyk urşy haçan başlady?", mutableListOf
    ("1939","1940","1941",
            "1942"),
            "1941"))
    umumy_soraglar.add(TestQuestionModel("Haýsy alymyň beýnisi 12% işläpdir?", mutableListOf
    ("Enşteýn","Nýuton","Mendeleýew",
            "Zukerberg"),
            "Enşteýn"))
    umumy_soraglar.c_shuffle()
}
    fun soraglaryGosh(){
        for(i in 0..(sorag_sany-1)){
            soraglar.add(umumy_soraglar[i])
        }

        ulanyjyn_saylan_jogaby_dogrymy=BooleanArray(soraglar.size)

    }
    fun soragSora(){
        rg.clearCheck()
        sorag.text=soraglar[sorag_nomeri].sorag
        soraglar[sorag_nomeri].jogaplar.c_shuffle()
        a.text=soraglar[sorag_nomeri].jogaplar[0]
        b.text=soraglar[sorag_nomeri].jogaplar[1]
        c.text=soraglar[sorag_nomeri].jogaplar[2]
        d.text=soraglar[sorag_nomeri].jogaplar[3]
        sorag_no.text="${(sorag_nomeri+1).toString()}."
        //bal.text="Bal:$dogry_sany"
    }


    fun jogaplaraBasylanda(view: View){

        if (view is RadioButton) {
            // knopka saýlananmy
           val checked = view.isChecked
           val t_view=view as TextView
           val ulanyjynyn_jogaby=t_view.text
           Log.d("SORAG",soraglar[sorag_nomeri].jogap)
            var progress_=((sorag_nomeri+1)*100)/soraglar.size
            progress.progress=progress_
           // eger ulanyjyn saylan jogaby berlen jogap bilen den bolsa
            if(ulanyjynyn_jogaby==soraglar[sorag_nomeri].jogap){

               //onda jogablar listine true yagny dogry diyip belle
               ulanyjyn_saylan_jogaby_dogrymy[sorag_nomeri]=true
                dogry_sany=dogry_sany+1
                // jogap dogry bolsa dogry berekella diyip dialog çykarmak
                dogry_sazy_ishlet()
                SweetAlertDialog(this@TestActivity, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Berekella!")
                        .setContentText("Jogap dogry!")
                        .setConfirmClickListener {
                            soraWeBarla()
                            it.dismiss()
                        }.show()
           }else{
                //jogab ýalňyş bolsa ýalňyş diýip dialog çykarmak
                yalnysh_sazy_ishlet()
                SweetAlertDialog(this@TestActivity,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Bagyşlaň!")
                        .setContentText("Jogap ýalňyş!")
                        .setConfirmClickListener {
                            soraWeBarla()
                            it.dismiss()
                        }.show()
            }
            //eger sorag nomerinin sany soraglaryn sanynda uly dal bolsa sorag sora



        }
    }
    fun dogry_sazy_ishlet(){
        val mMediaPlayer = MediaPlayer.create(this,R.raw.correct_new)
        mMediaPlayer.start()
    }
    fun yalnysh_sazy_ishlet(){
        val mMediaPlayer = MediaPlayer.create(this,R.raw.wrong)
        mMediaPlayer.start()
    }
    fun soraWeBarla(){

        if(sorag_nomeri+1<soraglar.size){
            sorag_nomeri=sorag_nomeri+1
            soragSora()
        }else{

            val b = Bundle()
            b.putString("dogry_jogap_sany",((dogry_sany*100)/sorag_sany).toString())
            b.putBooleanArray("ulanyjy_jogaplary",ulanyjyn_saylan_jogaby_dogrymy)
            val i = Intent(this@TestActivity, NetijeActivity::class.java)
            i.putExtras(b)
            finish()  //Kill the activity from which you will go to next activity
            startActivity(i)
        }
    }
    fun <T, L : MutableList<T>> L.c_shuffle(): L {
        val rng = Random()

        for (index in 0..(this.size - 1)) {
            val randomIndex = rng.nextInt(if(index<=0){1}else{index})

            // Swap with the random position
            val temp = this[index]
            this[index] = this[randomIndex]
            this[randomIndex] = temp
        }

        return this
    }
}
