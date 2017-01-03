package test.service;

import entity.Backup;
import entity.BackupRecord;
import entity.Equipment;
import exception.AuthorityException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import service.BackupService;
import service.EquipmentService;
import service.Role;
import service.State;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * BackupService Tester.
 *
 * @author <Yiding Wen>
 * @version 1.0
 * @since <pre>一月 3, 2017</pre>
 */
public class BackupServiceTest {
    Configuration config = new Configuration();
    SessionFactory factory = config.configure().buildSessionFactory();
    String eId = "E1483421681158";
    LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
    java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addBackup(String name, Role autho)
     */
    @Test
    public void testAddBackup() throws Exception {
        BackupService bs = new BackupService();
        Session session = factory.openSession();
        bs.addBackup("test_add", Role.Admin);
        Query query = session.createQuery("select backupName,backPurchaseDate,backActive FROM Backup order by backupId desc");
        query.setMaxResults(1);
        //默认查询出来的list里存放的是一个Object数组
        List<Object[]> list = query.list();
        if (list.size() > 0) {
            String name = (String) list.get(0)[0];
            Date date = (java.sql.Date) list.get(0)[1];
            byte active = (byte) list.get(0)[2];
            Assert.assertEquals("test_add", name);
            Assert.assertEquals(sqlDate, date);
            Assert.assertEquals(State.ACTIVE, active);
        }

        bs.addBackup("test_add2", Role.Purchaser);
        Query query2 = session.createQuery("select backupName,backPurchaseDate,backActive FROM Backup order by backupId desc");
        query2.setMaxResults(1);
        List<Object[]> list2 = query2.list();
        if (list2.size() > 0) {
            String name2 = (String) list2.get(0)[0];
            Date date2 = (java.sql.Date) list2.get(0)[1];
            byte active2 = (byte) list2.get(0)[2];
            Assert.assertEquals("test_add2", name2);
            Assert.assertEquals(sqlDate, date2);
            Assert.assertEquals(State.ACTIVE, active2);
        }

        try {
            bs.addBackup("test_add3", Role.HR);
        } catch (AuthorityException e) {
            Assert.assertTrue(e instanceof AuthorityException);
        }
        Query query3 = session.createQuery("FROM Backup where backupName = :name");
        query3.setParameter("name", "test_add3");
        List<Backup> list3 = query3.list();
        Assert.assertEquals(0, list3.size());

        try {
            bs.addBackup("test_add4", Role.Employee);
        } catch (AuthorityException e) {
            Assert.assertTrue(e instanceof AuthorityException);
        }
        Query query4 = session.createQuery("FROM Backup where backupName = :name");
        query4.setParameter("name", "test_add4");
        List<Backup> list4 = query4.list();
        Assert.assertEquals(0, list4.size());


        session.close();
    }

    /**
     * Method: brokeBackup(String backupId, Role autho)
     */
    @Test
    public void testBrokeBackup() throws Exception {
        BackupService bs = new BackupService();
        String bId = bs.addBackup("test_broke", Role.Admin);
        String bId2 = bs.addBackup("test_broke2", Role.Admin);
        String bId3 = bs.addBackup("test_broke3", Role.Admin);
        String bId4 = bs.addBackup("test_broke4", Role.Admin);

        bs.brokeBackup(bId, Role.Admin);
        Session session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupId = :id");
        query.setMaxResults(1);
        query.setParameter("id", bId);
        List<Backup> list = query.list();
        if (list.size() > 0) {
            Assert.assertEquals(sqlDate, list.get(0).getBackBrokenDate());
        }

        try {
            bs.brokeBackup(bId2, Role.Employee);
        } catch (AuthorityException e) {
            Assert.assertTrue(e instanceof AuthorityException);
        }
        Query query2 = session.createQuery("FROM Backup where backupId = :id");
        query2.setMaxResults(1);
        query2.setParameter("id", bId2);
        List<Backup> list2 = query2.list();
        Assert.assertEquals(1, list2.size());
        if (list2.size() > 0) {
            Assert.assertEquals(null, list2.get(0).getBackBrokenDate());
        }

        try {
            bs.brokeBackup(bId3, Role.HR);
        } catch (AuthorityException e) {
            Assert.assertTrue(e instanceof AuthorityException);
        }
        Query query3 = session.createQuery("FROM Backup where backupId = :id");
        query3.setMaxResults(1);
        query3.setParameter("id", bId3);
        List<Backup> list3 = query3.list();
        Assert.assertEquals(1, list3.size());
        if (list3.size() > 0) {
            Assert.assertEquals(null, list3.get(0).getBackBrokenDate());
        }

        bs.brokeBackup(bId4, Role.Purchaser);
        Query query4 = session.createQuery("FROM Backup where backupId = :id");
        query4.setMaxResults(1);
        query4.setParameter("id", bId4);
        List<Backup> list4 = query4.list();
        Assert.assertEquals(1, list4.size());
        if (list4.size() > 0) {
            Assert.assertEquals(sqlDate, list4.get(0).getBackBrokenDate());
        }

        session.close();
    }

    /**
     * Method: borrowBackup(String employeeId, String eId, String backupName)
     */
    @Test
    public void testBorrowBackup() throws Exception {
        BackupService bs = new BackupService();
        bs.addBackup("test_borrow", Role.Admin);
        String bId = bs.borrowBackup("U001", eId, "test_borrow");
        Session session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupId = :id");
        query.setMaxResults(1);
        query.setParameter("id", bId);
        //默认查询出来的list里存放的是一个Object数组
        List<Backup> list = query.list();
        if (list.size() > 0) {
            Assert.assertEquals(State.INACTIVE, (byte) list.get(0).getBackActive());
        }
        Query queryRecord = session.createQuery("FROM BackupRecord where backupId = :id order by backupRecordId desc");
        queryRecord.setMaxResults(1);
        queryRecord.setParameter("id", bId);
        List<BackupRecord> recordList = queryRecord.list();
        if (recordList.size() > 0) {
            Assert.assertEquals("U001", recordList.get(0).getEmployeeId());
            Assert.assertEquals(eId, recordList.get(0).getEquipmentId());
            LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
            java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
            Assert.assertEquals(sqlDate, recordList.get(0).getBackApplyDate());
            Assert.assertEquals(null, recordList.get(0).getBackReturnDate());
        }
    }

    /**
     * Method: returnBackup(String backupId)
     */
    @Test
    public void testReturnBackup() throws Exception {
        BackupService bs = new BackupService();
        bs.addBackup("test_return", Role.Admin);
        String bId = bs.borrowBackup("U001", eId, "test_return");
        bs.returnBackup(bId);
        Session session = factory.openSession();
        Query query = session.createQuery("FROM Backup where backupId = :id");
        query.setMaxResults(1);
        query.setParameter("id", bId);
        List<Backup> list = query.list();
        Assert.assertEquals(1, list.size());
        if (list.size() > 0) {
            Assert.assertEquals(State.ACTIVE, (byte) list.get(0).getBackActive());
        }
        Query queryRecord = session.createQuery("FROM BackupRecord order by backupRecordId desc");
        queryRecord.setMaxResults(1);
        List<BackupRecord> recordList = queryRecord.list();
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        Assert.assertEquals(sqlDate, recordList.get(0).getBackReturnDate());
        session.close();
    }


} 
