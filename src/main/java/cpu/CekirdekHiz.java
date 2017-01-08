package cpu;

public class CekirdekHiz {
	private Integer id;
    private Integer cihazId;
    private Integer cpuCekirdekSayiId;
    private String hiz;
    
    public CekirdekHiz() {
    }
    public CekirdekHiz(Integer id,Integer cihazId, Integer cpuCekirdekSayiId, String hiz) {
        this.id = id;
        this.cihazId = cihazId;
        this.cpuCekirdekSayiId=cpuCekirdekSayiId;
        this.hiz=hiz;
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
	public Integer getCpuCekirdekSayiId() {
        return this.cpuCekirdekSayiId;
    }
	public void setCpuCekirdekSayiId(Integer cpuCekirdekSayiId) {
        this.cpuCekirdekSayiId=cpuCekirdekSayiId;
    }	
	public void setHiz(String hiz) {
        this.hiz=hiz;
    }	
	public String getHiz() {
        return this.hiz;
    }	
    
    @Override
    public String toString()
    {
        return hiz;
    }
}
