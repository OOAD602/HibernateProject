package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ding on 17/1/1.
 */
public class SearchDao {
    private Session session = null;
    SessionFactory factory = null;

    public SearchDao() {
        Configuration config = new Configuration();
        factory = config.configure().buildSessionFactory();
    }

    public List<Equipment> searchAllMyEquipment(String userId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM EquipmentRecord where employeeId = :id");
        query.setParameter("id", userId);
        List<EquipmentRecord> records = query.list();
        session.close();

        List<Equipment> result = new LinkedList<>();
        for (EquipmentRecord equipmentRecord : records) {
            if (equipmentRecord.getReturnDate() == null) {
                result.add(searchEquipmentById(equipmentRecord.getEquipmentId()).get(0));
            }
        }
        return result;
    }

    public List<Software> searchAllMySoftware(String userId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM SoftwareRecord where employeeId = :id");
        query.setParameter("id", userId);
        List<SoftwareRecord> record = query.list();
        session.close();

        List<Software> result = new LinkedList<>();
        for (SoftwareRecord softRecord : record) {
            if (softRecord.getUninstallDate() == null) {
                result.add(searchSoftById(softRecord.getSoftwareId()).get(0));
            }
        }
        return result;
    }

    public List<Backup> searchAllMyBackup(String userId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM BackupRecord where employeeId = :id");
        query.setParameter("id", userId);
        List<BackupRecord> records = query.list();
        session.close();

        List<Backup> result = new LinkedList<>();
        for (BackupRecord backupRecord : records) {
            if (backupRecord.getBackReturnDate() == null) {
                result.add(searchBackupById(backupRecord.getBackupId()).get(0));
            }
        }
        return result;
    }

    public List searchAllMyLog(String userId) {
        List log = new ArrayList<>();
        log.addAll(searchBackupLogByUser(userId));
        log.addAll(searchEquipmentLogByUser(userId));
        log.addAll(searchSoftwareLogByUser(userId));

        return log;
    }

    public List<Equipment> searchAllEquipment() {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Equipment");
        List result = query.list();
        session.close();
        return result;
    }

    public List<Backup> searchAllBackup() {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Backup");
        List result = query.list();
        session.close();
        return result;
    }


    public List<EquipmentRecord> searchEquipmentLog() {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM EquipmentRecord ");
        List result = query.list();
        session.close();
        return result;
    }

    public List<EquipmentRecord> searchEquipmentLogByUser(String userId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM EquipmentRecord WHERE employeeId =:id");
        query.setParameter("id", userId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<SoftwareRecord> searchSoftwareLogByUser(String userId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM SoftwareRecord WHERE employeeId =:id");
        query.setParameter("id", userId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<BackupRecord> searchBackupLogByUser(String userId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM BackupRecord WHERE employeeId =:id");
        query.setParameter("id", userId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<SoftwareRecord> searchSoftUserLogBySoftId(String softId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM SoftwareRecord where softwareId = :id");
        query.setParameter("id", softId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<EquipmentRecord> searchEquipUserLogByEquipId(String equipmentId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM EquipmentRecord where equipmentId = :id");
        query.setParameter("id", equipmentId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<BackupRecord> searchBackupLogByEquipmentId(String equipId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM BackupRecord where equipmentId = :id");
        query.setParameter("id", equipId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<Equipment> searchEquipmentByName(String equipName) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Equipment where equipmentName = :name");
        query.setParameter("name", equipName);
        List result = query.list();
        session.close();
        return result;
    }

    public List<Equipment> searchEquipmentById(String equipId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Equipment where equipmentId = :id");
        query.setParameter("id", equipId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<Software> searchSoftwareByName(String softName) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Software where softwareName = :name");
        query.setParameter("name", softName);
        List result = query.list();
        session.close();
        return result;
    }

    public List<Software> searchSoftById(String softId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Software where softwareId = :id");
        query.setParameter("id", softId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<Backup> searchBackupByName(String backupName) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupName = :name");
        query.setParameter("name", backupName);
        List result = query.list();
        session.close();
        return result;
    }

    public List<Backup> searchBackupById(String backupId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupId = :id");
        query.setParameter("id", backupId);
        List result = query.list();
        session.close();
        return result;
    }

    public List<BackupRecord> searchBackupLogById(String backupId) {
        this.session = factory.openSession();
        Query query = session.createQuery("FROM BackupRecord where backupId = :id");
        query.setParameter("id", backupId);
        List result = query.list();
        session.close();
        return result;
    }
}
