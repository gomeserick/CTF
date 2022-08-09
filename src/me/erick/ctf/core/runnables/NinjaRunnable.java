package me.erick.ctf.core.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.utils.Facil;
import me.erick.ctf.core.CTFMain;

public class NinjaRunnable implements IRun{
	
	public static void execute() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Ctfer c = PlayerList.instance.getCtfer(p);
					if(c.getClasse().equals(Classes.Ninja)) {
						if(c.isInvisivel()) {
							for(Player pl : Bukkit.getOnlinePlayers()) {
								Ctfer ct = PlayerList.instance.getCtfer(pl);
								if(!c.getTime().equals(ct.getTime())) {
									pl.hidePlayer(p);
								}
							}
							ItemStack item = p.getItemInHand();
							if(item.getType().equals(Material.REDSTONE)) {
								if(item.getAmount()>1) item.setAmount(item.getAmount()-1);
        						else p.getInventory().remove(item);
							} else {
								c.setInvisivel(false);
								p.sendMessage(Facil.chat("&5[Ninja] &cVocê não está mais invisivel "));
							}
						} else {
							for(Player pl : Bukkit.getOnlinePlayers()) {
								Ctfer ct = PlayerList.instance.getCtfer(pl);
								if(!c.getTime().equals(ct.getTime())) {
									pl.showPlayer(p);
								}
							}
						}
					}
				}
				
			}
		}, 10, 10);
	}
	
	
}
