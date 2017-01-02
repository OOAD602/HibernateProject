package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
<<<<<<< HEAD
 * Created by junyuan on 02/01/2017.
=======
 * Created by Ding on 17/1/2.
>>>>>>> 8e476427233c2ec8ff3504cdb28dfeff6b3f0910
 */
public class EquipmentRecordPK implements Serializable {
    private String equipmentId;
    private String employeeId;

    @Column(name = "equipmentId")
    @Id
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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

        EquipmentRecordPK that = (EquipmentRecordPK) o;

        if (equipmentId != null ? !equipmentId.equals(that.equipmentId) : that.equipmentId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = equipmentId != null ? equipmentId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }
}
