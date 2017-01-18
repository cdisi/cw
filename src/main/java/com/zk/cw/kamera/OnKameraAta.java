package com.zk.cw.kamera;

public class OnKameraAta {
	private Integer id;
    private Integer cihazId;
    private Integer cozunurlukId;
    private Integer diyaframAcikligiId;
    private Integer pikselBuyukluguId;
    
    public OnKameraAta() {
    }
    public OnKameraAta(Integer id, Integer cihazId, Integer cozunurlukId,Integer diyaframAcikligiId,Integer pikselBuyukluguId) {
        this.id = id;
        this.cihazId = cihazId;
        this.cozunurlukId = cozunurlukId;
        this.diyaframAcikligiId = diyaframAcikligiId;
        this.pikselBuyukluguId = pikselBuyukluguId;
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
	public Integer getKameraCozunurlukId() {
        return this.cozunurlukId;
    }
	public void setKameraCozunurlukId(Integer cozunurlukId) {
        this.cozunurlukId=cozunurlukId;
    }
	public Integer getDiyaframAcikligiIdId() {
        return this.diyaframAcikligiId;
    }
	public void setDiyaframAcikligiId(Integer diyaframAcikligiId) {
        this.diyaframAcikligiId=diyaframAcikligiId;
    }
	public Integer getPikselBuyukluguId() {
        return this.pikselBuyukluguId;
    }
	public void setPikselBuyukluguId(Integer pikselBuyukluguId) {
        this.pikselBuyukluguId=pikselBuyukluguId;
    }	
}