package Core;

import java.io.IOException;

public interface ClassLoader {

    /**
     * Gibt die gewaehlte Datenbank in Baumstruktur zurueck
     *
     * @return Eine FileNode aus der die Datenbank-Struktur herausgelesen werden kann
     */
    public FileNode getTree();

    /**
     * Gibt einen Snippet aus der Datenbank anhand seines Primaer-Schluessels zureuck
     *
     * @return Die gewuenschte File aus der Datenbank
     * @throws IOException
     */
    public Snippet getSnippet(String primaryKey) throws IOException;

    /**
     * Speichert einen Snippet in der Datenbank
     *
     * @param file Die Datei die in der Datenbank gespeichert werden soll
     * @throws IOException
     */
    public void saveSnippet(Snippet snippet) throws IOException;
}
