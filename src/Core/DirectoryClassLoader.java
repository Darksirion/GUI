package Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DirectoryClassLoader implements ClassLoader {
	// Attribute
	private String path;

	private static final String filePlaceholder = "==========";

	/**
	 * Konstruktor mit Pfadangabe
	 * 
	 * @param path Der Pfad der Datenbank
	 */
	public DirectoryClassLoader(String path) {
		this.path = path;
	}

	/**
	 * Gibt die Baumstruktur der Datenbank zurueck
	 */
	public FileNode getTree() {
		return new FileNode(path);
	}
	
	/**
	 * Legt einen Ordner samt Ueberordner an
	 * 
	 * @param path Der Pfad des Ordners der erstellt werden soll
	 */
	public boolean addDirectories(String path){
		File file = new File(path);
		return file.mkdirs();
	}
	
	/**
	 * Benennt eine File um
	 * 
	 * @param oldName Der alte Name der File
	 * @param newName Der neue Name der File
	 * @return 
	 */
	public boolean renameFile(String oldName, String newName){
		File file = new File(oldName);
		return file.renameTo(new File(newName));
	}

	/**
	 * Gibt einen Snippet anhand seines Primaerschluessels zurueck
	 * 
	 * @param primaryKey Der Primaerschluessel des Snippets
	 * @throws IOException 
	 */
	public Snippet getSnippet(String primaryKey) throws IOException {
		// Variablen werden geladen und initialisiert
		Snippet snip = new Snippet();
		File file = new File(primaryKey);
		String segment;
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);

		// Name
		snip.setName(bufferedReader.readLine());

		// Author
		snip.setAuthor(bufferedReader.readLine());

		// Datum
		snip.setDatum(bufferedReader.readLine());

		// Sprache
		snip.setSprache(bufferedReader.readLine());

		// Code-Segment
		segment = bufferedReader.readLine();
		//segment = readSegment(bufferedReader, filePlaceholder);
		snip.setCode(segment);

		// Notizen-Segment
		segment = bufferedReader.readLine();
		//segment = readSegment(bufferedReader, filePlaceholder);
		snip.setNotizen(segment);

		// Quellen-Segment
		segment = bufferedReader.readLine();
		//segment = readSegment(bufferedReader, filePlaceholder);
		snip.setQuellen(segment);

		bufferedReader.close();
		return snip;
	}

	/**
	 * Speichert einen Snippet ab
	 * 
	 * @param snip Der Snippet der gespeichert werden soll
	 * @throws IOException
	 */
	public void saveSnippet(Snippet snip) throws IOException {
		// Variablen werden geladen und initialisiert
		File file = new File(snip.getPrimaryKey());
		FileWriter writer = new FileWriter(file);

		// Name
		writer.write(snip.getName() + "\n");

		// Author
		writer.append(snip.getAuthor() + "\n");

		// Datum
		writer.append(snip.getDatum() + "\n");

		// Sprache
		writer.append(snip.getSprache() + "\n");

		// Code-Segment
		writer.append(snip.getCode() + "\n");

		// Notizen
		writer.append(snip.getNotizen() + "\n");

		// Quellen
		writer.append(snip.getQuellen() + "\n");

		writer.close();
	}

	/**
	 * Loescht einen Snippet aus der Datenbank
	 * 
	 * @param primaryKey Der Primaerschluessels dessen Snippet geloescht werden soll
	 */
	public void deleteSnippet(String primaryKey) {
		File file = new File(primaryKey);

		if (file.isFile()) {
			// Ist es eine normale File, wird diese geloescht
			file.delete();
		} else {
			// Ist es ein Ordner, muss geschaut werden ob er Inhalt hat
			File[] inhalt = file.listFiles();

			if (inhalt.length == 0) {
				// Gibt es keinen Inhalt, wird der Ordner geloescht
				file.delete();
			} else {
				// Ansonsten muss erst der inhalt geloescht werden
				for (int i = 0; i < inhalt.length; i++) 
					deleteSnippet(inhalt[i].getAbsolutePath());
				
				// Und am Ende wird der Ordner selbst geloescht
				file.delete();
			}
		}
	}

	/**
	 * Liest einen Block an Zeilen aus einer Datei und gibt ihn als String zurueck
	 * 
	 * @param bufferedReader Ein BufferedReader der mit der File verbunden ist die gelesen werden soll
	 * @param placeholder Ein Platzhalter der bestimmt dass das Segment zu Ende ist
	 * @return Ein String der das Segment enthaelt
	 * @throws IOException
	 */
	private String readSegment(BufferedReader bufferedReader, String placeholder) throws IOException {
		String segment = "";
		String line = bufferedReader.readLine();

		while (!line.matches(placeholder)) {
			segment += line;
			line = bufferedReader.readLine();
		}

		return segment;
	}

}
