package com.heschlie.twitterArchive.beans;

import com.heschlie.twitterArchive.entities.HashtagEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by heschlie on 11/3/2014. Copyright under Iridium Flare Games LLC.
 */

@Stateless
public class HashtagEJB {

    @PersistenceContext(unitName = "twitterArchivePU")
    private EntityManager em;

    public HashtagEntity findHashtag(String tag) {
        return em.find(HashtagEntity.class, tag);
    }
}
