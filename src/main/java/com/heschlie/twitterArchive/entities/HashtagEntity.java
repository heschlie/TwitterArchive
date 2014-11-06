package com.heschlie.twitterArchive.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heschlie on 11/2/2014. Copyright under Iridium Flare Games LLC.
 */

@Entity
public class HashtagEntity {

    private String hashtag;
    private List<TweetEntity> tweetsUsed = new ArrayList<TweetEntity>();
    private List<UserEntity> usedBy = new ArrayList<UserEntity>();

    public HashtagEntity() {}

    @Id
    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    @ManyToMany
    public List<TweetEntity> getTweetsUsed() {
        return tweetsUsed;
    }

    public void setTweetsUsed(List<TweetEntity> tweetsUsed) {
        this.tweetsUsed = tweetsUsed;
    }

    @ManyToMany
    public List<UserEntity> getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(List<UserEntity> usedBy) {
        this.usedBy = usedBy;
    }
}
