import entity.Equipment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.BackupService;
import service.EquipmentService;
import service.Role;
import service.SoftwareService;

import java.util.Scanner;

/**
 * Created by Ding on 17/1/1.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        EquipmentService es = new EquipmentService();
        BackupService bs = new BackupService();
        SoftwareService ss = new SoftwareService();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户类型：");
        String usertype = sc.next();
        Role role;
        switch (usertype) {
            case "admin":
                role = Role.Admin;
                break;
            case "HR":
                role = Role.HR;
                break;
            case "purchaser":
                role = Role.Purchaser;
                break;
            case "employee":
                role = Role.Employee;
                break;
            default:
                role = Role.Employee;
        }
        System.out.println("请输入用户ID：");
        String userId = sc.next();
        System.out.println("请输入操作类别：");

        while(sc.hasNext()) {
            String opr = sc.next();
            switch (opr) {
                case "adde":
                    System.out.println("请输入新购买设备的名称");
                    String newEName = sc.next();
                    es.addEquipment(newEName, role);
                    break;
                case "addb":
                    System.out.println("请输入新购买备件的名称");
                    String newBName = sc.next();
                    bs.addBackup(newBName, role);
                    break;
                case "adds":
                    System.out.println("请输入新购买软件的名称");
                    String newSName = sc.next();
                    ss.addSoftware(newSName, role);
                    break;
                case "be":
                    System.out.println("请输入申请设备的名称");
                    String eName = sc.next();
                    es.borrowEquipment(userId, eName);
                    break;
                case "re":
                    System.out.println("请输入归还设备的ID");
                    String eId = sc.next();
                    es.returnEquipment(eId);
                    break;
                case "bb":
                    System.out.println("请输入申请备件的名称");
                    String bName = sc.next();
                    System.out.println("请输入安装备件的设备的ID");
                    String addEId = sc.next();
                    bs.borrowBackup(userId,addEId, bName);
                    break;
                case "rb":
                    System.out.println("请输入归还备件的ID");
                    String rbId = sc.next();
                    bs.returnBackup(rbId);
                    break;
                case "is":
                    System.out.println("请输入申请软件的名称");
                    String isName = sc.next();
                    ss.installSoftware(userId, isName);
                    break;
                case "us":
                    System.out.println("请输入归还软件的ID");
                    String isId = sc.next();
                    ss.uninstallSoftware(isId);
                    break;
                default:
                    System.out.println("请输入正确的指令");
            }
        }
//        es.returnEquipment("E1483350326639");
        try {
            System.out.println("querying all the managed entities...");

//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
        } finally {
            session.close();
        }
    }
}