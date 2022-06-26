package com.example.materialscompany;
public class Rashods {
    private Integer id;
    private String datarashoda;
    private String sotrudnik;
    private String codeobject;
    private String object;
    private String material;
    private String osnovanie;
    private String summa;

    public Rashods(Integer id, String datarashoda, String sotrudnik, String codeobject, String object, String material, String osnovanie, String summa){
        this.id = id;
        this.datarashoda = datarashoda;
        this.sotrudnik = sotrudnik;
        this.codeobject = codeobject;
        this.object = object;
        this.material = material;
        this.osnovanie = osnovanie;
        this.summa = summa;
    }

    public Rashods() {

    }

    public Integer getId() {return id;}

    public void setId (Integer id) {this.id = id;}

    public String getDatarashoda() {return datarashoda;}

    public void setDatarashoda (String datarashoda) {this.datarashoda = datarashoda;}

    public String getSotrudnik() {return sotrudnik;}

    public void setSotrudnik (String sotrudnik) {this.sotrudnik = sotrudnik;}

    public String getCodeobject() {return codeobject;}

    public void setCodeobject (String codeobject) {this.codeobject = codeobject;}

    public String getObject() {return object;}

    public void setObject (String object) {this.object = object;}

    public String getMaterial() {return material;}

    public void setMaterial (String material) {this.material = material;}

    public String getOsnovanie() {return osnovanie;}

    public void setOsnovanie (String osnovanie) {this.osnovanie = osnovanie;}

    public String getSumma() {return summa;}

    public void setSumma (String summa) {this.summa = summa;}

}