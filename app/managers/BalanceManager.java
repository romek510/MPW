package managers;

import play.Logger;
import services.Sender;
import utils.CommonUtil;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BalanceManager {

    protected static BalanceManager balanceManagerInstance = null;

    private Queue<File> fileQueue = new ArrayDeque<>();

    private final static int numberOfStorages = Integer.valueOf(CommonUtil.getNumberOfStorages());

    private Random randomSeed = new Random();

    private int currentStorage = 1;

    protected BalanceManager() {
    }

    public synchronized static BalanceManager getBalanceManagerInstance() {
        if (balanceManagerInstance == null) {
            balanceManagerInstance = new BalanceManager();
        }
        return balanceManagerInstance;
    }

    public void addFileToQueue(File file) {
        Logger.info("heloszka filku " + file.getName());
        boolean result = this.fileQueue.offer(file);
        if (!result) {
            Logger.info("Not able to add file " + file.getName());
        }
    }

    public Queue<File> getCurrentFileQueue() {
        return this.fileQueue;
    }

    public void cleanCurrentFileQueue() {
        for (File element : fileQueue) {
            fileQueue.poll();
        }

    }

    public void printFileQueue() {
        StringBuilder sb = new StringBuilder();
        for (File element : fileQueue) {
            sb.append(element.getName());
        }
        Logger.info("@#@# printed queue " + sb.toString());
    }

    public int getCurrentAvailableStorageNumber() {
        if (currentStorage == numberOfStorages) {
            currentStorage = 1;
        } else {
            currentStorage++;
        }
        return currentStorage;
    }

    private void launchGivenThreads(List<Thread> threads) {
//        for (Thread element : threads) {
//            element.start();
//        }

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for(Thread element : threads){
            threadPool.submit(element);
        }
        threadPool.shutdown();
    }


    public synchronized void sendFilesToStorages() {
        List<Thread> threadsToLaunch = new ArrayList<>();
        int tempNumber = 0;
        if (this.fileQueue.size() < numberOfStorages) {
            for (File element : fileQueue) {
                tempNumber = randomSeed.nextInt(numberOfStorages);
                Sender sender = new Sender(element, tempNumber);
                Logger.error("@@@@@@ " + sender.dest + " tempnmbr: " + tempNumber);
                threadsToLaunch.add(new Thread(sender));
            }
        } else {
            Logger.error("!@!@!@! cuka bljat");
            for (File element : fileQueue) {
                tempNumber = balanceManagerInstance.getCurrentAvailableStorageNumber();
                Sender sender = new Sender(element, tempNumber);
                threadsToLaunch.add(new Thread(sender));
            }

        }
        launchGivenThreads(threadsToLaunch);
    }

}
