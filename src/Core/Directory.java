package Core;

/**
 * Created by Darksirion on 02.10.15.
 */
public class Directory {
    private int directoryID;
    private String directoryName;
    private String parent;

    public Directory(int directoryID, String directoryName, String parent) {
        this.directoryID = directoryID;
        this.directoryName = directoryName;
        this.parent = parent;
    }

    public Directory() {

    }

    public int getDirectoryID() {
        return directoryID;
    }

    public void setDirectoryID(int directoryID) {
        this.directoryID = directoryID;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
