import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.*;


/**
 * Glowna klasa programu, za jej pomoca mozna obslugiwac system do obslugi
 * Biura Posrednictwa Nieruchomosci.
 * Domyslnie ta klasa powinna byc wywolywana przez uzytkownika, za jej pomoca
 * mozna dokonywac wywolania obslugi np. dodawania klienta do bazy
 * @author Rafal Bebenek
 */
public class Biuro implements Zmienne{
	
	
	static Klient klient = new Klient();
	static Sprzedaz s = new Sprzedaz();
	static DodajKlienta dodajKlienta = new DodajKlienta();
	static BazaDanych bazaDanych = new BazaDanych();
	
	static JFrame f = new JFrame("System obslugi biura posrednictwa nieruchomosci");
    static JPanel panel = new JPanel();
    final static JLabel label = new JLabel("Biuro");
    final static JLabel label2 = new JLabel("Polaczono z baza");
    final static JButton b1 = new JButton("Zaloguj");
    final static JButton b2 = new JButton("Sprzeda¿ nieruchomosci");
    final static JButton b3 = new JButton("Zakup nieruchomosci");
    final static JButton b4 = new JButton("Zarejestruj klienta");
    final static JButton przyciskWyswietlDaneKlienta = new JButton("Wyswietl dane klienta");
    final static JButton przyciskWyswietlDaneNieruchomosci = new JButton("Wyswietl dane nieruchomosci");
    final static JButton b99 = new JButton("Powrót");
    final static JButton przyciskWyloguj = new JButton("Wyloguj");
    
    final static JLabel labelLogin = new JLabel("Nazwa uzytkownika (domyslnie 'pracownik'): ");
    final static JLabel labelHaslo = new JLabel("Haslo (domyslnie 'haslo'): ");
    final static JTextField login = new JTextField();
    final static JPasswordField haslo = new JPasswordField();
    
    static Dimension rozmiarPrzyciskow = new Dimension(300,30);
    
    
    //Zmienne potrzebne do obslugi sieci:
    static String host;
	static Socket socket = null;
	static int czyPolaczono = 0;
	static Wiadomosc wiadomosc= new Wiadomosc();
	static Logowanie logowanie = new Logowanie();
	final static JLabel labelPolaczenie = new JLabel("Polaczenie: ");
	static boolean odebranoWiadomosc = false;
	
	
	final static Lock lock = new ReentrantLock();
	
	public Biuro() {
		
	}
	
	/**
	 * Ustawianie ramki
	 */
	static void ustawRamke() {
        f.setSize(SZEROKOSC,WYSOKOSC);
        f.add(panel);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        f.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        panel.add(label);
        label2.setVisible(false);
        panel.add(label2);
        //f.setResizable(false);
        //f.pack();
        f.setLayout(new FlowLayout());
        f.setVisible(true);
	}
	
	/**
	 * Ustawienie przyciskow do zalogowania, czyli na ekranie widac tylko pola tekstowe
	 * i przycisk "zaloguj"
	 */
	static void ustawPrzyciski () {
        labelLogin.setVisible(true);
        labelHaslo.setVisible(true);
        login.setVisible(true);
        haslo.setVisible(true);
        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelHaslo);
        panel.add(haslo);
        
        labelPolaczenie.setVisible(false);
        panel.add(labelPolaczenie);
        
		//b1.setSize(100, 50);
		b1.setPreferredSize(rozmiarPrzyciskow);
		b1.setVisible(true);
        panel.add(b1);
		
        b2.setSize(100, 50);
        b2.setPreferredSize(rozmiarPrzyciskow);
        b2.setVisible(false);
        panel.add(b2);
        
        b3.setSize(100, 50);
        b3.setPreferredSize(rozmiarPrzyciskow);
        b3.setVisible(false);
        panel.add(b3);
        
        b4.setSize(100, 50);
        b4.setVisible(false);
        panel.add(b4);
        
        przyciskWyswietlDaneKlienta.setSize(100, 50);
        przyciskWyswietlDaneKlienta.setVisible(false);
        panel.add(przyciskWyswietlDaneKlienta);
        
