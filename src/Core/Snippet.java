package Core;

/**
 * Attribut-Klasse
 *
 * @author Sven Manier
 */
public class Snippet {
    private String name;
    private String datum;
    private String code;
    private String sprache;
    private String notizen;
    private String quellen;
    private String author;
    private String primaryKey;

    /**
     * Konstruktor mit Uebergabe von Attributen
     *
     * @param name
     * @param datum
     * @param code
     * @param sprache
     * @param notizen
     * @param quellen
     * @param author
     * @param primaryKey
     */
    public Snippet(String name, String datum, String code, String sprache, String notizen, String quellen, String author, String primaryKey) {
        this.name = name;
        this.datum = datum;
        this.code = code;
        this.sprache = sprache;
        this.notizen = notizen;
        this.quellen = quellen;
        this.author = author;
        this.primaryKey = primaryKey;
    }

    /**
     * Leerer Konstruktor
     */
    public Snippet() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public String getQuellen() {
        return quellen;
    }

    public void setQuellen(String quellen) {
        this.quellen = quellen;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

}
