import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Ta klasa pozwala na zakupienie przez uzytkownika nieruchomosci.
 * Najpierw naezy zaznaczyc klienta, ktory dokonuje transakcji, nastepnie nieuchomosc
 * do kupienia.
 * @author Rafal Bebenek
 */
public class Kupno {
	
	final static JFrame f = new JFrame("Kupno nieruchomosci");
    static JPanel panel = new JPanel();
    static JButton ok = new JButton ("OK");
    static JButton kup = new JButton("Zakup nieruchomosc");
    
    static JLabel label1 = new JLabel();
    static String data[][] = {};
    static String col[] = {"Imie","Nazwisko"};
    static DefaultTableModel model = new DefaultTableModel(data,col);
    
    static JTable tabelaKupujacych = new JTable(model);
    static JScrollPane scrollpane = new JScrollPane(tabelaKupujacych);
    
    static JLabel label2 = new JLabel();
    static String data2[][] = {};
    static String col2[] = {"Miejscowosc","Ulica","cena"};
    static DefaultTableModel model2 = new DefaultTableModel(data2,col2);
    
    static JTable tabelaNieruchomosci = new JTable(model2);
    static JScrollPane scrollpane2 = new JScrollPane(tabelaNieruchomosci);
	
	static BazaDanych baza = new BazaDanych();
	static int iloscRekordow = 50;
	static String[] imie = new String[iloscRekordow];
	static String[] nazwisko = new String[iloscRekordow];
	static String[] miejscowosc = new String[iloscRekordow];
	static String[] ulica = new String[iloscRekordow];
	static int[] cena = new int[iloscRekordow];
	
	static int idKlienta;
	static int idNieruchomosci;
	
	static Serwer serwer = new Serwer();
	static Biuro biuro = new Biuro();
	

	/**
	 * Tworzenie i ustawianie ramki, na ktorej znajduja sie poszczegolne elementy
	 */
	static void ustawRamke() {
	    f.setSize(640,480);
        f.add(panel);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        f.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        //f.setResizable(false);
        //f.pack();
        f.setLayout(new FlowLayout());
        f.setVisible(true);
	}
	
	
	/**
	 * Metoda wyswietlajaca wszystkich klientow w ramce, klikniecie w poszczegolna osobe
	 * ustawia ja jako osobe kupujaca.
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
					
		        	biuro.lock.lock();
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
					
					biuro.lock.unlock();
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
                  idKlienta = tabelaKupujacych.getSelectedRow() + 1;
            }
         };
         
         tabelaKupujacych.addMouseListener(listener);

	}
	
	
	/**
	 * Wyswietlenie listy nieruchomosci, ktore sa wystawione na sprzedaz.
	 * Klikniecie na konkretna nieruchomosc powoduje jej wybranie, po zatwierdzeniu
	 * ta nieruchomosc powinna stac sie wlasnoscia klienta wybranego w pozwyzszej tabeli.
	 */
	static void wyswietlListeDostepnychNieruchomosci() {
		//Lista dostepnych nieruchomosci
        
        label2.setText("Lista dostepnych nieruchomosci:");
        panel.add(label2);
        
        scrollpane2.setPreferredSize(new Dimension(500,150));
        panel.add(scrollpane2);
        
        	//serwer.wyswietlNieruchomosci("SELECT * FROM nieruchomosc",miejscowosc,ulica,cena);
        	//baza.wyswietlNieruchomosci("SELECT * FROM nieruchomosc",miejscowosc,ulica,cena);
        
        Thread t = new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
	        	biuro.lock.lock();
				biuro.wiadomosc.idCzynnosciDoWykonania = 3;
	        	biuro.wiadomosc.skryptSQL = "SELECT * FROM nieruchomosc";
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
		        	while (biuro.wiadomosc.miejscowosc[i] != null) {
		        		model2.insertRow(i-1,new Object[]{biuro.wiadomosc.miejscowosc[i],
		        				biuro.wiadomosc.ulica[i],biuro.wiadomosc.cena[i]});
		        		i++;
		        	}
		        	biuro.odebranoWiadomosc = false;
		        	//wyswietlSzczegoloweDaneKlienta(1);
				}
				
				biuro.lock.unlock();
			}
    	});
    	
    	t.start();


        
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  //id = tabelaKupujacych.rowAtPoint(e.getPoint())+1;
                  idNieruchomosci = tabelaNieruchomosci.getSelectedRow() + 1;
            }
         };
         
         tabelaNieruchomosci.addMouseListener(listener);
        
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() { public void run() {

			ustawRamke();
	         
	        ok.setSize(100, 50);
	        ok.setVisible(true);
	        
	        kup.setSize(100, 50);
	        kup.setVisible(true);
	        
	        model.setRowCount(0);
	        model2.setRowCount(0);
	        
	        wyswietlListeKlientow();
	        wyswietlListeDostepnychNieruchomosci();
	        
	        panel.add(kup);
	        panel.add(ok);
	        
	        kup.addActionListener(new ActionListener() {
            	@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
            		if (kup.isEnabled()) {
            			int wybor = JOptionPane.showConfirmDialog(null, "Czy potwierdzasz zakup nieruchomosci: " +
            					biuro.wiadomosc.miejscowosc[idNieruchomosci] + " ul. " + biuro.wiadomosc.ulica[idNieruchomosci] + 
            					" za " + biuro.wiadomosc.cena[idNieruchomosci] + " zlotych?"
            					, "Potwierdzenie", JOptionPane.YES_NO_OPTION);
            			if (wybor == JOptionPane.YES_OPTION) {
            				//baza.zakupNieruchomosci(idKlienta, idNieruchomosci);
            				biuro.lock.lock();
            				biuro.wiadomosc.idCzynnosciDoWykonania = 6;
            				biuro.wiadomosc.idKlienta = idKlienta;
            				biuro.wiadomosc.idNieruchomosci = idNieruchomosci;
            				biuro.przesylka();
            				biuro.lock.unlock();
            			}
            		}
            	}
            });
	        
	        ok.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		if (ok.isEnabled()) {
            			f.dispose();
            			baza.zamknijPolaczenie();
            		}
            	}
            });
	        
		}
	});

}
}
