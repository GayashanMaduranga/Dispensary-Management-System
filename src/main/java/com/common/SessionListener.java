package com.common;

import org.hibernate.Session;

/**
 * Created by gayashan on 9/17/2017.
 */
public interface SessionListener {
    public void setSession(Session session);
}
