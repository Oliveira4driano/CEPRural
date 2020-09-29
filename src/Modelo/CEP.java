/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jonat
 */
public class CEP {
    
    private int cepidcodigo;
    private String cepcodigo;
    private String lat;
    private String longi;
    private String cepnomeprop;
    private Comunidade cepcomcodigo;
    private TipoPropriedade ceptprcodigo;

    public CEP(){
        this.cepcomcodigo = new Comunidade();
        this.ceptprcodigo = new TipoPropriedade();
        
    }
    public CEP(int cepidcodigo, String cepcodigo, String lat, String longi, 
            String cepnomeprop, Comunidade cepcomcodigo, TipoPropriedade ceptprcodigo){
        this.cepidcodigo = cepidcodigo;
        this.cepcodigo = cepcodigo;
        this.lat = lat;
        this.longi = longi;
        this.cepnomeprop = cepnomeprop;
        this.cepcomcodigo = cepcomcodigo;
        this.ceptprcodigo = ceptprcodigo;
    }

    
    /**
  
     * @return the cepidcodigo
     */
    public int getCepidcodigo() {
        return cepidcodigo;
    }

    /**
     * @return the cepcodigo
     */
    public String getCepcodigo() {
        return cepcodigo;
    }

    /**
     * @return the lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @return the longi
     */
    public String getLongi() {
        return longi;
    }

    /**
     * @return the cepnomeprop
     */
    public String getCepnomeprop() {
        return cepnomeprop;
    }

    /**
     * @return the cepcomcodigo
     */
    public Comunidade getCepcomcodigo() {
        return cepcomcodigo;
    }

    /**
     * @return the ceptprcodigo
     */
    public TipoPropriedade getCeptprcodigo() {
        return ceptprcodigo;
    }

    /**
     * @param cepidcodigo the cepidcodigo to set
     */
    public void setCepidcodigo(int cepidcodigo) {
        this.cepidcodigo = cepidcodigo;
    }

    /**
     * @param cepcodigo the cepcodigo to set
     */
    public void setCepcodigo(String cepcodigo) {
        this.cepcodigo = cepcodigo;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @param longi the longi to set
     */
    public void setLongi(String longi) {
        this.longi = longi;
    }

    /**
     * @param cepnomeprop the cepnomeprop to set
     */
    public void setCepnomeprop(String cepnomeprop) {
        this.cepnomeprop = cepnomeprop;
    }

    /**
     * @param cepcomcodigo the cepcomcodigo to set
     */
    public void setCepcomcodigo(Comunidade cepcomcodigo) {
        this.cepcomcodigo = cepcomcodigo;
    }

    /**
     * @param ceptprcodigo the ceptprcodigo to set
     */
    public void setCeptprcodigo(TipoPropriedade ceptprcodigo) {
        this.ceptprcodigo = ceptprcodigo;
    }
}
    
    