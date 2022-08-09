package me.erick.ctf.partida.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BeefListener implements Listener{
	@EventHandler
	public void eatBeef(PlayerInteractEvent e) {
		ItemStack i = e.getItem();
		Player p = e.getPlayer();
		if(i==null) return;
		if(!i.getType().equals(Material.COOKED_BEEF)) return;
		if(p.getHealth()==20) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)||e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(i.getAmount()>1) {
				i.setAmount(i.getAmount()-1);
				healthIncrementer(p);
			} else {
				p.getInventory().remove(i);
				healthIncrementer(p);
			}
		}
	}
	
	
	public void healthIncrementer(Player p) {
		if(p.getHealth()<12) {
			p.setHealth(p.getHealth()+8);
		} else {
			p.setHealth(20);
		}
	}
}
