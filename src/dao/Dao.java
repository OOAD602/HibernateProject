package dao;

import entity.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.State;

import java.sql.Date;
import java.util.List;

/**
 * Created by Ding on 17/1/1.
 */
public class Dao {
    private Session session = null;
    private SessionFactory factory;

    public Dao() {
        Configuration config = new Configuration();
        factory = config.configure().buildSessionFactory();
    }

    // 所有的操作都是通过session来完成的
    public boolean buy(Object obj) {
        session = factory.openSession();
        Transaction tran = session.beginTransaction();//开始事物
        session.save(obj);//执行
        tran.commit();//提交
        session.close();
        return true;
    }

    public String borrowEquipment(EquipmentRecord er, String equipmentName) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Equipment where equipmentName = :name");
        query.setParameter("name", equipmentName);
        //默认查询出来的list里存放的是一个Object数组
        List<Equipment> equipments = query.list();
        for (Equipment equipment : equipments) {
            String name = equipment.getEquipmentName();
            if (equipment.getEquipmentActive() == State.ACTIVE) {
                equipment.setEquipmentActive(State.INACTIVE);
                er.setEquipmentId(equipment.getEquipmentId());
                Transaction tran = session.beginTransaction();//开始事物
                session.save(er);
                tran.commit();
                session.close();
                return equipment.getEquipmentId();
            }
        }
        session.close();
        return null;
    }

    public boolean returnEquipment(Date sqldate, String eId) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Equipment where equipmentId = :id");
        query.setParameter("id", eId);
        Transaction tran = session.beginTransaction();//开始事物
        //默认查询出来的list里存放的是一个Object数组
        List<Equipment> equipments = query.list();
        for (Equipment equipment : equipments) {
            if (equipment.getEquipmentActive() == State.ACTIVE) {
                return false;
            }
            equipment.setEquipmentActive(State.ACTIVE);
        }
        query = session.createQuery("FROM EquipmentRecord where equipmentId = :id order by applyDate desc");
        query.setParameter("id", eId);
        List<EquipmentRecord> ers = query.list();
        for (EquipmentRecord er : ers) {
            if (er.getReturnDate() == null) {
                er.setReturnDate(sqldate);
                tran.commit();
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }

    public boolean saveBrokenEquipment(Date brokeDate, String eId) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Equipment where equipmentId = :id");
        query.setParameter("id", eId);
        Transaction tran = session.beginTransaction();
        //默认查询出来的list里存放的是一个Object数组
        List<Equipment> equipments = query.list();
        for (Equipment equipment : equipments) {
            if (equipment.getBrokenDate() == null && equipment.getEquipmentActive() == State.INACTIVE) {
                equipment.setBrokenDate(brokeDate);
                equipment.setEquipmentActive(State.INACTIVE);
                tran.commit();
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }

    public boolean buySoftware(Software sft) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Software where softwareName = :name");
        query.setParameter("name", sft.getSoftwareName());
        List<Software> softwares = query.list();
        if (softwares.size() == 0) {
            Transaction tran = session.beginTransaction();//开始事物
            session.save(sft);
            tran.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    public String installSoftware(SoftwareRecord sr, String softwareName) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Software where softwareName = :name");
        query.setParameter("name", softwareName);
        //默认查询出来的list里存放的是一个Object数组
        List<Software> softwares = query.list();
        if (softwares.size() == 1 && softwares.get(0).getSoftwareActive() == State.ACTIVE) {
            sr.setSoftwareId(softwares.get(0).getSoftwareId());
            Transaction tran = session.beginTransaction();//开始事物
            session.save(sr);
            tran.commit();
            session.close();
            return sr.getSoftwareId();
        }
        session.close();
        return null;
    }

    public boolean uninstallSoftware(Date sqldate, String sId) {
        session = factory.openSession();
        Transaction tran = session.beginTransaction();//开始事物
        Query query = session.createQuery("FROM SoftwareRecord where softwareId= :id order by installDate desc");
        query.setParameter("id", sId);
        List<SoftwareRecord> srs = query.list();
        for (SoftwareRecord sr : srs) {
            if (sr.getUninstallDate() == null) {
                sr.setUninstallDate(sqldate);
                tran.commit();
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }

    public boolean saveExpireSoftware(Date brokeDate, String sId) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Software where softwareId = :id");
        query.setParameter("id", sId);
        Transaction tran = session.beginTransaction();
        //默认查询出来的list里存放的是一个Object数组
        List<Software> softwares = query.list();
        if (softwares.size() > 0) {
            softwares.get(0).setExpireDate(brokeDate);
            softwares.get(0).setSoftwareActive(State.INACTIVE);
            tran.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    public String borrowBackup(BackupRecord br, String backupName) {
        session = factory.openSession();
        // 判断此equipment是否正在被当前员工借用中，如不是，返回
        Query queryEquip = session.createQuery("FROM EquipmentRecord where equipmentId = :eqId and employeeId = :emId order by applyDate desc");
        queryEquip.setParameter("eqId", br.getEquipmentId());
        queryEquip.setParameter("emId", br.getEmployeeId());
        List<EquipmentRecord> equipmentRecords = queryEquip.list();
        boolean isBorrow = false;
        for (EquipmentRecord equipmentRecord : equipmentRecords) {
            if (equipmentRecord.getReturnDate() == null) {
                isBorrow = true;
                break;
            }
        }
        if (!isBorrow) {
            session.close();
            return null;
        }
        Query query = session.createQuery("FROM Backup where backupName = :name");
        query.setParameter("name", backupName);
        //默认查询出来的list里存放的是一个Object数组
        List<Backup> backups = query.list();
        for (Backup backup : backups) {
            if (backup.getBackActive() == State.ACTIVE) {
                backup.setBackActive(State.INACTIVE);
                br.setBackupId(backup.getBackupId());
                Transaction tran = session.beginTransaction();//开始事务
                session.save(br);
                tran.commit();
                session.close();
                return backup.getBackupId();
            }
        }
        session.close();
        return null;
    }

    public boolean returnBackup(Date sqldate, String bId) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupId = :id");
        query.setParameter("id", bId);
        Transaction tran = session.beginTransaction();//开始事物
        //默认查询出来的list里存放的是一个Object数组
        List<Backup> backups = query.list();
        for (Backup backup : backups) {
            if (backup.getBackActive() == State.ACTIVE) {
                session.close();
                return false;
            }
            backup.setBackActive(State.ACTIVE);
        }
        query = session.createQuery("FROM BackupRecord where backupId = :id order by backApplyDate desc");
        query.setParameter("id", bId);
        List<BackupRecord> brs = query.list();
        for (BackupRecord br : brs) {
            if (br.getBackReturnDate() == null) {
                br.setBackReturnDate(sqldate);
                tran.commit();
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }

    public boolean saveBrokenBackup(Date brokeDate, String bId) {
        session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupId = :id");
        query.setParameter("id", bId);
        Transaction tran = session.beginTransaction();
        //默认查询出来的list里存放的是一个Object数组
        List<Backup> backups = query.list();
        for (Backup backup : backups) {
            if (backup.getBackBrokenDate() == null && backup.getBackActive() == State.INACTIVE) {
                backup.setBackBrokenDate(brokeDate);
                tran.commit();
                session.close();
                return true;
            }
        }
        session.close();
        return false;
    }
}
