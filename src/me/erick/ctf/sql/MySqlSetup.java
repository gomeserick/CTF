package me.erick.ctf.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;


public class MySqlSetup {
	private Connection connection;
	private String host = "localhost";
	private String database = "ctf";
	private String username = "root";
	private String password = "";
	private String port = "3306";
	private Plugin p;
	
	public MySqlSetup() {
		try {
			synchronized (this) {
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://"+host+":"
				+port+"/"+database, username, password));
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	public Connection getConnection() {
		return connection;
	}
	public Plugin getPlugin() {
		return p;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public void setP(Plugin p) {
		this.p = p;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
