package Codehaufen;

/*
 * stellt Verbindung zur Datenbank her.
 * erlaubt das Einfuegen und Loeschen von Daten aus der Datenbank.
 * @author Dominik Becker
 * 
 */
public class DBController {

//	private static final String DB_PATH = "snippet.db";
//
//	//private DBController() {}
//
//	private static final Codehaufen.DBController dbcontroller = new Codehaufen.DBController();
//	private static Connection connection;
//	protected Connection con = null;
//
//	protected void connect() {
//		try {
//			Class.forName("org.sqlite.JDBC");
//			con = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
//		} catch (ClassNotFoundException e) {
//			System.err.println("Fehler beim Laden des JDBC-Treibers");
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	//Controller wird als Singleton implementiert.
//	public static Codehaufen.DBController getInstance() {
//		return dbcontroller;
//	}
//
//	/**
//	 * stellt Verbindung zur Datenbank her.
//	 */
//	public void initDBConnection() {
//		try {
//			if(connection != null)
//				return;
//			System.out.println("Creating Connection to Database...");
//			connection = DriverManager.getConnection("jdbc:sqlite:"+DB_PATH);
//			if(connection.isClosed())
//				System.out.println("...Connection established");
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			public void run() {
//				try {
//					if(!connection.isClosed() && connection != null) {
//						connection.close();
//						if(connection.isClosed())
//							System.out.println("Connection to Database closed");
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * erstellt eine Tabelle, in die Snippets eingefuegt werden koennen.
//	 * @param name
//	 */
//	public void createSnippetTable(String name) {
//		try {
//			Statement stmt = connection.createStatement();
//
//			stmt.executeUpdate(
//					"CREATE TABLE " + name + " (snippetID INTEGER PRIMARY KEY AUTOINCREMENT,directoryID INTEGER(15), snippetName varchar(10), datum varchar(30), code varchar(5000), sprache varchar(20),"
//							+ "notizen varchar(200), quellen varchar(100), author varchar(50));");
//			stmt.executeUpdate("CREATE INDEX directoryID ON " + name + "(directoryID)");
//		} catch (SQLException e) {
//			System.err.println("Tabelle konnte nicht erstellt werden");
//			e.printStackTrace();
//		}
//	}
//
//	public void createDirectoryTable(String name) {
//		try {
//			Statement stmt = connection.createStatement();
//			stmt.executeUpdate(
//					"CREATE TABLE " + name + "(directoryID INTEGER(15) PRIMARY KEY , directoryName varchar(30), parent varchar(50));");
//		} catch (SQLException e) {
//			System.err.println("Tabelle konnte nicht erstellt werden");
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * laed Snippet aus der Datenbank.
//	 * @param directoryID
//	 * @return Snippet
//	 */
//	public Snippet loadSnippet(int directoryID) {
//		Statement stmt;
//		try {
//			stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery
//					("SELECT * FROM snippets WHERE snippetID = " + directoryID + ";");
//			int snippetID = Integer.parseInt(rs.getString("snippetID"));
//			directoryID = Integer.parseInt(rs.getString("directoryID"));
//			String snippetName = rs.getString("snippetName");
//				String datum = rs.getString("datum");
//				String code = rs.getString("code");
//				String sprache = rs.getString("sprache");
//				String notizen = rs.getString("notizen");
//				String quellen = rs.getString("quellen");
//				String author = rs.getString("author");
//			rs.close();
//			return new Snippet(snippetID, directoryID, snippetName, datum, code, sprache, notizen, quellen, author);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	/**
//	 * loescht Snippet aus der Datenbank.
//	 * @param directoryID
//	 */
//	public void deleteSnippet(String directoryID) {
//
//		PreparedStatement pStmt;
//		try {
//			pStmt = connection.prepareStatement("DELETE FROM snippets WHERE snippetID = ?");
//			pStmt.setString(1, directoryID);
//			pStmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * loescht einen Directory und die darin enthaltenen
//	 * Directory und Snippets.
//	 * @param directoryID
//	 */
//	public void deleteDirectory(String directoryID) {
//		PreparedStatement dirStmt;
//		PreparedStatement snipStmt;
//		PreparedStatement pStmt;
//		try {
//			snipStmt = connection.prepareStatement("SELECT * FROM snippets WHERE directoryID = ?");
//			snipStmt.setString(1, directoryID);
//			ResultSet rs = snipStmt.executeQuery();
//			while(rs.next()) {
//				deleteSnippet(rs.getString("snippetID"));
//			}
//			pStmt = connection.prepareStatement("SELECT * FROM directories WHERE parent = ?");
//			pStmt.setString(1, directoryID);
//			ResultSet set = pStmt.executeQuery();
//			while(set.next()) {
//				deleteDirectory(rs.getString("directoryID"));
//			}
//			dirStmt = connection.prepareStatement("DELETE FROM directories WHERE directoryID = ?");
//			dirStmt.setString(1, directoryID);
//			dirStmt.executeUpdate();
//		} catch (SQLException e) {
//			System.err.println("Directory konnte nicht geloescht werden.");
//			e.printStackTrace();
//		}
//	}
//
//	public void renameDirectory(String directoryID, String newName) {
//		PreparedStatement pStmt;
//		try {
//			pStmt = connection.prepareStatement(
//					"UPDATE directories SET name = ? WHERE directoryID = ?");
//			pStmt.setString(1, newName);
//			pStmt.setString(2, directoryID);
//			pStmt.executeUpdate();
//		} catch (SQLException e) {
//			System.err.println("Directory konnte nicht umbenannt werden.");
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * fuegt Snippet in die Datenbank ein.
//	 */
//	public void insertSnippet(Snippet snippet, int directoryID) {
//		try {
//			PreparedStatement ps = connection.prepareStatement
//					("INSERT INTO snippets VALUES(?,?,?,?,?,?,?,?,?);");
//			ps.setInt(1, snippet.getSnippetID());
//			ps.setInt(2, snippet.getDirectoryID());
//			ps.setString(3, snippet.getSnippetName());
//			ps.setString(4, snippet.getDatum());
//			ps.setString(5, snippet.getCode());
//			ps.setString(6, snippet.getSprache());
//			ps.setString(7, snippet.getNotizen());
//			ps.setString(8, snippet.getQuellen());
//			ps.setString(9, snippet.getAuthor());
//			ps.addBatch();
//			connection.setAutoCommit(false);
//			ps.executeBatch();
//			connection.setAutoCommit(true);
//		} catch (SQLException e) {
//			System.err.println("Insert failed");
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * fuegt einen Directory ein
//	 * @param directoryID
//	 * @param directoryName
//	 * @param parent
//	 */
//	public void insertDirectory(String directoryID, String directoryName, String parent) {
//		try {
//			PreparedStatement ps = connection.prepareStatement
//					("INSERT INTO directories VALUES(?,?,?);");
//			ps.setString(1, directoryID);
//			ps.setString(2, directoryName);
//			ps.setString(3, parent);
//			ps.addBatch();
//			connection.setAutoCommit(false);
//			ps.executeBatch();
//			connection.setAutoCommit(true);
//		} catch (SQLException e) {
//			System.err.println("insert failed");
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * gibt die Keys der Kinder des Ordners zurueck.
//	 * @param directoryID
//	 * @return children
//	 */
//	public Collection<String> getChildren(String directoryID) {
//			PreparedStatement pStmt;
//			Collection<String> children = new ArrayList<String>();
//			try {
//				pStmt = connection.prepareStatement(
//						"SELECT * FROM directories WHERE parent = ?");
//				pStmt.setString(1, directoryID);
//				ResultSet rs = pStmt.executeQuery();
//				while(rs.next()) {
//					children.add(rs.getString("directoryID"));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return children;
//	}
//
//	/**
//	 * gibt den Namen eines Ordners zurueck.
//	 * @param directoryID
//	 * @return
//	 */
//	public String getDirectoryName(String directoryID) {
//		PreparedStatement pStmt;
//
//		try {
//			pStmt = connection.prepareStatement(
//					"SELECT * FROM directories WHERE directoryID = ?");
//			pStmt.setString(1, directoryID);
//			ResultSet rs = pStmt.executeQuery();
//			return rs.getString("name");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public void showDatabase() {
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM snippets;");
//			while(rs.next()) {
//				System.out.println("SnippetID = " + rs.getString("snippetID"));
//				System.out.println("SnippetName = " + rs.getString("snippetName"));
//				System.out.println("Datum = "+rs.getString("datum"));
//				System.out.println("Code = "+rs.getString("code"));
//				System.out.println("Sprache = "+rs.getString("sprache"));
//				System.out.println("Notizen = "+rs.getString("notizen"));
//				System.out.println("Quellen = "+rs.getString("quellen"));
//				System.out.println("Author = "+rs.getString("author"));
//				System.out.println("Directory = " + rs.getString("directoryID"));
//				System.out.println("\n\n");
//			}
//			rs.close();
//		} catch (SQLException e) {
//			System.err.println("Tabelle konnte nicht geladen werden.");
//			e.printStackTrace();
//		}
//	}
//
//	public void showDirectories() {
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM directories;");
//			while(rs.next()) {
//				System.out.println("directoryID= " + rs.getString("directoryID"));
//				System.out.println("directoryName= " + rs.getString("directoryName"));
//				System.out.println("parent= "+rs.getString("parent"));
//				System.out.println("\n");
//			}
//			rs.close();
//		} catch (SQLException e) {
//			System.err.println("Tabelle konnte nicht geladen werden");
//			e.printStackTrace();
//		}
//	}
//
//
//	public static ResultSet select(String sql) {
//		// Connection mit SQL Datenbank herstellen
//
//		Statement statement;
//		try {
//			statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			// resultSet.close();
//			return resultSet;
//		} catch (SQLException e) {
//			System.out.println("Select fehlgeschlagen!");
//			e.printStackTrace();
//			return null;
//		} finally {
//			// System.out.println("Select erfolgreich! ");
//			/* try { //connection.close(); } catch (SQLException e) {
//			 * e.printStackTrace(); } */
//		}
//	}
//
//
//	public static void closeDBConnection() {
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
