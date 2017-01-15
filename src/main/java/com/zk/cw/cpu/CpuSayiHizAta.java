package com.zk.cw.cpu;

public class CpuSayiHizAta {
	private Integer id;
    private Integer cihazId;
    private Integer sayiId;
    private Integer hizId;
    
    public CpuSayiHizAta() {
    }
    public CpuSayiHizAta(Integer id, Integer cihazId, Integer sayiId, Integer hizId) {
        this.id = id;
        this.cihazId = cihazId;
        this.sayiId = sayiId;
        this.hizId = hizId;
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
	public Integer getSayiId() {
        return this.sayiId;
    }
	public void setSayiId(Integer sayiId) {
        this.sayiId=sayiId;
    }	
	public Integer getHizId() {
        return this.hizId;
    }
	public void setHizId(Integer hizId) {
        this.hizId=hizId;
    }
}
