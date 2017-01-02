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
public class SoftwareRecord {
    private String softwareId;
    private String employeeId;
    private Date installDate;
    private Date uninstallDate;
    private String softwareRecordId;

    @Basic
    @Column(name = "softwareId")
    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
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
    @Column(name = "installDate")
    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    @Basic
    @Column(name = "uninstallDate")
    public Date getUninstallDate() {
        return uninstallDate;
    }

    public void setUninstallDate(Date uninstallDate) {
        this.uninstallDate = uninstallDate;
    }

    @Id
    @Column(name = "softwareRecordId")
    public String getSoftwareRecordId() {
        return softwareRecordId;
    }

    public void setSoftwareRecordId(String softwareRecordId) {
        this.softwareRecordId = softwareRecordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoftwareRecord that = (SoftwareRecord) o;

        if (softwareId != null ? !softwareId.equals(that.softwareId) : that.softwareId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (installDate != null ? !installDate.equals(that.installDate) : that.installDate != null) return false;
        if (uninstallDate != null ? !uninstallDate.equals(that.uninstallDate) : that.uninstallDate != null)
            return false;
        if (softwareRecordId != null ? !softwareRecordId.equals(that.softwareRecordId) : that.softwareRecordId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareId != null ? softwareId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (installDate != null ? installDate.hashCode() : 0);
        result = 31 * result + (uninstallDate != null ? uninstallDate.hashCode() : 0);
        result = 31 * result + (softwareRecordId != null ? softwareRecordId.hashCode() : 0);
        return result;
    }
}
