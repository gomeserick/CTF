package me.erick.ctf.classes.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class SoldadoListener implements Listener{
	
	
	@EventHandler
	public void climb(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!(c.getClasse().equals(Classes.Soldado))) return;
		if(!(p.getItemInHand().getType().equals(Material.IRON_SWORD))) return;
		if(!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
		p.setVelocity(new Vector(0,1,0));
	}
	
	@EventHandler
	public void noFallDamage(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!c.getClasse().equals(Classes.Soldado)) return;
		if(e.getCause().equals(DamageCause.FALL)) e.setCancelled(true);
	}
}
