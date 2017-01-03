package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import dao.Dao;
import entity.Equipment;
import entity.EquipmentRecord;
import exception.AuthorityException;

/**
 * Created by Ding on 17/1/1.
 */
public class EquipmentService {
    Dao dao = new Dao();

    public String addEquipment(String name, Role role) throws AuthorityException {
        if( !role.equals(Role.Admin) && !role.equals(Role.Purchaser)) {
            throw new AuthorityException(role);
        }
        System.out.println("in service");
        Equipment newEquipment = new Equipment();
        newEquipment.setEquipmentName(name);
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        newEquipment.setPurchaseDate(sqlDate);
        newEquipment.setEquipmentId("E" + System.currentTimeMillis());
        newEquipment.setEquipmentActive(State.ACTIVE);
        boolean result = dao.buy(newEquipment);
        if (result) {
            return newEquipment.getEquipmentId();
        } else {
            return null;
        }
    }

    public String borrowEquipment(String userId, String equipmentName) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        EquipmentRecord er = new EquipmentRecord();
        er.setApplyDate(sqlDate);
        er.setEmployeeId(userId);
        er.setEquipmentRecordId("ER" + System.currentTimeMillis());
        String equipId = dao.borrowEquipment(er, equipmentName);
        return equipId;
    }

    public boolean returnEquipment(String equipmentId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.returnEquipment(sqlDate, equipmentId);
        return result;
    }

    public boolean brokeEquipment(String equipmentId, Role autho) throws AuthorityException{
        if( !autho.equals(Role.Admin) && !autho.equals(Role.Purchaser)) {
            throw new AuthorityException(autho);
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.saveBrokenEquipment(sqlDate, equipmentId);
        return result;
    }


}
