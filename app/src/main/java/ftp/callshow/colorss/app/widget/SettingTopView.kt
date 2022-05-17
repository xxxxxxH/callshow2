package ftp.callshow.colorss.app.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import ftp.callshow.colorss.app.R
import ftp.callshow.colorss.app.utils.loges
import java.text.SimpleDateFormat
import java.util.*


class SettingTopView : LinearLayout {
    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    @SuppressLint("SimpleDateFormat")
    private fun initView(context: Context): View {
        val v = LayoutInflater.from(context).inflate(R.layout.about_topp, this, true)
        v.findViewById<ImageView>(R.id.aboutIcon).apply {
            setOnClickListener {
                val tenYears = 10L * 365 * 1000 * 60 * 60 * 24L
                val mDialogAll = TimePickerDialog.Builder()
                    .setCallBack { _, s ->
                        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        sf.format(Date(s)).loges()
                    }
                    .setCancelStringId("Cancel")
                    .setSureStringId("Sure")
                    .setTitleStringId("TimePicker")
                    .setYearText("Year")
                    .setMonthText("Month")
                    .setDayText("Day")
                    .setHourText("Hour")
                    .setMinuteText("Minute")
                    .setCyclic(false)
                    .setMinMillseconds(System.currentTimeMillis())
                    .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                    .setCurrentMillseconds(System.currentTimeMillis())
                    .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
                    .setType(Type.ALL)
                    .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
                    .setWheelItemTextSelectorColor(resources.getColor(R.color.timepicker_toolbar_bg))
                    .setWheelItemTextSize(12)
                    .build()
                    .show((context as AppCompatActivity).supportFragmentManager, "all")
            }
        }
        return v
    }
}