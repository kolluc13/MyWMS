/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artikelVerwalter.model;

import artikelVerwalter.data.Artikel;
import artikelVerwalter.enumeration.ArtikelSpalten;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author lukik
 */
public class ArtikelstammdatenModel extends AbstractTableModel {

    private List<Artikel> liste = new ArrayList<>();

    public void addTestdaten() {
        liste.add(new Artikel("12345670", "Cola", 4.2, 2.5, "Liter"));
        liste.add(new Artikel("12345670", "Wasser", 4.2, 2.5, "Liter"));
        liste.add(new Artikel("12345670", "Fanta", 4.2, 2.5, "Liter"));

        super.fireTableDataChanged();
    }

    public void add(Artikel a) {
        liste.add(a);
        super.fireTableDataChanged();
    }

    public Artikel get(int row) {
        return liste.get(row);
    }

    public void update(int row, Artikel o) {
        liste.set(row, o);
        super.fireTableDataChanged();
    }

    public void remove(Artikel a) {
        liste.remove(a);
        super.fireTableDataChanged();
    }

    public void remove(int index) {
        liste.remove(index);
        super.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return liste.size();
    }

    @Override
    public int getColumnCount() {
        return ArtikelSpalten.values().length;
    }

    public Document save(Document doc) throws Exception {
        Element rootElement = doc.createElement("Artikeln");
        doc.appendChild(rootElement);
        for (Artikel a : liste) {
            Element art = doc.createElement("artikel");
            rootElement.appendChild(art);
            art.setAttribute("EAN", a.getEan());
            art.setAttribute("Bezeichnung", a.getBezeichnung());
            art.setAttribute("Menge", "" + a.getMenge());
            art.setAttribute("Gewicht", "" + a.getGewicht());
            art.setAttribute("Einheit", a.getEinheit());
        }
        return doc;
    }

    public void load(Document doc) throws Exception {
        String ean, bezeichnung, einheit;
        double gewicht, menge;
        liste.clear();
        NodeList nList = doc.getElementsByTagName("artikel");
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            Element eElement = (Element) nNode;

            ean = eElement.getAttribute("EAN");
            bezeichnung = eElement.getAttribute("Bezeichnung");
            menge = Double.parseDouble(eElement.getAttribute("Menge"));
            gewicht = Double.parseDouble(eElement.getAttribute("Gewicht"));
            einheit = eElement.getAttribute("Einheit");

            liste.add(new Artikel(ean, bezeichnung, menge, gewicht, einheit));
        }
        super.fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex
    ) {
        Artikel a = liste.get(rowIndex);
        switch (ArtikelSpalten.values()[columnIndex]) {
            case EAN:
                return a.getEan();
            case BEZEICHNUNG:
                return a.getBezeichnung();
            case EINHEIT:
                return a.getEinheit();
            case GEWICHT:
                return a.getGewicht();
            case MENGE:
                return a.getMenge();
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int column
    ) {
        return ArtikelSpalten.values()[column].getName();
    }
}
