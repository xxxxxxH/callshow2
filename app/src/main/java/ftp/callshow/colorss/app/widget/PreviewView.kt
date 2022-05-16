package ftp.callshow.colorss.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import ftp.callshow.colorss.app.R

class PreviewView : LinearLayout {
    private lateinit var previewAnswer: ImageView
    private lateinit var previewBackground: ImageView
    private lateinit var previewAv: ImageView

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
        val v = LayoutInflater.from(context).inflate(R.layout.preview_view, this, true)
        v.findViewById<ImageView>(R.id.previewBack)
            .apply { setOnClickListener { (context as AppCompatActivity).finish() } }
        previewAnswer = v.findViewById(R.id.previewAnswer)
        previewBackground = v.findViewById(R.id.previewBackground)
        previewAv = v.findViewById(R.id.previewAv)
        return v
    }

    fun setBackground(id:Int){
        previewBackground.setImageResource(id)
    }

    fun setAv(id:Int){
        previewAv.setImageResource(id)
    }

    fun getAnswerBtn():ImageView{
        return previewAnswer
    }
}