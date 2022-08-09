package me.erick.ctf.classes.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class QuimicoListener implements Listener{
	@EventHandler
	public void onSplash(PotionSplashEvent e) {
		Player shooter = (Player)e.getPotion().getShooter();
		Ctfer c = PlayerList.instance.getCtfer(shooter);
		for(LivingEntity l : e.getAffectedEntities()) {
			if(l instanceof Player) {
				Player p = ((Player) l);
				Ctfer ct = PlayerList.instance.getCtfer(p);
				if(e.getPotion().getEffects().toString()=="[]") {
					if(ct.getTime()!=c.getTime()) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
							public void run() {
								p.removePotionEffect(PotionEffectType.REGENERATION);
							}
						},0l);
					}
				}
					for(PotionEffect ef : e.getPotion().getEffects()) {
						
						if(c.getTime() == ct.getTime()) {
							PotionEffectType type2 = ef.getType();
							if(type2.equals(PotionEffectType.SLOW)) {
							
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type2);
									}
								},0l);
							}
							if(type2.equals(PotionEffectType.WEAKNESS)) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type2);
									}
								},0l);
							}
							if(type2.equals(PotionEffectType.POISON)) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type2);
									}
								},0l);
							}
						} else if(c.getTime() != ct.getTime()) {
							double life = p.getHealth();
							
							PotionEffectType type = ef.getType();
							if(ef.getType().equals(PotionEffectType.INCREASE_DAMAGE)) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type);
									}
								},0l);
							}
							if(ef.getType().equals(PotionEffectType.FIRE_RESISTANCE)) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type);
									}
								},0l);
							}
							if(ef.getType().equals(PotionEffectType.REGENERATION)) {
								
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type);
									}
								},0l);
							}
							if(ef.getType().equals(PotionEffectType.HEAL)) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.setHealth(life);
									}
								},0l);
							}
							if(ef.getType().equals(PotionEffectType.SPEED)) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
									public void run() {
										p.removePotionEffect(type);
									}
								},0l);
							}
						} 
						if(ef.getType().equals(PotionEffectType.HARM)) {
							e.setCancelled(true);
							if(c.getTime()!=ct.getTime()) {
								if(p.getHealth()>=8) {
									p.setHealth(p.getHealth()-8);
								} else {
									p.setHealth(0);
								}
								p.damage(0);
							}
						}
					}
				}
			}
		}

}
