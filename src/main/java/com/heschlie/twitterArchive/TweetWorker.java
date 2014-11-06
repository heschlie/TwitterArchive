package com.heschlie.twitterArchive;

import com.heschlie.twitterArchive.beans.HashtagEJB;
import com.heschlie.twitterArchive.beans.UserEJB;
import com.heschlie.twitterArchive.entities.TweetEntity;
import com.heschlie.twitterArchive.entities.UserEntity;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import javax.ejb.EJB;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by heschlie on 11/3/2014. Copyright under Iridium Flare Games LLC.
 */
public class TweetWorker implements Runnable {

    @EJB
    private UserEJB userEJB;

    @EJB
    private HashtagEJB hashtagEJB;

    private LinkedBlockingQueue<Status> workQueue;

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                Status status = workQueue.take();

                processStatus(status);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processStatus(Status status) {
        UserEntity user = getUserInfo(status);

        userEJB.updateUser(user);
    }

    private UserEntity getUserInfo(Status status) {
        UserEntity user = userEJB.findUser(status.getUser().getId());

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
        HashtagEntity[] tags = status.getHashtagEntities();
        for (HashtagEntity tag : tags) {
            com.heschlie.twitterArchive.entities.HashtagEntity myTag = hashtagEJB.findHashtag(tag.getText());
            if (myTag == null) {
                myTag = new com.heschlie.twitterArchive.entities.HashtagEntity();
                myTag.setHashtag(tag.getText());
            }
            myTag.getTweetsUsed().add(tweet);
            myTag.getUsedBy().add(user);
            user.getHashtags().add(myTag);
            tweet.getHashtags().add(myTag);
        }
    }

    public LinkedBlockingQueue<Status> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(LinkedBlockingQueue<Status> workQueue) {
        this.workQueue = workQueue;
    }
}
