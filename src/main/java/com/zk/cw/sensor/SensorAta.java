package com.zk.cw.sensor;

public class SensorAta {
	private Integer id;
    private Integer cihazId;
    private Integer sensorId;
    
    public SensorAta() {
    }
    public SensorAta(Integer id, Integer cihazId, Integer sensorId) {
        this.id = id;
        this.cihazId = cihazId;
        this.sensorId = sensorId;
    }
	public void setId(Integer id) {
        this.id=id;
    }  
	public Integer getId() {
        return this.id;
    }		
	public Integer getCihazId() {
        return this.cihazId;
    }
	public void setCihazId(Integer cihazId) {
        this.cihazId=cihazId;
    }	
	public Integer getSensorId() {
        return this.sensorId;
    }
	public void setSensorId(Integer sensorId) {
        this.sensorId=sensorId;
    }		
}