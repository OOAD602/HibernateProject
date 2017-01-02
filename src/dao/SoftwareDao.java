package dao;

import entity.Software;

import java.util.Date;

/**
 * Created by Ding on 17/1/1.
 */
public class SoftwareDao {
    public boolean saveAddSoftware(Software eqp) {
        return true;
    }
    public boolean saveBorrowSoftware(String userId, Date borrowDate, String SoftwareName) {
        return true;
    }
    public boolean saveReturnSoftware(Date returnDate, String SoftwareId) {
        return true;
    }
    public boolean saveBrokenSoftware(Date returnDate, String SoftwareId) {
        return true;
    }



}
