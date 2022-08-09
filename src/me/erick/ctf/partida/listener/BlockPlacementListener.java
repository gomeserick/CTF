package me.erick.ctf.partida.listener;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class BlockPlacementListener implements Listener{
	
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent e) {
		if(CTFMain.getInstance().getMatch().isFinished() || !CTFMain.getInstance().getMatch().isStarted()) {
			e.setCancelled(true);
			return;
		}
		Block b = e.getBlock();
		Material m = b.getType();
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(m.equals(Material.WEB)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable(){
				@Override
				public void run(){
					b.setType(Material.AIR);
				}
			}, 600L);
			return;
		} else if(m.equals(Material.FIRE)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable(){
				@Override
				public void run(){
					b.setType(Material.AIR);
				}
			}, 600L);
			return;
		} else if(m.equals(Material.WOOL)) {
			if(c.isBand()) {
				return;
			}else {
				e.setCancelled(true);
			}
		} else if(m.equals(Material.ENDER_STONE)) {
			return;
		} else {
		
			e.setCancelled(true);
		}
	}
}
