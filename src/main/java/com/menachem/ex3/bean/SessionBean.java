package com.menachem.ex3.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * This is bean class that that action in session.
 * Tt contain boolean variable.
 */
@Component
public class SessionBean implements Serializable {

    /**
     * declare boolean variable called session boolean.
     */
    private boolean sessionStatus;

    /**
     * SessionBean constructor
     * init sessionBean to false.
     */
    public SessionBean() {
        sessionStatus = false;
    }

    /**
     * SessionBean constructor
     * init sessionBean with outside value.
     * @param sessionStatus
     */
    public SessionBean(boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * function that check the sessionBean value.
     * @return the sessionBean variable.
     */
    public boolean isSessionStatus() {
        return sessionStatus;
    }

    /**
     * function that set the sessionBean value.
     * @param sessionStatus
     */
    public void setSessionStatus(boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
