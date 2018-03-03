package com.chapters.z.palette;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringBufferInputStream;

public class WidgetsActivity extends AppCompatActivity {

    RadioButton radioMale,radioFemale;
    Spinner spinnerLocation;
    ProgressBar progressBar1,progressBar2;
    TextView tvProgressbar2Value,tvSeekbarValue,tvRatingbarValue;
    Handler mHandler;
    SeekBar seekBar;
    RatingBar ratingBar;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);
        radioMale=(RadioButton) findViewById(R.id.radio_male);
        radioFemale=(RadioButton) findViewById(R.id.radio_female);
        spinnerLocation=(Spinner) findViewById(R.id.spinner);
        progressBar1=(ProgressBar) findViewById(R.id.progressBar);//圆形无进度值进度条
        progressBar2=(ProgressBar) findViewById(R.id.progressBar2);//有进度值进度条
        tvProgressbar2Value=(TextView) findViewById(R.id.progressbar2value);//显示progressbar进度值
        seekBar=(SeekBar) findViewById(R.id.seekBar);//seekbar
        tvSeekbarValue=(TextView) findViewById(R.id.seekbarvalue);//显示seekbar进度值
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);//ratingbar
        tvRatingbarValue=(TextView) findViewById(R.id.ratingbarvalue);//显示ratingbar进度值


    //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        // 设置下拉列表下拉后的样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 绑定到spineer上
        spinnerLocation.setAdapter(adapter);
        //设置 AdapterView.OnItemSelectedListener 接口并实现 onItemSelected() 回调方法
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                Toast.makeText(getApplicationContext(), (CharSequence) parent.getItemAtPosition(pos),Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


     //ProgressBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            progressBar2.setMin(0);//该属性仅为API26以上支持
        }
        progressBar2.setMax(100);

        //接受线程消息更新进度条进度及其值
        mHandler=new Handler() {
            @Override
            public void handleMessage(Message msg){
                if (msg != null) {
                    // 更新进度条
                    progressBar2.setProgress(msg.arg1);
                    // 更新数值显示
                    String st= String.valueOf(progressBar2.getProgress());
                    tvProgressbar2Value.setText(st + "%");
                }
            }
        };


        //SeekBar
        seekBar.setMax(100);
        seekBar.setProgress(10);//设置初始值
        //seekbar进度条滑杆变化事件处理
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbarValue.setText(String.valueOf(progress));
                if(fromUser){
                    Toast.makeText(getApplicationContext(),"User has moved seekBar",Toast.LENGTH_SHORT).show();
                }//滑杆变化是否是用户拖动造成的
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvSeekbarValue.setText(String.valueOf(seekBar.getProgress()));
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    // RatingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvRatingbarValue.setText("等级为："+ String.valueOf(rating));
                if(fromUser){
                    Toast.makeText(getApplicationContext(),"User has select ratingBar",Toast.LENGTH_SHORT).show();
                }//等第变化是否是用户交互造成的
            }
        });


    }



    //控制进度条的显示与隐藏
    public void progressbarinout(View v){
        if(progressBar1.getVisibility()==View.VISIBLE){
            progressBar1.setVisibility(View.INVISIBLE);
        }else{
            progressBar1.setVisibility(View.VISIBLE);
        }
    }

    //线程模拟进度值进度条
    public void progressbarstart(View v){
        // 启动线程模拟加载
        new Thread() {
            @Override
            public void run() {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50);
                            Message message = mHandler.obtainMessage();
                            message.arg1 = i;
                            mHandler.sendMessage(message);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }.start();
    }


    //增加进度值
    public void progressbarstart2(View v){
        if(progressBar2.getProgress()>=progressBar2.getMax()){
            progressBar2.setProgress(0);
        }else {
            progressBar2.setProgress(progressBar2.getProgress() + 1);
            // 更新数值显示
            String st = String.valueOf(progressBar2.getProgress());
            tvProgressbar2Value.setText(st + "%");
        }
    }

    public void onRadioClicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked)
                    Toast.makeText(this, radioMale.getText() + "be checked", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(this, radioMale.getText() + "be unchecked", Toast.LENGTH_SHORT).show();

                break;
            case R.id.radio_female:
                if (checked)
                    Toast.makeText(this, radioFemale.getText() + "be checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, radioFemale.getText() + "be unchecked", Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
