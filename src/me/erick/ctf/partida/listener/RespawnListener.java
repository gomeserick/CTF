package me.erick.ctf.partida.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.tablist.TabListManager;

public class RespawnListener implements Listener{
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		c.setItems(c.getClasse());
		TabListManager.updateOneLists(c);
	}
}
