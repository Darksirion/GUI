package Core;

import java.io.File;

public class FileNode {
    // Attribute
    private FileNode[] children;
    private boolean isFile;
    private String primaryKey;
    private String name;

    /**
     * Konstruktor der dazu fuehrt dass sich die Baumstruktur automatisch erstellt
     *
     * @param file Die File die dieser Node zugeordnet ist
     */
    public FileNode(String root) {
        this.primaryKey = root;
        File file = new File(root);
        this.name = file.getName();

        // Es wird geschaut ob diese Node mit einem Directory oder einer File verbunden ist
        if (file.isDirectory()) {
            isFile = false;

            // Liste aller Files in diesem Directory wird entnommen
            File[] lowerFiles = file.listFiles();
            children = new FileNode[lowerFiles.length];

            // Aus jeder File in diesem Directory wird eine neue Node erstellt
            for (int i = 0; i < children.length; i++)
                children[i] = new FileNode(lowerFiles[i].getAbsolutePath());
        } else {
            isFile = true;
        }
    }

    /**
     * Gibt eine Child-Node anhand seines Index zurueck
     *
     * @param index Der Index der die Node bestimmt
     * @return Die Node die verlangt wurde
     */
    public FileNode getChild(int index) {
        return children[index];
    }

    /**
     * Gibt den primaryKey des Snippets zurueck die mit dieser Node verbunden ist
     *
     * @return der primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Gibt den Namen des Snippets zurueck die mit dieser Node verbunden ist
     *
     * @return Der Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt an wie viele Child-Nodes mit dieser Node verbunden sind
     *
     * @return Die Anzahl der Child-Nodes
     */
    public int childrenSize() {
        return children.length;
    }

    /**
     * Gibt an ob diese Node mit einer File oder einem Directory verbunden ist
     *
     * @return false, wenn es ein Directory ist, sonst true
     */
    public boolean isFile() {
        return isFile;
    }
}
