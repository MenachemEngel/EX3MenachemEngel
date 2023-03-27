package com.menachem.ex3.controller;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.menachem.ex3.repo.GithubUser;
import com.menachem.ex3.repo.GithubUserRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is controller of the github search page and result page.
 * its handel the requests and the response of it.
 */
@Controller
public class SearchController {

    /**
     * define database table
     */
    @Autowired
    private GithubUserRepo repository;

    /**
     * this function is return the database table
     * @return the database table
     */
    private GithubUserRepo getRepo() {
        return repository;
    }

    /**
     * this function handel get request of "/" path
     * @param model
     * @return search page
     */
    @GetMapping("/")
    public String getSearchPage(Model model){
        model.addAttribute("errStyle",0);
        return "search";
    }

    /**
     * this function handel post request of "/search" path
     * and add values to database table
     * @param user
     * @param result
     * @param username
     * @param model
     * @return search page or results page
     */
    @GetMapping("/search")
    public String getSearch(@Valid GithubUser user, BindingResult result, @RequestParam("name") String username, Model model){
        username = username.toLowerCase();
        if(result.hasErrors()){
            model.addAttribute("errStyle",1);
            model.addAttribute("errMsg","Unexpect error occurred");
            return "search";
        }
        try {
            if(username==""){
                model.addAttribute("errStyle",1);
                model.addAttribute("errMsg","Please enter username");
                return "search";
            }
            List<String> followers = getGithubJsons(username);
            JSONObject jsonObject = new JSONObject(followers.get(0));
            JSONArray jsonArray = new JSONArray(followers.get(1));
            List<String> followersList = new ArrayList();
            for(int i=0; i < jsonArray.length(); i++){
                followersList.add(jsonArray.getJSONObject(i).get("login").toString());
            }
            model.addAttribute("username", jsonObject.get("login"));
            model.addAttribute("numOfFollowers", jsonObject.get("followers"));
            model.addAttribute("followers", followersList);
            synchronized (this) {
                if (getRepo().findByName(username).isEmpty()) {
                    user.setLink((String)jsonObject.get("html_url"));
                    getRepo().save(user);
                } else {
                    GithubUser u = getRepo().findByName(username).get(0);
                    u.increase();
                    getRepo().save(u);
                }
            }
            return "results";
        } catch (IOException /*| MalformedURLException*/ | JSONException e) {
            model.addAttribute("errStyle",1);
            model.addAttribute("errMsg", "The username not exist in GitHub data system.");
            return "search";
        }
    }

    /**
     * this function handel get request of "/results" path
     * and redirect to "/" path.
     * @param model
     * @return search page.
     */
    @GetMapping("/results")
    public String getResults(Model model, HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
        return "search";
    }

    /**
     * this function is call to github api and get the json response.
     * @param username
     * @return the all's followers json as string
     * @throws IOException
     */
    private List<String> getGithubJsons(String username) throws IOException {
        List<String> list = new ArrayList<>();
        URL url = new URL("https://api.github.com/users/"+username);
        list.add(readData(url));
        url = new URL("https://api.github.com/users/"+username+"/followers");
        list.add(readData(url));
        return list;
    }

    /**
     * This function read the data from github
     * @param url
     * @return string with the data.
     * @throws IOException
     */
    private String readData(URL url) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }

}
