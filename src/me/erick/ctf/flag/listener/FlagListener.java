package me.erick.ctf.flag.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;
import me.erick.ctf.utils.Logger;
import me.erick.ctf.flag.events.*;
import me.erick.ctf.tablist.TabListManager;

public class FlagListener implements Listener {
	
	@EventHandler
	public void pickUpFlag(PlayerPickupItemEvent e) {
		if(CTFMain.getInstance().getMatch().isFinished()) return;
		Item item = e.getItem();
		ItemStack i = item.getItemStack();
		if(!(i.getType().equals(Material.WOOL))) {
			e.setCancelled(true);
			return;
		}
		if(i.getDurability()==0) {
			e.setCancelled(true);
			return;
		}
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(c.getTime().getColorCode()==i.getDurability()) {
			e.setCancelled(true);
			return;
		}
		p.getWorld().strikeLightningEffect(item.getLocation());
		c.setBand(true);
		if(c.getTime().equals(Teams.BLUE)) {
			CTFMain.getInstance().getMatch().getFlagManager().setRedStealer(p);
		} else {
			CTFMain.getInstance().getMatch().getFlagManager().setBlueStealer(p);
		}
		Location l = item.getLocation();
		Item whiteWool = l.getWorld().dropItem(l, new ItemStack(Material.WOOL));
		whiteWool.setVelocity(new Vector(0,0,0));
		CTFMain.getInstance().getMatch().getMap().getFlag(Teams.getOtherTeam(c.getTime())).setStolen(true);
		Facil.allPlayers("O jogador &"+c.getTime().getHexColorCode() + e.getPlayer().getName() + 
						 "&e roubou a bandeira da equipe &" + 
						 Teams.getOtherTeam(c.getTime()).getHexColorCode()+
						 Teams.getOtherTeam(c.getTime()).getTime());
		Bukkit.getServer().getPluginManager().callEvent(new PlayerStealFlagEvent(p));
		CTFMain.getInstance().getMatch().getFlagManager().bandRefresh();
		Logger.sendLog("O jogador " + CTFMain.getInstance().getMatch().getFlagManager().getRedStealer().getName() + "Roubou a bandeira Vermelha");
		TabListManager.updateAllLists();
	}
	
	@EventHandler
	public void flagRecover(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(!(c.isBand())) return;
		CTFMain.getInstance().getMatch().getMap().getFlag(Teams.getOtherTeam(c.getTime())).setStolen(false);
		Bukkit.getServer().getPluginManager().callEvent(
				new FlagRecoverEvent(p,CTFMain.getInstance()
						.getMatch().getMap().getFlag(Teams.getOtherTeam(c.getTime()))));
		TabListManager.updateAllLists();
	}
	
	@EventHandler
	public void flagTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(c.isBand() && c.getClasse().equals(Classes.Ninja)) {
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void speedWhenSteal(PlayerStealFlagEvent e) {
		if(e.getCtfer().getClasse().equals(Classes.Ladrao)) {
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 4));
		}
	}
	
	@EventHandler
	public void onRecover(FlagRecoverEvent e) {
		Ctfer c = PlayerList.instance.getCtfer(e.getKilled());
		c.setBand(false);
		Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Teams team = Teams.getOtherTeam(c.getTime());
				Facil.allPlayers("&eA bandeira &"+team.getHexColorCode()+team.getTime()+" &efoi recuperada!");
				for(Entity e : CTFMain.getInstance().getMatch().getMap().getWorld().getEntities()) {
					if(e instanceof Item){
						e.remove();
					}
				}
				CTFMain.getInstance().getMatch().getFlagManager().bandRefresh();
			}
		}, 60l);
	}
	
	
	
	
}
