package com.example.mainactivity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Jetpack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetpack_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, JetpackFragment.newInstance())
//                    .commitNow();
//        }
        Toolbar toolbar = findViewById(R.id.main_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getNavController().addOnNavigatedListener((controller, destination) -> {
            setTitle(destination.getLabel());
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return getNavController().navigateUp();
    }

    public NavController getNavController() {
        return Navigation.findNavController(this, R.id.jetpackMainFragment);
    }
}
