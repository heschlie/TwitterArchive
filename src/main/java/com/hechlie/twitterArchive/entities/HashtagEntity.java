package com.hechlie.twitterArchive.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by heschlie on 11/2/2014. Copyright under Iridium Flare Games LLC.
 */

@Entity
public class HashtagEntity {

    private String hashtag;
    private List<TweetEntity> tweetsUsed;

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
}
