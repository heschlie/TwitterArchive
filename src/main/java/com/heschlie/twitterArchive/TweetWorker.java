package com.heschlie.twitterArchive;

import com.heschlie.twitterArchive.beans.UserEJB;
import com.heschlie.twitterArchive.entities.TweetEntity;
import com.heschlie.twitterArchive.entities.UserEntity;
import twitter4j.Status;

import javax.ejb.EJB;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by heschlie on 11/3/2014. Copyright under Iridium Flare Games LLC.
 */
public class TweetWorker implements Runnable {

    @EJB
    private UserEJB userEJB;

    private LinkedBlockingQueue<Status> workQueue;

    @Override
    public void run() {

    }

    private void processStatus() {
        try {
            Status status = workQueue.take();

            UserEntity user = getUserInfo(status);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private UserEntity getUserInfo(Status status) {
        UserEntity user = userEJB.findUser(status.getUser().getScreenName());

        if (user == null) {
            user = new UserEntity();
            user.setUsername(status.getUser().getScreenName());
            user.setUserID(status.getUser().getId());
        }

        processTweet(user, status);

        return user;
    }

    private void processTweet(UserEntity user, Status status) {
        TweetEntity tweet = new TweetEntity();

        tweet.setTweetId(status.getId());
        tweet.setAuthor(user);
        tweet.setTweetText(status.getText());
        tweet.setReTweet(status.isRetweet());
        tweet.setCreationDate(status.getCreatedAt());
        if (status.isRetweet()) {
            tweet.setReTweetId(status.getRetweetedStatus().getId());
        }
        tweet.setReTweeted(status.isRetweeted());
        if (status.isRetweeted()) {
            tweet.setRetweetCount(status.getRetweetCount());
        }

        if (status.getHashtagEntities().length > 0)
            getHashtagInfo(user, tweet, status);
    }

    private void getHashtagInfo(UserEntity user, TweetEntity tweet, Status status) {

    }

    public LinkedBlockingQueue<Status> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(LinkedBlockingQueue<Status> workQueue) {
        this.workQueue = workQueue;
    }
}
