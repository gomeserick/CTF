package me.erick.ctf.sql;

public enum MySqlTables {
	/**
	 * Cada um desses enums representa uma tabela do banco de dados
	 * Os parametros são respectivamente:
	 * 1: O nome da tabela
	 * 2: A quantidade de colunas que a tabela tem
	 * 3 em diante: o nome das colunas
	 */
	MAPS		("Mapa", 			3,"ID","Nome","Concluido"),
	BLUE_FLAG	("BandeiraAzul",	3,"x","y","z"),
	RED_FLAG	("BandeiraVermelha",3,"x","y","z"),
	SPONGES		("Esponjas", 		8,"x","y","z","vx","vy","vz","EsponjaID","Nome"),
	BLUE_SPAWN	("SpawnAzul", 		5,"x","y","z","yaw","Bloco"),
	RED_SPAWN	("SpawnVermelho", 	5,"x","y","z","yaw","Bloco"),
	SPAWN		("Spawn", 			4,"x","y","z","yaw");
	private String tableName;
	private int dados;
	private String[] columns;
	
	MySqlTables(String tableName, int dados, String...columns) {
		this.tableName = tableName;
		this.dados = dados;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	public int getDataAmount() {
		return this.dados;
	}

	public String[] getColumns() {
		return columns;
	}
}
