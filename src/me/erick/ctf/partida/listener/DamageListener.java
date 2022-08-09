package me.erick.ctf.partida.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.ctfer.util.Kill;
import me.erick.ctf.flag.events.FlagRecoverEvent;
import me.erick.ctf.teams.Teams;

public class DamageListener implements Listener{
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted() || CTFMain.getInstance().getMatch().isFinished()) {
			if(e.getCause().equals(DamageCause.VOID)) {
				e.getEntity().teleport(CTFMain.getInstance().getMatch().getMap().getPreMatchSpawn());
			}
			e.setCancelled(true);
			return;
		}
		if(!(e.getEntity() instanceof Player)) return; 
			Player p = (Player) e.getEntity();
			Ctfer c = PlayerList.instance.getCtfer(p);
		if(c.isOnSponge() && e.getCause().equals(DamageCause.FALL)) {
			e.setCancelled(true);
			c.setOnSponge(false);
			return;
		} else if(e.getCause().equals(DamageCause.LIGHTNING)) {
			e.setCancelled(true);
		} else if(e.getCause().equals(DamageCause.ENTITY_EXPLOSION)) {
			e.setCancelled(true);
		} else if(e.getCause().equals(DamageCause.PROJECTILE)) {
			if(e.getDamage() <1) {
				e.setCancelled(true);
			}
		}
		if(p.getHealth()<=0) {
			if(c.isBand()) {
				c.setBand(false);
				Bukkit.getServer().getPluginManager().callEvent(
						new FlagRecoverEvent(p,e.getCause(),CTFMain.getInstance()
								.getMatch().getMap().getFlag(Teams.getOtherTeam(c.getTime()))));
			}
		}
	}
	@EventHandler
	public void damageByEntity(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Item) {
			e.setCancelled(true);
		}
		if(!(e.getEntity() instanceof Player)) return;
		Player damager = null;
		if(!(e.getDamager() instanceof Player)) {
			if(e.getDamager() instanceof Projectile) {
				Projectile pr = (Projectile) e.getDamager();
				if(pr.getShooter() instanceof Player) {
					damager = (Player) pr.getShooter();
				}
			} else {
				return;
			}
		} else {
			damager = (Player) e.getDamager();
		}
		
		Player damaged = (Player) e.getEntity();
		Ctfer ctfDamager = PlayerList.instance.getCtfer(damager);
		Ctfer ctfDamaged = PlayerList.instance.getCtfer(damaged);
		if(ctfDamaged.getTime().equals(ctfDamager.getTime())) {
			e.setCancelled(true);
			return;
		}
		if(e.getFinalDamage()>=damaged.getHealth()) {
			Kill.kill(ctfDamager, ctfDamaged);
		}
		
	}
	
	
}
