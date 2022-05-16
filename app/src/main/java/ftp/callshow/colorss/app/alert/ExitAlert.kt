package ftp.callshow.colorss.app.alert

import android.app.Dialog
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.allattentionhere.fabulousfilter.AAH_FabulousFragment
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.base.ChainsmokersActivity
import ftp.callshow.colorss.app.listener.IListener

class ExitAlert(private val activity: AppCompatActivity, private val listener: IListener) :
    AAH_FabulousFragment() {
    private lateinit var frameLayout: FrameLayout
    override fun setupDialog(dialog: Dialog, style: Int) {
        val v = View.inflate(context, R.layout.layout_dialog, null)
        frameLayout = v.findViewById(R.id.dialogAd)
        v.findViewById<TextView>(R.id.dialogTv).apply {
            text = "Are you exist app ?"
        }
        v.findViewById<ImageView>(R.id.cancel).apply {
            setOnClickListener {
                listener.click1("exit")
            }
        }
        v.findViewById<ImageView>(R.id.sure).apply {
            setOnClickListener {
                listener.click2("exit")
            }
        }
        isCancelable = false
        setAnimationDuration(0)
        setViewMain(v.findViewById(R.id.main))
        setMainContentView(v)
        super.setupDialog(dialog, style)
    }

    fun setAd(){
        (activity as ChainsmokersActivity).showNativeAd {
            it?.let {
                frameLayout.removeAllViews()
                frameLayout.addView(it)
            }
        }
    }
}