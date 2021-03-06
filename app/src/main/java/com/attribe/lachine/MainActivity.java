package com.attribe.lachine;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import com.attribe.lachine.adapters.DrawerExpandableListAdapter;
import com.attribe.lachine.fragments.MenuFragment;
import com.attribe.lachine.models.Category;
import com.attribe.lachine.models.DrawerGroupItems;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerList;
    ArrayList<DrawerGroupItems> drawerGroupItems;
    ArrayList<String> categoryList;
    ArrayList<Category> actualCategories;
    private DrawerExpandableListAdapter expandableListAdapter;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getCategories();
        menuItemTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        toolbar= (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        mTitle = mDrawerTitle = getSupportActionBar().getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ExpandableListView) findViewById(R.id.list_slidermenu);

//        LayoutInflater inflater = getLayoutInflater();
//        View listHeaderView = inflater.inflate(R.layout.drawer_header,null, false);
//        mDrawerList.addHeaderView(listHeaderView,null,false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void getCategories() {
        ArrayList<Category> categoriesListObtained = (ArrayList<Category>) getIntent().getSerializableExtra("Obtained_Categories");
        actualCategories = categoriesListObtained;

        categoryList = new ArrayList<String>();

        for( Category groupCategoryItems : categoriesListObtained){

            categoryList.add(groupCategoryItems.getName());
        }

        makeDrawerList();
    }

    private void makeDrawerList() {

        drawerGroupItems= new ArrayList<DrawerGroupItems>();

        ArrayList<String> childListOrder= new ArrayList<String>();
        childListOrder.add("Online Order");
        childListOrder.add("My reservations");
        DrawerGroupItems drawerGroupOrder = new DrawerGroupItems("My Order",childListOrder,null);
        drawerGroupItems.add(drawerGroupOrder);

        ArrayList<String> childListReservation= new ArrayList<String>();
        childListReservation.add("Make Reservation");
        DrawerGroupItems drawerGroupReservation = new DrawerGroupItems("Reservation",childListReservation,null);
        drawerGroupItems.add(drawerGroupReservation);

        DrawerGroupItems drawerGroupMenu = new DrawerGroupItems("Menu",categoryList,null);
        drawerGroupItems.add(drawerGroupMenu);

        expandableListAdapter = new DrawerExpandableListAdapter(MainActivity.this,drawerGroupItems);
        mDrawerList.setAdapter(expandableListAdapter);
    }

    private void menuItemTransaction() {

        mDrawerList.setOnChildClickListener(new ChildItemListner());
    }

    private class ChildItemListner implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            if(groupPosition == 2) {
                category = actualCategories.get(childPosition);
                send_CategoryId(category.getId());
                setDrawerclosed();
                getSupportActionBar().setTitle(category.getName());
            }

            return true;
        }
    }

    private void send_CategoryId(int cat_id) {
        Bundle bundle=new Bundle();
        bundle.putInt("Category_id",cat_id);
        MenuFragment menufragment = new MenuFragment();
        menufragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, menufragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void setDraweropened() {
        mDrawerLayout.openDrawer(mDrawerList);
    }

    public void setDrawerclosed(){
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
