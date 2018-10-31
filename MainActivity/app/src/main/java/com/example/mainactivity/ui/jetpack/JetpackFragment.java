package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mainactivity.R;

import java.io.Console;


public class JetpackFragment extends Fragment {

    private JetpackViewModel mViewModel;
    private Button changeButton;
    private TextView messageView;

    public static JetpackFragment newInstance() {
        return new JetpackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jetpack_fragment, container, false);
        messageView = view.findViewById(R.id.jack_message);
        changeButton = view.findViewById(R.id.jack_button);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateDataValue("Hello Jetpack!");
            }
        });

        StockLiveData stockLiveData = new StockLiveData("888888");
        stockLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mViewModel.updateDataValue(s);
            }
        });

        //transformations
        LiveData<String> transformationsData= Transformations.map(mViewModel.getUserLiveData(), user -> user.lastName + " " + user.firstName);

        transformationsData.observe(this, string -> {

        });
        //Switch map
        LiveData<User> user = Transformations.switchMap(transformationsData, name -> mViewModel.getUserLiveData());

        user.observe(this, userInfo -> {

        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JetpackViewModel.class);
        // TODO: Use the ViewModelSystem.console

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //upate UI
                System.out.println("name changed: " + s);
                String string = messageView.getText().toString() + "\n" + s;
                messageView.setText(string);
            }
        };
        mViewModel.getData().observe(this, nameObserver);
    }

    @Override
    public void onStart() {
        super.onStart();
        //
    }
}
