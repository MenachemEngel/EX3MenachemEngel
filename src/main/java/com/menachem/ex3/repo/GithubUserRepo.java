package com.menachem.ex3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * This interface is's contain function for database in jpa syntax.
 */
public interface GithubUserRepo extends JpaRepository<GithubUser, Long> {

    /**
     * This function search some name in database table.
     * @param name
     * @return list of all name founds in database table.
     */
    List<GithubUser> findByName(String name);

    /**
     * This function search the top 10 of popular users in database table,
     * and order them from the biggest to the smallest.
     * @return list of the users with the biggest counter.
     */
    List<GithubUser> findFirst10ByOrderByCounterDesc();

    /**
     * This function delete all the database table.
     */
    void deleteAll();
}
