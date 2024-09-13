package model;

import java.util.Date;

public class Pickup {
	private int id;
    private int alertId;
    private int equipmentId;
    private Date pickupDate;
    private boolean isPickedUp;

    public Pickup(int id, int alertId, int equipmentId, Date pickupDate, boolean isPickedUp) {
        this.id = id;
        this.alertId = alertId;
        this.equipmentId = equipmentId;
        this.pickupDate = pickupDate;
        this.isPickedUp = isPickedUp;
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

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public boolean isPickedUp() {
		return isPickedUp;
	}

	public void setPickedUp(boolean isPickedUp) {
		this.isPickedUp = isPickedUp;
	}
    
    
}
