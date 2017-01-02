package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import dao.Dao;
import entity.Equipment;
import entity.EquipmentRecord;

/**
 * Created by Ding on 17/1/1.
 */
public class EquipmentService {
    Dao dao = new Dao();

    public boolean addEquipment(String name, int autho) {
        if (autho != 0 && autho != 1) {
            return false;
        }
        System.out.println("in service");
        Equipment newEquipment = new Equipment();
        newEquipment.setEquipmentName(name);
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        newEquipment.setPurchaseDate(sqlDate);
        newEquipment.setEquipmentId("E" + System.currentTimeMillis());
        newEquipment.setEquipmentActive(State.ACTIVE);
        return dao.buy(newEquipment);
    }

    public void borrowEquipment(String userId, String equipmentName) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        EquipmentRecord er = new EquipmentRecord();
        er.setApplyDate(sqlDate);
        er.setEmployeeId(userId);
        er.setEquipmentRecordId("ER" + System.currentTimeMillis());
        String equipId = dao.borrowEquipment(er, equipmentName);
        if (equipId != null) {
            System.out.println("您申请到的设备ID为：" + equipId);
        } else {
            System.out.println("您未成功申领到设备");
        }
    }

    public void returnEquipment(String equipmentId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.returnEquipment(sqlDate, equipmentId);
        if (result) {
            System.out.println("归还成功");
        } else {
            System.out.println("归还失败");
        }
    }

    public void brokeEquipment(String equipmentId, int autho) {
        if (autho != 0 && autho != 1) {
            System.out.println("无权限");
            ;
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.saveBrokenEquipment(sqlDate, equipmentId);
        if (result) {
            System.out.println("报废成功");
        } else {
            System.out.println("报废失败");
        }
    }


}
