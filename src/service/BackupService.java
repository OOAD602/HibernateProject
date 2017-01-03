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
            System.out.println("新增备件成功");
            return newBackup.getBackupId();
        } else {
            System.out.println("新增备件失败");
            return null;
        }
    }

    public void brokeBackup(String backupId, Role autho) throws AuthorityException{
        if( !autho.equals(Role.Admin) && !autho.equals(Role.Purchaser)) {
            throw new AuthorityException(autho);
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result =  dao.saveBrokenBackup(sqlDate, backupId);
        if (result) {
            System.out.println("报废成功");
        } else {
            System.out.println("报废失败");
        }
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
        if (result != null) {
            System.out.println("申请备件成功，您为设备" + eId + "申请到的备件ID为：" + result);
        } else {
            System.out.println("申请备件失败");
        }
        return result;
    }

    public void returnBackup(String backupId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.returnBackup(sqlDate, backupId);
        if (result) {
            System.out.println("归还备件成功");
        } else {
            System.out.println("归还备件失败");
        }
    }
}
