package com.example.a79136.c4b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by 79136 on 2018/1/4.
 */
public class MyAdapter extends BaseAdapter {
    // 填充数据的list
    private ArrayList<HashMap> list;
    private String[] districts, postcodes;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;
    //用来控制CheckBox的选中状况
    private static HashMap<Integer,Boolean>isSelected;

    // 构造器
    public MyAdapter(ArrayList<HashMap> list, Context context) {
        this.context = context;
        this.list = list;
        isSelected=new HashMap<Integer, Boolean>();
        inflater = LayoutInflater.from(context);
        // 初始化数据
        initData();
    }
    // 初始化isSelected的数据 
    public void initData(){
        for (int i=0;i<list.size();i++){
            getIsSelected().put(i,false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.listview_item_simpleadapter,null);
            holder.im=(ImageView) convertView.findViewById(R.id.apk_image);
            holder.tv1 = (TextView) convertView.findViewById(R.id.apk_name);
            holder.tv2 = (TextView) convertView.findViewById(R.id.apk_time);
            holder.tv3=(TextView) convertView.findViewById(R.id.apk_size);
            holder.cb = (CheckBox) convertView.findViewById(R.id.ck_select);
            // 为view设置标签
            convertView.setTag(holder);

        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }
        //设置list中ImageView的显示
        holder.im.setImageResource((int)((HashMap) list.get(position)).get("imgid"));
        // 设置list中TextView的显示
        holder.tv1.setText((String) ((HashMap) list.get(position)).get("name"));
        holder.tv2.setText((String) ((HashMap) list.get(position)).get("time"));
        holder.tv3.setText((String) ((HashMap) list.get(position)).get("size"));
        // 根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }
    public  static HashMap<Integer,Boolean> getIsSelected(){
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected){
        MyAdapter.isSelected=isSelected;
    }
    public static class ViewHolder {
        ImageView im;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        CheckBox cb;
    }
}
