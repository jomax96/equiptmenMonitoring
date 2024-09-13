package model;

public class Equipment {

	private int id;
	private int equipmentTypeId;
	private String Serial;
	private String Mac;
	private String observations;
	
	public Equipment(int id, int equipmentType, String lSerial, String lMac, String observations) {
		this.id = id;
		this.equipmentTypeId = equipmentType;
		this.Serial = lSerial;
		this.Mac = lMac;
		this.observations = observations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEquipmentTypeId() {
		return equipmentTypeId;
	}

	public void setEquipmentTypeId(int equipmentTypeId) {
		this.equipmentTypeId = equipmentTypeId;
	}

	public String getSerial() {
		return Serial;
	}

	public void setSerial(String serial) {
		Serial = serial;
	}

	public String getMac() {
		return Mac;
	}

	public void setMac(String mac) {
		Mac = mac;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return id + " " + equipmentTypeId + " " + Serial;
	}
	
}
