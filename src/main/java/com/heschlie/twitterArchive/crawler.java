package com.heschlie.twitterArchive;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by heschlie on 11/3/2014. Copyright under Iridium Flare Games LLC.
 */

public class Crawler implements Runnable, StatusListener {

    private TwitterStream twitterStream;
    private LinkedBlockingQueue<Status> workQueue;

    public Crawler() {
        ConfigurationBuilder builder = createBuilder();
        TwitterStreamFactory factory = new TwitterStreamFactory(builder.build());
        twitterStream = factory.getInstance();
        twitterStream.addListener(this);
    }

    private ConfigurationBuilder createBuilder() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("iwikDfljpyBHyjOKcJ2DR3VUO")
                .setOAuthConsumerSecret("qeG9R35Gc2SOndd5Zjzhkd590BweI06bQdmj5Q3osyHHFUoxS9")
                .setOAuthAccessToken("2753139690-aKLgjJSTevUuylpdtUHL0qwkg0ZJOajDZZWGbDH")
                .setOAuthAccessTokenSecret("FnlQyqjFrQRYY41azsuZNUgFEdRc615jBELtQh7jJXEcC");
        return cb;
    }

    @Override
    public void run() {
        twitterStream.sample();
    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onStatus(Status status) {
        try {
            workQueue.put(status);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l2) {

    }

    public LinkedBlockingQueue<Status> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(LinkedBlockingQueue<Status> workQueue) {
        this.workQueue = workQueue;
    }
}