        przyciskWyswietlDaneNieruchomosci.setSize(100, 50);
        przyciskWyswietlDaneNieruchomosci.setVisible(false);
        panel.add(przyciskWyswietlDaneNieruchomosci);
        
        b99.setSize(100, 50);
        b99.setVisible(false);
        panel.add(b99);
        
        przyciskWyloguj.setSize(100, 50);
        przyciskWyloguj.setVisible(false);
        panel.add(przyciskWyloguj);
        
        label.setText("Biuro posrednictwa nieruchomosci");
        
	}
	
	/**
	 * Ukrywanie rzeczy od logowania i pokazywanie przyciskow, ktore pozwalaja wybrac
	 * odpowiednie opcje w systemie.
	 * Czyli pokazanie tych rzeczy, ktore wyswietlaja sie po poprawnym zalogowaniu do systemu.
	 */
	static void ustawWidocznoscPoZalogowaniu () {
		b1.setName("ASDF");
		b1.setVisible(false);
		label.setText("Zalogowano do systemu jako: " + login.getText());
		label2.setVisible(true);
		b2.setVisible(true);
		b3.setVisible(true);
		b4.setVisible(true);
		przyciskWyswietlDaneKlienta.setVisible(true);
		przyciskWyswietlDaneNieruchomosci.setVisible(true);
		b99.setVisible(false);
		labelLogin.setVisible(false);
        labelHaslo.setVisible(false);
        login.setVisible(false);
        haslo.setVisible(false);
        przyciskWyloguj.setVisible(true);
        
        labelPolaczenie.setText("Polaczono z serwerem o adresie: " + host);
        labelPolaczenie.setVisible(true);
	}
	
	/**
	 * Zalogowanie do systemu, listener przycisku 'Zaloguj'.
	 */
	static void listenerPrzyciskuZaloguj () {
        b1.addActionListener(new ActionListener() {
			@SuppressWarnings({ "deprecation", "static-access" })
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(b1.isEnabled()) {
					
					logowanie.login = login.getText();
					logowanie.haslo = haslo.getText();
					logowanie();
					
					if (login.getText().equals("pracownik") && haslo.getText().equals("haslo")) {
						ustawWidocznoscPoZalogowaniu();
						if (bazaDanych.polaczenie.length() != 0)
							label2.setText("Polaczono z baza danych: " + bazaDanych.polaczenie);
					}
					else {
						JOptionPane.showMessageDialog(null,"Nieprawidlowe dane logowania!");
					}
				}
			}
        	
        });
	}
	
	/**
	 * Wywolanie okna pozwalajacego na sprzedaz nieruchomosci
	 */
	static void listenerPrzyciskuSprzedazNieruchomosci () {
        b2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (b2.isEnabled()) {
        			Sprzedaz.main(null);
        		}
        	}
        });
	}
	
	/**
	 * Wywolanie okna pozwalajacego na dodanie nowego klienta do bazy
	 */
	static void listenerPrzyciskuDodajKlienta () {
		b4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(b4.isEnabled()) {
        			DodajKlienta.main(null);
        		}
        	}
        });
	}
	
	/**
	 * Wywolanie okna pozwalajacego na wyswietlenie szczegolowych danych klientow
	 */
	static void listenerPrzyciskuWyswietlDaneKlienta() {
		przyciskWyswietlDaneKlienta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (przyciskWyswietlDaneKlienta.isEnabled()) {
					Klient.main(null);
				}
			}
		});
	}
	
	/**
	 * Wywolanie okna pozwalajacego na wyswietlenie szcegolowych danych nieruchomosci
	 */
	static void listenerPrzyciskuWyswietlDaneNieruchomosci() {
		przyciskWyswietlDaneNieruchomosci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (przyciskWyswietlDaneNieruchomosci.isEnabled()) {
					Nieruchomosc.main(null);
				}
			}
		});
	}
	
	/**
	 * Wywolanie okna pozwalajacego na zakup nieruchomosci przez klienta
	 */
	static void listenerPrzyciskuKupnoNieruchomosci() {
        b3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (b2.isEnabled()) {
        			Kupno.main(null);
        		}
        	}
        });
	}
	

	/**
	 * Wylogowanie sie z systemu
	 */
	static void listenerPrzyciskuWyloguj() {
        przyciskWyloguj.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (przyciskWyloguj.isEnabled()) {
        			login.setText(null);
        			haslo.setText(null);
        			ustawRamke();
        			ustawPrzyciski();
        			
        			wiadomosc.koniec = true;
        			przesylka();
        			
        		}
        	}
        });
	}
	
	
	/**
	 * Metoda, ktora probuje sie zalogowac do serwera
	 * 
	 * @return ...
	 */
	static public int logowanie() {
		
		Thread t = new Thread(new Runnable() {
		public void run() {
			
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {// Przesyï¿½ka obiektï¿½w na porcie 54322
				// socket = new Socket(host, 54322);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			if (czyPolaczono != 0) {

				out.writeObject(logowanie);
				// out.flush();
			}


			logowanie = (Logowanie) in.readObject();

			// ========================
			// socket.close();

			if (logowanie.czyZalogowano == false) { // gdy logowanie nie powiodlo sie
				
				JOptionPane.showMessageDialog(null, "Serwer zwrocil blad: " + logowanie.wiadomoscZwrotna, "Blad", 0);
				socket.close();
				System.exit(1);
			}
			// }
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,
			 "Nie udalo sie polaczyc z serwerem", "SERWER", 0);
			// System.out.println("Nieudane polaczenie z serwerem - gracz " +
			// ktoryGracz);
			// e.printStackTrace();
			//if (!nieudanePolaczenie
			//		.equals("##########################################")) {


				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				//}
			}

		}
		}
		});
		
		t.start();
		
		return 0;
	}
	
	/**
	 * Cyklicznie wywolywana funkcja. Przesyla obiekty do serwera, a potem je
	 * odbiera.
	 * 
	 * @return ...
	 */
	public static int przesylka() {
		
		Thread t = new Thread(new Runnable() {
		public void run() {
		
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {// Przesyï¿½ka obiektï¿½w na porcie 54322
				// socket = new Socket(host, 54322);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Blad we/wy", "BIURO", JOptionPane.ERROR_MESSAGE);
		}
		try {
			if (czyPolaczono != 0) {
				out.writeObject(wiadomosc);
				// out.flush();
			}


			
			wiadomosc = (Wiadomosc) in.readObject();
			
			odebranoWiadomosc = true;
		

			// ========================
			// socket.close();

			if (wiadomosc.koniec == true) { 
				String info = new String();
				
				JOptionPane.showMessageDialog(null, "Koniec transmisji!\n" + info, "Biuro", JOptionPane.INFORMATION_MESSAGE);
				socket.close();
				//return 0;
			}
			// }
		} catch (ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(null,
			 "Nie udalo sie odebrac wiadomosc", "SERWER", 0);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problem z przesylka", "Biuro", JOptionPane.ERROR_MESSAGE);
		}
		}
		});
		
		t.start();
		
		return 0;
	}

	
	public static void polaczSieZSerwerem() {
		
		Thread t = new Thread(new Runnable() {
		public void run(){
		
		//host = getDocumentBase().getHost();
		host = "127.0.0.1";

		
		try {// Wyslanie na port 54321 informacji o przyï¿½aczeniu sie
			socket = new Socket(host, 54321);
			socket.getOutputStream().write(12345);
			czyPolaczono = (socket.getInputStream().read());// Pobranie nr gracza

			socket.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Nie udalo sie polaczyc z serwerem", "Blad", 0);
			e.printStackTrace();
		}

		try {
			socket = new Socket(host, 54322);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nieznany host!", "Blad", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// /////////////////////////////////
		
		}
		});
		t.start();
		
		
	}

	/**
	 * glowna metoda w programie, tak wlasciwie to tylko wywoluje inne metody
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() { public void run() {
            
        	ustawRamke();
            ustawPrzyciski();
                        
            listenerPrzyciskuZaloguj();
            listenerPrzyciskuDodajKlienta();
            listenerPrzyciskuSprzedazNieruchomosci();
            listenerPrzyciskuWyswietlDaneKlienta();
            listenerPrzyciskuWyswietlDaneNieruchomosci();
            listenerPrzyciskuKupnoNieruchomosci();
            listenerPrzyciskuWyloguj();
            
            polaczSieZSerwerem();

        } });

	}
	

}
