package com.zk.cw.kamera;

public class ArkaKameraAta {
	private Integer id;
    private Integer cihazId;
    private Integer arkaKameraCozunurlukId;
    private Integer diyaframAcikligiId;
    private Integer pikselBuyukluguId;
    
    public ArkaKameraAta() {
    }
    public ArkaKameraAta(Integer id, Integer cihazId, Integer arkaKameraCozunurlukId,Integer diyaframAcikligiId,Integer pikselBuyukluguId) {
        this.id = id;
        this.cihazId = cihazId;
        this.arkaKameraCozunurlukId = arkaKameraCozunurlukId;
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
	public Integer getArkaKameraCozunurlukId() {
        return this.arkaKameraCozunurlukId;
    }
	public void setArkaKameraCozunurlukId(Integer arkaKameraCozunurlukId) {
        this.arkaKameraCozunurlukId=arkaKameraCozunurlukId;
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