package com.heschlie.twitterArchive.beans;

import com.heschlie.twitterArchive.entities.UserEntity;
import twitter4j.Status;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by heschlie on 11/3/2014. Copyright under Iridium Flare Games LLC.
 */

@Stateless
public class UserEJB {

    @PersistenceContext(unitName = "twitterArchivePU")
    private EntityManager em;

    public UserEntity createUser(UserEntity user) {
        em.persist(user);
        return user;
    }

    public UserEntity findUser(String username) {
        return em.find(UserEntity.class, username);
    }
}
