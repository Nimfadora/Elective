package service.impl;


import electiveException.PageNotFoundException;
import electiveException.UserIsBannedException;
import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthService {

    public static String getOutputURL(String requestURL, User user){
        if(user==null){
            return "/";
        }

        handleURL("admin", requestURL, user);
        handleURL("tutor", requestURL, user);
        handleURL("student", requestURL, user);


        return requestURL;
    }
    private static void handleURL(String pattern, String requestURL, User user){
        Pattern r = Pattern.compile(".*"+pattern+".*");

        Matcher m = r.matcher(requestURL);
        if(m.find()){
            if(!user.getRole().equals(pattern))
                throw new PageNotFoundException();
            else if(user.getRole().equals("student")&&StudentServiceImpl.getInstance().getBanStatus(user.getId()))
                throw new UserIsBannedException();
        }
    }
}
