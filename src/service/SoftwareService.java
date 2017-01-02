package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import dao.Dao;
import entity.Software;
import entity.SoftwareRecord;

import service.State;

/**
 * Created by Ding on 17/1/1.
 */
public class SoftwareService {
    Dao dao = new Dao();

    public void addSoftware(String name, int autho) {
        if (autho != 0 && autho != 1) {
            System.out.println("无权限");
        }
        Software newSoftware = new Software();
        newSoftware.setSoftwareName(name);
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        newSoftware.setUpdateDate(sqlDate);
        newSoftware.setSoftwareId("S" + System.currentTimeMillis());
        newSoftware.setSoftwareActive(State.ACTIVE);
        boolean result = dao.buySoftware(newSoftware);
        if (result) {
            System.out.println("新增成功");
        } else {
            System.out.println("新增失败");
        }
    }

    public void installSoftware(String userId, String SoftwareName) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        SoftwareRecord sr = new SoftwareRecord();
        sr.setInstallDate(sqlDate);
        sr.setEmployeeId(userId);
        sr.setSoftwareRecordId("SR" + System.currentTimeMillis());
        String equipId = dao.installSoftware(sr, SoftwareName);
        if (equipId != null) {
            System.out.println("您申请到的设备ID为：" + equipId);
        } else {
            System.out.println("您未成功申领到设备");
        }
    }

    public void uninstallSoftware(String SoftwareId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.uninstallSoftware(sqlDate, SoftwareId);
        if (result) {
            System.out.println("归还成功");
        } else {
            System.out.println("归还失败");
        }
    }

    public void brokeSoftware(String SoftwareId, int autho) {
        if (autho != 0 && autho != 1) {
            System.out.println("无权限");
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.saveExpireSoftware(sqlDate, SoftwareId);
        if (result) {
            System.out.println("报废成功");
        } else {
            System.out.println("报废失败");
        }
    }


}