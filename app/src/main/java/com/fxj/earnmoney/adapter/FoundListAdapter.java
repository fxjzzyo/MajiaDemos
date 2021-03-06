package com.fxj.earnmoney.adapter;

import android.view.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fxj.earnmoney.R;
import com.fxj.earnmoney.javabean.Info;

import java.util.List;

public class FoundListAdapter extends BaseAdapter {
    private List<Info> infos;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FoundListAdapter(Context context, List<Info> infos) {
        this.mContext = context;
        this.infos = infos;
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.list_item_fount_layout, null);

            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvSalary = convertView.findViewById(R.id.tv_salary);
            holder.tvType = convertView.findViewById(R.id.tv_type);
            holder.tvPersons = convertView.findViewById(R.id.tv_person_count);
            holder.ivPic = convertView.findViewById(R.id.iv_pic);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Info info = infos.get(position);


        holder.tvTitle.setText(info.getTitle());
        holder.tvSalary.setText(info.getSalary());
        holder.tvType.setText(info.getType());
        holder.tvPersons.setText(info.getPersonCount());
        holder.ivPic.setImageResource(info.getPicture());
        return convertView;
    }

    class ViewHolder{
        TextView tvTitle,tvSalary,tvType,tvPersons;
        ImageView ivPic;
    }
}
