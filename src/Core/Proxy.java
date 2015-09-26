package Core;

import UI.Controller;

import java.io.IOException;

public class Proxy {


	private Core core;
	private Controller controller;
	
	public Proxy(Core core){
		this.core = core;

	}
	
	//=============================
	//=GUI
	//=============================
	
	public void addSnippet(String name, String code, String notizen, String quellen, String author, String parentPath) {
		core.addSnippet(name, code, notizen, quellen, author, parentPath);
	}
	
	public void deleteSnippet(String primaryKey) {
		core.deleteSnippet(primaryKey);
	}
	
	public Snippet getSnippet(String primaryKey) throws IOException {
		 Snippet test =core.getSnippet(primaryKey);

		return core.getSnippet(primaryKey);
	}
	
	public void addDirectory(String name) {
		core.addDirectory(name);
	}
	
	public void renameDirectory(String oldName, String newName) {
		core.renameDirectory(oldName, newName);
	}
	
	public void deleteDirectory(String primaryKey) {
		core.deleteDirectory(primaryKey);
	}

	public FileNode loadTree(String root) {
		return core.loadTree(root);
	}
	public void exit() {
		core.exit();
	}

	public void setting(){
		core.settings();
	}


	
	//============================
	//= Core
	//============================
	

	public String getSprache() {
		//TODO von GUI abrufen

		String lang = controller.getLang();
		return lang;
	}
	

	public int[] getSettings() {
		//TODO ruft die aktuell eingestellten Settings der GUI ab
		int [] setting = controller.getSetting();
		return setting;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}
}
