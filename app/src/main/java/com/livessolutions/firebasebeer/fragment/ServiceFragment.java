package com.livessolutions.firebasebeer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.livessolutions.firebasebeer.R;

/**
 * Created by Admins on 11/26/17.
 */

public class ServiceFragment extends Fragment {

    //    Explicit
    private String tag = "26NovV3";
    private String uidString;
    private DatabaseReference databaseReference;
    private String nameDisplayString;



    public static ServiceFragment serviceInstance(String strID) {

        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("UID", strID);
        serviceFragment.setArguments(bundle);


        return serviceFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument

        uidString = getArguments().getString("UID");
        Log.d(tag, "Receive UID == " + uidString);

//        Find NameDisplay
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users");




    } // Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service,
                container, false);
        return view;
    }
}   //Main Class
