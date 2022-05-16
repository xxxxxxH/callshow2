package ftp.callshow.colorss.app.activity

import androidx.lifecycle.lifecycleScope
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.alert.LoadingAlert
import ftp.callshow.colorss.app.alert.SaveAlert
import ftp.callshow.colorss.app.base.ChainsmokersActivity
import ftp.callshow.colorss.app.listener.IListener
import ftp.callshow.colorss.app.utils.buildFloatActionButton
import ftp.callshow.colorss.app.widget.PreviewView
import kotlinx.android.synthetic.main.activity_first_time.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstTimeActivity : ChainsmokersActivity(),IListener {

    private val background by lazy {
        intent.getIntExtra("background", 0)
    }
    private val av by lazy {
        intent.getIntExtra("av", 0)
    }

    private val fab by lazy {
        buildFloatActionButton()
    }

    private val saveAlert by lazy {
        SaveAlert(this, this)
    }

    private val loadingAlert by lazy {
        LoadingAlert(this, this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_first_time
    }

    override fun initActivity() {
        super.initActivity()
        val previewView = PreviewView(this)
        previewView.setBackground(background)
        previewView.setAv(av)
        previewView.getAnswerBtn().apply {
            setOnClickListener {
                saveAlert.setParentFab(fab)
                saveAlert.show(supportFragmentManager,"")
                saveAlert.setAd()
            }
        }
        content.removeAllViews()
        content.addView(previewView)
    }

    override fun click1(type:String) {
        when(type){
            "save" -> {
                saveAlert.dismiss()
            }
            "loading" -> {
                loadingAlert.dismiss()
            }
        }
    }

    override fun click2(type: String) {
       when(type){
           "save" -> {
               loadingAlert.setParentFab(fab)
               loadingAlert.show(supportFragmentManager, "")
               loadingAlert.setAd()
               lifecycleScope.launch(Dispatchers.IO){
                   delay(5000)
                   withContext(Dispatchers.Main){
                       finish()
                   }
               }
           }
           "loading" -> {
               loadingAlert.dismiss()
           }
       }
    }
}