import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Klasa pozwalajaca na sprzedaz nieruchomosci w imieniu klienta.
 * Najpierw nalezy wybrac klienta, ktory chce dodac nieruchomosc do bazy danych.
 * Nastepnie trzeba wypelnic pola tekstowe odpowieniemi wartosciami dotyczacych
 * konkretnej nieruchomosci, po zatwierdzeniu zostanie wykonane polecenie SQL INSERT INTO,
 * ktore doda do bazy danych te wartosci.
 * @author Rafal Bebenek
 */
public class Sprzedaz implements Zmienne{
	
	final static JFrame f = new JFrame("Sprzedaz nieruchomosci");
	static JPanel panel = new JPanel();
	static JButton anuluj = new JButton ("Anuluj");
	
	final static JLabel labelUlica = new JLabel("Ulica:");
    final static JTextField textUlica = new JTextField("");
    final static JLabel labelNrDomu = new JLabel("Numer domu:");
    final static JTextField textNrDomu = new JTextField("");
    final static JLabel labelNrMieszkania = new JLabel("Numer mieszkania:");
    final static JTextField textNrMieszkania = new JTextField("");
    final static JLabel labelKodPocztowy = new JLabel("Kod pocztowy:");
    final static JTextField textKodPocztowy = new JTextField("");
    final static JLabel labelMiejscowosc = new JLabel("Miejscowosc:");
    final static JTextField textMiejscowosc = new JTextField("");
    final static JLabel labelPowierzchnia = new JLabel("Powierzchnia:");
    final static JTextField textPowierzchnia = new JTextField("");
    final static JLabel labelCena = new JLabel("Cena:");
    final static JTextField textCena = new JTextField("");
    final static JLabel labelRokBudowy = new JLabel("Rok wybudowania:");
    final static JTextField textRokBudowy = new JTextField("");
    
    final static JButton przyciskDodajNieruchomosc = new JButton("Dodaj do bazy");
    static JLabel label1 = new JLabel();
    static String data[][] = {};
    static String col[] = {"Imie","Nazwisko"};
    static DefaultTableModel model = new DefaultTableModel(data,col);
    static JTable tabelaKlientow = new JTable(model);
    static JScrollPane scrollpane = new JScrollPane(tabelaKlientow);
	
	static BazaDanych baza = new BazaDanych();
    static Serwer serwer = new Serwer();
	static Biuro biuro = new Biuro();
	
