package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by junyuan on 02/01/2017.
 */
@Entity
public class Backup {
    private String backupId;
    private String backupName;
    private Date backPurchaseDate;
    private Date backBrokenDate;
    private Byte backActive;

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
    @Column(name = "backPurchaseDate")
    public Date getBackPurchaseDate() {
        return backPurchaseDate;
    }

    public void setBackPurchaseDate(Date backPurchaseDate) {
        this.backPurchaseDate = backPurchaseDate;
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
    @Column(name = "backActive")
    public Byte getBackActive() {
        return backActive;
    }

    public void setBackActive(Byte backActive) {
        this.backActive = backActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Backup backup = (Backup) o;

        if (backupId != null ? !backupId.equals(backup.backupId) : backup.backupId != null) return false;
        if (backupName != null ? !backupName.equals(backup.backupName) : backup.backupName != null) return false;
        if (backPurchaseDate != null ? !backPurchaseDate.equals(backup.backPurchaseDate) : backup.backPurchaseDate != null)
            return false;
        if (backBrokenDate != null ? !backBrokenDate.equals(backup.backBrokenDate) : backup.backBrokenDate != null)
            return false;
        if (backActive != null ? !backActive.equals(backup.backActive) : backup.backActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = backupId != null ? backupId.hashCode() : 0;
        result = 31 * result + (backupName != null ? backupName.hashCode() : 0);
        result = 31 * result + (backPurchaseDate != null ? backPurchaseDate.hashCode() : 0);
        result = 31 * result + (backBrokenDate != null ? backBrokenDate.hashCode() : 0);
        result = 31 * result + (backActive != null ? backActive.hashCode() : 0);
        return result;
    }
}
