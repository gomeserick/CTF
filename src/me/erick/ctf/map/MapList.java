package me.erick.ctf.map;

import java.util.ArrayList;
import java.util.HashMap;

import me.erick.ctf.map.maps.MapLoader;
import me.erick.ctf.utils.Logger;

public class MapList {
	private HashMap<String,CTFMap> nameList = new HashMap<>();
	private ArrayList<CTFMap> idList = new ArrayList<>();
	private HashMap<String,CTFMap> allMaps = new HashMap<>();
	private static MapList instance;
	
	public MapList() {
		instance = this;
		MapLoader.load();
	}
	
	public static MapList getInstance() {
		return instance;
	}
	
	public void addMap(CTFMap map) {
		allMaps.put(map.getName(),map);
		if(map.isReady()) {
			this.addMapByID(map);
			map.setTemporaryID(this.idList.size());
			this.addMapByName(map);
		} else {
			Logger.sendLog("O mapa " +map.getName()+ " não foi adicionado pois não está pronto");
		}
	}
	private void addMapByName(CTFMap map) {
		this.nameList.put(map.getName(), map);
	}
	public CTFMap getMapByName(String name) {
		return this.nameList.get(name);
	}
	private void addMapByID(CTFMap map) {
		this.idList.add(map);
	}
	public CTFMap getMapByID(int ID) {
		return this.idList.get(ID);
	}
	
	public CTFMap selectRandomMap() {
		int max = idList.size();
		int random = (int)Math.floor(Math.random()*max);
		
		return this.idList.get(random);
	}

	public HashMap<String, CTFMap> getAllMaps() {
		return allMaps;
	}

	public void setAllMaps(HashMap<String, CTFMap> allMaps) {
		this.allMaps = allMaps;
	}
	
	
	
}
