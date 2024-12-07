package SCD;

import SCD.controllers.MainController;
import SCD.model.service.SyncService.DataSync;
import SCD.model.service.SyncService.SyncService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        DataSync dataSync = new DataSync();
        SyncService syncService = new SyncService(dataSync);
        syncService.startSyncing();
        new MainController();
    }
}
