package test.service;


import entity.Equipment;
import entity.EquipmentRecord;
import exception.AuthorityException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.EquipmentService;
import service.Role;
import service.State;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
* EquipmentService Tester. 
* 
* @author <Authors name> 
* @since <pre>一月 3, 2017</pre> 
* @version 1.0 
*/ 
public class EquipmentServiceTest {
    Configuration config = new Configuration();
    SessionFactory factory = config.configure().buildSessionFactory();
    LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
    java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);

@Before
public void before() throws Exception { 
}

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: addEquipment(String name, int autho) 
* 
*/ 
@Test
public void testAddEquipment() throws Exception {
    EquipmentService es = new EquipmentService();
    es.addEquipment("test_add", Role.Admin);
    Session session = factory.openSession();
    Query query = session.createQuery( "select equipmentName,purchaseDate,equipmentActive FROM Equipment order by equipmentId desc");
    query.setMaxResults(1);
    //默认查询出来的list里存放的是一个Object数组
    List<Object[]> list = query.list();
    if(list.size()>0) {
        String name = (String)list.get(0)[0];
        Date date = (java.sql.Date)list.get(0)[1];
        byte active = (byte)list.get(0)[2];
        Assert.assertEquals("test_add", name);
        Assert.assertEquals(sqlDate, date);
        Assert.assertEquals(State.ACTIVE, active);
    }

    try {
        es.addEquipment("test_add3", Role.Employee);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    try {
        es.addEquipment("test_add4", Role.HR);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }

    es.addEquipment("test_add2", Role.Purchaser);
    Query query2 = session.createQuery( "select equipmentName,purchaseDate,equipmentActive FROM Equipment order by equipmentId desc");
    query2.setMaxResults(1);
    //默认查询出来的list里存放的是一个Object数组
    List<Object[]> list2 = query2.list();
    if(list2.size()>0) {
        String name2 = (String)list2.get(0)[0];
        Date date2 = (java.sql.Date)list2.get(0)[1];
        byte active2 = (byte)list2.get(0)[2];
        Assert.assertEquals("test_add2", name2);
        Assert.assertEquals(sqlDate, date2);
        Assert.assertEquals(State.ACTIVE, active2);
    }
    session.close();
//TODO: Test goes here...
} 

/** 
* 
* Method: borrowEquipment(String userId, String equipmentName) 
* 
*/ 
@Test
public void testBorrowEquipment() throws Exception {
    EquipmentService es = new EquipmentService();
    es.addEquipment("test_borrow", Role.Admin);
    String eId = es.borrowEquipment("U001","test_borrow");
    Session session = factory.openSession();
    Query query = session.createQuery( "FROM Equipment where equipmentId = :id");
    query.setMaxResults(1);
    query.setParameter("id", eId);
    //默认查询出来的list里存放的是一个Object数组
    List<Equipment> list = query.list();
    if(list.size()>0) {
        Assert.assertEquals(State.INACTIVE, (byte)list.get(0).getEquipmentActive());
    }
    Query queryRecord = session.createQuery("SELECT employeeId,applyDate,returnDate FROM EquipmentRecord where equipmentId = :id order by equipmentRecordId desc");
    queryRecord.setMaxResults(1);
    queryRecord.setParameter("id", eId );
    List<Object[]> recordList = queryRecord.list();
    if(recordList.size()>0) {
        Assert.assertEquals("U001", (String)recordList.get(0)[0]);
        Assert.assertEquals(sqlDate, (Date) recordList.get(0)[1]);
        Assert.assertEquals(null, (Date) recordList.get(0)[2]);
    }
    session.close();
//TODO: Test goes here...
} 

/** 
* 
* Method: returnEquipment(String equipmentId) 
* 
*/ 
@Test
public void testReturnEquipment() throws Exception {
    EquipmentService es = new EquipmentService();
    es.addEquipment("test_return", Role.Admin);
    String eId = es.borrowEquipment("U001", "test_return");
    es.returnEquipment(eId);
    Session session = factory.openSession();
    Query query = session.createQuery( "FROM Equipment where equipmentId = :id");
    query.setMaxResults(1);
    query.setParameter("id", eId);
    List<Equipment> list = query.list();
    if(list.size()>0) {
        Assert.assertEquals(State.ACTIVE, (byte)list.get(0).getEquipmentActive());
    }
    Query queryRecord = session.createQuery("FROM EquipmentRecord order by equipmentRecordId desc");
    queryRecord.setMaxResults(1);
    List<EquipmentRecord> recordList = queryRecord.list();
    Assert.assertEquals(sqlDate, recordList.get(0).getReturnDate());
    session.close();
//TODO: Test goes here...
} 

/** 
* 
* Method: brokeEquipment(String equipmentId, int autho) 
* 
*/ 
@Test
public void testBrokeEquipment() throws Exception { 
//TODO: Test goes here...
    EquipmentService es = new EquipmentService();
    String eId1 = es.addEquipment("test_broke1", Role.Admin);
    String eId2 = es.addEquipment("test_broke2", Role.Admin);
    String eId3 = es.addEquipment("test_broke3", Role.Admin);
    String eId4 = es.addEquipment("test_broke4", Role.Admin);

    es.brokeEquipment(eId1, Role.Admin);
    Session session = factory.openSession();
    Query query = session.createQuery( "FROM Equipment where equipmentId = :id");
    query.setMaxResults(1);
    query.setParameter("id", eId1);
    List<Equipment> list = query.list();
    Assert.assertEquals(1, list.size());
    if(list.size()>0) {
        Assert.assertEquals(sqlDate, list.get(0).getBrokenDate());
    }

    try {
        es.brokeEquipment(eId2, Role.Employee);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    Query query2 = session.createQuery( "FROM Equipment where equipmentId = :id");
    query2.setMaxResults(1);
    query2.setParameter("id", eId2);
    List<Equipment> list2 = query2.list();
    Assert.assertEquals(1, list2.size());
    if(list2.size()>0) {
        Assert.assertEquals(null, list2.get(0).getBrokenDate());
    }

    try {
        es.brokeEquipment(eId3, Role.HR);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    Query query3 = session.createQuery( "FROM Equipment where equipmentId = :id");
    query3.setMaxResults(1);
    query3.setParameter("id", eId3);
    List<Equipment> list3 = query3.list();
    Assert.assertEquals(1, list3.size());
    if(list3.size()>0) {
        Assert.assertEquals(null, list3.get(0).getBrokenDate());
    }

    es.brokeEquipment(eId4, Role.Purchaser);
    Query query4 = session.createQuery( "FROM Equipment where equipmentId = :id");
    query4.setMaxResults(1);
    query4.setParameter("id", eId4);
    List<Equipment> list4 = query4.list();
    Assert.assertEquals(1, list4.size());
    if(list4.size()>0) {
        Assert.assertEquals(sqlDate, list.get(0).getBrokenDate());
    }

    session.close();
} 


} 
