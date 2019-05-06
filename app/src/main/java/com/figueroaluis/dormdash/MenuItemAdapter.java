package com.figueroaluis.dormdash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuItemAdapter extends BaseAdapter implements Filterable {

    private Context mContext;

    private ArrayList<MenuItem> menuItemList;
    private ArrayList<MenuItem> originalListsForFilter;

    private LayoutInflater mInflater;

    private ArrayList<MenuItem> allMenuItems;
    private ArrayList<MenuItem> mpMenuItems;
    private ArrayList<MenuItem> gbMenuItems;
    private ArrayList<MenuItem> ccMenuItems;
    private ArrayList<MenuItem> coolerMenuItems;
    private ArrayList<MenuItem> filteredMenuItems;

    private ArrayList<String> selectedItemNames;
    private String itemsToString;

    public MenuItemAdapter(Context mContext, ArrayList<MenuItem> menuItemList) {
        this.mContext = mContext;
        this.menuItemList = menuItemList;
        this.originalListsForFilter = new ArrayList<>(menuItemList);

        this.selectedItemNames = new ArrayList<>();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<String> getSelectedItemNames(){
        return selectedItemNames;
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
                    selectedItemNames.add(selectedItem.getFoodName());
                    itemsToString = itemsArrayToString(selectedItemNames);
                }
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                if(charSequence == null || charSequence.length() == 0){
                    results.values = originalListsForFilter;
                    results.count = originalListsForFilter.size();

                }else{
                    ArrayList<MenuItem> filterResultData = new ArrayList<>();

                    for(MenuItem item : originalListsForFilter){
                        if(item.getFoodName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            filterResultData.add(item);
                        }
                    }
                    results.values = filterResultData;
                    results.count = filterResultData.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                menuItemList = (ArrayList<MenuItem>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder{
        public CheckBox checkBox;
        public TextView menuItemName;
        public TextView menuItemType;
    }

    public void printItems(ArrayList<String> items){
        for(int i = 0; i < items.size(); i++){
            if(i==items.size()-1){
                System.out.println(items.get(i));
            }else{
                System.out.println(items.get(i) + ", ");
            }
        }
    }

    public String itemsArrayToString(ArrayList<String> items){
        String itemsString = "";
        for(int i = 0; i < items.size(); i++){
            if(i==items.size()-1){
                itemsString+=items.get(i);
            }else{
                itemsString+=items.get(i)+", ";
            }
        }
        return itemsString;
    }
}
