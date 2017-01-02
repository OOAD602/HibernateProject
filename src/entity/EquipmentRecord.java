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
public class EquipmentRecord {
    private String equipmentId;
    private String employeeId;
    private Date applyDate;
    private Date returnDate;
    private String equipmentRecordId;

    @Basic
    @Column(name = "equipmentId")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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
    @Column(name = "applyDate")
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Basic
    @Column(name = "returnDate")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Id
    @Column(name = "equipmentRecordId")
    public String getEquipmentRecordId() {
        return equipmentRecordId;
    }

    public void setEquipmentRecordId(String equipmentRecordId) {
        this.equipmentRecordId = equipmentRecordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentRecord that = (EquipmentRecord) o;

        if (equipmentId != null ? !equipmentId.equals(that.equipmentId) : that.equipmentId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (applyDate != null ? !applyDate.equals(that.applyDate) : that.applyDate != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;
        if (equipmentRecordId != null ? !equipmentRecordId.equals(that.equipmentRecordId) : that.equipmentRecordId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentId != null ? equipmentId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (applyDate != null ? applyDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (equipmentRecordId != null ? equipmentRecordId.hashCode() : 0);
        return result;
    }
}
