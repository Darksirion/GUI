package Database;

import Core.Snippet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/*
 * stellt Verbindung zur Datenbank her.
 * erlaubt das Einfuegen und Loeschen von Daten aus der Datenbank.
 * @author Dominik Becker
 * 
 */
public class DBController {

	private static final String DB_PATH = "snippet.db";
	
	private DBController() {}
	
	private static final DBController dbcontroller = new DBController();
	private static Connection connection;

	private static void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}
	
	//Controller wird als Singleton implementiert.
	public static DBController getInstance() {
		return dbcontroller;
	}
	
	/**
	 * stellt Verbindung zur Datenbank her.
	 */
	public void initDBConnection() {
		try {
			if(connection != null)
				return;
			System.out.println("Creating Connection to Database...");
			connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
			if(connection.isClosed())
				System.out.println("...Connection established");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if(!connection.isClosed() && connection != null) {
						connection.close();
						if(connection.isClosed())
							System.out.println("Connection to Database closed");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * erstellt eine Tabelle, in die Snippets eingefuegt werden koennen.
	 * @param name
	 */
	public void createSnippetTable(String name) {
		try {
			Statement stmt = connection.createStatement();

			stmt.executeUpdate(
					"CREATE TABLE "+name+" (primaryKey varchar(50), name varchar(10), datum varchar(30), code varchar(5000), sprache varchar(20),"
							+ "notizen varchar(200), quellen varchar(100), author varchar(50), ordner varchar(50));");
		} catch (SQLException e) {
			System.err.println("Tabelle konnte nicht erstellt werden");
			e.printStackTrace();
		}
	}
	
	public void createDirectoryTable(String name) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE "+name+"(key varchar(50), name varchar(30), parent varchar(50));");
		} catch (SQLException e) {
			System.err.println("Tabelle konnte nicht erstellt werden");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * laed Snippet aus der Datenbank.
	 * @param key
	 * @return Snippet
	 */
	public Snippet loadSnippet(String key) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery
					("SELECT * FROM snippets WHERE primaryKey = "+key+";");
				String primaryKey = rs.getString("primaryKey");
				String name = rs.getString("name");
				String datum = rs.getString("datum");
				String code = rs.getString("code");
				String sprache = rs.getString("sprache");
				String notizen = rs.getString("notizen");
				String quellen = rs.getString("quellen");
				String author = rs.getString("author");
			rs.close();
			return new Snippet(name, datum, code, sprache, notizen, quellen, author, primaryKey);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * loescht Snippet aus der Datenbank.
	 * @param key
	 * @param table
	 */
	public void deleteSnippet(String key) {
		
		PreparedStatement pStmt;
		try {
			pStmt = connection.prepareStatement("DELETE FROM snippets WHERE primaryKey = ?");
			pStmt.setString(1, key);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * loescht einen Ordner und die darin enthaltenen
	 * Ordner und Snippets.
	 * @param key
	 */
	public void deleteDirectory(String key) {
		PreparedStatement dirStmt;
		PreparedStatement snipStmt;
		PreparedStatement pStmt;
		try {
			snipStmt = connection.prepareStatement("SELECT * FROM snippets WHERE ordner = ?");
			snipStmt.setString(1, key);
			ResultSet rs = snipStmt.executeQuery();
			while(rs.next()) {
				deleteSnippet(rs.getString("primaryKey"));
			}
			pStmt = connection.prepareStatement("SELECT * FROM directories WHERE parent = ?");
			pStmt.setString(1, key);
			ResultSet set = pStmt.executeQuery();
			while(set.next()) {
				deleteDirectory(rs.getString("key"));
			}
			dirStmt = connection.prepareStatement("DELETE FROM directories WHERE key = ?");
			dirStmt.setString(1, key);
			dirStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Ordner konnte nicht geloescht werden.");
			e.printStackTrace();
		}
	}
	
	public void renameDirectory(String key, String newName) {
		PreparedStatement pStmt;
		try {
			pStmt = connection.prepareStatement(
					"UPDATE directories SET name = ? WHERE key = ?");
			pStmt.setString(1, newName);
			pStmt.setString(2, key);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Ordner konnte nicht umbenannt werden.");
			e.printStackTrace();
		}
	}
	
	/**
	 * fuegt Snippet in die Datenbank ein.
	 */
	public void insertSnippet(Snippet snippet, String ordner) {
		try {
			PreparedStatement ps = connection.prepareStatement
					("INSERT INTO snippets VALUES(?,?,?,?,?,?,?,?,?);");
			ps.setString(1, snippet.getPrimaryKey());
			ps.setString(2, snippet.getName());
			ps.setString(3, snippet.getDatum());
			ps.setString(4, snippet.getCode());
			ps.setString(5, snippet.getSprache());
			ps.setString(6, snippet.getNotizen());
			ps.setString(7, snippet.getQuellen());
			ps.setString(8, snippet.getAuthor());
			ps.setString(9, ordner);
			ps.addBatch();
			connection.setAutoCommit(false);
			ps.executeBatch();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			System.err.println("Insert failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * fuegt einen Ordner ein
	 * @param key
	 * @param name
	 * @param parent
	 */
	public void insertDirectory(String key, String name, String parent) {
		try {
			PreparedStatement ps = connection.prepareStatement
					("INSERT INTO directories VALUES(?,?,?);");
			ps.setString(1, key);
			ps.setString(2, name);
			ps.setString(3, parent);
			ps.addBatch();
			connection.setAutoCommit(false);
			ps.executeBatch();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			System.err.println("insert failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * gibt die Keys der Kinder des Ordners zurueck.
	 * @param key
	 * @return children
	 */
	public Collection<String> getChildren(String key) {
			PreparedStatement pStmt;
			Collection<String> children = new ArrayList<String>();
			try {
				pStmt = connection.prepareStatement(
						"SELECT * FROM directories WHERE parent = ?");
				pStmt.setString(1, key);
				ResultSet rs = pStmt.executeQuery();
				while(rs.next()) {
					children.add(rs.getString("key"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return children;
	}
	
	/**
	 * gibt den Namen eines Ordners zurueck.
	 * @param key
	 * @return
	 */
	public String getDirectoryName(String key) {
		PreparedStatement pStmt;
		
		try {
			pStmt = connection.prepareStatement(
					"SELECT * FROM directories WHERE key = ?");
			pStmt.setString(1, key);
			ResultSet rs = pStmt.executeQuery();
			return rs.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void showDatabase() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM snippets;");
			while(rs.next()) {
				System.out.println("PrimaryKey = "+rs.getString("primaryKey"));
				System.out.println("Name = "+rs.getString("name"));
				System.out.println("Datum = "+rs.getString("datum"));
				System.out.println("Code = "+rs.getString("code"));
				System.out.println("Sprache = "+rs.getString("sprache"));
				System.out.println("Notizen = "+rs.getString("notizen"));
				System.out.println("Quellen = "+rs.getString("quellen"));
				System.out.println("Author = "+rs.getString("author"));
				System.out.println("Ordner = "+rs.getString("ordner"));
				System.out.println("\n\n");
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("Tabelle konnte nicht geladen werden.");
			e.printStackTrace();
		}
	}
	
	public void showDirectories() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM directories;");
			while(rs.next()) {
				System.out.println("key= "+rs.getString("key"));
				System.out.println("name= "+rs.getString("name"));
				System.out.println("parent= "+rs.getString("parent"));
				System.out.println("\n");
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("Tabelle konnte nicht geladen werden");
			e.printStackTrace();
		}
	}


	public static ResultSet select(String sql) {
		// Connection mit SQL Datenbank herstellen
		connect();
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			// resultSet.close();
			return resultSet;
		} catch (SQLException e) {
			System.out.println("Select fehlgeschlagen!");
			e.printStackTrace();
			return null;
		} finally {
			// System.out.println("Select erfolgreich! ");
			/* try { //connection.close(); } catch (SQLException e) {
			 * e.printStackTrace(); } */
		}
	}


	public static void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
