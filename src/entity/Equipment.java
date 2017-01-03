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
public class Equipment {
    private String equipmentId;
    private String equipmentName;
    private Date purchaseDate;
    private Date brokenDate;
    private Byte equipmentActive;

    @Id
    @Column(name = "equipmentId")
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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
    @Column(name = "purchaseDate")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Basic
    @Column(name = "brokenDate")
    public Date getBrokenDate() {
        return brokenDate;
    }

    public void setBrokenDate(Date brokenDate) {
        this.brokenDate = brokenDate;
    }

    @Basic
    @Column(name = "equipmentActive")
    public Byte getEquipmentActive() {
        return equipmentActive;
    }

    public void setEquipmentActive(Byte equipmentActive) {
        this.equipmentActive = equipmentActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (equipmentId != null ? !equipmentId.equals(equipment.equipmentId) : equipment.equipmentId != null)
            return false;
        if (equipmentName != null ? !equipmentName.equals(equipment.equipmentName) : equipment.equipmentName != null)
            return false;
        if (purchaseDate != null ? !purchaseDate.equals(equipment.purchaseDate) : equipment.purchaseDate != null)
            return false;
        if (brokenDate != null ? !brokenDate.equals(equipment.brokenDate) : equipment.brokenDate != null) return false;
        if (equipmentActive != null ? !equipmentActive.equals(equipment.equipmentActive) : equipment.equipmentActive != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentId != null ? equipmentId.hashCode() : 0;
        result = 31 * result + (equipmentName != null ? equipmentName.hashCode() : 0);
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (brokenDate != null ? brokenDate.hashCode() : 0);
        result = 31 * result + (equipmentActive != null ? equipmentActive.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", brokenDate=" + brokenDate +
                ", equipmentActive=" + equipmentActive +
                '}';
    }
}
