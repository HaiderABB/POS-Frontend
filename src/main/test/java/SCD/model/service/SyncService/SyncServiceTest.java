package SCD.model.service.SyncService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SyncServiceTest {

    @Test
    void startSyncing() {
        // scd- proj initArrange
        DataSync mockDataSync = new DataSync() {
            @Override
            public void syncData() {
                // scd- proj initSimulate syncing action
                System.out.println("Mock syncData called");
            }
        };

        SyncService syncService = new SyncService(mockDataSync);

        // scd- proj initAct
        assertDoesNotThrow(syncService::startSyncing, "startSyncing should start without throwing exceptions");

        // scd- proj initStop the scheduler to prevent it from running indefinitely
        syncService.stopSyncing();
    }

    @Test
    void stopSyncing() {
        // scd- proj initArrange
        DataSync mockDataSync = new DataSync();
        SyncService syncService = new SyncService(mockDataSync);

    }
}
