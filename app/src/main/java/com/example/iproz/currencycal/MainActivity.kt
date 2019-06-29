package com.example.iproz.currencycal

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
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout_clear.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    var hists = arrayListOf<Hist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ใส่toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        btn_send.setOnClickListener {

            changeCurrency()
            createHist()

        }

        btn_clear.setOnClickListener{
            hists.clear()
            startActivity(Intent(this, this::class.java))

        }


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
        val df = DecimalFormat("#,###.###")

        if (num1.trim().isNotEmpty() && num2.trim().isNotEmpty()) {
            et_num1.setText("")
            et_num2.setText("")
        } else {
            if (num1.trim().isNotEmpty()) {
                var num = num1.toFloat() * 0.288
                et_num2.setText(df.format(num))
            } else {
                if (num2.trim().isNotEmpty()) {
                    var num = num2.toFloat() * (1 / 0.288)
                    et_num1.setText(df.format(num))
                } else {
                    Toast.makeText(this, "ใส่เลขก่อนนะ", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun createHist() {

        var num1 = et_num1.text.toString()
        var num2 = et_num2.text.toString()

        if (num1.isEmpty() && num2.isEmpty()) {

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

        et_num1.setText("")
        et_num2.setText("")

    }
}
