package com.example.btl_nhom_7.User.adapter;

import com.example.btl_nhom_7.User.model.User;

import java.util.ArrayList;

public class UserAdapter {
    private ArrayList<User> userArrayList = new ArrayList<User>();

    public UserAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
    private void addUser(User u){
        userArrayList.add(u);
    }
    private boolean checkExistedUser(User u){
        int countUser=0;
        for (User user: userArrayList){
            if(user.getPhoneNumber().contains(u.getPhoneNumber())&&user.getPassword().contains(u.getPhoneNumber())){
                countUser ++;
            }
        }
        if (countUser>1) {
            return true;
        }
        else {
            return false;
        }
    }
}
