package me.erick.ctf.classes.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.ctfer.util.Kill;
import me.erick.ctf.utils.Facil;

public class IncendiarioListener implements Listener{
	@EventHandler
	public void arrowExplosion(ProjectileHitEvent e) {
		
		Player p = (Player) e.getEntity().getShooter();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!c.getClasse().equals(Classes.Incendiario)) return;
		if(e.getEntityType().equals(EntityType.ARROW)) {
			Arrow arrow = (Arrow) e.getEntity();
			Location l = arrow.getLocation();
			World w = l.getWorld();
			w.createExplosion(l, 0f);
			w.spigot().playEffect(l, Effect.EXPLOSION_LARGE);
			for(Entity entity : l.getWorld().getNearbyEntities(l, 3,3, 3)) {
				if(entity instanceof Player) {
					Player pl = ((Player) entity).getPlayer();
					Ctfer ct = PlayerList.instance.getCtfer(pl);
					if(ct.getTime()!=c.getTime()) {
						Bukkit.getConsoleSender().sendMessage(Facil.chat("&3[CTF] &fColocando em fogo o jogador " + pl.getName()));
						pl.setFireTicks(200);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void hitOnFire(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)||!(e.getDamager() instanceof Player)) return;
		Player p = (Player) e.getDamager();
		Player pl = (Player) e.getEntity();
		Ctfer c = PlayerList.instance.getCtfer(p);
		Ctfer ct = PlayerList.instance.getCtfer(pl);
		ItemStack item = p.getItemInHand();
		Material m = item.getType();
		if(c.getClasse().equals(Classes.Incendiario)) {
			if(pl.getFireTicks()>0 && m.equals(Material.DIAMOND_AXE)) {
				e.setDamage(10000);
				Kill.kill(c, ct);
			}
		} 
	}
	
	
	
}
