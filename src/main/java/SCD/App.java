package SCD;

import javax.swing.SwingUtilities;

import SCD.controllers.CommonControllers.SplashScreenController;
import SCD.utils.HibernateUtil;

public class App {
    public static void main(String[] args) {
        // scd- proj initDataSync dataSync = new DataSync();
        // scd- proj initSyncService syncService = new SyncService(dataSync);
        // scd- proj initsyncService.startSyncing();

        HibernateUtil.getSessionFactory();
        HibernateUtil.getRemoteSessionFactory();

        SwingUtilities.invokeLater(() -> {

            new SplashScreenController();

        });

    }
}
