package com.zk.cw.hafiza;

public class DahiliHafizaAta {
	private Integer id;
    private Integer cihazId;
    private Integer dahiliHafizaId;
    
    public DahiliHafizaAta() {
    }
    public DahiliHafizaAta(Integer id, Integer cihazId, Integer dahiliHafizaId) {
        this.id = id;
        this.cihazId = cihazId;
        this.dahiliHafizaId = dahiliHafizaId;
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
	public Integer getDahiliHafizaId() {
        return this.dahiliHafizaId;
    }
	public void setDahiliHafizaId(Integer dahiliHafizaId) {
        this.dahiliHafizaId=dahiliHafizaId;
    }
}