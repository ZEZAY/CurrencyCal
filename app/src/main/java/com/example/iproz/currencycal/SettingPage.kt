package com.example.iproz.currencycal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_setting.*

class SettingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //ทำ slide หน้านี้
        Slidr.attach(this)

        btn_saveRate.setOnClickListener{
            setNRate()
        }

    }

    override fun finish() {
        super.finish()
        //ปิดสวยๆ
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right )
    }

    fun setNRate(){

        var rateName = et_rateName.text.toString()
        var rateNum = et_rateNum.text.toString()

        onClickedItem(rateName,rateNum)

//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("RNs", rateName)
//        intent.putExtra("RNd", rateNum)
//        startActivity(intent)

    }
    fun onClickedItem(rateName: String,rateNum: String) {
        startActivity(mainActivity(rateName,rateNum))
    }

}
