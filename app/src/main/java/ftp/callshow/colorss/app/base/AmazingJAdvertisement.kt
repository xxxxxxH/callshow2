package ftp.callshow.colorss.app.base

import android.app.Application
import android.os.Build
import android.webkit.WebView
import com.anythink.core.api.ATSDK
import com.anythink.core.api.NetTrafficeCallback
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkSettings
import com.tencent.mmkv.MMKV
import ftp.callshow.colorss.app.BuildConfig
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.utils.loges
import kotlin.system.measureTimeMillis

class AmazingJAdvertisement{

    companion object{
        @Volatile
        private var i:AmazingJAdvertisement?=null
        get() {
            field?.run {
                field = AmazingJAdvertisement()
            }
            return field
        }

        @Synchronized
        fun get():AmazingJAdvertisement{
            return i!!
        }
    }

    lateinit var app:Application

    val maxSdk by lazy {
        AppLovinSdk.getInstance(app.getString(R.string.lovin_app_key).reversed(),
            AppLovinSdkSettings(app),
            app)
    }

    fun initSdk(){
        "app $app".loges()
        measureTimeMillis {
            MMKV.initialize(app)
            maxSdk.apply {
                mediationProvider = AppLovinMediationProvider.MAX
                initializeSdk()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val processName = Application.getProcessName()
                if (app.packageName != processName) {
                    WebView.setDataDirectorySuffix(processName)
                }
            }

            ATSDK.checkIsEuTraffic(app, object : NetTrafficeCallback {
                override fun onResultCallback(isEU: Boolean) {
                    if (isEU && ATSDK.getGDPRDataLevel(app) == ATSDK.UNKNOWN) {
                        ATSDK.showGdprAuth(app)
                    }
                }

                override fun onErrorCallback(errorMsg: String) {
                }
            })

            ATSDK.setNetworkLogDebug(BuildConfig.DEBUG)
            ATSDK.integrationChecking(app)
            ATSDK.init(
                app,
                app.getString(R.string.top_on_app_id),
                app.getString(R.string.top_on_app_key)
            )
        }.let {

        }
    }
}