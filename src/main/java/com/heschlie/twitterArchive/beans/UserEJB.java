package com.heschlie.twitterArchive.beans;

import com.heschlie.twitterArchive.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public UserEntity findUser(Long userID) {
        return em.find(UserEntity.class, userID);
    }

    public UserEntity updateUser(UserEntity user) {
        if (findUser(user.getUserID()) != null) {
            em.merge(user);
        } else {
            em.persist(user);
        }
        return user;
    }
}
