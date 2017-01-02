package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import dao.Dao;
import entity.Equipment;
import entity.EquipmentRecord;

import dao.EquipmentDao;

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

    public boolean borrowEquipment(String userId, String equipmentName) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        EquipmentRecord er = new EquipmentRecord();
        er.setApplyDate(sqlDate);
        er.setEmployeeId(userId);
        return dao.borrow(er, equipmentName);
    }

    public boolean returnEquipment(String equipmentId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        return dao.saveReturnEquipment(sqlDate, equipmentId);
    }

    public boolean brokeEquipment(String equipmentId, int autho) {
        if (autho != 0 && autho != 1) {
            return false;
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        return dao.saveBrokenEquipment(sqlDate, equipmentId);
    }


}
