package dao;

import entity.Employee;
import entity.EquipmentRecord;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ding on 17/1/1.
 */
public class SearchDao {
    private static SessionFactory factory;

    public SearchDao() {

    }

    public List<EquipmentRecord> searchAllMyEquipment(String userId) {
        Session session = factory.openSession();
        Query query = session.createQuery("FROM EquipmentRecord where employeeId = :id");
        query.setParameter("id", userId);
        return query.list();
    }

    public boolean searchAllMyLog(String userId) {
        return true;
    }

    public boolean searchAllEquipment() {
        return true;
    }

    public boolean searchAllBackup() {
        return true;
    }


    public boolean searchEquipmentLog() {
        return true;
    }

    public boolean searchSoftUserLogBySoftId(String softId) {
        return true;
    }

    public boolean searchEquipUserLogByEquipId(String softId) {
        return true;
    }

    public boolean searchBackupLogByEquipmentId(String equipId) {
        return true;
    }

    public boolean searchEquipmentIdByName(String equipName) {
        return true;
    }

    public boolean searchSoftIdByName(String softName) {
        return true;
    }

    public boolean searchBackupIdByName(String backupName) {
        return true;
    }

    public boolean searchBackupLogById(String backupId) {
        return true;
    }

    public boolean searchAllByUserId(String userId) {
        return true;
    }

    public boolean searchLogByUserId(String userId) {
        return true;
    }


}
