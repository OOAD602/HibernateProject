package entity;

import javax.persistence.*;
import java.sql.Date;

/**
<<<<<<< HEAD
 * Created by junyuan on 02/01/2017.
=======
 * Created by Ding on 17/1/2.
>>>>>>> 8e476427233c2ec8ff3504cdb28dfeff6b3f0910
 */
@Entity
@IdClass(AddRecordPK.class)
public class AddRecord {
    private String equipmentId;
    private String backupId;
    private Date backAddDate;
    private Date backUnistallDate;

    @Id
    @Column(name = "equipmentId")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Id
    @Column(name = "backupId")
    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    @Basic
    @Column(name = "backAddDate")
    public Date getBackAddDate() {
        return backAddDate;
    }

    public void setBackAddDate(Date backAddDate) {
        this.backAddDate = backAddDate;
    }

    @Basic
    @Column(name = "backUnistallDate")
    public Date getBackUnistallDate() {
        return backUnistallDate;
    }

    public void setBackUnistallDate(Date backUnistallDate) {
        this.backUnistallDate = backUnistallDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddRecord addRecord = (AddRecord) o;

        if (equipmentId != null ? !equipmentId.equals(addRecord.equipmentId) : addRecord.equipmentId != null)
            return false;
        if (backupId != null ? !backupId.equals(addRecord.backupId) : addRecord.backupId != null) return false;
        if (backAddDate != null ? !backAddDate.equals(addRecord.backAddDate) : addRecord.backAddDate != null)
            return false;
        if (backUnistallDate != null ? !backUnistallDate.equals(addRecord.backUnistallDate) : addRecord.backUnistallDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentId != null ? equipmentId.hashCode() : 0;
        result = 31 * result + (backupId != null ? backupId.hashCode() : 0);
        result = 31 * result + (backAddDate != null ? backAddDate.hashCode() : 0);
        result = 31 * result + (backUnistallDate != null ? backUnistallDate.hashCode() : 0);
        return result;
    }
}
