package com.example.materialscompany;

public class SostoyanieSkladaM {
    private Integer id;
    private String naimenovanie;
    private String kolechestvo;
    private String edizmerenie;

    public SostoyanieSkladaM(Integer id, String naimenovanie, String kolechestvo, String edizmerenie){
        this.id = id;
        this.naimenovanie = naimenovanie;
        this.kolechestvo = kolechestvo;
        this.edizmerenie = edizmerenie;
    }

    public SostoyanieSkladaM() {

    }

    public Integer getId() {return id;}

    public void setId (Integer id) {this.id = id;}

    public String getNaimenovanie() {return naimenovanie;}

    public void setNaimenovanie(String naimenovanie) {this.naimenovanie = naimenovanie;}

    public String getKolechestvo() {return kolechestvo;}

    public void setKolechestvo (String kolechestvo) {this.kolechestvo = kolechestvo;}

    public String getEdizmerenie() {return edizmerenie;}

    public void setEdizmerenie (String edizmerenie) {this.edizmerenie = edizmerenie;}
    }
