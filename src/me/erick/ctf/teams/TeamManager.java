package me.erick.ctf.teams;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class TeamManager {
	
	private int redQuant;
	private int blueQuant;
	private ArrayList<Player> blueList= new ArrayList<>();
	private ArrayList<Player> redList = new ArrayList<>();
	
	
	
	public void generalTeamAttributor() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			Ctfer c = PlayerList.instance.getCtfer(p);
			if(teamSelector()) {
				c.setTime(Teams.BLUE);
				blueQuant++;
			} else {
				c.setTime(Teams.RED);
				redQuant++;
			}
		}
	}
	
	public ArrayList<Player> getBlueList() {
		return blueList;
	}

	public ArrayList<Player> getRedList() {
		return redList;
	}

	public void setBlueList(ArrayList<Player> blueList) {
		this.blueList = blueList;
	}

	public void setRedList(ArrayList<Player> redList) {
		this.redList = redList;
	}

	public void particularTeamAttributor(Player p) {
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(teamSelector()) {
			c.setTime(Teams.BLUE);
			blueQuant++;
		} else {
			c.setTime(Teams.RED);
			redQuant++;
		}
	}
	
	public void teamRemover(Player p) {
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(c.getTime().equals(Teams.BLUE)) {
			blueQuant--;
		} else {
			redQuant--;
		}
	}
	
	public int getRedQuant() {
		return redQuant;
	}

	public int getBlueQuant() {
		return blueQuant;
	}

	public void setRedQuant(int redQuant) {
		this.redQuant = redQuant;
	}

	public void setBlueQuant(int blueQuant) {
		this.blueQuant = blueQuant;
	}
	
	public boolean teamSelector() {
		if(this.redQuant>=this.blueQuant) {
			return true;
		} else {
			return false;
		}
	}
}
