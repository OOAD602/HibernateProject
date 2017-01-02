package service;

import dao.BackupDao;
import entity.Backup;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Ding on 17/1/1.
 */
public class BackupService {
    BackupDao dao = new BackupDao();
    public boolean addBackup(String name, String descriptor, int autho) {
        if(autho != 0 && autho != 1) {
            return false;
        }
        Backup newBackup = new Backup();
        newBackup.setBackupName(name);
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        newBackup.setBackPurchaseDate(sqlDate);
        return dao.saveAddBackup(newBackup);
    }
    public boolean brokeBackup(String backupId, int autho) {
        if(autho != 0 && autho != 1) {
            return false;
        }
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveBrokenBackup(sqlDate, backupId);
    }
    public boolean borrowBackup(String eId, String backupName) {
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveBorrowBackup(eId, sqlDate, backupName);
    }
    public boolean returnBackup(String backupId) {
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveReturnBackup(sqlDate, backupId);
    }
}
