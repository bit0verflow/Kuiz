package com.tduweai.jemal.kuiz // programmanyň özüne mahsus bolan id si
// gerekli kitaphanalary proýekte goşaly
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.R.raw
import android.media.MediaPlayer
import android.R.id.button1


// Androidyň klasyndan miras alýan esasy klasy döredeli
class SplashActivity : AppCompatActivity() {
    // ekranyň saklanjak wagtyny görkezýän üýtgeýäni döredeli
    companion object {
        private val SPLASH_SCREEN_TIME_OUT:Long = 2000

    }
// programma ilki işläp başlanda işläp başlajak funksiýa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // programmanyň görnüşini baglaly
        setContentView(R.layout.activity_splash)
        // programm tutş ekrany gaplar ýaly parametrleri bereli
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        // android täze penjiräni gökezer ýaly kod ýazaly
        Handler().postDelayed({
            //bir penjireden beýleki penjirä geççer ýaly intent ulanaly

            val i = Intent(this@SplashActivity,
                    NewGameActivity::class.java)
            // beýleki penjiräni görkezeli
            startActivity(i)

            // häzirki peniräni tutuş ýok edeli
            finish()

        }, SPLASH_SCREEN_TIME_OUT)
    }

}
