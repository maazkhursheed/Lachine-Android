package com.attribe.lachine.fragments;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attribe.lachine.R;
import com.attribe.lachine.adapters.WishListAdapter;
import com.attribe.lachine.models.MenusItem;
import com.attribe.lachine.utils.WishList;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishItems extends Fragment {

public RecyclerView WishList_recyclerview;




    public WishItems() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View List1 = inflater.inflate(R.layout.fragment_wish_items, container, false);
        //   ((AppCompatActivity)getActivity()).getSupportActionBar().show();

        WishList_recyclerview = (RecyclerView)List1.findViewById(R.id.wishList);
        // WishListAdapter adapter=new WishListAdapter(getActivity(),WishList.List);
        //WishList_recyclerview.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        WishListAdapter adapter=new WishListAdapter(getActivity(),WishList.List);

        WishList_recyclerview.setLayoutManager(mLayoutManager);
        WishList_recyclerview.setItemAnimator(new DefaultItemAnimator());
        WishList_recyclerview.setAdapter(adapter);
        adapter.SetOnItemClickListner(new WishListAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Bundle data = new Bundle();
                MenusItem item = WishList.List.get(position);
                data.putSerializable("Item", (Serializable) item);
                ItemDetailFragment detailsFragment = new ItemDetailFragment();
                detailsFragment.setArguments(data);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, detailsFragment).commit();
            }
        });
        //    WishList_recyclerview.setAdapter(mAdapter);


        // adapter.notifyDataSetChanged();
//        reloadFragment();

        // adapter.notifyDataSetChanged();
        // reloadAllData();
        return List1;

    }

}
