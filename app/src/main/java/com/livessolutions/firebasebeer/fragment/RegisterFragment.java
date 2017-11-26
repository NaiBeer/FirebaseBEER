package com.livessolutions.firebasebeer.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.livessolutions.firebasebeer.MainActivity;
import com.livessolutions.firebasebeer.R;
import com.livessolutions.firebasebeer.utility.MyAlertDialog;

/**
 * Created by Admins on 11/25/17.
 */

public class RegisterFragment extends Fragment {

    //    Explicit
    private String tag = "25NovV1";
    private String nameString, emailString, passwordString;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase databaseReference;








    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Setup Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        // databaseReference = FirebaseDatabase.getInstance();




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


//        Check Space
        if (nameString.isEmpty() || emailString.isEmpty() || passwordString.isEmpty()) {
//                Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
            myAlertDialog.myNormalDialog("Have Space",
                    getString(R.string.Sub_register));
        } else {
//                  No Space
            updateFirebase();


        }


    } // checkSpace

    private void updateFirebase() {

//        Setup ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please Wait ...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            // Successs

                            saveNameDisplayTofirebase();




                            Toast.makeText(getActivity(),"Update Firebase Success",
                                    Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager()
                                    .popBackStack();



                        } else {
                            // Non Success
                            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                            myAlertDialog.myNormalDialog("Cannot Update Firebase",
                                    task.getException().getMessage());

                        }


                    }
                });





    } // UpdateFirebase

    private void saveNameDisplayTofirebase() {

//        Get UID of Firebase
            // UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
            // firebaseUser.updateProfile()

        firebaseUser = firebaseAuth.getCurrentUser();

        showLog();




    }// saveNameDisplayTofirebase

    private void showLog() {

        String tag = "26NovV1";
        Log.d(tag, "UID == > " + firebaseUser.getUid());
        Log.d(tag, "Email == > " + firebaseUser.getEmail());




    }

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
