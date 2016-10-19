package com.attribe.lachine.models;

import java.util.ArrayList;

/**
 * Created by Maaz on 10/18/2016.
 */
public class DrawerGroupItems {

    String groupName;
    ArrayList<String> groupChild= new ArrayList<>();
    ArrayList<Category> categoriesList;

    public DrawerGroupItems(String groupName, ArrayList<String> stringChild ,ArrayList<Category> categoriesList) {
        this.groupName = groupName;
        this.groupChild = stringChild;
        this.categoriesList = categoriesList;
    }

    public ArrayList<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<String> getGroupChild() {
        return groupChild;
    }

    public void setGroupChild(ArrayList<String> groupChild) {
        this.groupChild = groupChild;
    }
}
