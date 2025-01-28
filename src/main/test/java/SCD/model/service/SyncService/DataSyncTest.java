package SCD.model.service.SyncService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSyncTest {

    @Test
    void syncData() {

        DataSync dataSync = new DataSync();


    }

    @Test
    void main() {

        assertDoesNotThrow(() -> DataSync.main(new String[]{}), "main method should execute without exceptions");
    }
}
