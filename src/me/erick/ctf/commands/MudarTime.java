package me.erick.ctf.commands;

import org.bukkit.entity.Player;

import com.nametagedit.plugin.NametagEdit;

import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class MudarTime implements Command{

	private final String name = "MudarTime";
	
	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!(this.name.equalsIgnoreCase(name))) return false;
		Player p = (Player) sender;
		Ctfer c = PlayerList.instance.getCtfer(p);
		if(CTFMain.getInstance().getMatch().isStarted() && !CTFMain.getInstance().getMatch().isFinished()) {
			if(c.getTime().equals(Teams.BLUE)) {
				c.setTime(Teams.RED);
				p.setBedSpawnLocation(CTFMain.getInstance().getMatch().getMap().getRedSpawn(), true);
				p.setHealth(0);
				p.sendMessage(Facil.chat("&3[CTF] &eVocê mudou para a equipe &cVermelha"));
				NametagEdit.getApi().setPrefix(p, "&c");
				NametagEdit.getApi().setSuffix(p, "");
			} else {
				c.setTime(Teams.BLUE);
				p.setBedSpawnLocation(CTFMain.getInstance().getMatch().getMap().getBlueSpawn(), true);
				p.setHealth(0);
				p.sendMessage(Facil.chat("&3[CTF] &eVocê mudou para a equipe &9Azul"));
				NametagEdit.getApi().setPrefix(p, "&9");
				NametagEdit.getApi().setSuffix(p, "");
			}
		} else if(!CTFMain.getInstance().getMatch().isStarted()) {
			if(c.getTime().equals(Teams.BLUE)) {
				c.setTime(Teams.RED);
				p.setBedSpawnLocation(CTFMain.getInstance().getMatch().getMap().getRedSpawn(), true);
				p.sendMessage(Facil.chat("&3[CTF] &eVocê mudou para a equipe &cVermelha"));
				NametagEdit.getApi().setPrefix(p, "&c");
				NametagEdit.getApi().setSuffix(p, "");
			} else {
				c.setTime(Teams.BLUE);
				p.setBedSpawnLocation(CTFMain.getInstance().getMatch().getMap().getBlueSpawn(), true);
				p.sendMessage(Facil.chat("&3[CTF] &eVocê mudou para a equipe &9Azul"));
				NametagEdit.getApi().setPrefix(p, "&9");
				NametagEdit.getApi().setSuffix(p, "");
			}
		}
		TabListManager.updateAllLists();
		return true;
	}

}
