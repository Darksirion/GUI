package Core;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Team Turing on 09.09.15.
 */
public class Core {

	DirectoryClassLoader loader;
	Proxy proxy;

	public Core() {
		loader = new DirectoryClassLoader("./root");
		proxy = new Proxy(this);
		// shadow proxy

	}

	public void search() {

	}

	@SuppressWarnings("deprecation")
	public void addSnippet(String name, String code, String notizen, String quellen, String author, String parentPath) {
		Date datum = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		String formatString = dateFormat.format(datum);

		String sprache = proxy.getSprache();
		try {
			String newKey = parentPath + name;

			loader.saveSnippet(new Snippet(name, formatString, code, sprache, notizen, quellen, author, newKey));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteSnippet(String primaryKey) {
		loader.deleteSnippet(primaryKey);
	}

	public Snippet getSnippet(String primaryKey) throws IOException {
		return loader.getSnippet(primaryKey);
	}

	public void help() {
		// get help;
	}

    public FileNode loadTree(String root) {
        return loader.getTree();
    }

	public void settings() {

		// new Lang
		// HotKey Change

	}

	public void addDirectory(String name) {
		loader.addDirectories(name);
	}

	public void renameDirectory(String oldName, String newName) {
		loader.renameFile(oldName, newName);
		;
	}

	public void deleteDirectory(String primaryKey) {
		loader.deleteSnippet(primaryKey);
	}

	public void exit() {
		int[] settings = proxy.getSettings();
	}

    public Proxy getProxy() {
        return proxy;
    }

    //public static void main(String[] args) {
	//	new Core();
//	}
}
