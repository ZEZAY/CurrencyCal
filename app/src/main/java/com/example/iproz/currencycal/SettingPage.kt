package com.example.iproz.currencycal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_setting.*

class SettingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //ทำ slide หน้านี้
        Slidr.attach(this)

        btn_saveRate.setOnClickListener{
            var rateName = et_rateName.text.toString()
            var rateNum = et_rateNum.text.toString()
            if (rateName.trim().isNotEmpty() && rateNum.trim().isNotEmpty()) {
                val bundle = Bundle().apply {
                    this.putString("RATE_MAME",rateName)
                    this.putString("RATE_NUM",rateNum)
                }
                startActivity(mainActivity(bundle))

            } else {
                Toast.makeText(this, "ใส่ข้อมูลก่อนนะ", Toast.LENGTH_LONG).show()
                    }
        }
    }

    override fun finish() {
        super.finish()
        //ปิดสวยๆ
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right )
    }

}
