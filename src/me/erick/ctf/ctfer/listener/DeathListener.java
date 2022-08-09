package me.erick.ctf.ctfer.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.ctfer.util.Kill;


public class DeathListener implements Listener{
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		for(ItemStack i : e.getDrops()) {
			i.setType(Material.AIR);
		}
		e.getEntity().playSound(e.getEntity().getLocation(), Sound.VILLAGER_DEATH, 1, 1);
		Entity en = e.getEntity().getKiller();
		if(en==null) {
			Ctfer c = PlayerList.instance.getCtfer(e.getEntity());
			Kill.kill(c);
		}
	}
}
