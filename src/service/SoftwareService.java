package service;

import dao.SoftwareDao;
import entity.Software;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Ding on 17/1/1.
 */
public class SoftwareService {
    SoftwareDao dao = new SoftwareDao();
    public boolean addSoftware(String name, String descriptor, int autho) {
        if(autho != 0 && autho != 1) {
            return false;
        }
        Software newSoftware = new Software();
        newSoftware.setSoftwareName(name);
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveAddSoftware(newSoftware);
    }
    public boolean borrowSoftware(String userId, String SoftwareName) {
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveBorrowSoftware(userId, sqlDate, SoftwareName);
    }
    public boolean returnSoftware(String SoftwareId) {
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveReturnSoftware(sqlDate, SoftwareId);
    }
    public boolean brokeSoftware(String SoftwareId, int autho) {
        if(autho != 0 && autho != 1) {
            return false;
        }
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        return dao.saveBrokenSoftware(sqlDate, SoftwareId);
    }


}
