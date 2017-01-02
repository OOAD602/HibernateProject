package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Ding on 17/1/2.
 */
public class BackupRecordPK implements Serializable {
    private String backupId;
    private String employeeId;

    @Column(name = "backupId")
    @Id
    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    @Column(name = "employeeId")
    @Id
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BackupRecordPK that = (BackupRecordPK) o;

        if (backupId != null ? !backupId.equals(that.backupId) : that.backupId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = backupId != null ? backupId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }
}
