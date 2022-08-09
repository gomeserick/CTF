package me.erick.ctf.ctfer.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.nametagedit.plugin.NametagEdit;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.map.CTFMap;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class JoinListener implements Listener{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PlayerList.instance.addTotalPlayers();
		Player p = e.getPlayer();
		p.setHealth(20);
		p.setExp(0);
		Ctfer c = new Ctfer(p);
		PlayerList.instance.getpList().put(p.getUniqueId(), c);
		PlayerInventory inv = p.getInventory();
		inv.clear();
		
		inv.setArmorContents(new ItemStack[]{new ItemStack(Material.AIR),
											 new ItemStack(Material.AIR),
											 new ItemStack(Material.AIR),
											 new ItemStack(Material.AIR)});
		
		CTFMain.getInstance().getMatch().getTeamManager().particularTeamAttributor(p);
		e.setJoinMessage(Facil.chat("&3[CTF] &eO jogador &"+c.getTime().getHexColorCode()+p.getName()+" &eentrou na partida"));
		if(c.getTime().equals(Teams.BLUE)) {
			NametagEdit.getApi().setPrefix(p, "&9");
			NametagEdit.getApi().setSuffix(p, "");
		} else {
			NametagEdit.getApi().setPrefix(p, "&c");
			NametagEdit.getApi().setSuffix(p, "");
		}
		CTFMap map = CTFMain.getInstance().getMatch().getMap();
		Location tSpawn =  map.getSpawn(c.getTime());
		TabListManager tabList = new TabListManager(e.getPlayer());
		c.setTabListManager(tabList);
		TabListManager.updateAllLists();
		if(!CTFMain.getInstance().getMatch().isStarted() || CTFMain.getInstance().getMatch().isFinished()) {
			p.teleport(map.getPreMatchSpawn());
			p.setBedSpawnLocation(map.getPreMatchSpawn(), true);
		} else {
			c.setItems(c.getClasse());
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
			p.teleport(tSpawn);
			p.setBedSpawnLocation(tSpawn, true);
		}
	}
}
