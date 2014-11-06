package com.heschlie.twitterArchive.entities;

import javax.persistence.*;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heschlie on 11/2/2014. Copyright under Iridium Flare Games LLC.
 */

@Entity
public class UserEntity {

    private Long userID;
    private String username;
    private List<TweetEntity> tweets = new ArrayList<TweetEntity>();
    private List<HashtagEntity> hashtags = new ArrayList<HashtagEntity>();
    private Integer numberOfTweets;
    private Map<String, Integer> hashtagCount = new HashMap<String, Integer>();

    public UserEntity() {}

    private void calculateTweets() {
        numberOfTweets = tweets.size();
    }

    private void calculateHashtagCount() {
        for (HashtagEntity tag : hashtags) {
            int count = hashtagCount.containsKey(tag.getHashtag()) ? hashtagCount.get(tag.getHashtag()) : 0;
            hashtagCount.put(tag.getHashtag(), count + 1);
        }
    }

    @Id
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userEntity")
    public List<TweetEntity> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetEntity> tweets) {
        this.tweets = tweets;
    }

    @Transient
    public Integer getNumberOfTweets() {
        return numberOfTweets;
    }

    public void setNumberOfTweets(Integer numberOfTweets) {
        this.numberOfTweets = numberOfTweets;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<HashtagEntity> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashtagEntity> hashtags) {
        this.hashtags = hashtags;
    }

    @Transient
    @ElementCollection
    public Map<String, Integer> getHashtagCount() {
        return hashtagCount;
    }

    public void setHashtagCount(Map<String, Integer> hashtagCount) {
        this.hashtagCount = hashtagCount;
    }
}
