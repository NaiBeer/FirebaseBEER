package com.livessolutions.firebasebeer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.livessolutions.firebasebeer.MainActivity;
import com.livessolutions.firebasebeer.R;

/**
 * Created by Admins on 11/25/17.
 */

public class RegisterFragment extends Fragment {

    //    Explicit
    private String tag = "25NovV1";
    private String nameString, emailString, passwordString;




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //        Create Toolbar
        createToolbar();

//        Create Menu Icon
        setHasOptionsMenu(true);


    } // Main Method


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemSave) {
            checkSpace();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void checkSpace() {

        Log.d(tag, "CheckSpace Work");

//        Initail View

        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText emailEditText = getView().findViewById(R.id.edtMail);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//        Get Value_From EditText
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();



    } // checkSpace

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_save, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Setup Title

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar()
                .setSubtitle(getString(R.string.Sub_register));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
} // Main Class
