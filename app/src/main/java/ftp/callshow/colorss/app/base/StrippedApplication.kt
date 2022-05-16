package ftp.callshow.colorss.app.base

import android.app.Activity
import android.app.Application
import android.content.Context
import com.anythink.splashad.api.ATSplashAd
import com.anythink.splashad.api.ATSplashAdListener
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import ftp.callshow.colorss.app.R

class StrippedApplication : Application() {
    companion object {
        lateinit var instance: StrippedApplication
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        AmazingJAdvertisement.get().app = this
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        AmazingJAdvertisement.get().initSdk()
    }

    fun getMaxInterstitialAd(ac: Activity): MaxInterstitialAd {
        return MaxInterstitialAd(
            resources.getString(R.string.lovin_insert_ad_id),
            AmazingJAdvertisement.get().maxSdk,
            ac
        )
    }

    fun getMaxNativeAdLoader(): MaxNativeAdLoader {
        return MaxNativeAdLoader(
            resources.getString(R.string.lovin_native_ad_id),
            AmazingJAdvertisement.get().maxSdk,
            this
        )
    }

    //banner
    fun getMaxAdView(): MaxAdView {
        return MaxAdView(
            resources.getString(R.string.lovin_banner_ad_id),
            AmazingJAdvertisement.get().maxSdk,
            this
        )
    }

    //open
    fun getATSplashAd(listener: ATSplashAdListener?): ATSplashAd {
        return ATSplashAd(this, resources.getString(R.string.top_on_open_ad_id), listener)
    }
}