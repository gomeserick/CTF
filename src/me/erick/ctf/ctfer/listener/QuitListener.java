package me.erick.ctf.ctfer.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.flag.events.FlagRecoverEvent;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.teams.TeamManager;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class QuitListener implements Listener{
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		TabListManager.removeFromList(p);
		TabListManager.updateAllLists();
		PlayerList.instance.removeTotalPlayers();
		Ctfer c = PlayerList.instance.getCtfer(p);
		e.setQuitMessage(Facil.chat("&3[CTF] &eO jogador &"+c.getTime().getHexColorCode()+p.getName()+" &edeixou a partida"));
		if(c.isBand()) {
			Bukkit.getServer().getPluginManager().callEvent(
					new FlagRecoverEvent(p,CTFMain.getInstance()
							.getMatch().getMap().getFlag(Teams.getOtherTeam(c.getTime()))));
			c.setBand(false);
		}
		TeamManager teamManager = CTFMain.getInstance().getMatch().getTeamManager();
		teamManager.teamRemover(p);
		PlayerList.instance.getpList().remove(p.getUniqueId());
		
	}
}
