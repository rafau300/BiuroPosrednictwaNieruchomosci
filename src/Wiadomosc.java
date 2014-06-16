import java.io.Serializable;


public class Wiadomosc implements Serializable, Zmienne{
	
	private static final long serialVersionUID = 1L;
	int idKlienta;
	int idNieruchomosci;
	boolean koniec;
	String skryptSQL = new String();
	int idCzynnosciDoWykonania;
	String[] dane = new String[LICZBA_REKORDOW];
	String[] kolumny = new String[LICZBA_REKORDOW];
	
	String[] imiona = new String[LICZBA_REKORDOW];
	String[] nazwiska = new String[LICZBA_REKORDOW];
	
	String[] miejscowosc = new String[LICZBA_REKORDOW];
	String[] ulica = new String[LICZBA_REKORDOW];
	int[] cena = new int[LICZBA_REKORDOW];

	
	public Wiadomosc() {
		this.koniec = false;
		this.skryptSQL = "";
	}	

}
