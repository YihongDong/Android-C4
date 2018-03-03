package com.chapters.z.palette;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

public class DateActivity extends AppCompatActivity {
    TimePicker timePicker;
    DatePicker datePicker;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        // 获取当前的年、月、日、小时、分钟
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        // 初始化DatePicker组件，初始化时指定监听器
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker arg0, int year
                    , int month, int day)
            {
                DateActivity.this.year = year;
                DateActivity.this.month = month;
                DateActivity.this.day = day;

                Toast.makeText(DateActivity.this,"您选择的日期："+year+"年  "
                        +month+"月  "+day+"日", Toast.LENGTH_SHORT).show();
            }
        });

        // 初始化并为TimePicker指定监听器
        timePicker.setHour(hour);
        timePicker.setMinute(minute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {

            @Override
            public void onTimeChanged(TimePicker view
                    , int hourOfDay, int minute)
            {
                DateActivity.this.hour = hourOfDay;
                DateActivity.this.minute = minute;

                Toast.makeText(DateActivity.this,"您选择的时间："+hourOfDay+"时  "
                        +minute+"分", Toast.LENGTH_SHORT).show();
//
            }
        });


        //演示日期时间基本操作
        Calendar calendar = Calendar.getInstance();
        showCurrentDateAndTime(calendar);
        showFormatDateTime(calendar);
        //假设传入的timeString格式为"yyyy-MM-dd HH:mm"
        String timestring = "2017-01-01 12:20";
        try {
            Date date=parseTimeStringToDate(timestring);
            calendar.setTime(date);
            showFormatDateTime(calendar);
        } catch (ParseException e){
            e.printStackTrace();
        }

    }

    public void showCurrentDateAndTime(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);//当前年份
        int month = calendar.get(Calendar.MONTH);//当前月份
        int day = calendar.get(Calendar.DAY_OF_MONTH);//当前日期 用get(Calendar.DATE)也返回日期
        int hours = calendar.get(Calendar.HOUR);//当前小时数，12小时制，get(Calendar.HOUR_OF_DAY)可得24小时制
        int minute = calendar.get(Calendar.MINUTE);//当前分钟数
        int second = calendar.get(Calendar.SECOND);//当前秒数
        int millisecond = calendar.get(Calendar.MILLISECOND);//当前毫秒数
        Log.i("CurrentDateTime", "当前的年份为：" +year);
        Log.i("CurrentDateTime", "当前的月份为：" +month+1);//当前月份数从0开始，因此要+1
        Log.i("CurrentDateTime", "今天是：" +day+"号");//当前月份数从0开始，因此要+1
        Log.i("CurrentDateTime", "今天是今年的第" + calendar.get(Calendar.DAY_OF_YEAR) + "天");//一年中的第n天
        Log.i("CurrentDateTime", "今天在本月的第" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "周");//今天在本月的第几周
        Log.i("CurrentDateTime", getDayOfWeek(calendar));//今天是周几
        Log.i("CurrentDateTime", "现在是" +getAMPM(calendar)+hours+"点");//当前小时数
        Log.i("CurrentDateTime", "现在是" +minute+"分");
        Log.i("CurrentDateTime", "现在是" +second+"秒");
        Log.i("CurrentDateTime", "现在是" +millisecond+"豪秒");//当前秒内的毫秒数calendar.getTimeInMillis();获取长毫秒数绝对值1970年1月1日0点0分0秒算起
    }

//判断周几
    private String getDayOfWeek(Calendar c) {
        String result;
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                result= "今天是周日";
            break;
            case Calendar.MONDAY:
                result= "今天是周一";
            break;
            case Calendar.TUESDAY:
                result= "今天是周二";
            break;
            case Calendar.WEDNESDAY:
                result= "今天是周三";
            break;
            case Calendar.THURSDAY:
                result= "今天是周四";
            break;
            case Calendar.FRIDAY:
                result= "今天是周五";
            break;
            case Calendar.SATURDAY:
                result= "今天是周六";
            break;
            default:
                result= "今天是周n";
                break;
        }
        return result;
    }

    //判断上下午
    private String getAMPM(Calendar c){
        if (c.get(Calendar.AM_PM) == Calendar.AM) {
            return "上午";
        } else {
            return "下午";
        }
    }

    //格式化显示
    private void showFormatDateTime(Calendar c){
        Date date=c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Log.i("showFormatDateTime", sdf.format(date));
    }

    //假设传入的timeString格式为"yyyy-MM-dd HH:mm"
    public Date parseTimeStringToDate(String timeString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.parse(timeString);
    }


    //如果compareTo返回0，表示两个日期相等，返回小于0的值，表示d1在d2之前，大于0表示d1在d2之后
    public static int greater(Date d1, Date d2) {
        return d1.compareTo(d2) ;
    }



}
