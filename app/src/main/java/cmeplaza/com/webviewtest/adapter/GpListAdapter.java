package cmeplaza.com.webviewtest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import cmeplaza.com.webviewtest.R;
import cmeplaza.com.webviewtest.bean.GpBean;

/**
 * Created by klx on 2018/3/1.
 */

public class GpListAdapter extends BaseAdapter {
    private Context mContent;
    private List<GpBean> mDatas;

    public GpListAdapter(Context mContent, List<GpBean> mDatas) {
        this.mContent = mContent;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContent, R.layout.listview_item_gpinfo, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DecimalFormat format = new DecimalFormat("###,###,###.##");

        GpBean gpBean = mDatas.get(position);
        holder.tv_code.setText(gpBean.getCode());
        holder.tv_name.setText(gpBean.getName());
        holder.tv_number.setText(String.valueOf(gpBean.getNumber()));
        holder.tv_price.setText(String.valueOf(gpBean.getPrice()));
        holder.tv_now_price.setText(String.valueOf(gpBean.getNowPrice()));
        holder.tv_change.setText(format.format(gpBean.getChange()));
        holder.tv_result.setText(format.format(gpBean.getChange() * gpBean.getNumber()));
        holder.tv_min_price.setText("低："+String.valueOf(gpBean.getMinPrice()));
        holder.tv_max_price.setText("高："+String.valueOf(gpBean.getMaxPrice()));

        return convertView;
    }

    public class ViewHolder {
        public TextView tv_name;
        public TextView tv_code;
        public TextView tv_number;
        public TextView tv_price;
        public TextView tv_now_price;
        public TextView tv_change;
        public TextView tv_change_percent;
        public TextView tv_result;

        public TextView tv_min_price;
        public TextView tv_max_price;

        public ViewHolder(View convertView) {
            tv_name = convertView.findViewById(R.id.tv_name);
            tv_code = convertView.findViewById(R.id.tv_code);
            tv_number = convertView.findViewById(R.id.tv_number);
            tv_price = convertView.findViewById(R.id.tv_price);
            tv_now_price = convertView.findViewById(R.id.tv_now_price);
            tv_change = convertView.findViewById(R.id.tv_change);
            tv_change_percent = convertView.findViewById(R.id.tv_change_percent);
            tv_result = convertView.findViewById(R.id.tv_result);
            tv_min_price = convertView.findViewById(R.id.tv_min_price);
            tv_max_price = convertView.findViewById(R.id.tv_max_price);
        }
    }
}
