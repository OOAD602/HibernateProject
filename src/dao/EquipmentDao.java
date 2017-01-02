package dao;

import entity.Equipment;

import java.util.Date;

/**
 * Created by Ding on 17/1/1.
 */
public class EquipmentDao {
    public boolean saveAddEquipment(Equipment eqp) {
        System.out.println(eqp.getEquipmentName());
        System.out.println(eqp.getPurchaseDate());
        return true;
    }
    public boolean saveBorrowEquipment(String userId, Date borrowDate, String equipmentName) {
        return true;
    }
    public boolean saveReturnEquipment(Date returnDate, String equipmentId) {
        return true;
    }
    public boolean saveBrokenEquipment(Date returnDate, String equipmentId) {
        return true;
    }



}
