package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Ding on 17/1/2.
 */
public class AddRecordPK implements Serializable {
    private String equipmentId;
    private String backupId;

    @Column(name = "equipmentId")
    @Id
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Column(name = "backupId")
    @Id
    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddRecordPK that = (AddRecordPK) o;

        if (equipmentId != null ? !equipmentId.equals(that.equipmentId) : that.equipmentId != null) return false;
        if (backupId != null ? !backupId.equals(that.backupId) : that.backupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentId != null ? equipmentId.hashCode() : 0;
        result = 31 * result + (backupId != null ? backupId.hashCode() : 0);
        return result;
    }
}
