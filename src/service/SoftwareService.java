package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import dao.Dao;
import entity.Software;
import entity.SoftwareRecord;

import exception.AuthorityException;
import service.State;

/**
 * Created by Ding on 17/1/1.
 */
public class SoftwareService {
    Dao dao = new Dao();

    public String addSoftware(String name, Role autho) throws AuthorityException{
        if( !autho.equals(Role.Admin) && !autho.equals(Role.Purchaser)) {
            throw new AuthorityException(autho);
        }
        Software newSoftware = new Software();
        newSoftware.setSoftwareName(name);
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        newSoftware.setUpdateDate(sqlDate);
        newSoftware.setSoftwareId("S" + System.currentTimeMillis());
        newSoftware.setSoftwareActive(State.ACTIVE);
        boolean result = dao.buySoftware(newSoftware);
        if (result) {
            return newSoftware.getSoftwareId();
        } else {
            return null;
        }
    }

    public String installSoftware(String userId, String SoftwareName) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        SoftwareRecord sr = new SoftwareRecord();
        sr.setInstallDate(sqlDate);
        sr.setEmployeeId(userId);
        sr.setSoftwareRecordId("SR" + System.currentTimeMillis());
        String softId = dao.installSoftware(sr, SoftwareName);
        return softId;
    }

    public boolean uninstallSoftware(String SoftwareId) {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.uninstallSoftware(sqlDate, SoftwareId);
        return result;
    }

    public boolean brokeSoftware(String SoftwareId, Role autho) throws AuthorityException{
        if( !autho.equals(Role.Admin) && !autho.equals(Role.Purchaser)) {
            throw new AuthorityException(autho);
        }
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("America/Montreal"));
        java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);
        boolean result = dao.saveExpireSoftware(sqlDate, SoftwareId);
        return result;
    }


}