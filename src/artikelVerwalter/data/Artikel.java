/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artikelVerwalter.data;

/**
 *
 * @author lukik
 */
public class Artikel {

    private String ean;
    private String bezeichnung;
    private double menge;
    private double gewicht;
    private String einheit;

    public Artikel(String ean, String bezeichnung, double menge, double gewicht, String einheit) {
        this.ean = ean;
        this.bezeichnung = bezeichnung;
        this.menge = menge;
        this.gewicht = gewicht;
        this.einheit = einheit;
    }

    public String getEan() {
        return ean;
    }

    public double getMenge() {
        return menge;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public double getGewicht() {
        return gewicht;
    }

    public String getEinheit() {
        return einheit;
    }

}
