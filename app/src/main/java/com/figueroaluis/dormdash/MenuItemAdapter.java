package com.figueroaluis.dormdash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuItemAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MenuItem> menuItemList;
    private LayoutInflater mInflater;

    public MenuItemAdapter(Context mContext, ArrayList<MenuItem> menuItemList) {
        this.mContext = mContext;
        this.menuItemList = menuItemList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return menuItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return menuItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.fragment_menu_option, parent, false);

            holder = new ViewHolder();
            holder.checkBox = convertView.findViewById(R.id.menu_option_checkbox);
            holder.menuItemName = convertView.findViewById(R.id.menu_option_item_name);
            holder.menuItemType = convertView.findViewById(R.id.menu_option_item_type);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        CheckBox checkBox = holder.checkBox;
        TextView menuItemName = holder.menuItemName;
        TextView menuItemType = holder.menuItemType;

        final MenuItem menuItem = (MenuItem) getItem(position);

        menuItemName.setText(menuItem.getFoodName());
        menuItemName.setTextSize(16);

        menuItemType.setText(menuItem.getFoodType());
        menuItemType.setTextSize(12);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    MenuItem selectedItem = (MenuItem) getItem(position);
                    Toast.makeText(mContext,selectedItem.foodName,Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder{
        public CheckBox checkBox;
        public TextView menuItemName;
        public TextView menuItemType;
    }
}
