package com.chapters.z.palette;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerLocation;
    ToggleButton mytoggleButton;
    CheckBox checkBoxSwim,checkBoxSport;
    TextView myFavorite;
    AutoCompleteTextView autoAddress;
    FloatingActionButton mybutton;
    EditText myname,myphone,mybir,myemail;
    RadioButton male;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerLocation=(Spinner) findViewById(R.id.spinner);
        checkBoxSport=(CheckBox)findViewById(R.id.checkBox2);
        checkBoxSwim=(CheckBox)findViewById(R.id.checkBox);
        myFavorite=(TextView)findViewById(R.id.textView9);
        myname=(EditText)findViewById(R.id.editText2);
        myphone=(EditText)findViewById(R.id.editText3);
        mybir=(EditText)findViewById(R.id.editText4);
        myemail=(EditText)findViewById(R.id.editText5);
        male=(RadioButton)findViewById(R.id.radioButton2);
        final CharSequence[] temp3 = new CharSequence[1];


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
                temp3[0] =(CharSequence) parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        //ToggleButton
        mytoggleButton=(ToggleButton) findViewById(R.id.toggleButton);
        //设置 mytoggleButton.setOnCheckedChangeListener 接口并实现 OnCheckedChangeListener() 回调方法
        mytoggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            //当ToggleButton关闭时不显示下面的偏好选项，否则显示偏好选项
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    myFavorite.setVisibility(View.VISIBLE);
                    checkBoxSport.setVisibility(View.VISIBLE);
                    checkBoxSwim.setVisibility(View.VISIBLE);
                }else{
                    myFavorite.setVisibility(View.GONE);
                    checkBoxSport.setVisibility(View.GONE);
                    checkBoxSwim.setVisibility(View.GONE);
                }
            }
        });

        //AutoCompleteTextView
        autoAddress=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        String [] regions=getResources().getStringArray(R.array.city_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> regionadp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,regions);
        autoAddress.setAdapter(regionadp);

        //toast
        mybutton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        //按选择的情况输出相应的text
        mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String temp,temp2="";
                if(male.isChecked()) temp="男";
                else temp="女";
                if(mytoggleButton.isChecked()) {
                    if(checkBoxSwim.isChecked()) temp2+="游泳  ";
                    if(checkBoxSport.isChecked()) temp2+="健身";
                    Toast.makeText(MainActivity.this, "会员名：" + myname.getText()
                                    + "\n电话：" + myphone.getText() + "\n生日：" + mybir.getText() + "\nEmail："
                                    + myemail.getText()+"\n性别："+temp+"\n地址："+temp3[0]
                                    +"\n偏好："+temp2+"\n区域："+autoAddress.getText()
                            , Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "会员名：" + myname.getText()
                                    + "\n电话：" + myphone.getText() + "\n生日：" + mybir.getText() + "\nEmail："
                                    + myemail.getText()+"\n性别："+temp+"\n地址："+temp3[0]
                                    +"\n区域："+autoAddress.getText()
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}