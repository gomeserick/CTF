package me.erick.ctf.ctfer;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerList {
	
	private int totalPlayers;
	
	public static PlayerList instance;
	public PlayerList(){
		instance = this;
	}
	
	private HashMap<UUID, Ctfer> pList = new HashMap<UUID, Ctfer>();

	public HashMap<UUID, Ctfer> getpList() {
		return pList;
	}
	
	public Ctfer getCtfer(Player p) {
		return this.pList.get(p.getUniqueId());
	}
	public void registerAll() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			pList.put(p.getUniqueId(), new Ctfer(p));
		}
	}

	public int getTotalPlayers() {
		return totalPlayers;
	}

	public void setTotalPlayers(int totalPlayers) {
		this.totalPlayers = totalPlayers;
	}
	public void addTotalPlayers() {
		this.totalPlayers++;
	}
	public void removeTotalPlayers() {
		this.totalPlayers--;
	}
}
