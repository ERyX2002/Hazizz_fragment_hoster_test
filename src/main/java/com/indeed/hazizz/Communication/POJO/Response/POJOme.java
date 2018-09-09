package com.indeed.hazizz.Communication.POJO.Response;

import com.indeed.hazizz.Communication.POJO.POJOInterface;

import java.util.ArrayList;
import java.util.List;

public class POJOme implements POJOInterface {



    public Object getValue(String key) {
        return null;
    }

    @Override
    public List<Object> getList() {
        return null;
    }

    @Override
    public Object getInnerClass() {
        return null;
    }

    public int id;
    public String username;
    public String emailAddress;

    public List<String> permissions;
    public List<Group> groups;

    public Boolean locked;
    public Boolean disabled;
    public Boolean expired;
    public String registrationDate;
    public String lastPasswordReset;

    class Group{
        public int id;
        public String name;
        public String groupType;
        public int userCount;
    }

}
