package com.qa.accproj.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.qa.accproj.formbean.AppUserForm;
import com.qa.accproj.model.AppUser;
import org.springframework.stereotype.Repository;
 
@Repository
public class AppUserDAO {
 
    private static final Map<Integer, AppUser> USERS_MAP = new HashMap<>();
 
    static {
    
    initDATA();
    }
 
    private static void initDATA() {
 
        AppUser john = new AppUser(1234, "John", "Doe");
        AppUser jane = new AppUser(5678, "Jane", "Doe");
 
        USERS_MAP.put(john.getAccountNumber(), john);
        USERS_MAP.put(jane.getAccountNumber(), jane);
    }
  
    public AppUser findAppUserByAccountNumber(int accountNumber) {
        Collection<AppUser> appUsers = USERS_MAP.values();
        for (AppUser u : appUsers) {
            if (u.getAccountNumber()==(accountNumber)) {
                return u;
            }
        }
        return null;
    }
    
    public List<AppUser> getAppUsers() {
        List<AppUser> list = new ArrayList<>();
 
        list.addAll(USERS_MAP.values());
        return list;
    }
 
    public AppUser createAppUser(AppUserForm form) {
        
        AppUser user = new AppUser(form.getAccountNumber(), form.getFirstName(), form.getLastName());
 
        USERS_MAP.put(form.getAccountNumber(), user);
        System.out.println("User added");
        return user;
    }
    
    public AppUser deleteAppUser(AppUserForm form1) {
    	AppUser user1 = new AppUser(form1.getAccountNumber(), form1.getFirstName(), form1.getLastName());
    	
    	USERS_MAP.remove(form1.getAccountNumber());
    	System.out.println("User deleted");
    	return user1;
    }
    
    public AppUser editAppUser (AppUserForm form2) {
    	AppUser user2 = new AppUser(form2.getAccountNumber(), form2.getFirstName(), form2.getLastName());
    	
    	USERS_MAP.replace(form2.getAccountNumber(), user2);
    	System.out.println("User edited");
    	return user2;
    }
 
}