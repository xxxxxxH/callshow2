package ftp.callshow.colorss.app.activity

import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.alert.ExitAlert
import ftp.callshow.colorss.app.base.ChainsmokersActivity
import ftp.callshow.colorss.app.listener.IListener
import ftp.callshow.colorss.app.utils.buildFloatActionButton
import ftp.callshow.colorss.app.widget.MainContentView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ChainsmokersActivity(), IListener {

    private val fab by lazy {
        buildFloatActionButton()
    }

    private val exitAlert by lazy {
        ExitAlert(this, this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initActivity() {
        super.initActivity()
        showNativeAd {
            it?.let {
                mainAd.removeAllViews()
                mainAd.addView(it)
            }
        }
        val content = MainContentView(this)
        content.addContent1()
        content.addContent2()
        item.removeAllViews()
        item.addView(content)
    }

    override fun onBackPressed() {
        exitAlert.setParentFab(fab)
        exitAlert.show(supportFragmentManager, "")
        exitAlert.setAd()
    }

    override fun click1(type: String) {
        if (type == "exit") {
            exitAlert.dismiss()
        }
    }

    override fun click2(type: String) {
        if (type == "exit") {
            exitAlert.dismiss()
            finish()
        }
    }
}