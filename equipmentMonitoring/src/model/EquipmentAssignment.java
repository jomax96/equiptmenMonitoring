package model;

public class EquipmentAssignment {

	private int id;
    private int alertId;
    private int equipmentId;
    private boolean isAssigned;

    public EquipmentAssignment(int id, int alertId, int equipmentId, boolean isAssigned) {
        this.id = id;
        this.alertId = alertId;
        this.equipmentId = equipmentId;
        this.isAssigned = isAssigned;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}
}
