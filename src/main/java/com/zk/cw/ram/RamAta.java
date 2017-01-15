package com.zk.cw.ram;

public class RamAta {
	private Integer id;
    private Integer cihazId;
    private Integer ramId;
    
    public RamAta() {
    }
    public RamAta(Integer id, Integer cihazId, Integer ramId) {
        this.id = id;
        this.cihazId = cihazId;
        this.ramId = ramId;
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
	public Integer getRamId() {
        return this.ramId;
    }
	public void setRamId(Integer ramId) {
        this.ramId=ramId;
    }
}
