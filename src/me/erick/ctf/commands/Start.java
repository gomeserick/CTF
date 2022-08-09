package me.erick.ctf.commands;

import org.bukkit.entity.Player;

import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.utils.Facil;
import me.erick.ctf.utils.Logger;

public class Start implements Command{
	
	private final String name = "start";
	
	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!(this.name.equalsIgnoreCase(name))) return false;
		if(CTFMain.getInstance().getMatch().isStarted()) {
			sender.sendMessage(Facil.chat("&cA partida já iniciou"));
			return false;
		}
		CTFMain.getInstance().getMatch().start();
		Logger.sendLog(sender.getName() + " Forçou o início da partida");
		return true;
	}

	

}
