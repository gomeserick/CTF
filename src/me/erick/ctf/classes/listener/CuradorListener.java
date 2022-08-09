package me.erick.ctf.classes.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class CuradorListener implements Listener{
	
	@EventHandler
	public void heal(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getDamager() instanceof Player)) return;
		Player healed = (Player) e.getEntity();
		Player healer = (Player) e.getDamager();
		Ctfer cHealed = PlayerList.instance.getCtfer(healed);
		Ctfer cHealer = PlayerList.instance.getCtfer(healer);
		if(!(cHealer.getClasse().equals(Classes.Curador))) return;
		if(!(healer.getItemInHand().getType().equals(Material.GOLD_SWORD))) return;
		if(!(cHealed.getTime().equals(cHealer.getTime()))) return;
		e.setCancelled(true);
		healed.setHealth(20);
	}
}
