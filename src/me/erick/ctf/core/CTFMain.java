package me.erick.ctf.core;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import me.erick.ctf.commands.Commander;
import me.erick.ctf.core.runnables.Glowstone;
import me.erick.ctf.core.runnables.NinjaRunnable;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.map.MapList;
import me.erick.ctf.partida.Match;
import me.erick.ctf.sql.MySqlManager;
import me.erick.ctf.utils.Logger;

public class CTFMain extends JavaPlugin {

	public PlayerList lista = new PlayerList();
	private Commander commander = new Commander();
	private MapList mapList;
	private MySqlManager SQLmanager = new MySqlManager();
	private static CTFMain instance;
	
	private Match match;
	
	@Override
	public void onEnable() {
		setInstance(this);
		
		try{
			mapList = new MapList();
			Bukkit.createWorld(new WorldCreator("CTF_Whiteout"));
			EventRegister.register();
			match = new Match(mapList.selectRandomMap());
			CommandRegister.register();
			Logger.sendLog("CTF iniciado com sucesso");
			
		} catch(Exception e) {
			Logger.sendErrorLog("Ocorreu um erro ao inicar o CTF: ");
			e.printStackTrace();
		}
		NinjaRunnable.execute();
		Glowstone.execute();
	}
	
	@Override
	public void onDisable() {
		Logger.sendLog("CTF Finalizado com sucesso");
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Commander getCommander() {
		return commander;
	}

	public void setCommander(Commander commander) {
		this.commander = commander;
	}

	public MySqlManager getSQLmanager() {
		return SQLmanager;
	}

	public void setSQLmanager(MySqlManager sQLmanager) {
		SQLmanager = sQLmanager;
	}

	public static CTFMain getInstance() {
		return instance;
	}

	public static void setInstance(CTFMain instance) {
		CTFMain.instance = instance;
	}

	public MapList getMapList() {
		return mapList;
	}

	public void setMapList(MapList mapList) {
		this.mapList = mapList;
	}
	
}
