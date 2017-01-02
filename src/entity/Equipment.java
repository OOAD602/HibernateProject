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
public class Equipment {
    private String equipmentId;
    private Date purchaseDate;
    private String equipmentName;
    private Date applyDate;
    private Date returnDate;
    private Date brokenDate;

    @Id
    @Column(name = "equipmentId")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Basic
    @Column(name = "purchaseDate")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Basic
    @Column(name = "equipmentName")
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
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

    @Basic
    @Column(name = "brokenDate")
    public Date getBrokenDate() {
        return brokenDate;
    }

    public void setBrokenDate(Date brokenDate) {
        this.brokenDate = brokenDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (equipmentId != null ? !equipmentId.equals(equipment.equipmentId) : equipment.equipmentId != null)
            return false;
        if (purchaseDate != null ? !purchaseDate.equals(equipment.purchaseDate) : equipment.purchaseDate != null)
            return false;
        if (equipmentName != null ? !equipmentName.equals(equipment.equipmentName) : equipment.equipmentName != null)
            return false;
        if (applyDate != null ? !applyDate.equals(equipment.applyDate) : equipment.applyDate != null) return false;
        if (returnDate != null ? !returnDate.equals(equipment.returnDate) : equipment.returnDate != null) return false;
        if (brokenDate != null ? !brokenDate.equals(equipment.brokenDate) : equipment.brokenDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentId != null ? equipmentId.hashCode() : 0;
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (equipmentName != null ? equipmentName.hashCode() : 0);
        result = 31 * result + (applyDate != null ? applyDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (brokenDate != null ? brokenDate.hashCode() : 0);
        return result;
    }
}
