package com.menachem.ex3.controller;

import com.menachem.ex3.bean.SessionBean;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.thymeleaf.standard.expression.GreaterOrEqualToExpression;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is controller of the login page
 * its handel the requests and the response of it.
 */
@Controller
public class LoginController {

    /**
     * Variable that contain the username that define in application.properties file.
     */
    @Value("${spring.security.user.name}")
    private String username;

    /**
     * Variable that contain the password that define in application.properties file.
     */
    @Value("${spring.security.user.password}")
    private String password;

    /**
     * This POST method manage the login request by check the username and password.
     * @param json
     * @param model
     * @param request
     * @return string in jason format that contain the status of the action.
     * @throws JSONException
     */
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String getLogin(@RequestBody String json, Model model, HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.getString("username").equals(username) && jsonObject.getString("password").equals(password)){
            request.getSession().setAttribute("sessionStatus", new SessionBean(true));
            return "{\"status\": \"Ok\"}";
        }else {
            return "{\"status\": \"NotOk\"}";
        }
    }

    /**
     * This GET method just return the login page.
     * This method called by "/login" path.
     * @return login page
     */
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    /**
     * This GET method hangs up the user.
     * This method called by "/logout" path.
     * @param model
     * @param request
     * @return login page
     */
    @GetMapping("/logout")
    public String getLogout(Model model, HttpServletRequest request){
        request.getSession().setAttribute("sessionStatus", new SessionBean());
        return "login";
    }

}
