package Database;

import de.htw.saarland.stl.Stdin;

public class DBDialog {
	
	private static final String QUIT = "quit";
	private static final String CREATE_TABLE = "createtable";
	private static final String CREATE_TABLE_DIREC = "create ordner table";
	private static final String INSERT_DIREC = "insert ordner";
	private static final String INSERT = "insert";
	private static final String PRINT = "print";
	private static final String PRINT_DIREC = "printDir";
	private static final String DELETE = "delete";
	private static final String DELETE_DIREC = "rmdir";
	private String function;
	
	private DBController dbc;
	
	public DBDialog() {
		dbc = DBController.getInstance();
		dbc.initDBConnection();
	}
	
	private void start() {
		function = " ";
		while(!function.equals(QUIT)) {
			function = readCmd();
			executeCmd(function);
		}
		dbc.closeDBConnection();
	}
	
	private String readCmd() {
		System.out.println(CREATE_TABLE + " = create new Table\n"+
							CREATE_TABLE_DIREC + " = create Directory Table\n"+
							INSERT + " = insert new Snippet\n"+
							INSERT_DIREC + "= insert Directory\n"+
							PRINT + " = print database\n"+
							PRINT_DIREC + " = print directories\n"+
							DELETE + " = delete Snippet\n"+
							DELETE_DIREC + " = delete directory\n"+
							QUIT + " = quit\n -> ");
		return Stdin.readlnString();
	}
	
	private void executeCmd(String cmd) {
		
		if(cmd.equals(INSERT)) {
			insertSnippet();
			System.out.println("Snippet wurde hinzugefuegt");
		}
		else if(cmd.equals(CREATE_TABLE)) {
			String table = Stdin.readlnString("Tabellenname: ");
			dbc.createSnippetTable(table);
		}
		else if(cmd.equals(DELETE)) {
			String key = Stdin.readlnString("Key eingeben: ");
			dbc.deleteSnippet(key);
			System.out.println("Snippet wurde geloescht.\n");
		}
		else if(cmd.equals(PRINT)) {
			dbc.showDatabase();
		}
		else if(cmd.equals(QUIT)) {
			System.out.println("Programm wurde beendet.");
		}
		else if(cmd.equals(CREATE_TABLE_DIREC)) {
			String table =Stdin.readlnString("name der Tabelle: ");
			dbc.createDirectoryTable(table);
		}
		else if(cmd.equals(INSERT_DIREC)) {
			String key = Stdin.readlnString("key des Ordners: ");
			String name = Stdin.readlnString("name des Ordners: ");
			String parent = Stdin.readlnString("parent: ");
			dbc.insertDirectory(key, name, parent);
			System.out.println("Ordner "+name+" wurde hinzugefuegt\n");
		}
		else if(cmd.equals(DELETE_DIREC)) {
			String key = Stdin.readlnString("key des Ordners: ");
			dbc.deleteDirectory(key);
		}
		else if(cmd.equals(PRINT_DIREC)){
			dbc.showDirectories();
		}
	}
	
	
	private void insertSnippet() {
		String ordner = Stdin.readlnString("\n\nKey des Ordners: ");
		String primaryKey = Stdin.readlnString("Key eingeben: ");
		String name = Stdin.readlnString("Name eingeben: ");
		String datum = Stdin.readlnString("Datum eingeben: ");
		String code = Stdin.readlnString("Code eingeben: ");
		String sprache = Stdin.readlnString("Sprache eingeben: ");
		String notizen = Stdin.readlnString("Notizen eingeben: ");
		String quellen = Stdin.readlnString("Quellen eingeben: ");
		String author = Stdin.readlnString("Author eingeben: ");
		Snippet snip = new Snippet(name, datum, code, sprache, notizen, quellen, author, primaryKey);
		dbc.insertSnippet(snip,ordner);
		System.out.println("Snippet "+name+ "wurde hinzugefuegt.\n\n");
	}
	
	public static void main(String[] args) {
		new DBDialog().start();
	}

}
