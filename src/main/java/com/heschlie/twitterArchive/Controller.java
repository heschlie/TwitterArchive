package com.heschlie.twitterArchive;

import twitter4j.Status;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by heschlie on 11/3/2014. Copyright under Iridium Flare Games LLC.
 */
public class Controller {

    private static int numThreads = Runtime.getRuntime().availableProcessors();

    private LinkedBlockingQueue<Status> workQueue;

    public Controller() {

    }

    public void setup() {
        workQueue = new LinkedBlockingQueue<Status>();

        startCrawler();
    }

    private void startCrawler() {
        Crawler crawler = new Crawler();
        crawler.setWorkQueue(workQueue);

        new Thread(crawler);
    }
}
