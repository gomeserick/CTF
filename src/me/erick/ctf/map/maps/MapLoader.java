package me.erick.ctf.map.maps;

import me.erick.ctf.sql.MySqlManager;

public class MapLoader {
	
	private static MySqlManager manager = new MySqlManager();
	
	public static void load() {
		int i = 1;
		int max = manager.getTotalMaps();
		while(i<=max) {
			manager.loadMap(i);
			i++;
		}
	}
	
}
