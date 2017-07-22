/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObject;
import domen.Kompanija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FILIP
 */
public class KompanijaTableModel extends AbstractTableModel {

    String[] kolone = new String[] {"Reg. broj", "Sifra djelatnosti", "Ime", "Kontakt"};
    List<AbstractObject> kompanije;

    public KompanijaTableModel(List<AbstractObject> kompanije) {
        this.kompanije = kompanije;
    }
    
    @Override
    public int getRowCount() {
        return kompanije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kompanija kompanija = new Kompanija();
        if(kompanije.get(rowIndex) instanceof Kompanija) {
            kompanija = (Kompanija) kompanije.get(rowIndex);
           
        }
        
        switch(columnIndex) {
            case 0: 
                return kompanija.getRegBr();
            case 1: 
                return kompanija.getSifraDelatnosti();
            case 2:
                return kompanija.getPoslovnoIme();
            case 3:
                return kompanija.getKontakt();
            default:
                return "N/A";
                
        }
    }

    public void setKompanije(List<AbstractObject> kompanije) {
        this.kompanije = kompanije;
    }

    public List<AbstractObject> getKompanije() {
        return kompanije;
    }
    
    
    
}
