package com.heschlie.twitterArchive.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by heschlie on 11/2/2014. Copyright under Iridium Flare Games LLC.
 */

@Entity
public class TweetEntity {

    private Long tweetId;
    private UserEntity author;
    private String tweetText;
    private Boolean reTweet;
    private Long reTweetId;
    private Boolean reTweeted;
    private Integer retweetCount;
    private List<HashtagEntity> hashtags;
    private List<UserEntity> mentions;
    private Date creationDate;

    public TweetEntity() {}

    @Id
    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public Boolean getReTweet() {
        return reTweet;
    }

    public void setReTweet(Boolean reTweet) {
        this.reTweet = reTweet;
    }

    public Long getReTweetId() {
        return reTweetId;
    }

    public void setReTweetId(Long reTweetId) {
        this.reTweetId = reTweetId;
    }

    public Boolean getReTweeted() {
        return reTweeted;
    }

    public void setReTweeted(Boolean reTweeted) {
        this.reTweeted = reTweeted;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    @ManyToMany
    public List<HashtagEntity> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashtagEntity> hashtags) {
        this.hashtags = hashtags;
    }

    @ManyToMany
    public List<UserEntity> getMentions() {
        return mentions;
    }

    public void setMentions(List<UserEntity> mentions) {
        this.mentions = mentions;
    }

    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
