package com.figueroaluis.dormdash;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentProfileFoodAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ProfileOptionFoodItem> optionItems;
    private LayoutInflater inflater;


    public FragmentProfileFoodAdapter(Context mContext, ArrayList<ProfileOptionFoodItem> optionItems) {
        this.mContext = mContext;
        this.optionItems = optionItems;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return optionItems.size();
    }

    @Override
    public Object getItem(int i) {
        return optionItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_fragment_profile_food, parent, false);

            holder = new ViewHolder();

            holder.foodText = convertView.findViewById(R.id.textView_foodItem);
            holder.specialRequest = convertView.findViewById(R.id.editText_foodDescription);
            holder.selectFoodItem = convertView.findViewById(R.id.checkbox_selectFood);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView foodText = holder.foodText;
        TextView specialRequest = holder.specialRequest;
        TextView selectFoodItem = holder.selectFoodItem;

        ProfileOptionFoodItem optionItem = (ProfileOptionFoodItem) getItem(position);

        foodText.setText(optionItem.getText());
        foodText.setTextColor(ContextCompat.getColor(mContext, R.color.orangeProto));
        foodText.setTextSize(18);

//        if(foodText.getText().equals("Payment")){
//            Picasso.get().load(R.drawable.payment_icon).into(profileOptionIcon);
//        } else if(profileOptionText.getText().equals("Help")){
//            Picasso.get().load(R.drawable.help_icon).into(profileOptionIcon);
//        } else if(profileOptionText.getText().equals("Settings")) {
//            Picasso.get().load(R.drawable.settings_icon).into(profileOptionIcon);
//        } else if(profileOptionText.getText().equals("About")) {
//            Picasso.get().load(R.drawable.about_icon).into(profileOptionIcon);
//        }

        return convertView;
    }

    private static class ViewHolder {
        //parameters that will go inside of each item
        //food item
        public TextView foodText;
        public EditText specialRequest;
        public CheckBox selectFoodItem;
    }
}