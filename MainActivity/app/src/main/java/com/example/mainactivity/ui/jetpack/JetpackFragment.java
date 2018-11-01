package com.example.mainactivity.ui.jetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mainactivity.R;
import com.example.mainactivity.databinding.JetpackFragmentBinding;

import androidx.navigation.Navigation;

public class JetpackFragment extends Fragment {

    private JetpackFragmentBinding binding;
    private JetpackViewModel mViewModel;
    private AdvancedUser advancedUser;

    public static JetpackFragment newInstance() {
        return new JetpackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.jetpack_fragment, container, false);

        User user = new User("Jetpack", "google");
        //set view model to layout file
        binding.setUser(user);

        LogJetmessageHandler messageHandler = new LogJetmessageHandler();
        binding.setMessageHandler(messageHandler);

        binding.jackButton.setOnClickListener( view -> {
            String s = "Jetpack+added";
            String string = binding.jackMessage.getText().toString() + "\n" + s;
            mViewModel.updateDataValue(string);
        });

        //Observable
        advancedUser = new AdvancedUser();
        advancedUser.firstName.set("Apple");
        advancedUser.lastName.set("Steven");
        advancedUser.age.set(18);
        binding.setAdvancedUser(advancedUser);

        binding.dataBindingTextView.setOnClickListener(view -> {
            String lastName = advancedUser.lastName.get();
            advancedUser.lastName.set(advancedUser.firstName.get());
            advancedUser.firstName.set(lastName);
            advancedUser.age.set(advancedUser.age.get() + 1);
        });

        binding.navigateButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_jetpackFragment_to_helloBlankFragment);
//            findNavController(view).navigate(R.id.action_jetpackFragment_to_helloBlankFragment);
        });

        StockLiveData stockLiveData = new StockLiveData("888888");
        stockLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                mViewModel.updateDataValue(s);
            }
        });

        //transformations
        /*
        LiveData<String> transformationsData= Transformations.map(mViewModel.getUserLiveData(), user -> user.lastName + " " + user.firstName);

        transformationsData.observe(this, string -> {

        });
        //Switch map
        LiveData<User> user = Transformations.switchMap(transformationsData, name -> mViewModel.getUserLiveData());

        user.observe(this, userInfo -> {

        });
        */

        //life cycle
        JetpackLifecycleListener listener = new JetpackLifecycleListener(this.getContext(), getLifecycle(), string -> {
           System.out.println("call back: " + string);
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JetpackViewModel.class);
        // TODO: Use the ViewModelSystem.console
        mViewModel.getStringData().observe(this, string -> {
            binding.jackMessage.setText(string);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //
    }
}
