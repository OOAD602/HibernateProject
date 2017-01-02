package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Ding on 17/1/2.
 */
@Entity
public class Backup {
    private String backupId;
    private String backupName;
    private Date backApplyDate;
    private Date backReturnDate;
    private Date backBrokenDate;
    private Date backPurchaseDate;

    @Id
    @Column(name = "backupId")
    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    @Basic
    @Column(name = "backupName")
    public String getBackupName() {
        return backupName;
    }

    public void setBackupName(String backupName) {
        this.backupName = backupName;
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

    @Basic
    @Column(name = "backBrokenDate")
    public Date getBackBrokenDate() {
        return backBrokenDate;
    }

    public void setBackBrokenDate(Date backBrokenDate) {
        this.backBrokenDate = backBrokenDate;
    }

    @Basic
    @Column(name = "backPurchaseDate")
    public Date getBackPurchaseDate() {
        return backPurchaseDate;
    }

    public void setBackPurchaseDate(Date backPurchaseDate) {
        this.backPurchaseDate = backPurchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Backup backup = (Backup) o;

        if (backupId != null ? !backupId.equals(backup.backupId) : backup.backupId != null) return false;
        if (backupName != null ? !backupName.equals(backup.backupName) : backup.backupName != null) return false;
        if (backApplyDate != null ? !backApplyDate.equals(backup.backApplyDate) : backup.backApplyDate != null)
            return false;
        if (backReturnDate != null ? !backReturnDate.equals(backup.backReturnDate) : backup.backReturnDate != null)
            return false;
        if (backBrokenDate != null ? !backBrokenDate.equals(backup.backBrokenDate) : backup.backBrokenDate != null)
            return false;
        if (backPurchaseDate != null ? !backPurchaseDate.equals(backup.backPurchaseDate) : backup.backPurchaseDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = backupId != null ? backupId.hashCode() : 0;
        result = 31 * result + (backupName != null ? backupName.hashCode() : 0);
        result = 31 * result + (backApplyDate != null ? backApplyDate.hashCode() : 0);
        result = 31 * result + (backReturnDate != null ? backReturnDate.hashCode() : 0);
        result = 31 * result + (backBrokenDate != null ? backBrokenDate.hashCode() : 0);
        result = 31 * result + (backPurchaseDate != null ? backPurchaseDate.hashCode() : 0);
        return result;
    }
}
