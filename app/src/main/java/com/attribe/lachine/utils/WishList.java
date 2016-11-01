package com.attribe.lachine.utils;

import android.content.Context;
import android.widget.Toast;
import com.attribe.lachine.models.MenusItem;

import java.util.ArrayList;

/**
 * Created by Talha Ghaffar on 10/27/2016.
 */
public class WishList {
    Context mContext;

    public WishList(Context mContext1) {
        this.mContext = mContext1;
    }


    public static ArrayList<MenusItem> List = new ArrayList<>();

    public void RemoveItems(MenusItem item) {

        List.remove(item);
        Toast.makeText(mContext, "Remove From Wishlist", Toast.LENGTH_SHORT).show();
    }

    public  void Additems(MenusItem item) {

        boolean isExist = false;
        if(!List.isEmpty()) {

            for (int i = 0; i < List.size(); i++) {

                if (item.getId() == List.get(i).getId()) {
                    Toast.makeText(mContext.getApplicationContext(),"Already Exists",Toast.LENGTH_SHORT).show();
                    isExist = true;
                }
            }

            if(!isExist) {
                List.add(item);
                Toast.makeText(mContext, "Added to wishlist", Toast.LENGTH_SHORT).show();

            }
        }
        else {

            List.add(item);
            Toast.makeText(mContext, "Added to wishlist", Toast.LENGTH_SHORT).show();
        }
    }
}
