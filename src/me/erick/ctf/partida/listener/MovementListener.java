package me.erick.ctf.partida.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.teams.Teams;

public class MovementListener implements Listener{
	@EventHandler
	public void onMovement(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		Location l = p.getLocation();
		Block b = l.subtract(0,1,0).getBlock();
		Material m = b.getType();
		switch(m) {
		case REDSTONE_BLOCK:
		case NETHERRACK:
		case LAPIS_BLOCK:
			moveOnSpawn(c, m);
			break;
		case SPONGE:
			moveOnSponge(c);
			break;
		default:
			return;
		}
	}
	
	private void moveOnSponge(Ctfer c) {
		Player p = c.getPlayer();
		c.setOnSponge(true);
		p.setVelocity(new Vector(0,2,0));
	}
	private void moveOnSpawn(Ctfer c, Material m) {
		if(!CTFMain.getInstance().getMatch().isStarted() || CTFMain.getInstance().getMatch().isFinished())return;
		Teams team = c.getTime();
		if(c.getPlayer().isDead()) return;
		if(team.equals(Teams.BLUE) && m.equals(CTFMain.getInstance().getMatch().getMap().getRedBlock())) {
			c.getPlayer().setHealth(0);
		} else if(team.equals(Teams.RED) && m.equals(CTFMain.getInstance().getMatch().getMap().getBlueBlock())) {
			c.getPlayer().setHealth(0);
		} else if(c.isBand()) {
			c.getPlayer().setHealth(0);
		}
	}
	
	
}
