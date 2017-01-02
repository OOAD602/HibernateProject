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
@IdClass(BackupRecordPK.class)
public class BackupRecord {
    private String backupId;
    private String employeeId;
    private Date backApplyDate;
    private Date backReturnDate;

    @Id
    @Column(name = "backupId")
    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    @Id
    @Column(name = "employeeId")
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

        if (backupId != null ? !backupId.equals(that.backupId) : that.backupId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (backApplyDate != null ? !backApplyDate.equals(that.backApplyDate) : that.backApplyDate != null)
            return false;
        if (backReturnDate != null ? !backReturnDate.equals(that.backReturnDate) : that.backReturnDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = backupId != null ? backupId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (backApplyDate != null ? backApplyDate.hashCode() : 0);
        result = 31 * result + (backReturnDate != null ? backReturnDate.hashCode() : 0);
        return result;
    }
}
