package com.chapters.z.palette;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class GridViewActivity extends AppCompatActivity {

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
                    R.drawable.yg

            };

    private String[] e_countries,c_countries;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        //取得GridView对象
        GridView gridview = (GridView) findViewById(R.id.gridview);
        //添加元素给gridview
        gridview.setAdapter(getSimpleAdapter());

        // 设置Gallery的背景
        //gridview.setBackgroundResource(R.drawable.bg0);

        //事件监听
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(getApplicationContext(), "这是" + c_countries[position] + " 国旗", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //使用simpleadapter绑定数据和显示格式
    private ListAdapter getSimpleAdapter(){


        e_countries= getResources().getStringArray(R.array.countriesflag_english);
        c_countries= getResources().getStringArray(R.array.countries_chinese);


        ArrayList list=new ArrayList<HashMap>();

        for(int i=0;i<=e_countries.length-1;i++){

            HashMap mp= new HashMap();
            mp.put("imgid", mImageIds[i]);
            mp.put("ename", e_countries[i]);
            mp.put("cname", c_countries[i]);
            list.add(mp);
        }



        SimpleAdapter adapter=new SimpleAdapter(this,list,
                R.layout.gridview_item,
                new String[]{"imgid","ename","cname"},
                new int[]{R.id.flag_image,R.id.tv_ename,R.id.tv_cname});
        return adapter;
    }
}
