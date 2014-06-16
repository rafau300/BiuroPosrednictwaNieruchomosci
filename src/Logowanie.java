import java.io.Serializable;


/**
 * Ta klasa sa przesylane dane logowania
 * @author Rafal
 *
 */
public class Logowanie implements Serializable{

	private static final long serialVersionUID = 1L;
	String login;
	String haslo;
	boolean czyZalogowano;
	String wiadomoscZwrotna;
	
	Logowanie() {
		
	}

}
