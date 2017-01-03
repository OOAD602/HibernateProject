package test.service; 

import entity.Software;
import entity.SoftwareRecord;
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
import service.Role;
import service.SoftwareService;
import service.State;

import javax.net.ssl.SSLContext;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/** 
* SoftwareService Tester. 
* 
* @author <Authors name> 
* @since <pre>一月 3, 2017</pre> 
* @version 1.0 
*/ 
public class SoftwareServiceTest {
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
* Method: addSoftware(String name, Role autho) 
* 
*/ 
@Test
public void testAddSoftware() throws Exception { 
//TODO: Test goes here...
    SoftwareService ss = new SoftwareService();
    ss.addSoftware("test_add", Role.Admin);
    Session session = factory.openSession();
    Query query = session.createQuery( "FROM Software order by softwareId desc");
    query.setMaxResults(1);
    //默认查询出来的list里存放的是一个Object数组
    List<Software> list = query.list();
    if(list.size()>0) {
        String name = list.get(0).getSoftwareName();
        Date updateDate = list.get(0).getUpdateDate();
        Date expireDate = list.get(0).getExpireDate();
        byte active = list.get(0).getSoftwareActive();
        Assert.assertEquals("test_add", name);
        Assert.assertEquals(sqlDate, updateDate);
        Assert.assertEquals(null, expireDate);
        Assert.assertEquals(State.ACTIVE, active);
    }

    ss.addSoftware("test_add2", Role.Purchaser);
    Query query2 = session.createQuery( "FROM Software order by softwareId desc");
    query2.setMaxResults(1);
    //默认查询出来的list里存放的是一个Object数组
    List<Software> list2 = query2.list();
    if(list2.size()>0) {
        String name2 = list2.get(0).getSoftwareName();
        Date updateDate2 = list2.get(0).getUpdateDate();
        Date expireDate2 = list2.get(0).getExpireDate();
        byte active2 = list2.get(0).getSoftwareActive();
        Assert.assertEquals("test_add2", name2);
        Assert.assertEquals(sqlDate, updateDate2);
        Assert.assertEquals(null, expireDate2);
        Assert.assertEquals(State.ACTIVE, active2);
    }

    try {
        ss.addSoftware("test_add3", Role.HR);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    Query query3 = session.createQuery( "FROM Software where softwareName = :name");
    query3.setMaxResults(1);
    query3.setParameter("name","test_add3");
    //默认查询出来的list里存放的是一个Object数组
    List<Software> list3 = query3.list();
    Assert.assertEquals(0,list3.size());

    try {
        ss.addSoftware("test_add4", Role.Employee);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    Query query4 = session.createQuery( "FROM Software where softwareName = :name");
    query4.setMaxResults(1);
    query4.setParameter("name","test_add4");
    //默认查询出来的list里存放的是一个Object数组
    List<Software> list4 = query4.list();
    Assert.assertEquals(0,list4.size());
    session.close();
} 

/** 
* 
* Method: installSoftware(String userId, String SoftwareName) 
* 
*/ 
@Test
public void testInstallSoftware() throws Exception { 
//TODO: Test goes here...
    SoftwareService ss = new SoftwareService();
    ss.addSoftware("test_install", Role.Admin);
    String sId = ss.installSoftware("U001","test_install");
    Session session = factory.openSession();
    Query queryRecord = session.createQuery("FROM SoftwareRecord where softwareId = :id order by softwareRecordId desc");
    queryRecord.setMaxResults(1);
    queryRecord.setParameter("id", sId );
    List<SoftwareRecord> recordList = queryRecord.list();
    if(recordList.size()>0) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        Assert.assertEquals(sqlDate, recordList.get(0).getInstallDate());
        Assert.assertEquals(null, recordList.get(0).getUninstallDate());
        Assert.assertEquals("U001",recordList.get(0).getEmployeeId());
    }
    session.close();
} 

/** 
* 
* Method: uninstallSoftware(String SoftwareId) 
* 
*/ 
@Test
public void testUninstallSoftware() throws Exception { 
//TODO: Test goes here...
    SoftwareService ss = new SoftwareService();
    ss.addSoftware("test_uninstall", Role.Admin);
    String sId = ss.installSoftware("U001","test_uninstall");
    ss.uninstallSoftware(sId);
    Session session = factory.openSession();
    Query queryRecord = session.createQuery("FROM SoftwareRecord where softwareId = :id order by softwareRecordId desc");
    queryRecord.setMaxResults(1);
    queryRecord.setParameter("id", sId );
    List<SoftwareRecord> recordList = queryRecord.list();
    if(recordList.size()>0) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        Assert.assertEquals(sqlDate, recordList.get(0).getUninstallDate());
        Assert.assertEquals("U001",recordList.get(0).getEmployeeId());
    }
    session.close();
} 

/** 
* 
* Method: brokeSoftware(String SoftwareId, int autho) 
* 
*/ 
@Test
public void testBrokeSoftware() throws Exception { 
//TODO: Test goes here...
    SoftwareService ss = new SoftwareService();
    Session session = factory.openSession();

    String sId = ss.addSoftware("test_expire", Role.Admin);
    String sId2 = ss.addSoftware("test_expire2", Role.Admin);
    String sId3 = ss.addSoftware("test_expire3", Role.Admin);
    String sId4 = ss.addSoftware("test_expire4", Role.Admin);

    ss.brokeSoftware(sId, Role.Admin);
    Query query = session.createQuery( "FROM Software where softwareId = :id order by softwareId desc");
    query.setMaxResults(1);
    query.setParameter("id", sId);
    List<Software> list = query.list();
    if(list.size()>0) {
        Date expireDate = list.get(0).getExpireDate();
        byte active = list.get(0).getSoftwareActive();
        Assert.assertEquals(sqlDate, expireDate);
        Assert.assertEquals(State.INACTIVE, active);
    }

    ss.brokeSoftware(sId2, Role.Purchaser);
    Query query2 = session.createQuery( "FROM Software where softwareId = :id");
    query2.setMaxResults(1);
    query2.setParameter("id", sId2);
    List<Software> list2 = query2.list();
    if(list2.size()>0) {
        Date expireDate2 = list2.get(0).getExpireDate();
        byte active2 = list2.get(0).getSoftwareActive();
        Assert.assertEquals(sqlDate, expireDate2);
        Assert.assertEquals(State.INACTIVE, active2);
    }

    try {
        ss.brokeSoftware(sId3, Role.HR);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    Query query3 = session.createQuery( "FROM Software where softwareId = :id");
    query3.setMaxResults(1);
    query3.setParameter("id", sId3);
    List<Software> list3 = query3.list();
    if(list3.size()>0) {
        Date expireDate3 = list3.get(0).getExpireDate();
        byte active3 = list3.get(0).getSoftwareActive();
        Assert.assertEquals(null, expireDate3);
        Assert.assertEquals(State.ACTIVE, active3);
    }

    try {
        ss.brokeSoftware(sId4, Role.Employee);
    } catch (Exception e) {
        Assert.assertTrue(e instanceof AuthorityException);
    }
    Query query4 = session.createQuery( "FROM Software where softwareId = :id");
    query4.setMaxResults(1);
    query4.setParameter("id", sId4);
    List<Software> list4 = query4.list();
    if(list4.size()>0) {
        Date expireDate4 = list4.get(0).getExpireDate();
        byte active4 = list4.get(0).getSoftwareActive();
        Assert.assertEquals(null, expireDate4);
        Assert.assertEquals(State.ACTIVE, active4);
    }

    session.close();
} 


} 
