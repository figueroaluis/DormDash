package com.figueroaluis.dormdash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FragmentProfileFood extends Fragment implements View.OnClickListener {

    private ListView mListView;
    private Context mContext;
    private FragmentProfileFoodAdapter adapter;
    private ArrayList<String> optionItemNames;
    private ArrayList<ProfileOptionFoodItem> optionItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout
        View view = inflater.inflate(R.layout.fragment_fragment_profile_food, container, false);

        mContext = getContext();

        //establish connections to the database
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/DormDash_db";
        final String USER = "root";
        final String PASSWORD = "Carol13Uc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //open connection
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

//			String getFoods = "SELECT foodItem, mealType FROM menu WHERE date "
//					+ "LIKE '(SELECT monthname(current_date)) (SELECT day(current_date)), (SELECT year(current_date))'";

            ps = conn.prepareStatement("SELECT foodItem, mealType FROM menu WHERE date LIKE 'April 26, 2019'");

            rs = ps.executeQuery();

            optionItemNames = new ArrayList<>();
            optionItemList = new ArrayList<>();

            if(rs.next()) {
                while(rs.next()) {
                    //System.out.println(rs.getString("foodItem"));
                    //System.out.println(rs.getString("mealType"));
                    optionItemNames.add(rs.getString("foodItem"));
                }
            }

            ps.close();
            conn.close();

        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < optionItemNames.size(); i++){
            optionItemList.add(new ProfileOptionFoodItem(optionItemNames.get(i)));
        }

        adapter = new FragmentProfileFoodAdapter(getContext(), optionItemList);
        mListView = view.findViewById(R.id.textView_foodItem);
        mListView.setAdapter(adapter);



        return view;
    }

    @Override
    public void onClick(View view){

    }
}