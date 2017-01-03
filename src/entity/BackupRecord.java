package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Ding on 17/1/3.
 */
@Entity
public class BackupRecord {
    private String backupRecordId;
    private String backupId;
    private String employeeId;
    private String equipmentId;
    private Date backApplyDate;
    private Date backReturnDate;

    @Id
    @Column(name = "backupRecordId")
    public String getBackupRecordId() {
        return backupRecordId;
    }

    public void setBackupRecordId(String backupRecordId) {
        this.backupRecordId = backupRecordId;
    }

    @Basic
    @Column(name = "backupId")
    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    @Basic
    @Column(name = "employeeId")
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "equipmentId")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Basic
    @Column(name = "backApplyDate")
    public Date getBackApplyDate() {
        return backApplyDate;
    }

    public void setBackApplyDate(Date backApplyDate) {
        this.backApplyDate = backApplyDate;
    }

    @Basic
    @Column(name = "backReturnDate")
    public Date getBackReturnDate() {
        return backReturnDate;
    }

    public void setBackReturnDate(Date backReturnDate) {
        this.backReturnDate = backReturnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BackupRecord that = (BackupRecord) o;

        if (backupRecordId != null ? !backupRecordId.equals(that.backupRecordId) : that.backupRecordId != null)
            return false;
        if (backupId != null ? !backupId.equals(that.backupId) : that.backupId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (equipmentId != null ? !equipmentId.equals(that.equipmentId) : that.equipmentId != null) return false;
        if (backApplyDate != null ? !backApplyDate.equals(that.backApplyDate) : that.backApplyDate != null)
            return false;
        if (backReturnDate != null ? !backReturnDate.equals(that.backReturnDate) : that.backReturnDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = backupRecordId != null ? backupRecordId.hashCode() : 0;
        result = 31 * result + (backupId != null ? backupId.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (equipmentId != null ? equipmentId.hashCode() : 0);
        result = 31 * result + (backApplyDate != null ? backApplyDate.hashCode() : 0);
        result = 31 * result + (backReturnDate != null ? backReturnDate.hashCode() : 0);
        return result;
    }
}
