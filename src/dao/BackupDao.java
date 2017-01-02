package dao;

import entity.Backup;

import java.util.Date;

/**
 * Created by Ding on 17/1/1.
 */
public class BackupDao {
    public boolean saveAddBackup(Backup newBackup) {

        return true;
    }
    public boolean saveBrokenBackup(Date brokeDate, String backupId) {
        return true;
    }
    public boolean saveBorrowBackup(String equipmentId, Date borrowDate, String backupName) {
        return true;
    }
    public boolean saveReturnBackup(Date returnDate, String backupId) {
        return true;
    }
}
