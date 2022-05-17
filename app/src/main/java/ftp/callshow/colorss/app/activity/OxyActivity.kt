package ftp.callshow.colorss.app.activity

import android.view.View
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.http.getConfig
import ftp.callshow.colorss.app.base.ChainsmokersActivity
import ftp.callshow.colorss.app.event.Event
import ftp.callshow.colorss.app.utils.*
import kotlinx.android.synthetic.main.activity_oxy.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class OxyActivity : ChainsmokersActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_oxy
    }

    override fun initActivity() {
        super.initActivity()
        EventBus.getDefault().register(this)
        loginBtn.setOnClickListener { nextActivity(FadingActivity::class.java, finish = false) }
        getConfig({
            if (login) {
                showOpen(MainActivity::class.java) {
                    if (showOpenAdvertisement(rootParent, haveTo = true)) {
                        b = true
                        return@showOpen
                    }
                }
                return@getConfig
            }
            if (configEntity.needLogin()) {
                loginBtn.visibility = View.VISIBLE
                return@getConfig
            }
            showOpen(MainActivity::class.java) {
                if (showOpenAdvertisement(rootParent, haveTo = true)) {
                    b = true
                    return@showOpen
                }
            }
        }, {

        }, {

        }, {

        })
    }

    override fun ic() {
        super.ic()
        whenInsertAdClose(MainActivity::class.java)
    }

    override fun sd() {
        super.sd()
        whenSplashAdClose(MainActivity::class.java)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(e:Event){
        if (e.getMessage()[0] == "finish"){
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}