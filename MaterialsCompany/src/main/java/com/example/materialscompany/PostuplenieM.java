package com.example.materialscompany;
public class PostuplenieM {
    private Integer id;
    private String dataPostupleniya;
    private String codeMaterials;
    private String materials;
    private String kolichestvo;
    private String cena;
    private String edizmerenie;
    private String summa;

    public PostuplenieM(Integer id, String dataPostupleniya, String codeMaterials, String materials, String kolichestvo, String cena, String edizmerenie, String summa){
        this.id = id;
        this.dataPostupleniya = dataPostupleniya;
        this.codeMaterials = codeMaterials;
        this.materials = materials;
        this.kolichestvo = kolichestvo;
        this.cena = cena;
        this.edizmerenie = edizmerenie;
        this.summa = summa;
    }

    public PostuplenieM() {

    }

    public Integer getId() {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getDatapostupleniya() {
        return dataPostupleniya;
    }

    public void setDatapostupleniya (String dataPostupleniya) {this.dataPostupleniya = dataPostupleniya;}

    public String getCodematerials() {
        return codeMaterials;
    }

    public void setCodematerials (String codeMaterials) {
        this.codeMaterials = codeMaterials;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials (String materials) {
        this.materials = materials;
    }

    public String getKolichestvo() {
        return kolichestvo;
    }

    public void setKolichestvo (String kolichestvo) {
        this.kolichestvo = kolichestvo;
    }

    public String getCena() {
        return cena;
    }

    public void setCena (String cena) {
        this.cena = cena;
    }

    public String getEdizmerenie() {return edizmerenie;}

    public void setEdizmerenie (String edizmerenie) {this.edizmerenie = edizmerenie;}

    public String getSumma() {
        return summa;
    }

    public void setSumma (String summa) {
        this.summa = summa;
    }

}