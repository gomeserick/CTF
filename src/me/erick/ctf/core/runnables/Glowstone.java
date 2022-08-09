package me.erick.ctf.core.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class Glowstone implements IRun{
	
	
	
	public static void execute() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Ctfer c = PlayerList.instance.getCtfer(p);
					if(c.isBand()) {
            			Location l = p.getLocation();
            			l.setY(l.getY()+2);
            			Item i = p.getWorld().dropItem(l, new ItemStack(Material.GLOWSTONE));
            			i.setVelocity(new Vector(0,0.2,0));
            			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable(){
							@Override
							public void run(){
								i.remove();
							}
						}, 2L);
            		}
				}
			}
		},0l,4l);
	}
}
