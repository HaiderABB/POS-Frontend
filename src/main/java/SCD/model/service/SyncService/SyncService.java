package SCD.model.service.SyncService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import SCD.utils.InternetConnectionChecker;

public class SyncService {

  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  DataSync dataSync;

  public SyncService(DataSync dataSync) {
    this.dataSync = dataSync;
  }

  public void startSyncing() {
    Runnable syncTask = () -> {
      if (InternetConnectionChecker.isInternetAvailable()) {
        System.out.println("Internet is available. Syncing data...");
        syncLocalDataToRemote();
      } else {
        System.out.println("No internet connection. Data will be synced once connected.");
      }
    };

    scheduler.scheduleAtFixedRate(syncTask, 0, 3, TimeUnit.MINUTES);
  }

  private void syncLocalDataToRemote() {
    if (dataSync != null) {
      dataSync.syncData();
    } else {
      System.out.println("DataSync instance is not initialized.");
    }
  }

  public void stopSyncing() {
    scheduler.shutdown();
  }
}
