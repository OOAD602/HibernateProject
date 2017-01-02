package dao;

import entity.Equipment;
import entity.EquipmentRecord;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.State;

import java.util.Date;
import java.util.List;

/**
 * Created by Ding on 17/1/1.
 */
public class Dao {
    private Session session = null;

    public Dao() {
        Configuration config = new Configuration();
        SessionFactory factory = config.configure().buildSessionFactory();
        this.session = factory.openSession();
    }

    // 所有的操作都是通过session来完成的
    public boolean buy(Object obj) {
        Transaction tran = this.session.beginTransaction();//开始事物
        this.session.save(obj);//执行
        tran.commit();//提交
        return true;
    }

    public boolean borrow(EquipmentRecord er, String equipmentName) {
        Query query = session.createQuery("FROM Equipment where equipmentName = :name");
        query.setParameter("name", equipmentName);
        //默认查询出来的list里存放的是一个Object数组
        List<Equipment> equipments = query.list();
        for (Equipment equipment : equipments) {
            String name = equipment.getEquipmentName();
            if (equipment.getEquipmentActive() == State.ACTIVE) {
                equipment.setEquipmentActive(State.INACTIVE);
                er.setEquipmentId(equipment.getEquipmentId());
                System.out.println(er);
                Transaction tran = this.session.beginTransaction();//开始事物
                this.session.save(er);
                tran.commit();
                return true;
            }
        }
        return false;
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
