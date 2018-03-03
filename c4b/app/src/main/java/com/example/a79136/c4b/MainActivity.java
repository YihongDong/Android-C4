package com.example.a79136.c4b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MyAdapter myAdapter;
    private String[] names;
    private String[] times;
    private String[] sizes;
    private Button allcheck;
    private Button othercheck;
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
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化各个控件并准备资源
        listView =(ListView)findViewById(R.id.listview01);
        names=getResources().getStringArray(R.array.name_array);
        times=getResources().getStringArray(R.array.time_array);
        sizes=getResources().getStringArray(R.array.size_array);
        allcheck=(Button)findViewById(R.id.allCheck);
        othercheck=(Button)findViewById(R.id.otherCheck);
        //准备Adapter的各项数据并实例化自定义的MyAdapter
        final ArrayList itemList=new ArrayList<HashMap>();
        for(int i=0;i<names.length;i++){
            HashMap mp= new HashMap();
            mp.put("imgid", mImageIds[i]);
            mp.put("name",names[i]);
            mp.put("time",times[i]);
            mp.put("size",sizes[i]);
            itemList.add(mp);
        }
        myAdapter=new MyAdapter(itemList,this);
        //绑定Adapter
        listView.setAdapter(myAdapter);

        //全选按钮的回调接口
        allcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //遍历listView的长度，将MyAdapter中的map值全部设为true  
                for(int i=0;i<names.length;i++)
                    MyAdapter.getIsSelected().put(i,true);
                //刷新listview
                myAdapter.notifyDataSetChanged();
            }
        });

        //反选按钮的回调接口 
        othercheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历listView的长度，将已选的设为未选，未选的设为已选  
                for(int i=0;i<names.length;i++)
                    if(MyAdapter.getIsSelected().get(i))
                        MyAdapter.getIsSelected().put(i,false);
                    else
                        MyAdapter.getIsSelected().put(i,true);
                //刷新listview
                myAdapter.notifyDataSetChanged();
            }
        });

        //绑定listView的监听器 
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化cb实例的步骤
                MyAdapter.ViewHolder holder=(MyAdapter.ViewHolder) view.getTag();
                //改变CheckBox的状态 
                holder.cb.toggle();
                //将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(position,holder.cb.isChecked());
            }
        });
    }
}
