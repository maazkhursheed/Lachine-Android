package com.attribe.lachine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.attribe.lachine.R;
import com.attribe.lachine.models.MenusItem;

import java.util.List;

/**
 * Created by Maaz on 10/18/2016.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>  {

    Context mContext;
    private List<MenusItem> menusList;
   // OnItemClickListner menuItemClickListner;

    public MenuAdapter(Context mContext, List<MenusItem> menusList) {
        this.mContext = mContext;
        this.menusList = menusList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView menu_item_name,menu_item_price, menu_item_viewBtn;
        ImageView item_pic, item_fvrt, item_cart;

        public MyViewHolder(View view) {
            super(view);

            menu_item_name = (TextView) view.findViewById(R.id.item_name);
            menu_item_viewBtn = (TextView) view.findViewById(R.id.item_viewBtn);
            menu_item_price = (TextView) view.findViewById(R.id.item_price);
            item_pic= (ImageView) view.findViewById(R.id.itemMenu_image);
            item_fvrt = (ImageView) view.findViewById(R.id.itemMenu_fvrt);
            item_cart  = (ImageView) view.findViewById(R.id.itemMenu_cart);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenusItem menus = menusList.get(position);

        holder.menu_item_name.setText("" + menus.getName());
        holder.menu_item_price.setText("Rs" + " " + (int) menus.getPrice());


//        if(menus.getImages().size() > 0) {
//            Picasso.with(mContext).load(menus.getImages().get(0).getUrl()).
//                    placeholder(R.drawable.progress_animation).resize(100, 100).into(holder.item_pic);
//        }else{
//            holder.item_pic.setBackgroundResource(R.drawable.fastfood);
//        }

    }
    @Override
    public int getItemCount() {
        return menusList.size();
    }
}
