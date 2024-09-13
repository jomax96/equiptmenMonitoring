package model;

import java.util.Date;

public class Alert {
	private int id;
	private String name;
	private String trackingNumber;
	private Date createdAt;
	private boolean isFinished;

	public Alert(int id, String name, String trackingNumber, Date createdAt, boolean isFinished) {
		this.id = id;
		this.name = name;
		this.trackingNumber = trackingNumber;
		this.createdAt = createdAt;
		this.isFinished = isFinished;
	}

	// Getters y Setters
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

	public String getTrackingNumber() {
		return trackingNumber; 
		}
	public void setTrackingNumber(String trackingNumber) { 
		this.trackingNumber = trackingNumber; 
		}

	public Date getCreatedAt() { 
		return createdAt; 
		}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt; 
		}

	public boolean isFinished() {
		return isFinished; 
		}
	public void setFinished(boolean isFinished) { 
		this.isFinished = isFinished; 
		}

	@Override
	public String toString() {
		return id + " " + name;
	}
	
}
