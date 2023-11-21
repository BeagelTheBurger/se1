package org.hbrs.se1.ws23.uebung4.prototype;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2023, Version 1.0
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Was ist ihre Strategie zur Wiederverwendung? (F1)
 *
 * Alternative 1:
 * Klasse UserStory implementiert Interface Member (UserStory implements Member)
 * Vorteil: Wiederverwendung von Member, ID verwenden; Strenge Implementierung gegen Interface
 * Nachteil: Viele Casts notwendig, um auf die anderen Methoden zuzugreifen
 *
 * Alternative 2:
 * Container mit Generic entwickeln (z.B. Container<E>))
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities) sind in einer Klasse, d.h. Container,
 * diese liegt in einem Package.
 * ToDo: Wie bewerten Sie diese Entscheidung? (F2, F6)
 * 
 */

public class Container {

	private List<UserStory> liste = null;

	private static Container instance = null;

	private PersistenceStrategy strategy = null;

	private boolean connectionOpen = false;

	public static synchronized Container getInstance() {
		if (instance == null) {
			instance = new Container();
			System.out.println("Objekt vom Typ Container wurde instanziiert!");
		}
		return instance;
	}

	private Container() {
		System.out.println("Container ist instanziiert (Konstruktor)!");
		this.liste = new ArrayList<UserStory>();
	}

	public List getCurrentList() {
		return  this.liste;
	}

	public void addUserStorie (UserStory r) {
		if(contains(r)) {
			System.out.println("Duplikat: " + r.toString());
		}
		liste.add(r);
	}

	boolean contains(UserStory r) {
		Integer ID = r.getID();
		for ( UserStory rec : liste) {
			if (rec.getID() == ID) {
				return true;
			}
		}
		return false;
	}
	public int size() {
		return liste.size();
	}

	public void setPersistenceStrategie(PersistenceStrategy persistenceStrategy) {
		if (connectionOpen == true) {
			try {
				this.closeConnection();
			} catch (PersistenceException e) {
				// ToDo here: delegate to client (next year maybe ;-))
				e.printStackTrace();
			}
		}
		this.strategy = persistenceStrategy;
	}
	private void openConnection() throws PersistenceException {
		try {
			this.strategy.openConnection();
			connectionOpen = true;
		} catch( UnsupportedOperationException e ) {
			throw new PersistenceException(
					PersistenceException.ExceptionType.ImplementationNotAvailable ,
					"Not implemented!" );
		}
	}

	private void closeConnection() throws PersistenceException {
		try {
			this.strategy.closeConnection();
			connectionOpen = false;
		} catch( UnsupportedOperationException e ) {
			throw new PersistenceException( PersistenceException.ExceptionType.ImplementationNotAvailable , "Not implemented!" );
		}
	}

	public void store() throws PersistenceException {
		if (this.strategy == null)
			throw new PersistenceException( PersistenceException.
					ExceptionType.NoStrategyIsSet,
					"Strategy not initialized");

		if (connectionOpen == false) {
			this.openConnection();
			connectionOpen = true;
		}
		this.strategy.save( this.liste  );
	}

	public void load() throws PersistenceException {
		if (this.strategy == null)
			throw new PersistenceException( PersistenceException.ExceptionType.NoStrategyIsSet,
					"Strategy not initialized");

		if (connectionOpen == false) {
			this.openConnection();
			connectionOpen = true;
		}
		List<UserStory> liste = this.strategy.load();
		this.liste = liste; // MayBe merge
	}
}

// F1: mehr Speicherbedarf, da das Objekt schon vorher erstellt wird.
// Methoden auslagern, anders strukturieren, hier ist alles zusammen(main, methoden, dies und das )
//Fehlermeldung von store wird nicht in store,startEingabe oder main behandelt