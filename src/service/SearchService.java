package service;

import dao.SearchDao;
import entity.EquipmentRecord;

import java.util.List;

/**
 * Created by Ding on 17/1/1.
 */

public class SearchService {
    SearchDao dao = new SearchDao();

    public List<EquipmentRecord> allMyEquipment(String userId) {
        return dao.searchAllMyEquipment(userId);
    }

    public boolean allMyLog(String userId) {
        return dao.searchAllMyLog(userId);
    }

    public boolean allEquipment(int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchAllEquipment();
    }

    public boolean allBackup(int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchAllBackup();
    }

    public boolean equipmentLog(int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchEquipmentLog();
    }

    public boolean listSoftOwners(String softId, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchSoftUserLogBySoftId(softId);
    }

    public boolean listEquipmentOwners(String equipmentId, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchEquipUserLogByEquipId(equipmentId);
    }

    public boolean listEquipmentBackupLog(String equipmentId, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchBackupLogByEquipmentId(equipmentId);
    }

    public boolean listUsableEquipmentId(String equipmentName, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchEquipmentIdByName(equipmentName);
    }

    public boolean listUsableSoftId(String softName, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchSoftIdByName(softName);
    }

    public boolean listUsableBackupId(String backupName, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchBackupIdByName(backupName);
    }

    public boolean listBackupLog(String backupId, int autho) {
        if (autho != 0) {
            return false;
        }
        return dao.searchBackupLogById(backupId);
    }

    public boolean listUsersAll(String userId, int autho) {
        if (autho != 0 && autho != 2) {
            return false;
        }
        return dao.searchAllByUserId(userId);
    }

    public boolean listUsersLog(String userId, int autho) {
        if (autho != 0 && autho != 2) {
            return false;
        }
        return dao.searchLogByUserId(userId);
    }
}
