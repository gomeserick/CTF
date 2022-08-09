package me.erick.ctf.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import me.erick.ctf.map.CTFMap;
import me.erick.ctf.sponge.Sponge;
import me.erick.ctf.utils.Facil;
import me.erick.ctf.utils.Logger;

public class MySqlManager {

	private MySqlSetup sql;


	public MySqlManager() {
		setSql(new MySqlSetup());
		Logger.sendLog("Manager Criado");
	}
	
	public MySqlSetup getSql() {
		return sql;
	}

	public void setSql(MySqlSetup sql) {
		this.sql = sql;
	}

	public void close() {
		this.sql.close();
	}

	/**
	 * Checa se o mapa existe
	 *
	 * @param id A chave primária da tabela
	 *
	 * @return True se o mapa existe <br>
	 *         False se o mapa não existe
	 *
	 */
	public boolean mapExists(int id) {
		PreparedStatement statement = null;
		ResultSet results = null;
		MySqlTables tab = MySqlTables.MAPS;
		try {
			statement = sql.getConnection().prepareStatement("SELECT * FROM " + tab.getTableName() + " WHERE ID=?");
			statement.setInt(1, id);

			results = statement.executeQuery();
			if (results.next()) {
				results.close();
				statement.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean mapExists(String mapName) {
		PreparedStatement statement = null;
		ResultSet results = null;
		MySqlTables tab = MySqlTables.MAPS;
		try {
			statement = sql.getConnection().prepareStatement("SELECT * FROM " + tab.getTableName() + " WHERE Nome=?");
			statement.setString(1, mapName);

			results = statement.executeQuery();
			if (results.next()) {
				results.close();
				statement.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * Checa se a informação fornecida existe
	 * 
	 * @param tab Tabela à ser procurada
	 *
	 * @param id  A chave primária da tabela
	 *
	 * @return True se a informação existe <br>
	 *         False se a informação não existe
	 *
	 */
	public boolean dataExists(int idMapa, MySqlTables tab) {
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			statement = sql.getConnection().prepareStatement("SELECT * FROM " + tab.getTableName() + " WHERE MapaID=?");
			statement.setInt(1, idMapa);

			results = statement.executeQuery();

			if (results.next()) {
				results.close();
				statement.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				results.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	public void createMap(String nome) {
		PreparedStatement statement = null;
		PreparedStatement insert = null;
		ResultSet results = null;
		int id = -1;
		try {
			if (mapExists(nome)) {
				Logger.sendErrorLog("Erro tentando criar um mapa já existente, ID: ");
				return;
			}
			insert = sql.getConnection()
					.prepareStatement("INSERT INTO mapa (ID,Nome, Concluido) VALUE (?,?,?)");
			insert.setInt(1, (getTotalMaps()+1));
			insert.setString(2, nome);
			insert.setBoolean(3, false);
			insert.executeUpdate();
			
			statement = sql.getConnection().prepareStatement("Select * From Mapa where nome=?");
			statement.setString(1, nome);
			results = statement.executeQuery();
			if(results != null && results.next()) {
				id = results.getInt(1);
			}
			if(id == -1) {
				Logger.sendErrorLog("Ocorreu um erro ao Buscar o id do mapa a ser criado");
				return;
			}
			
			createData(MySqlTables.BLUE_FLAG, id, 0, 0, 0);
			createData(MySqlTables.BLUE_SPAWN, id, 0, 0, 0, 0, 0);
			createData(MySqlTables.SPAWN, id, 0, 0, 0, 0);
			createData(MySqlTables.RED_SPAWN, id, 0, 0, 0, 0, 0);
			createData(MySqlTables.RED_FLAG, id, 0, 0, 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				insert.close();
				results.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void createData(MySqlTables table, int MapaID, double... data) {
		if (MySqlTables.MAPS.equals(table) || MySqlTables.MAPS.equals(table)) {
			Logger.sendErrorLog(
					"Ocorreu um erro, o método 'createData' não é compativel com as tabelas 'Mapa' e 'Esponja'");
		}
		;
		if (!mapExists(MapaID)) {
			Facil.logger("Falha ao criar uma row " + table.getTableName() + " pois o mapa " + MapaID + " não existe");
			return;
		}
		PreparedStatement statement = null;

		StringBuilder sb = new StringBuilder("INSERT INTO " + table.getTableName() + "(MapaID");
		switch (table) {
		case SPAWN:
			sb.append(",x");
			sb.append(",y");
			sb.append(",z");
			sb.append(",yaw");
			break;
		case BLUE_SPAWN:
		case RED_SPAWN:
			sb.append(",x");
			sb.append(",y");
			sb.append(",z");
			sb.append(",yaw");
			sb.append(",bloco");
			break;
		case BLUE_FLAG:
		case RED_FLAG:
			sb.append(",x");
			sb.append(",y");
			sb.append(",z");
			break;
		default:
			Logger.sendErrorLog("Ocorreu um erro, tabela " 
					+ table.getTableName() 
					+ " inexperada no método 'updateData'");
			break;
		}
		sb.append(") VALUES (?");
		for (int i = 0; i < table.getDataAmount(); i++) {
			sb.append(",?");
		}
		sb.append(")");
		try {
			statement = sql.getConnection().prepareStatement(sb.toString());
			int column = 1;
			statement.setInt(column, MapaID);
			for (double d : data) {
				column++;
				statement.setDouble(column, d);
			}
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateData(MySqlTables table, int MapaID, double... data) {
		if (MySqlTables.MAPS.equals(table) || MySqlTables.MAPS.equals(table)) {
			Logger.sendErrorLog(
					"Ocorreu um erro, o método 'createData' não é compativel com as tabelas 'Mapa' e 'Esponja'");
		}
		;
		if (!mapExists(MapaID)) {
			Facil.logger("Falha ao criar uma row " + table.getTableName() + " pois o mapa " + MapaID + " não existe");
			return;
		}
		if(!dataExists(MapaID, table)) {
			createData(table, MapaID, data);
		}
		PreparedStatement statement = null;

		StringBuilder sb = new StringBuilder("UPDATE " + table.getTableName() + " SET ");

		try {
			switch (table) {
			case SPAWN:
			case BLUE_SPAWN:
			case RED_SPAWN:
				sb.append("x="+data[0]);
				sb.append(",y="+data[1]);
				sb.append(",z="+data[2]);
				sb.append(",yaw="+data[3]);
				break;
			case BLUE_FLAG:
			case RED_FLAG:
				sb.append("x="+data[0]);
				sb.append(",y="+data[1]);
				sb.append(",z="+data[2]);
				break;
			default:
				Logger.sendErrorLog("Ocorreu um erro, tabela " 
						+ table.getTableName() 
						+ " inexperada no método 'updateData'");
				break;
			}
			sb.append("WHERE MapaID="+MapaID);
			statement = sql.getConnection().prepareStatement(sb.toString());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createSponge(int MapaID, int EsponjaID, String Nome, double... data) {
		if (!mapExists(MapaID)) {
			Facil.logger("Falha ao criar uma row SPONGE pois o mapa " + MapaID + " não existe");
			return;
		}
		PreparedStatement statement = null;

		String s = "INSERT INTO Esponja (MapaID, EsponjaID,Nome,x,y,z,vx,vy,vz) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			statement = sql.getConnection().prepareStatement(s);
			statement.setInt(1, MapaID);
			statement.setInt(2, EsponjaID);
			statement.setString(3, Nome);
			for (int i = 4; i <= data.length; i++) {
				statement.setDouble(i, data[i]);
			}
			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean spongeExists(int EsponjaID, int MapaID) {
		PreparedStatement statement = null;
		ResultSet results = null;
		MySqlTables tab = MySqlTables.SPONGES;
		try {
			statement = sql.getConnection().prepareStatement("SELECT * FROM " + tab.getTableName() + " WHERE EsponjaID=? AND MapaID=?");
			statement.setInt(1, EsponjaID);
			statement.setInt(1, MapaID);

			results = statement.executeQuery();
			if (results.next()) {
				results.close();
				statement.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateSponge(Sponge sponge) {
		
	}
	
	/*public Sponge loadSponge(World w, int EsponjaID, int MapaID) {
		if(!spongeExists(EsponjaID,MapaID)) {
			Facil.logger("Falha ao carregar o mapa pois o mapa " + MapaID + " não existe");
			return null;
		}
		PreparedStatement statement = null;
		ResultSet results = null;
		Sponge sponge = null;
		try {
			statement = sql.getConnection().prepareStatement("SELECT * FROM esponja WHERE EsponjaID=? AND MapaID=?");
			statement.setInt(1, EsponjaID);
			statement.setInt(1, MapaID);

			results = statement.executeQuery();
			
			if(results != null && results.next()) {
				sponge = new Sponge(w,
									results.getString("Nome"),
									results.getDouble("x"),
									results.getDouble("y"),
									results.getDouble("z"),
									results.getDouble("vx"),
									results.getDouble("vy"),
									results.getDouble("vz"));
				results.close();
				statement.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	public CTFMap loadMap(int MapaID) {
		if (!mapExists(MapaID)) {
			Facil.logger("Falha ao carregar o mapa pois o mapa " + MapaID + " não existe");
			return null;
		}
		PreparedStatement statement = null;
		ResultSet results = null;
		CTFMap map=null;
		try {
			statement = sql.getConnection().prepareStatement("SELECT * FROM Mapa WHERE ID=?");
			statement.setInt(1, MapaID);
			results = statement.executeQuery();
			if(results != null && results.next()) {
				int id = results.getInt("ID");
				String nome = results.getString("Nome");
				boolean pronto = results.getBoolean("Concluido");
				map = new CTFMap(id, nome, pronto);
				results.close();
				statement.close();
			}
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				results.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateMap(CTFMap map) {
		int MapaID = map.getId();
		String name = map.getWorld().getName();
		if (!mapExists(MapaID)) {
			Facil.logger("Falha ao carregar o mapa pois o mapa " + MapaID + " não existe");
			return;
		}
		PreparedStatement statement = null;
		try {
			statement = sql.getConnection().prepareStatement("UPDATE Mapa SET Nome=?, Concluido=? WHERE ID=?");
			statement.setString(1, name);
			statement.setBoolean(2, map.isReady());
			statement.setInt(3, MapaID);
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet loadData(MySqlTables table, int MapaID) {
		if (MySqlTables.MAPS.equals(table) || MySqlTables.MAPS.equals(table)) {
			Logger.sendErrorLog(
					"Ocorreu um erro, o método 'loadData' não é compativel com as tabelas 'Mapa' e 'Esponja'");
			return null;
		}
		;
		if (!mapExists(MapaID)) {
			Facil.logger("Falha ao criar uma row " + table.getTableName() + " pois o mapa " + MapaID + " não existe");
			return null;
		}
		PreparedStatement statement = null;
		ResultSet results = null;

		StringBuilder sb = new StringBuilder("SELECT * FROM " + table.getTableName() + " WHERE MapaID="+MapaID);

		try {
			statement = sql.getConnection().prepareStatement(sb.toString());
			results = statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public int getTotalMaps() {
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			statement = sql.getConnection().prepareStatement("SELECT COUNT(*) FROM mapa");
			results = statement.executeQuery();
			if(results != null && results.next()) {
				return results.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<ResultSet> getAllMaps() {
		PreparedStatement statement = null;
		ArrayList<ResultSet> list = new ArrayList<ResultSet>();
		try {
			int max = getTotalMaps();
			int i = 1;
			while(i<=max) {
				statement = sql.getConnection().prepareStatement("SELECT * FROM mapa where ID = " + i);
				ResultSet results = statement.executeQuery();
				list.add(results);
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
