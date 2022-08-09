package me.erick.ctf.classes.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.utils.Facil;

public class NinjaListener implements Listener{
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted()
		||CTFMain.getInstance().getMatch().isFinished()) return;
		if(!(e.getEntity().getShooter() instanceof Player))	return;
		Player p = (Player) e.getEntity().getShooter();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!c.getClasse().equals(Classes.Ninja)) return;
		if(e.getEntityType().equals(EntityType.EGG)) {
			Egg egg = (Egg) e.getEntity();
			Location l = egg.getLocation();
			World world = l.getWorld();
			world.createExplosion(l, 0f);
			world.spigot().playEffect(l, Effect.EXPLOSION_LARGE);
			for(Entity entity : l.getWorld().getNearbyEntities(l, 3,3, 3)) {
				if(entity instanceof Player) {
					Player pl = ((Player) entity).getPlayer();
					Ctfer ct = PlayerList.instance.getCtfer(pl);
					if(ct.getTime()!=c.getTime()) {
						Bukkit.getConsoleSender().sendMessage(Facil.chat("&3[CTF] &fFlashBanging o jogador " + pl.getName()));
						PotionEffect potion = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1);
						pl.addPotionEffect(potion, true);
					}
				}
			}
		}
	}
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted()
		||CTFMain.getInstance().getMatch().isFinished()) return;
		Action a = e.getAction();
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		ItemStack item = p.getItemInHand();
		Material m = item.getType();
		if(!c.getClasse().equals(Classes.Ninja)) return;
		if(a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) return;
		if(m.equals(Material.ENDER_PEARL)) {
			if(c.isBand()) {
				e.setCancelled(true);
				p.sendMessage(Facil.chat("&3[CTF] &cVocê não pode teleportar com a bandeira"));
			}
		} 
		if(m.equals(Material.REDSTONE)) {
			c.setInvisivel(true);
			p.sendMessage(Facil.chat("&5[Ninja] Agora você está invisivel "));
		}
	}
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted()
		||CTFMain.getInstance().getMatch().isFinished()) return;
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!c.getClasse().equals(Classes.Ninja)) return;
		if(!(e.getCause().equals(TeleportCause.ENDER_PEARL))) return;
		if(c.isBand()) {
			e.setCancelled(true);
			p.sendMessage(Facil.chat("&3[CTF] &cVocê não pode teleportar com a bandeira"));
			return;
		}
		p.setFallDistance(0);
		p.teleport(e.getTo());
		e.setCancelled(true);
	}
	@EventHandler
	public void damaged(EntityDamageByEntityEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted()
		||CTFMain.getInstance().getMatch().isFinished()) return;
		if(!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!c.getClasse().equals(Classes.Ninja)) return;
		if(!c.isInvisivel()) return;
		c.setInvisivel(false);
		p.sendMessage(Facil.chat("&5[Ninja] &cVocê não está mais invisivel "));
		for(Player pl : Bukkit.getOnlinePlayers()) {
			pl.showPlayer(p);
		}
	}
}
