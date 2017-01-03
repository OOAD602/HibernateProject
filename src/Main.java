import entity.Equipment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.*;

import java.util.List;
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
        SearchService schs = new SearchService();

        //        es.addEquipment("macbookpro", 0);
//        es.borrowEquipment("U001","macbookpro");
        while (sc.hasNext()) {
            String opr = sc.next();
            String resultId;
            boolean resultBo;
            switch (opr) {
                case "adde":
                    System.out.println("请输入新购买设备的名称");
                    String newEName = sc.next();
                    resultId = es.addEquipment(newEName, role);
                    if(resultId != null) {
                        System.out.println("新增设备成功");
                    } else {
                        System.out.println("新增设备失败");
                    }
                    break;
                case "addb":
                    System.out.println("请输入新购买备件的名称");
                    String newBName = sc.next();
                    resultId = bs.addBackup(newBName, role);
                    if(resultId != null) {
                        System.out.println("新增备件成功");
                    } else {
                        System.out.println("新增备件失败");
                    }
                    break;
                case "adds":
                    System.out.println("请输入新购买软件的名称");
                    String newSName = sc.next();
                    resultId = ss.addSoftware(newSName, role);
                    if(resultId != null) {
                        System.out.println("新增软件成功");
                    } else {
                        System.out.println("新增软件失败");
                    }
                    break;
                case "broe":
                    System.out.println("请输入要报废设备的ID");
                    String broEId = sc.next();
                    resultBo = es.brokeEquipment(broEId, role);
                    if (resultBo) {
                        System.out.println("报废成功");
                    } else {
                        System.out.println("报废失败");
                    }
                    break;
                case "brob":
                    System.out.println("请输入要报废备件的ID");
                    String broBId = sc.next();
                    resultBo = bs.brokeBackup(broBId, role);
                    if (resultBo) {
                        System.out.println("报废成功");
                    } else {
                        System.out.println("报废失败");
                    }
                    break;
                case "bros":
                    System.out.println("请输入要过期软件的ID");
                    String broSId = sc.next();
                    resultBo = ss.brokeSoftware(broSId, role);
                    if (resultBo) {
                        System.out.println("报废成功");
                    } else {
                        System.out.println("报废失败");
                    }
                    break;
                case "be":
                    System.out.println("请输入申请设备的名称");
                    String eName = sc.next();
                    resultId = es.borrowEquipment(userId, eName);
                    if(resultId!=null){
                        System.out.println("您申请到的设备ID为：" + resultId);
                    } else {
                        System.out.println("您未成功申领到设备");
                    }
                    break;
                case "re":
                    System.out.println("请输入归还设备的ID");
                    String eId = sc.next();
                    resultBo = es.returnEquipment(eId);
                    if (resultBo) {
                        System.out.println("归还成功");
                    } else {
                        System.out.println("归还失败");
                    }
                    break;
                case "bb":
                    System.out.println("请输入申请备件的名称");
                    String bName = sc.next();
                    System.out.println("请输入安装备件的设备的ID");
                    String addEId = sc.next();
                    resultId = bs.borrowBackup(userId, addEId, bName);
                    if(resultId!=null){
                        System.out.println("申请备件成功，您为设备" + addEId + "申请到的备件ID为：" + resultId);
                    } else {
                        System.out.println("申请备件失败");
                    }
                    break;
                case "rb":
                    System.out.println("请输入归还备件的ID");
                    String rbId = sc.next();
                    resultBo = bs.returnBackup(rbId);
                    if (resultBo) {
                        System.out.println("归还备件成功");
                    } else {
                        System.out.println("归还备件失败");
                    }
                    break;
                case "is":
                    System.out.println("请输入申请软件的名称");
                    String isName = sc.next();
                    resultId = ss.installSoftware(userId, isName);
                    if (resultId != null) {
                        System.out.println("您申请到的软件ID为：" + resultId);
                    } else {
                        System.out.println("您未成功申领到软件");
                    }
                    break;
                case "us":
                    System.out.println("请输入归还软件的ID");
                    String isId = sc.next();
                    resultBo = ss.uninstallSoftware(isId);
                    if (resultBo) {
                        System.out.println("归还成功");
                    } else {
                        System.out.println("归还失败");
                    }
                    break;
                case "search":
                    System.out.println("请输入需要查询的对象：");
                    opr = sc.next();
                    String id;
                    String name;
                    List<?> result = null;
                    switch (opr) {
                        case "eqbyuid":
                            id = sc.next();
                            result = schs.getAllMyEquipment(id);
                            break;
                        case "mylogbyuid":
                            id = sc.next();
                            result = schs.allMyLog(id);
                            break;
                        case "alleq":
                            result = schs.allEquipment(role);
                            break;
                        case "allbc":
                            result = schs.allBackup(role);
                            break;
                        case "alleqr":
                            result = schs.equipmentLog(role);
                            break;
                        case "ubysid":
                            id = sc.next();
                            result = schs.listSoftOwners(id, role);
                            break;
                        case "ubyeid":
                            id = sc.next();
                            result = schs.listEquipmentOwners(id, role);
                            break;
                        case "bcrbyeid":
                            id = sc.next();
                            result = schs.listEquipmentBackupLog(id, role);
                            break;
                        case "eidbyname":
                            name = sc.next();
                            result = schs.listUsableEquipmentId(name, role);
                            break;
                        case "sidbyname":
                            name = sc.next();
                            result = schs.listUsableSoftId(name, role);
                            break;
                        case "bidbyname":
                            name = sc.next();
                            result = schs.listUsableBackupId(name, role);
                            break;
                        case "bcrbybid":
                            id = sc.next();
                            result = schs.listBackupLog(id, role);
                            break;
                        case "allbyuid":
                            id = sc.next();
                            result = schs.listUsersAll(id, role);
                            break;
                        case "logbyuid":
                            id = sc.next();
                            result = schs.listUsersLog(id, role);
                            break;
                        default:
                            System.out.println("请输入正确的指令");
                    }
                    if (result != null) {
                        if (result.isEmpty()) {
                            System.out.println("No Result!");
                        } else {
                            iterateOutput(result);
                        }
                    }
                    break;
                default:
                    System.out.println("请输入正确的指令");

            }
        }
    }

    public static void iterateOutput(List result) {
        for (Object e : result) {
            System.out.println(e.toString());
        }
    }
}

