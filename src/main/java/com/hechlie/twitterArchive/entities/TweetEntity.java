package com.hechlie.twitterArchive.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by heschlie on 11/2/2014. Copyright under Iridium Flare Games LLC.
 */

@Entity
public class TweetEntity {

    private String tweetId;
    private UserEntity author;
    private String tweetText;
    private Boolean reTweet;
    private String reTweetId;
    private Boolean reTweeted;
    private List<TweetEntity> reTweets;
    private List<HashtagEntity> hashtags;

    @Id
    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
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

    public String getReTweetId() {
        return reTweetId;
    }

    public void setReTweetId(String reTweetId) {
        this.reTweetId = reTweetId;
    }

    public Boolean getReTweeted() {
        return reTweeted;
    }

    public void setReTweeted(Boolean reTweeted) {
        this.reTweeted = reTweeted;
    }

    @OneToMany
    public List<TweetEntity> getReTweets() {
        return reTweets;
    }

    public void setReTweets(List<TweetEntity> reTweets) {
        this.reTweets = reTweets;
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

    private List<UserEntity> mentions;
}
