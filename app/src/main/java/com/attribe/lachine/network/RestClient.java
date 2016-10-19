package com.attribe.lachine.network;

import com.attribe.lachine.utils.Constants;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by Maaz on 10/18/2016.
 */
public class RestClient {



    private static ApiInterface mApiInterface;

    public RestClient(){

    }

    static{
        setUpRestClient();
    }

    public static void setUpRestClient() {

        RequestInterceptor interceptor = new RequestInterceptor(){

            @Override
            public void intercept(RequestFacade request) {

                request.addHeader("Content-Type","application/json");
                request.addHeader("Accept","application/json");
                request.addHeader("Authorization","fff80796-6f42-4be0-a51d-e2749a902556");     // for stage

            }
        };

        OkHttpClient okHttpClient=new OkHttpClient();

        okHttpClient.setReadTimeout(Constants.TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(Constants.TIMEOUT, TimeUnit.MILLISECONDS);



        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://stage.dmenu.co:3000/api/v1")
                .setRequestInterceptor(interceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)

                .setClient(new OkClient(okHttpClient))
                .build();

        mApiInterface = restAdapter.create(ApiInterface.class);

    }

    public static ApiInterface getAdapter(){

        return mApiInterface;
    }
}
