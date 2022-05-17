package ftp.callshow.colorss.app.alert

import android.app.Dialog
import android.view.View
import android.widget.TextView
import com.allattentionhere.fabulousfilter.AAH_FabulousFragment
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.utils.ShareUtils

class ShareAlert : AAH_FabulousFragment() {
    override fun setupDialog(dialog: Dialog, style: Int) {
        val v = View.inflate(context, R.layout.layout_share, null)
        v.findViewById<TextView>(R.id.shareInFb).apply {
            setOnClickListener { ShareUtils.get().shareWithFb(context, "") }
        }
        v.findViewById<TextView>(R.id.shareInIns).apply {
            setOnClickListener { ShareUtils.get().shareWithIns(context, "") }
        }
        v.findViewById<TextView>(R.id.shareInApp).apply {
            setOnClickListener { ShareUtils.get().shareWithEmail(context, "") }
        }
        v.findViewById<TextView>(R.id.shareInEmail).apply {
            setOnClickListener { ShareUtils.get().shareWithNative(context) }
        }
        setAnimationDuration(0)
        setViewMain(v.findViewById(R.id.main))
        setMainContentView(v)
        super.setupDialog(dialog, style)
    }
}