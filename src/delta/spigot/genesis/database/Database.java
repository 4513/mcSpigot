package delta.spigot.genesis.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Database
{
	protected final String host;
	protected final int port;
	protected final String database;
	protected final String userName;
	protected final transient String password;
	public Connection connection;
	
	public Database(final String host, final int port, final String name, final String user, final String passwd) throws ClassNotFoundException, SQLException {
		this.host = host;
		this.port = port;
		this.database = name;
		this.userName = user;
		this.password = passwd;
		connect();
	}
	
	private final void connect() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed())
			return;
		
		synchronized (this) {
			if (connection != null && !connection.isClosed())
				return;
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.userName, this.password);
		}
	}
	
	public final ResultSet query(String query) {
		try {
			ResultSet result = connection.prepareStatement(query).executeQuery();
			if (!result.next()) return null;
			else return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final void insert(String query) {
		try {
			connection.prepareStatement(query).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}