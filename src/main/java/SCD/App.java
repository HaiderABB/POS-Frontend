package SCD;

import SCD.controllers.CommonControllers.MainController;

public class App {
    public static void main(String[] args) {
        // DataSync dataSync = new DataSync();
        // SyncService syncService = new SyncService(dataSync);
        // syncService.startSyncing();
        new MainController();
    }
}
