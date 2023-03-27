package com.menachem.ex3.controller;

import com.menachem.ex3.repo.GithubUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is controller of the history page
 * its handel the requests and the response of it.
 */
@Controller
public class HistoryController {

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
     * This GET method return the history page with list of top 10.
     * This method called by "/history" path.
     * @param model
     * @return history page
     */
    @GetMapping("/history")
    public String getHistory(Model model){
        model.addAttribute("usersHistory", getRepo().findFirst10ByOrderByCounterDesc());
        return "history";
    }

    /**
     * This function delete all records from database table and redirect to history page
     * @param response
     * @param model
     * @throws IOException
     */
    @GetMapping("/clear")
    public void getClear(HttpServletResponse response, Model model) throws IOException {
        getRepo().deleteAll();
        response.sendRedirect("history");
    }

}
