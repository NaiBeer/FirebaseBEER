package com.livessolutions.firebasebeer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.livessolutions.firebasebeer.R;
import com.livessolutions.firebasebeer.utility.MyAlertDialog;

/**
 * Created by Admins on 11/25/17.
 */

public class MainFragment extends Fragment {

    // Explicit
    private String emailString, passwordString;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller

        registerController();


//        Login Controller
        loginController();


    } // Main Method onActivityCreated

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Initial View
//
         EditText emailEditText = getView().findViewById(R.id.edtMail);
         EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//         Get Value From Edit Text

            emailString = emailEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

//            Check Space

                if (emailString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space

                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myNormalDialog("Have Space",
                            getString(R.string.Sub_register));
                } else {
//                    No Space
                    CheckEmailAnPass();



                }



            }


        });
    }

    private void CheckEmailAnPass() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailString,passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Welcome",
                                    Toast.LENGTH_SHORT).show();

                            processMoveToService();


                        } else {

                            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                            myAlertDialog.myNormalDialog("Authen False",
                                    task.getException().getMessage());

                        }

                    }
                });
    } //check EmailAnpass

    private void processMoveToService() {

        //find UID of Current Login
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String strUID = firebaseUser.getUid();
        Log.d("26NovV4", "strUID == " + strUID);

        //Replace Fragement and Put Value
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentMainFragment,
                        ServiceFragment.serviceInstance(strUID)).commit();





    } // processMoveToService

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null).commit();



            } // OnClick
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
} // Main Class
