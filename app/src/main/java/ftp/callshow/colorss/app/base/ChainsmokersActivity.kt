package ftp.callshow.colorss.app.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anythink.core.api.ATAdInfo
import com.anythink.core.api.AdError
import com.anythink.splashad.api.ATSplashAd
import com.anythink.splashad.api.ATSplashAdListener
import com.anythink.splashad.api.IATSplashEyeAd
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdView
import ftp.callshow.colorss.app.utils.appendBanner
import ftp.callshow.colorss.app.utils.*
import ftp.callshow.colorss.app.utils.loges
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class ChainsmokersActivity:AppCompatActivity() {

    private var isBackground = false
    private var openAd: ATSplashAd? = null
    private var insertAd: MaxInterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initActivity()
    }

    abstract fun getLayoutId():Int

    open fun initActivity(){
        initAdvertisement()
        appendBanner()
    }

    open fun sd(){}

    open fun ic(){}

    private fun initAdvertisement(){
        openAd = StrippedApplication.instance!!.getATSplashAd(openAdvertisementLisenter())
        openAd?.loadAd()

        insertAd = StrippedApplication.instance!!.getMaxInterstitialAd(this)
        insertAd?.setListener(insertAdvertisementListener())
        insertAd?.loadAd()
    }

    private fun getOpenAd() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000)
            openAd?.onDestory()
            openAd = StrippedApplication.instance!!.getATSplashAd(openAdvertisementLisenter())
            openAd?.loadAd()
        }
    }

    private fun getInsertAd(){
        lifecycleScope.launch(Dispatchers.Main) {
            insertAd?.destroy()
            insertAd = StrippedApplication.instance.getMaxInterstitialAd(this@ChainsmokersActivity)
            insertAd!!.setListener(insertAdvertisementListener())
            insertAd!!.loadAd()
        }
    }

    fun showOpenAdvertisement(parent: ViewGroup, haveTo:Boolean = false):Boolean{
        if (configEntity.showOpenAdWithInsertAd()){
            return showInertAdvertisement(haveTo = haveTo)
        }else{
            return showOpenAdvertisementExt(parent)
        }
    }

   private fun showOpenAdvertisementExt(parent: ViewGroup):Boolean{
        openAd?.let {
            if (it.isAdReady){
                it.show(this, parent)
                return true
            }
        }
        return false
    }

    fun showInertAdvertisement(percent: Boolean = false,
                               haveTo: Boolean = false,
                               target: String = ""):Boolean{
        if (haveTo){
            return showInsertAdvertisementExt(target)
        }else{
            if ((percent && configEntity.isCanShowByPercent()) || (!percent)) {
                if (System.currentTimeMillis() - lastTime > configEntity.insertAdInterval() * 1000) {
                    var result = false
                    if (list.getOrNull(index) == true) {
                        result = showInsertAdvertisementExt(target)
                    }
                    index++
                    if (index >= list.size) {
                        index = 0
                    }
                    return result
                }
            }
        }
        return false
    }

  private  fun showInsertAdvertisementExt(target:String):Boolean{
        insertAd?.let {
            "insert is ready = ${it.isReady}".loges()
            if (it.isReady){
                it.showAd(target)
                return true
            }else{
                getInsertAd()
                return false
            }
        }
        return false
    }

    fun showNativeAd(display:(MaxNativeAdView?)->Unit){
        val ad = StrippedApplication.instance!!.getMaxNativeAdLoader()
        ad.loadAd()
        ad.setNativeAdListener(object : MaxNativeAdListener(){
            override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd?) {
                super.onNativeAdLoaded(p0, p1)
                "onNativeAdLoaded".loges()
                display(p0)
            }

            override fun onNativeAdLoadFailed(p0: String?, p1: MaxError?) {
                super.onNativeAdLoadFailed(p0, p1)
                "onNativeAdLoadFailed $p1".loges()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        isBackground = isInBackground()
    }

    override fun onResume() {
        super.onResume()
        if (isBackground) {
            isBackground = false
            addOpen {
                showOpenAdvertisement(it)
            }
        }
    }

    inner class openAdvertisementLisenter : ATSplashAdListener{
        override fun onAdLoaded() {
            "openAd onAdLoaded".loges()
        }

        override fun onNoAdError(p0: AdError?) {
            "openAd onNoAdError $p0".loges()
            getOpenAd()
        }

        override fun onAdShow(p0: ATAdInfo?) {
            "openAd onShow".loges()
        }

        override fun onAdClick(p0: ATAdInfo?) {

        }

        override fun onAdDismiss(p0: ATAdInfo?, p1: IATSplashEyeAd?) {
            "openAd onAdDismiss".loges()
            sd()
            getOpenAd()
        }
    }

    inner class insertAdvertisementListener : MaxAdListener{
        override fun onAdLoaded(ad: MaxAd?) {
            "insertAd onAdLoaded".loges()
        }

        override fun onAdDisplayed(ad: MaxAd?) {

        }

        override fun onAdHidden(ad: MaxAd?) {
            lastTime = System.currentTimeMillis()
            getInsertAd()
            ic()
        }

        override fun onAdClicked(ad: MaxAd?) {

        }

        override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
            "insertAd onAdLoadFailed $error".loges()
            getInsertAd()
        }

        override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
            "insertAd onAdDisplayFailed $error".loges()
            getInsertAd()
        }
    }
}