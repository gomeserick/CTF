package me.erick.ctf.classes.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.utils.Facil;

public class LadraoListener implements Listener{
	@EventHandler
	public void Steal(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;
		Player p1 = (Player) e.getDamager();
		Player p2 = (Player) e.getEntity();
		Ctfer ladrao = PlayerList.instance.getCtfer(p1);
		if(!ladrao.getClasse().equals(Classes.Ladrao)) return;
		Ctfer roubado = PlayerList.instance.getCtfer(p2);
		ItemStack bastao = p1.getItemInHand();
		ItemStack itemRoubado = p2.getItemInHand();
		if(itemRoubado==null||bastao==null) return;
		if(itemRoubado.getType().equals(Material.WOOL)) return;
		Material mbastao = bastao.getType();
		if(ladrao.getTime()!=roubado.getTime()) {
			if(mbastao.equals(Material.STICK)) {
				ItemStack aux = new ItemStack(bastao);
				bastao.setType(itemRoubado.getType());
				bastao.setItemMeta(itemRoubado.getItemMeta());
				bastao.setAmount(itemRoubado.getAmount());
				itemRoubado.setType(aux.getType());
				itemRoubado.setItemMeta(aux.getItemMeta());
				itemRoubado.setAmount(aux.getAmount());
				p1.sendMessage(Facil.chat("&c[Ladrao]&b Você foi roubado por: &f" + p2.getName()));
				p2.sendMessage(Facil.chat("&e[Ladrao]&a Você roubou &7["+ladrao.getClasse()+"] &f"+ p1.getName()));
			}
		}
		
		
	}
}
