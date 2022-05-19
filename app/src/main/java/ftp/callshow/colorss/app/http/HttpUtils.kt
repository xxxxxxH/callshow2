package ftp.callshow.colorss.app.http

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import ftp.callshow.colorss.app.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import media.callshow.vc.flash.entity.ResultEntity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.xutils.common.Callback
import org.xutils.http.RequestParams
import org.xutils.x


fun AppCompatActivity.getConfig(
    onSuccess: () -> Unit,
    onError: () -> Unit,
    onCancelled: () -> Unit,
    onFinished: () -> Unit
) {
    val params = RequestParams("https://huwtao.xyz/config")
    x.http().get(params, object : Callback.CommonCallback<String> {
        override fun onSuccess(result: String?) {
            result?.let {
                "result1 $it".loges()
                formatResult1(it)
            }?.let {
                "result2 $it".loges()
                formatResult2(it)
            }?.let {
                "result3 $it".loges()
                formatResult3(it)
            }?.let {
                "result4 $it".loges()
                formatResult4(it)
            }?.let {
                "result5 $it".loges()
                formatResult5(it)
            }?.let {
                "result6 $it".loges()
                formatResult6(it)
            }?.let {
                "result7 $it".loges()
                formatResult7(it)
            }
            onSuccess()
        }

        override fun onError(ex: Throwable?, isOnCallback: Boolean) {
            onError()
        }

        override fun onCancelled(cex: Callback.CancelledException?) {
            onCancelled()
        }

        override fun onFinished() {
            onFinished()
        }

    })
}

fun AppCompatActivity.submit(content: String, onSuccess: () -> Unit, onFailure: () -> Unit){
    val body: RequestBody =
        Gson().toJson(mutableMapOf("content" to content))
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    OkGo.post<String>(testUrl).upRequestBody(body).execute(object :StringCallback(){
        override fun onSuccess(response: Response<String>?) {
            response?.let {
                "onSuccess ${it.body().toString()}".loges()
                val result = gson.fromJson(
                    it.body().toString(),
                    ResultEntity::class.java
                )
                if (result.code == "0" && result.data?.toBooleanStrictOrNull() == true){
                    login = true
                    lifecycleScope.launch(Dispatchers.Main){
                        onSuccess()
                    }
                }
            }?: kotlin.run {
                "onFailure".loges()
                onFailure()
            }
        }
    })
}