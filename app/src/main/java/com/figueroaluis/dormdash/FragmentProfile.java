package com.figueroaluis.dormdash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class FragmentProfile extends Fragment implements View.OnClickListener {

    private ListView mListView;
    private Context mContext;
    private FragmentProfileAdapter adapter;
    private ArrayList<String> optionItemNames;
    private ArrayList<ProfileOptionItem> optionItemList;
    private ImageView userImage;

    private Switch newSwitch;


    public class switchBoolean{
        private boolean workerModeOn;
        private String successMessage;

        public switchBoolean(boolean workerModeOn, String successMessage){
            this.workerModeOn = workerModeOn;
            this.successMessage = successMessage;
        }

        public boolean isWorkerModeOn(){
            return this.workerModeOn;
        }

        public String getSuccessMessage(){
            return this.successMessage;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mContext = getContext();

        newSwitch = view.findViewById(R.id.profile_option_icon);
        EventBus.getDefault().post(new switchBoolean(newSwitch.isChecked(), "Success"));

        optionItemNames = new ArrayList<>();
        optionItemList = new ArrayList<>();
        optionItemNames.add("Payment");
        optionItemNames.add("Help");
        optionItemNames.add("Settings");
        optionItemNames.add("About");
        optionItemNames.add("Log Out");

        for(int i = 0; i < optionItemNames.size(); i++){
            optionItemList.add(new ProfileOptionItem(optionItemNames.get(i)));
        }

        adapter = new FragmentProfileAdapter(getContext(), optionItemList);
        mListView = view.findViewById(R.id.option_items_listview);
        mListView.setAdapter(adapter);

        userImage = view.findViewById(R.id.profile_user_imageview);
        Picasso.get().load("https://i.kym-cdn.com/photos/images/newsfeed/001/487/781/ea0.jpg").into(userImage);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ProfileOptionItem selectedOption = optionItemList.get(position);

                if(selectedOption.text.equals("Log Out")){
                    // delete the shared preferences
                    // open up the new fragment that replaces the profile screen
                    Toast.makeText(getActivity().getApplicationContext(),"Log out is clicked",Toast.LENGTH_SHORT).show();


                }
            }
        });


        return view;
    }


    @Override
    public void onClick(View view){

    }
}

