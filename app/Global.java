import managers.BalanceManager;
import managers.FileObserver;
import managers.SendManager;
import play.Application;
import play.GlobalSettings;
import play.Logger;


public class Global extends GlobalSettings {


    @Override
    public void onStart(Application app) {
        Runnable fileObserverThread = FileObserver.getFileObserverInstance();
        Thread observerThread = new Thread(fileObserverThread);
        observerThread.start();

        Runnable sendManager = new SendManager();
        Thread senderThread = new Thread(sendManager);

        senderThread.start();

    }
}
