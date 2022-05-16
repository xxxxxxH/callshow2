package ftp.callshow.colorss.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ftp.callshow.colorss.app.R

class ItemView : LinearLayout {
    private lateinit var itemBackground:ImageView
    private lateinit var itemAv: ImageView
    private lateinit var itemName: TextView
    private lateinit var itemPhone: TextView

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

    private fun initView(context: Context): View {
        val v = LayoutInflater.from(context).inflate(R.layout.item_view, this, true)
        itemBackground = v.findViewById(R.id.itemBackground)
        itemAv = v.findViewById(R.id.itemAv)
        itemName = v.findViewById(R.id.itemName)
        itemPhone = v.findViewById(R.id.itemPhone)
        return v
    }

    fun setBackground(id:Int){
        itemBackground.setImageResource(id)
    }

    fun setAv(id: Int) {
        itemAv.setImageResource(id)
    }

    fun setName(name: String) {
        itemName.text = name
    }

    fun setPhone(phone: String) {
        itemPhone.text = phone
    }
}