/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import communication.Communication;
import domen.AbstractObject;
import domen.Bransa;
import domen.Kompanija;
import domen.Student;
import domen.Zaposleni;
import exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operations.Operations;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author FILIP
 */
public class Controler {
    
    private static Controler instance;
    private ClientRequest clr;
    private ServerResponse ser;
    
    private Controler() {
        
    }
    
    public static Controler getInstance() {
        if(instance == null)
            instance = new Controler();
        return instance;
    }
    
    public boolean ulogujSe(Zaposleni z) throws MailException, ClassNotFoundException {
        boolean dobar;
        
        if(z == null)
            throw new NullPointerException("Zaposleni null");
        dobar = validirajMail(z);
        
        if(dobar) {
            clr = new ClientRequest(Operations.ULOGUJ_ZAPOSLENOG, z);
        } else {
            throw new MailException("Unesite ispravan mail!");
        }
        
        try {
//            saljemo zahtjev
            Communication.getInstance().sendRequest(clr);
            System.out.println("Zahtjev uspjesno poslat!");
            System.out.println("Primamo odgovor..");
            ser = Communication.getInstance().recieveResponse();
            if(ser.getUspesnost() == Operations.USPESNO)
                return true;
            else
                return false;
        } catch (IOException ex) {
            System.out.println("Greska pri slanju zahtjeva!");
            Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean validirajMail(Zaposleni z) throws MailException {
        String mail = z.getEmail();
        if(mail.charAt(0) == '@' || mail.charAt(mail.length()-1) == '@' || !mail.contains("@")) {
            System.out.println("Mail los");
            return false;
        }
        System.out.println("Mail dobar");
        return true;
    }

    public List<AbstractObject> ucitajSveFakultete() throws IOException, ClassNotFoundException, UcitavanjeFakultetaException {
        
        System.out.println("Saljemo zahtjev za ucitavanje liste fakulteta...");
        clr = new ClientRequest(Operations.UCITAJ_SVE_FAKULTETE, null);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (List<AbstractObject>)ser.getPodaci();
        }
        throw new UcitavanjeFakultetaException(ser.getException().getMessage());
        
    }

    public void unesiNovogStudenta(Student student) throws IOException, ClassNotFoundException, Exception {
        System.out.println("Saljemo zahtjev za cuvanje studenta..");
        clr = new ClientRequest(Operations.SACUVAJ_STUDENTA, student);
        
        Communication.getInstance().sendRequest(clr);
        ser = Communication.getInstance().recieveResponse();
        if(ser.getUspesnost() != Operations.USPESNO) {
            throw ser.getException();
        }
        System.out.println("Unos uspjesan!");
    }

    public List<AbstractObject> ucitajSveBranse() throws IOException, ClassNotFoundException, UcitavanjeBransiException {
        System.out.println("Saljemo zahtjev za ucitavanje liste bransi...");
        clr = new ClientRequest(Operations.UCITAJ_SVE_BRANSE, null);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (List<AbstractObject>)ser.getPodaci();
        }
        throw new UcitavanjeBransiException(ser.getException().getMessage());
    }

    public void unesiNovuKompaniju(Kompanija kompanija) throws IOException, ClassNotFoundException, Exception {
        System.out.println("Saljemo zahtjev za cuvanje studenta..");
        clr = new ClientRequest(Operations.SACUVAJ_KOMPANIJU, kompanija);
        
        Communication.getInstance().sendRequest(clr);
        ser = Communication.getInstance().recieveResponse();
        if(ser.getUspesnost() != Operations.USPESNO) {
           throw ser.getException(); 
        }
        System.out.println("Unos uspjesan!");
        
    }
    
    public List<AbstractObject> ucitajSveStudente() throws IOException, ClassNotFoundException, UcitavanjeStudenataException {
        System.out.println("Saljemo zahtjev za ucitavanje liste studenata...");
        clr = new ClientRequest(Operations.UCITAJ_SVE_STUDENTE, null);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (List<AbstractObject>)ser.getPodaci();
        }
        throw new UcitavanjeStudenataException(ser.getException().getMessage());
    }

    public List<AbstractObject> ucitajSveKompanije() throws IOException, ClassNotFoundException, UcitavanjeKompanijaException {
        System.out.println("Saljemo zahtjev za ucitavanje liste kompanija...");
        clr = new ClientRequest(Operations.UCITAJ_SVE_KOMPANIJE, null);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (List<AbstractObject>)ser.getPodaci();
        }
        throw new UcitavanjeKompanijaException(ser.getException().getMessage());
    }

    public AbstractObject obrisiStudenta(Student student) throws IOException, ClassNotFoundException, BrisanjeStudentaException {
        System.out.println("Saljemo zahtjev za brisanje studenta...");
        clr = new ClientRequest(Operations.OBRISI_STUDENTA, student);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (AbstractObject) ser.getPodaci();
        }
        throw new BrisanjeStudentaException(ser.getException().getMessage());
    }

    public AbstractObject azurirajStudenta(Student student) throws IOException, ClassNotFoundException, AzuriranjeStudentaException {
        System.out.println("Saljemo zahtjev za azuriranje studenta...");
        clr = new ClientRequest(Operations.IZMENI_STUDENTA, student);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (AbstractObject) ser.getPodaci();
        }
        throw new AzuriranjeStudentaException(ser.getException().getMessage());
    }

    public AbstractObject obrisiKompaniju(Kompanija kompanija) throws IOException, ClassNotFoundException, BrisanjeKompanijeException  {
        System.out.println("Saljemo zahtjev za brisanje kompanije...");
        clr = new ClientRequest(Operations.OBRISI_KOMPANIJU, kompanija);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (AbstractObject) ser.getPodaci();
        }
        throw new BrisanjeKompanijeException(ser.getException().getMessage());
    }

    public AbstractObject azurirajKompaniju(Kompanija kompanija) throws IOException, AzuriranjeKompanijeException, ClassNotFoundException {
        System.out.println("Saljemo zahtjev za azuriranje kompanije...");
        clr = new ClientRequest(Operations.IZMENI_KOMPANIJU, kompanija);
        Communication.getInstance().sendRequest(clr);
        
        System.out.println("Primamo odgovor od servera");
        ser = Communication.getInstance().recieveResponse();
        
        if(ser.getUspesnost() == Operations.USPESNO) {
            return (AbstractObject) ser.getPodaci();
        }
        throw new AzuriranjeKompanijeException(ser.getException().getMessage());
    }
}

