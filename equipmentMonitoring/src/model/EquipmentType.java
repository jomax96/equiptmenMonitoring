package model;

public class EquipmentType {
	
	private int id;
	private String name;
	private int serialLength;
	private int macLength;
	
	public EquipmentType(int id, String name, int serialLength, int macLength) {
		this.id = id;
		this.name = name;
		this.serialLength = serialLength;
		this.macLength = macLength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSerialLength() {
		return serialLength;
	}

	public void setSerialLength(int serialLength) {
		this.serialLength = serialLength;
	}

	public int getMacLength() {
		return macLength;
	}

	public void setMacLength(int macLength) {
		this.macLength = macLength;
	}

	@Override
	public String toString() {
		return id + " "  + name;
	}
	

}
