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
        startWorkers();
    }

    private void startWorkers() {
        for (int i = 0; i < 20; i++) {
            TweetWorker worker = new TweetWorker();
            worker.setWorkQueue(workQueue);
            new Thread(worker).start();
        }
    }

    private void startCrawler() {
        Crawler crawler = new Crawler();
        crawler.setWorkQueue(workQueue);

        new Thread(crawler).start();
    }
}
