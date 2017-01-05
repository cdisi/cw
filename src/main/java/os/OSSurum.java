package os;

public class OSSurum {
	private Integer id;
	private Integer osId;
    private String ad;
    
    public OSSurum() {
    }
    public OSSurum(Integer id, Integer osId, String ad) {
        this.id = id;
        this.osId=osId;
        this.ad = ad;
    }
	public void setId(Integer id) {
        this.id=id;
    }    
	public Integer getId() {
        return this.id;
    }
	public void setOsId(Integer id) {
        this.osId=id;
    }    
	public Integer getOsId() {
        return this.osId;
    }	
	public void setAd(String ad) {
        this.ad=ad;
    }	
	public String getAd() {
        return this.ad;
    }	
    
    @Override
    public String toString()
    {
        return ad;
    }
}
