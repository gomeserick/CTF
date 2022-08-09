package me.erick.ctf.classes.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class AssassinoListener implements Listener{
	@EventHandler
	public void consume(PlayerInteractEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted()
		||CTFMain.getInstance().getMatch().isFinished()) return;
		Action a = e.getAction();
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		ItemStack i = p.getItemInHand();
		Material m = i.getType();
		if(!c.getClasse().equals(Classes.Assassino)) return;
		if(a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) return;
		if(m.equals(Material.SUGAR)) {
			if(i.getAmount()>1) {
				i.setAmount(i.getAmount()-1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 2));
			} else {
				p.getInventory().remove(i);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 2));
			}
		} 
		if(m.equals(Material.REDSTONE)) {
			if(i.getAmount()>1) {
				i.setAmount(i.getAmount()-1);
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2));
			} else {
				p.getInventory().remove(i);
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2));
			}
		}
	}
}
