package service;

import dao.Dao;
import entity.Backup;
import entity.BackupRecord;
import exception.AuthorityException;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Ding on 17/1/1.
 */
public class BackupService {
    Dao dao = new Dao();

    public String addBackup(String name, Role autho) throws AuthorityException {
        if( !autho.equals(Role.Admin) && !autho.equals(Role.Purchaser)) {
            throw new AuthorityException(autho);
        }
        Backup newBackup = new Backup();
        newBackup.setBackupName(name);
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        newBackup.setBackPurchaseDate(sqlDate);
        newBackup.setBackupId("B" + System.currentTimeMillis());
        newBackup.setBackActive(State.ACTIVE);
        boolean result = dao.buy(newBackup);
        if (result) {
            return newBackup.getBackupId();
        } else {
            return null;
        }
    }

    public boolean brokeBackup(String backupId, Role autho) throws AuthorityException{
        if( !autho.equals(Role.Admin) && !autho.equals(Role.Purchaser)) {
            throw new AuthorityException(autho);
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result =  dao.saveBrokenBackup(sqlDate, backupId);
        return result;
    }

    public String borrowBackup(String employeeId, String eId, String backupName) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        BackupRecord br = new BackupRecord();
        br.setBackApplyDate(sqlDate);
        br.setEquipmentId(eId);
        br.setEmployeeId(employeeId);
        br.setBackupRecordId("BR" + System.currentTimeMillis());
        String result = dao.borrowBackup(br, backupName);
        return result;
    }

    public boolean returnBackup(String backupId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.returnBackup(sqlDate, backupId);
        return result;
    }
}
