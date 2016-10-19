package com.attribe.lachine.screens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.attribe.lachine.MainActivity;
import com.attribe.lachine.R;
import com.attribe.lachine.models.Category;
import com.attribe.lachine.network.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.util.ArrayList;

public class SplashScreen extends Activity {

    final int SPLASH_TIME_OUT = 1000;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread welcomeThread = new Thread() {
            int wait = 0;

            @Override
            public void run() {
                try {
                    super.run();
                    while (wait < SPLASH_TIME_OUT) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);
                } finally {
                    get_sendCategoriesToNextScreen();
                }
            }
        };
        welcomeThread.start();
    }

    private void get_sendCategoriesToNextScreen() {
        final Intent dataIntent = new Intent(getApplicationContext(),MainActivity.class);

      //  showProgress("Loading.....");
        RestClient.getAdapter().getCategories(new Callback<ArrayList<Category>>() {
            @Override
            public void success(ArrayList<Category> categories, Response response) {

               // hideProgress();
                categories.size();
                dataIntent.putExtra("Obtained_Categories",categories);
                startActivity(dataIntent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(SplashScreen.this, "Something goes wrong....", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showProgress(String message){
        progressDialog= ProgressDialog.show(getApplicationContext(),"",message,false);
    }

    private void hideProgress(){progressDialog.dismiss();}

}
