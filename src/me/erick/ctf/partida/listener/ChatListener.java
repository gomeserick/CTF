package me.erick.ctf.partida.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class ChatListener implements Listener{
	@EventHandler
	public void Chat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		Teams team = c.getTime();
		for(Player reciver: Bukkit.getOnlinePlayers()) {
			reciver.sendMessage(Facil.chat("&7[g] &"+team.getHexColorCode()+p.getName()+"&7: "+e.getMessage()));
		}
	}
}
