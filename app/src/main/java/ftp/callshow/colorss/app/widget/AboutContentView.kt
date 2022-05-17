package ftp.callshow.colorss.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import ftp.callshow.colorss.app.R

class AboutContentView :LinearLayout{
    private lateinit var itemIcon:ImageView
    private lateinit var itemArrow:ImageView
    private lateinit var itemTv:TextView
    private lateinit var itemRoot:RelativeLayout
    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    private fun initView(context: Context) :View{
        val v = LayoutInflater.from(context).inflate(R.layout.about_item,this, true)
        itemIcon = v.findViewById(R.id.itemIcon)
        itemArrow = v.findViewById(R.id.itemArrow)
        itemTv = v.findViewById(R.id.itemTv)
        itemRoot = v.findViewById(R.id.itemRoot)
        return v
    }

    fun getItem():RelativeLayout{
        return itemRoot
    }

    fun setIcon(id:Int){
        itemIcon.setImageResource(id)
    }

    fun setArrowVisible(v:Int){
        itemArrow.visibility = v
    }

    fun setTvText(text:String){
        itemTv.text = text
    }
}