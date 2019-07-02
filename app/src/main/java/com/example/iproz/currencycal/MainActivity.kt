package com.example.iproz.currencycal

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import com.example.iproz.jpcal.Adapter
import com.example.iproz.jpcal.Hist
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout_clear.*
import java.text.DecimalFormat

fun Context.mainActivity(rateName: String,rateNum: String): Intent {
    return Intent(this, MainActivity::class.java).apply {
        this.putExtra(rateName,rateName)
        this.putExtra(rateNum,rateNum.toFloat())
    }
}

class MainActivity : AppCompatActivity() {

    var hists = arrayListOf<Hist>()
    var rateName: String? = null
    var rateNum: String? = null
//    val rateName = intent!!.getStringExtra("RNs")
//    val rateNum = intent!!.getStringExtra("RNd")!!.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ใส่toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        et_num1.setText(rateName)


        btn_send.setOnClickListener {

            changeCurrency()
            createHist()

        }

        btn_clear.setOnClickListener{
            hists.clear()
            startActivity(Intent(this, this::class.java))

        }

        startActivity(Intent(this,SettingPage::class.java))


    }

    //ใส่itemในtoolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_setting,menu)
        return super.onCreateOptionsMenu(menu)
    }
    //ใส่actiongเมื่อกดitemในtoolbar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.more_menu){
            slidePageGo()
        }
        return super.onOptionsItemSelected(item)
    }

    fun slidePageGo(){
        startActivity(Intent(this,SettingPage::class.java))
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left )
    }

    fun changeCurrency() {

        var num1 = et_num1.text.toString()
        var num2 = et_num2.text.toString()

        if (num1.trim().isNotEmpty() && num2.trim().isNotEmpty()) {
            et_num1.setText("")
            et_num2.setText("")
        } else {
            if (num1.trim().isNotEmpty()) {
                var num = num1.toFloat() * rateNum!!.toFloat()
                et_num2.setText(num.toString())
            } else {
                if (num2.trim().isNotEmpty()) {
                    var num = num2.toFloat() * rateNum!!.toFloat()
                    et_num1.setText(num.toString())
                } else {
                    Toast.makeText(this, "ใส่เลขก่อนนะ", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun createHist() {

        try {
            val df = DecimalFormat("#,###.##")
            var num1 = (df.format(et_num1.text.toString().toFloat()))
            var num2 = (df.format(et_num2.text.toString().toFloat()))

            if (num1.toString().isEmpty() && num2.toString().isEmpty()) {

            } else {
                val histSET = Hist(
                    num1,
                    num2
                )
                hists.add(histSET)

                var adapter = Adapter(hists, this)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
            }
        } catch (e: Exception){
            startActivity(Intent(this,SettingPage::class.java))
        }

        et_num1.setText("")
        et_num2.setText("")

    }

}
