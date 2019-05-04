package com.figueroaluis.dormdash;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentProfileAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ProfileOptionItem> optionItems;
    private LayoutInflater inflater;


    public FragmentProfileAdapter(Context mContext, ArrayList<ProfileOptionItem> optionItems) {
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
            convertView = inflater.inflate(R.layout.fragment_profile_option_item, parent, false);

            holder = new ViewHolder();

            holder.profileOptionText = convertView.findViewById(R.id.profile_option_text);
            holder.profileOptionIcon = convertView.findViewById(R.id.profile_option_icon);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        TextView profileOptionText = holder.profileOptionText;
        ImageView profileOptionIcon = holder.profileOptionIcon;

        ProfileOptionItem optionItem = (ProfileOptionItem) getItem(position);

        profileOptionText.setText(optionItem.getText());
        profileOptionText.setTextColor(ContextCompat.getColor(mContext, R.color.orangeProto));
        profileOptionText.setTextSize(18);

        if(profileOptionText.getText().equals("Payment")){
            Picasso.get().load(R.drawable.payment_icon).into(profileOptionIcon);
        } else if(profileOptionText.getText().equals("Help")){
            Picasso.get().load(R.drawable.help_icon).into(profileOptionIcon);
        } else if(profileOptionText.getText().equals("Settings")) {
            Picasso.get().load(R.drawable.settings_icon).into(profileOptionIcon);
        } else if(profileOptionText.getText().equals("About")) {
            Picasso.get().load(R.drawable.about_icon).into(profileOptionIcon);
        }

        return convertView;
    }

    private static class ViewHolder{
        public TextView profileOptionText;
        public ImageView profileOptionIcon;
    }
}
