/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObject;
import domen.Praksa;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FILIP
 */
public class PraksaTableModel extends AbstractTableModel {
    
    String[] kolone = new String[] {"Student", "Kompanija", "Pocetak", "Kraj", "Komentar", "Ocena", "Bransa"};
    List<AbstractObject> prakse;

    public PraksaTableModel(List<AbstractObject> prakse) {
        this.prakse = prakse;
    }

    @Override
    public int getRowCount() {
        return prakse.size();
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }
    
    
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Praksa praksa = new Praksa();
        if(prakse.get(rowIndex) instanceof Praksa) {
            praksa = (Praksa) prakse.get(rowIndex);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-DD-mm");
        switch(colIndex) {
            case 0:
                return praksa.getStudent().getIme() + " " +praksa.getStudent().getPrezime();
            case 1:
                return praksa.getKompanija().getPoslovnoIme();
            case 2:
                return sdf.format(praksa.getDatumOd());
            case 3:
                return sdf.format(praksa.getDatumDo());
            case 4:
                return praksa.getKomentarPoslodavca();
            case 5:
                return praksa.getOcena();
            case 6:
                return praksa.getBransa().getImeBranse();
            default:
                return "N/A";
        }
    }

    public List<AbstractObject> getPrakse() {
        return prakse;
    }

    public void setPrakse(List<AbstractObject> prakse) {
        this.prakse = prakse;
    }
    
    
    
}
