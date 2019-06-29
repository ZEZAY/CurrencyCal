package com.example.iproz.currencycal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.r0adkll.slidr.Slidr

class SettingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //ทำ slide หน้านี้
        Slidr.attach(this)

    }

    override fun finish() {
        super.finish()
        //ปิดสวยๆ
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right )
    }

}
