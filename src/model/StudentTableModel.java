/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObject;
import domen.Student;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FILIP
 */
public class StudentTableModel extends AbstractTableModel {

    String[] kolone = new String[] {"Ime", "Prezime", "Broj Indeksa", "Godina upisa", "Fakultet"};
    List<AbstractObject> studenti;

    public StudentTableModel(List<AbstractObject> studenti) {
        this.studenti = studenti;
    }
    
    @Override
    public int getRowCount() {
        return studenti.size();
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
        Student student = new Student();
        if(studenti.get(rowIndex) instanceof Student) {
            student = (Student) studenti.get(rowIndex);
           
        }
        
        switch(columnIndex) {
            case 0: 
                return student.getIme();
            case 1: 
                return student.getPrezime();
            case 2:
                return student.getBrIndeksa();
            case 3:
                return student.getGodinaUpisa();
            case 4:
                return student.getFakultet().getImeFakulteta();
            default:
                return "N/A";
                
        }
    }

    public void setStudenti(List<AbstractObject> studenti) {
        this.studenti = studenti;
    }

    public List<AbstractObject> getStudenti() {
        return studenti;
    }
    
    
    
}
