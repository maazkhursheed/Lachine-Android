package com.attribe.lachine.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.attribe.lachine.R;
import com.attribe.lachine.adapters.MenuAdapter;
import com.attribe.lachine.models.MenusItem;
import com.attribe.lachine.network.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Maaz on 10/18/2016.
 */
public class MenuFragment extends Fragment {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menus, container, false);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        recyclerView = (RecyclerView)rootView.findViewById(R.id.listMenu);
        prepareMenus();

        return rootView;
    }

    private void prepareMenus() {
        int cat_id = getArguments().getInt("Category_id",1);

        RestClient.getAdapter().getMenuItems(cat_id, new Callback<ArrayList<MenusItem>>() {
            @Override
            public void success(ArrayList<MenusItem> menusItems, Response response) {

                if (!menusItems.isEmpty()) {
                    menuAdapter = new MenuAdapter(getActivity().getApplicationContext(), menusItems);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(menuAdapter);

                } else {
                    Toast.makeText(getActivity(), "No Menu Found of this category", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getActivity(), "Menus not obtaned !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
