package com.chapters.z.palette;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewActivity extends AppCompatActivity {
private ListView listView;
private SimpleAdapter simpleAdapter;
private String[] cities;
private String[] postcodes;
private MyAdapter myAdapter;
    /** Called when the activity is first created. */
private Integer[]	mImageIds	=
            {
                    R.drawable.bx,
                    R.drawable.hk,
                    R.drawable.xyl,
                    R.drawable.bd,
                    R.drawable.yd,
                    R.drawable.yn,
                    R.drawable.ysl,
                    R.drawable.mxg,
                    R.drawable.yg,
                    R.drawable.bx,
                    R.drawable.hk,
                    R.drawable.xyl,
                    R.drawable.bd,
                    R.drawable.yd,
                    R.drawable.yn,
                    R.drawable.ysl,
                    R.drawable.mxg,
                    R.drawable.yg,
                    R.drawable.bx,
                    R.drawable.hk,
                    R.drawable.xyl,
                    R.drawable.bd,
                    R.drawable.yd,
                    R.drawable.yn,
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView=(ListView) findViewById(R.id.listview01);
    }

//ArrayAdapter的例子
    public void btaOnClick(View v){
        //简单设置测试字符串数组
        String []data ={"好雨知时节","当春乃发生","随风潜入夜","润物细无声"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(ListViewActivity.this,
                android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
        /* 为ListView视图添加On-Item-Click事件 */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                TextView tv=(TextView) arg1.findViewById(android.R.id.text1);
                Toast.makeText(getApplicationContext(),tv.getText()+",第"+(arg2+1)+"行被点击",Toast.LENGTH_SHORT).show();
            }

        });

		/* 为ListView视图添加On-Item-Selected事件 用于鼠标键盘的情形ListView中每个Item都有被选中和未被选中状态。
		最多只有一个Item被选中，被选中的Item背景色会和其他不同。
		鼠标滚轮和空格键会改变被选中的Item，但不能使所有的Item都处于未被选中的状态。
		Enter键会触发被选中的Item的OnClick事件，如果没有Item处于被选中状态，还会使上次被选中的Item进入被选中的状态。
		鼠标点击，会使触发点击的Item的OnClick事件，还会使所有的Item进入未被选中的状态。*/
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {

                listView.setItemChecked(arg2, true);
                Toast.makeText(getApplicationContext(),"滚动到第" + Long.toString(arg0.getSelectedItemId()) + "项",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                //没有选中
            }
        });

    }

    //使用Simpledapter的例子
    public void btsOnClick(View v){
        cities= getResources().getStringArray(R.array.city_array);
        postcodes= getResources().getStringArray(R.array.postcode_array);
        ArrayList itemList=new ArrayList<HashMap>();

        for(int i=0;i<=cities.length-1;i++){
            HashMap mp= new HashMap();
            mp.put("imgid", mImageIds[i]);
            mp.put("city", cities[i]);
            mp.put("postcode", postcodes[i]);
            mp.put("checked",true);
            itemList.add(mp);
        }

       simpleAdapter=new SimpleAdapter(this,itemList,
                R.layout.listview_item_simpleadapter,
                new String[]{"imgid","city","postcode","checked"},
                new int[]{R.id.flag_image,R.id.tv_city,R.id.tv_postcode,R.id.ck_select});
        listView.setAdapter(simpleAdapter);

        /* 为ListView视图添加On-Item-Click事件 */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                TextView tv=(TextView) arg1.findViewById(R.id.tv_city);
                Toast.makeText(getApplicationContext(),tv.getText()+",listview第"+(arg3+1)+"行被点击",Toast.LENGTH_SHORT).show();
                simpleAdapter.notifyDataSetChanged();
            }
        });

    }

    public void btmOnClick(View v){
        cities= getResources().getStringArray(R.array.city_array);
        postcodes= getResources().getStringArray(R.array.postcode_array);
        ArrayList itemList=new ArrayList<HashMap>();

        for(int i=0;i<=cities.length-1;i++){
            HashMap mp= new HashMap();
            mp.put("city", cities[i]);
            mp.put("postcode", postcodes[i]);
            mp.put("checked",true);
            itemList.add(mp);
        }
        myAdapter=new MyAdapter(itemList,this);
        listView.setAdapter(myAdapter);
    }


}
