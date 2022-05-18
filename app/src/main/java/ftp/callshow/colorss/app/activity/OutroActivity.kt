package ftp.callshow.colorss.app.activity

import android.view.View
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.alert.RateAlert
import ftp.callshow.colorss.app.alert.ShareAlert
import ftp.callshow.colorss.app.base.ChainsmokersActivity
import ftp.callshow.colorss.app.utils.buildFloatActionButton
import ftp.callshow.colorss.app.widget.AboutContentView
import ftp.callshow.colorss.app.widget.SettingTopView
import kotlinx.android.synthetic.main.about_top.*
import kotlinx.android.synthetic.main.layout_about.*

class OutroActivity :ChainsmokersActivity(){

    private val rateAlert by lazy {
        RateAlert(this)
    }

    private val shareAlert by lazy{
        ShareAlert()
    }

    private val fab by lazy {
        buildFloatActionButton()
    }

    override fun getLayoutId(): Int {
        return R.layout.layout_about
    }

    override fun initActivity() {
        super.initActivity()

        f.setOnClickListener { finish() }

        val view1 = SettingTopView(this)
        about1.addView(view1)

        val item1 = AboutContentView(this)
        item1.setIcon(R.mipmap.s5)
        item1.setTvText("Share")
        item1.setArrowVisible(View.VISIBLE)
        item1.getItem().setOnClickListener {
            shareAlert.setParentFab(fab)
            shareAlert.show(supportFragmentManager ,"")
        }

        val item2 = AboutContentView(this)
        item2.setIcon(R.mipmap.s6)
        item2.setTvText("Rate us")
        item2.setArrowVisible(View.VISIBLE)
        item2.getItem().setOnClickListener {
            rateAlert.setParentFab(fab)
            rateAlert.show(supportFragmentManager,"")
        }

        val item3 = AboutContentView(this)
        item3.setIcon(R.mipmap.s7)
        item3.setTvText("Version number 1.0.1")
        item3.setArrowVisible(View.INVISIBLE)

        about2.addView(item1)
        about2.addView(item2)
        about2.addView(item3)
    }
}