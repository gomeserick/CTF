package me.erick.ctf.flag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.teams.Teams;

public class PlayerStealFlagEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
	private Teams stolenTeam;
	private Player player;
	private Ctfer ctfer;



    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

	
	public PlayerStealFlagEvent(Player p) {
		this.ctfer = PlayerList.instance.getCtfer(p);
		this.player = p;
		this.stolenTeam = (ctfer.getTime().equals(Teams.BLUE)) ? Teams.BLUE : Teams.RED;
	}

	public Teams getStolenTeam() {
		return stolenTeam;
	}

	public void setStolenTeam(Teams stolenTeam) {
		this.stolenTeam = stolenTeam;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ctfer getCtfer() {
		return ctfer;
	}

	public void setCtfer(Ctfer ctfer) {
		this.ctfer = ctfer;
	}
	
	
	
	
	
}
