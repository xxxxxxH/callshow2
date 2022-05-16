package ftp.callshow.colorss.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import ftp.callshow.colorss.app.R

class MainContentView : LinearLayout {

    private lateinit var content1: LinearLayout
    private lateinit var content2: LinearLayout

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
        val v = LayoutInflater.from(context).inflate(R.layout.main_content, this, true)
        content1 = v.findViewById(R.id.content1)
        content2 = v.findViewById(R.id.content2)
        return v
    }

    fun addContent1() {
        val itemView1 = ItemView(context)
        val p1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        p1.weight = 1f
        itemView1.setBackground(R.mipmap.item1)
        itemView1.setAv(R.mipmap.av1)
        itemView1.setName("Audrey")
        itemView1.setPhone("916 740 0000")
        itemView1.layoutParams = p1

        val itemView2 = ItemView(context)
        val p2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        p2.weight = 1f
        itemView2.setBackground(R.mipmap.item2)
        itemView2.setAv(R.mipmap.av2)
        itemView2.setName("Audrey")
        itemView2.setPhone("916 740 0000")
        itemView2.layoutParams = p2

        content1.addView(itemView1)
        content1.addView(itemView2)
    }

    fun addContent2() {
        val itemView1 = ItemView(context)
        val p1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        p1.weight = 1f
        itemView1.setBackground(R.mipmap.item3)
        itemView1.setAv(R.mipmap.av3)
        itemView1.setName("Audrey")
        itemView1.setPhone("916 740 0000")
        itemView1.layoutParams = p1

        val itemView2 = ItemView(context)
        val p2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        p2.weight = 1f
        itemView2.setBackground(R.mipmap.item4)
        itemView2.setAv(R.mipmap.av1)
        itemView2.setName("Audrey")
        itemView2.setPhone("916 740 0000")
        itemView2.layoutParams = p2

        content2.addView(itemView1)
        content2.addView(itemView2)
    }
}