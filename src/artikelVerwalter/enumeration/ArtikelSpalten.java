/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artikelVerwalter.enumeration;

/**
 *
 * @author lukik
 */
public enum ArtikelSpalten {
    EAN("EAN"),
    BEZEICHNUNG("Bezeichnung"),
    MENGE("Menge"),
    GEWICHT("Gewicht"),
    EINHEIT("Einheit");
    
    private String name;

    private ArtikelSpalten(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
