package me.erick.ctf.flag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.erick.ctf.flag.Flag;


public class FlagRecoverEvent extends Event {
	private static final HandlerList HANDLERS = new HandlerList();
	private Player killed;
	private Player killer;
	private Flag flagRecovered;
	private DamageCause deathCause;
	private boolean quited;
	
	public FlagRecoverEvent(Player killer, Player killed, DamageCause deathCause, Flag flag) {
		this.killer = killer;
		this.killed = killed;
		this.deathCause = deathCause;
		this.quited = false;
	}
	public FlagRecoverEvent(Player killed, DamageCause deathCause, Flag flag) {
		this.deathCause = deathCause;
		this.killed=killed;
		this.quited = false;
	}
	
	public FlagRecoverEvent(Player killed, Flag flag) {
		this.killed=killed;
		this.quited = true;
		
	}
	
	public Player getKilled() {
		return killed;
	}

	public Player getKiller() {
		return killer;
	}

	public DamageCause getDeathCause() {
		return deathCause;
	}

	public void setKilled(Player killed) {
		this.killed = killed;
	}

	public void setKiller(Player killer) {
		this.killer = killer;
	}

	public void setDeathCause(DamageCause deathCause) {
		this.deathCause = deathCause;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	public boolean isQuited() {
		return quited;
	}
	public void setQuited(boolean quited) {
		this.quited = quited;
	}
	public Flag getFlagRecovered() {
		return flagRecovered;
	}
	public void setFlagRecovered(Flag flagRecovered) {
		this.flagRecovered = flagRecovered;
	}
	
	
}
