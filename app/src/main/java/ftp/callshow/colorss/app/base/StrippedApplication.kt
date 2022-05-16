package ftp.callshow.colorss.app.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.webkit.WebView
import com.anythink.core.api.ATSDK
import com.anythink.core.api.NetTrafficeCallback
import com.anythink.splashad.api.ATSplashAd
import com.anythink.splashad.api.ATSplashAdListener
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkSettings
import com.tencent.mmkv.MMKV
import ftp.callshow.colorss.app.BuildConfig
import ftp.callshow.colorss.app.R
import org.xutils.x
import kotlin.system.measureTimeMillis

class StrippedApplication : Application() {
    companion object {
        lateinit var instance: StrippedApplication
    }

    val maxSdk by lazy {
        AppLovinSdk.getInstance(
            getString(R.string.lovin_app_key).reversed(),
            AppLovinSdkSettings(this),
            this
        )
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        initSdk()
        x.Ext.init(this)
    }

    fun getMaxInterstitialAd(ac: Activity): MaxInterstitialAd {
        return MaxInterstitialAd(
            resources.getString(R.string.lovin_insert_ad_id),
            maxSdk,
            ac
        )
    }

    fun getMaxNativeAdLoader(): MaxNativeAdLoader {
        return MaxNativeAdLoader(
            resources.getString(R.string.lovin_native_ad_id),
            maxSdk,
            this
        )
    }

    //banner
    fun getMaxAdView(): MaxAdView {
        return MaxAdView(
            resources.getString(R.string.lovin_banner_ad_id),
            maxSdk,
            this
        )
    }

    //open
    fun getATSplashAd(listener: ATSplashAdListener?): ATSplashAd {
        return ATSplashAd(this, resources.getString(R.string.top_on_open_ad_id), listener)
    }

    fun initSdk() {
        measureTimeMillis {
            MMKV.initialize(instance)
            maxSdk.apply {
                mediationProvider = AppLovinMediationProvider.MAX
                initializeSdk()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val processName = getProcessName()
                if (packageName != processName) {
                    WebView.setDataDirectorySuffix(processName)
                }
            }

            ATSDK.checkIsEuTraffic(instance, object : NetTrafficeCallback {
                override fun onResultCallback(isEU: Boolean) {
                    if (isEU && ATSDK.getGDPRDataLevel(instance) == ATSDK.UNKNOWN) {
                        ATSDK.showGdprAuth(instance)
                    }
                }

                override fun onErrorCallback(errorMsg: String) {
                }
            })

            ATSDK.setNetworkLogDebug(BuildConfig.DEBUG)
            ATSDK.integrationChecking(instance)
            ATSDK.init(
                instance,
                getString(R.string.top_on_app_id),
                getString(R.string.top_on_app_key)
            )
        }.let {

        }
    }
}