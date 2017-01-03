package test.service;

import entity.*;
import exception.AuthorityException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import service.Role;
import service.SearchService;
import service.State;

import java.util.List;

/**
 * SearchService Tester.
 *
 * @author <Jun Yuan>
 * @version 1.0
 * @since <pre>一月 3, 2017</pre>
 */
public class SearchServiceTest {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getAllMyEquipment(String userId)
     */
    @Test
    public void testGetAllMyEquipment() throws Exception {
        SearchService ss = new SearchService();
        String userId = "employeeAAA";
        List<Equipment> list = ss.getAllMyEquipment(userId);

        Equipment expected = new Equipment();
        expected.setEquipmentId("a");
        expected.setEquipmentName("Mac");
        expected.setEquipmentActive(State.INACTIVE);
        expected.setPurchaseDate(null);
        expected.setBrokenDate(null);

        Assert.assertEquals(expected, list.get(0));
    }

    /**
     * Method: allMyLog(String userId)
     */
    @Test
    public void testAllMyLog() throws Exception {
        SearchService ss = new SearchService();
        String userId = "ea";
        List list = ss.allMyLog(userId);

        Assert.assertEquals(list.size(), 2);
        Assert.assertTrue(list.get(0) instanceof BackupRecord);
        Assert.assertTrue(list.get(1) instanceof SoftwareRecord);
    }

    /**
     * Method: allEquipment(Role role)
     */
    @Test
    public void testAllEquipment() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        List<Equipment> list = ss.allEquipment(role);

        Assert.assertEquals(3, list.size());

        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.allEquipment(role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: allBackup(Role role)
     */
    @Test
    public void testAllBackup() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        List<Backup> list = ss.allBackup(role);

        Assert.assertEquals(2, list.size());
        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.allBackup(role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: equipmentLog(Role role)
     */
    @Test
    public void testEquipmentLog() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        List<EquipmentRecord> list = ss.equipmentLog(role);

        Assert.assertEquals(1, list.size());
        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.allBackup(role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: listSoftOwners(String softId, Role role)
     */
    @Test
    public void testListSoftOwners() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        String softId = "sb";
        List<String> list = ss.listSoftOwners(softId, role);

        Assert.assertEquals(list.get(0), "ea");
        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.listSoftOwners(softId, role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: listEquipmentOwners(String equipmentId, Role role)
     */
    @Test
    public void testListEquipmentOwners() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        String equipmentId = "a";
        List<String> list = ss.listEquipmentOwners(equipmentId, role);

        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), "employeeAAA");
        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.listEquipmentOwners(equipmentId, role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: listEquipmentBackupLog(String equipmentId, Role role)
     */
    @Test
    public void testListEquipmentBackupLog() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        String eqId = "a";
        List<BackupRecord> list = ss.listEquipmentBackupLog(eqId, role);

        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getBackupId(), "bb");
        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.listEquipmentOwners(eqId, role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: listUsableEquipmentId(String equipmentName, Role role)
     */
    @Test
    public void testListUsableEquipmentId() throws Exception {
        SearchService ss = new SearchService();
        Role role = Role.Admin;
        List<String> list = ss.listUsableEquipmentId("Mac", role);

        Assert.assertEquals(list.size(), 2);
        role = Role.Purchaser;
        Exception exception = null;
        try {
            ss.listUsableEquipmentId("Mac", role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: listUsersAll(String userId, Role role)
     */
    @Test
    public void testListUsersAll() throws Exception {
        SearchService ss = new SearchService();
        String userId = "ea";
        Role role = Role.Admin;
        List list = ss.listUsersAll(userId, role);

        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.get(0) instanceof Backup);
        Assert.assertTrue(list.get(1) instanceof Software);

        role = Role.Employee;
        Exception exception = null;
        try {
            ss.listUsersAll(userId, role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

    /**
     * Method: listUsersLog(String userId, Role role)
     */
    @Test
    public void testListUsersLog() throws Exception {
        SearchService ss = new SearchService();
        String userId = "ea";
        Role role = Role.Admin;
        List list = ss.listUsersLog(userId, role);

        Assert.assertEquals(2, list.size());
        Assert.assertTrue(list.get(0) instanceof BackupRecord);
        Assert.assertTrue(list.get(1) instanceof SoftwareRecord);

        role = Role.Employee;
        Exception exception = null;
        try {
            ss.listUsersLog(userId, role);
        } catch (Exception e) {
            exception = e;
        }
        Assert.assertTrue(exception instanceof AuthorityException);
    }

} 
