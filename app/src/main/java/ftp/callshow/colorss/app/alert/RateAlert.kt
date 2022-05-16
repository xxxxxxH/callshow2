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

class RateAlert(activity: AppCompatActivity) :
    AAH_FabulousFragment() {
    override fun setupDialog(dialog: Dialog, style: Int) {
        val v = View.inflate(context, R.layout.layout_rate, null)
        v.findViewById<ImageView>(R.id.cancel).apply {
            setOnClickListener {
               this@RateAlert.dismiss()
            }
        }
        v.findViewById<ImageView>(R.id.sure).apply {
            setOnClickListener {
                this@RateAlert.dismiss()
            }
        }
        isCancelable = false
        setAnimationDuration(0)
        setViewMain(v.findViewById(R.id.main))
        setMainContentView(v)
        super.setupDialog(dialog, style)
    }
}