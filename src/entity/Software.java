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
public class Software {
    private String softwareId;
    private String softwareName;
    private Date updateDate;

    @Id
    @Column(name = "softwareId")
    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    @Basic
    @Column(name = "softwareName")
    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    @Basic
    @Column(name = "updateDate")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Software software = (Software) o;

        if (softwareId != null ? !softwareId.equals(software.softwareId) : software.softwareId != null) return false;
        if (softwareName != null ? !softwareName.equals(software.softwareName) : software.softwareName != null)
            return false;
        if (updateDate != null ? !updateDate.equals(software.updateDate) : software.updateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = softwareId != null ? softwareId.hashCode() : 0;
        result = 31 * result + (softwareName != null ? softwareName.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        return result;
    }
}
