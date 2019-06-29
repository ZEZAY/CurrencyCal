package com.example.iproz.jpcal

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iproz.currencycal.R
import kotlinx.android.synthetic.main.activity_card_view.view.*
import java.util.ArrayList

class Adapter(
    val hists: ArrayList<Hist>,
    val context: Context
) : RecyclerView.Adapter<ViewHoder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_card_view, parent, false)

        return ViewHoder(view) //ชื่อ class
    }


    override fun getItemCount(): Int {
        return hists.size
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        holder.n1.text = hists[position].setnum1
        holder.n2.text = hists[position].setnum2
    }


}

class ViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //ตาม layout
    var n1 = itemView.show_num1!!  //กัน null
    var n2 = itemView.show_num2!!  //กัน null
}