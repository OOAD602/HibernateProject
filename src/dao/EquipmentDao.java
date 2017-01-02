package dao;

import entity.Equipment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

/**
 * Created by Ding on 17/1/1.
 */
public class EquipmentDao{
    private Session session = null;
    public EquipmentDao() {
        Configuration config = new Configuration();
        SessionFactory factory = config.configure().buildSessionFactory();
        this.session = factory.openSession();
    }
    // 所有的操作都是通过session来完成的
    public boolean saveAddEquipment(Equipment eqp) {
        Transaction tran = this.session.beginTransaction();//开始事物
        this.session.save(eqp);//执行
        tran.commit();//提交
        return true;
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
