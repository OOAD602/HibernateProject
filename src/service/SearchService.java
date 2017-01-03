package service;

import dao.SearchDao;
import entity.*;
import exception.AuthorityException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ding on 17/1/1.
 */

public class SearchService {
    SearchDao dao = new SearchDao();

    public List<Equipment> getAllMyEquipment(String userId) {
        return dao.searchAllMyEquipment(userId);
    }

    public List allMyLog(String userId) {
        return dao.searchAllMyLog(userId);
    }

    public List<Equipment> allEquipment(Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }
        return dao.searchAllEquipment();
    }

    public List<Backup> allBackup(Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }
        return dao.searchAllBackup();
    }

    public List<EquipmentRecord> equipmentLog(Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }
        return dao.searchEquipmentLog();
    }

    public List<String> listSoftOwners(String softId, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }

        List<SoftwareRecord> softwareRecord = dao.searchSoftUserLogBySoftId(softId);
        List<String> result = new LinkedList<>();
        for (SoftwareRecord sr : softwareRecord) {
            result.add(sr.getEmployeeId());
        }
        return result;
    }

    public List<String> listEquipmentOwners(String equipmentId, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }
        List<EquipmentRecord> equipmentRecords = dao.searchEquipUserLogByEquipId(equipmentId);
        List<String> result = new LinkedList<>();
        for (EquipmentRecord er : equipmentRecords) {
            result.add(er.getEmployeeId());
        }
        return result;
    }

    public List<BackupRecord> listEquipmentBackupLog(String equipmentId, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }
        
        return dao.searchBackupLogByEquipmentId(equipmentId);
    }

    public List<String> listUsableEquipmentId(String equipmentName, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin) && !role.equals(Role.HR)) {
            throw new AuthorityException(role);
        }
        List<Equipment> equipments = dao.searchEquipmentByName(equipmentName);

        List<String> result = new LinkedList<>();
        for (Equipment equipment : equipments) {
            if (equipment.getEquipmentActive().equals(State.ACTIVE)) {
                result.add(equipment.getEquipmentId());
            }
        }

        return result;
    }

    public List<String> listUsableSoftId(String softName, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin) && !role.equals(Role.HR)) {

            throw new AuthorityException(role);
        }
        List<Software> softwareList = dao.searchSoftwareByName(softName);

        List<String> result = new LinkedList<>();
        for (Software software : softwareList) {
            if (software.getSoftwareActive().equals(State.ACTIVE)) {
                result.add(software.getSoftwareId());
            }
        }

        return result;
    }

    public List<String> listUsableBackupId(String backupName, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin) && !role.equals(Role.HR)) {
            throw new AuthorityException(role);
        }
        List<Backup> backupList = dao.searchBackupByName(backupName);

        List<String> result = new LinkedList<>();
        for (Backup backup : backupList) {
            if (backup.getBackActive().equals(State.ACTIVE)) {
                result.add(backup.getBackupId());
            }
        }

        return result;
    }

    public List<BackupRecord> listBackupLog(String backupId, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin)) {
            throw new AuthorityException(role);
        }
        return dao.searchBackupLogById(backupId);
    }

    public List<String> listUsersAll(String userId, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin) && !role.equals(Role.HR)) {
            throw new AuthorityException(role);
        }
        List result = new LinkedList();

        result.addAll(listUsableBackupId(userId, role));
        result.addAll(listUsableEquipmentId(userId, role));
        result.addAll(listUsableSoftId(userId, role));

        return result;
    }

    public List listUsersLog(String userId, Role role) throws AuthorityException {
        if (!role.equals(Role.Admin) && !role.equals(Role.HR)) {
            throw new AuthorityException(role);
        }
        return dao.searchAllMyLog(userId);
    }
}