	final static Lock lock = new ReentrantLock();
	static String[] imie = new String[LICZBA_REKORDOW];
	static String[] nazwisko = new String[LICZBA_REKORDOW];
	static int id;

	
	static void listenerPrzyciskuDodajNieruchomosc () {
		przyciskDodajNieruchomosc.addActionListener(new ActionListener() {
        	@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
        		if(przyciskDodajNieruchomosc.isEnabled() && textMiejscowosc.getText().equals("") == false) {
        				try {
        					int id = baza.liczbaNieruchomosci;
        					String ulica = textUlica.getText();
        					String nr_domu = textNrDomu.getText();
        					String kod_pocztowy = textKodPocztowy.getText();
        					String miejscowosc = textMiejscowosc.getText();
        					int powierzchnia = Integer.parseInt(textPowierzchnia.getText());
        					int cena = Integer.parseInt(textCena.getText());
        					int rok_budowy = Integer.parseInt(textRokBudowy.getText());
        					int id_wlasciciela = tabelaKlientow.getSelectedRow() + 1;
        					
        					String skrypt = new String();
        					skrypt = "INSERT INTO nieruchomosc(id, ulica, nr_domu, kod_pocztowy, miejscowosc, powierzchnia, cena, rok_budowy, id_wlasciciela) " +
        					"VALUES (" + id + ", '" + ulica + "', '" + nr_domu + "', '" + kod_pocztowy + 
        					"', '" + miejscowosc + "', " + powierzchnia + ", " + cena + ", " + rok_budowy + ", " + id_wlasciciela + ")";
        					
        					//baza.insert(skrypt);
        					//serwer.insert(skrypt);
        					biuro.lock.lock();
        					biuro.wiadomosc.idCzynnosciDoWykonania = 5;
        					biuro.wiadomosc.skryptSQL = skrypt;
        					biuro.przesylka();
        					biuro.lock.unlock();
        					
        				} catch (NumberFormatException wyjatek) {
        					JOptionPane.showMessageDialog(null,"Podaj cene!");
        					wyjatek.printStackTrace();
        				}
        				catch (Exception wyjatek) {
        					JOptionPane.showMessageDialog(null,"Wystapil blad!");
        				}
        				finally {
        					f.dispose();
        					//baza.zamknijPolaczenie();
        				}
        		}
        	}
        });
	}
	
	/**
	 * Tworzenie i ustawianie ramki.
	 */
	static void ustawRamke() {
		f.setSize(640,580);
        f.add(panel);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        f.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        //f.setResizable(false);
        //f.pack();
        f.setLayout(new FlowLayout());
        f.setVisible(true);
	}
	
	
	/**
	 * Metoda pozwalajaca na wybranie klienta.
	 * Dane wpisane w ponizsze pola beda widoczne jako nieruchomosc wybranego klienta.
	 */
	@SuppressWarnings("static-access")
	static void wyswietlListeKlientow() {
		//Lista dostepnych kupujacych
        
        label1.setText("Wybierz klienta:");
        panel.add(label1);
        

        scrollpane.setVisible(true);
        scrollpane.setPreferredSize(new Dimension(500,150));
        panel.add(scrollpane);
        
        try {
        	//serwer.wyswietlKlientow("SELECT * FROM klient",imiona,nazwiska);
        	//baza.wyswietlKlientow("SELECT * FROM klient",imiona,nazwiska);
     

        	
        	Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
		        	lock.lock();
					biuro.wiadomosc.idCzynnosciDoWykonania = 1;
		        	biuro.wiadomosc.skryptSQL = "SELECT * FROM klient";
		        	biuro.przesylka();
		        	
					
					while (biuro.odebranoWiadomosc != true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
					if (biuro.odebranoWiadomosc == true) {
			        	int i=1;
			        	while (biuro.wiadomosc.imiona[i] != null) {
			        		model.insertRow(i-1,new Object[]{biuro.wiadomosc.imiona[i],biuro.wiadomosc.nazwiska[i]});
			        		i++;
			        	}
			        	biuro.odebranoWiadomosc = false;
			        	//wyswietlSzczegoloweDaneKlienta(1);
					}
					
					lock.unlock();
				}
        	});
        	
        	t.start();
        	

        	} catch (Exception e) {
        		e.printStackTrace();
        }
        	
 
	
        
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  //id = tabelaKupujacych.rowAtPoint(e.getPoint())+1;
                  id = tabelaKlientow.getSelectedRow() + 1;
            }
         };
         
         tabelaKlientow.addMouseListener(listener);

	}
	
	/**
	 * Ustawianie widocznosci labeli i pol tekstowych, nastepnie umieszczenie ich
	 * na panelu widocznym na ekranie
	 */
	static void ustawLabeleIPolaTekstowe() {
		labelUlica.setVisible(true);
		textUlica.setVisible(true);
        panel.add(labelUlica);
        panel.add(textUlica);
        
		labelNrDomu.setVisible(true);
		textNrDomu.setVisible(true);
        panel.add(labelNrDomu);
        panel.add(textNrDomu);
        
        labelKodPocztowy.setVisible(true);
		textKodPocztowy.setVisible(true);
        panel.add(labelKodPocztowy);
        panel.add(textKodPocztowy);
        
        labelMiejscowosc.setVisible(true);
		textMiejscowosc.setVisible(true);
        panel.add(labelMiejscowosc);
        panel.add(textMiejscowosc);
        
        labelPowierzchnia.setVisible(true);
		textPowierzchnia.setVisible(true);
        panel.add(labelPowierzchnia);
        panel.add(textPowierzchnia);
        
		labelCena.setVisible(true);
		textCena.setVisible(true);
        panel.add(labelCena);
        panel.add(textCena);
        
        labelRokBudowy.setVisible(true);
		textRokBudowy.setVisible(true);
        panel.add(labelRokBudowy);
        panel.add(textRokBudowy);

        przyciskDodajNieruchomosc.setSize(100, 50);
        przyciskDodajNieruchomosc.setVisible(true);
        panel.add(przyciskDodajNieruchomosc);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() { public void run() {
		    
		    ustawRamke();	   
	        wyswietlListeKlientow();
	      
	        ustawLabeleIPolaTekstowe();
	       
	        listenerPrzyciskuDodajNieruchomosc();
	        
	        
	        anuluj.setSize(100, 50);
	        anuluj.setVisible(true);
	        panel.add(anuluj);
	        
	        anuluj.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (anuluj.isEnabled()) {
            			f.dispose();
            			//baza.zamknijPolaczenie();
            		}
            	}
            });
		}
	});

}
}
