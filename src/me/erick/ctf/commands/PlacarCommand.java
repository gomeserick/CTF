package me.erick.ctf.commands;

import org.bukkit.entity.Player;

import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.partida.Placar;
import me.erick.ctf.utils.Facil;

public class PlacarCommand implements Command{

	private final String name = "placar";
	
	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!this.name.equalsIgnoreCase(name)) return false;
		Placar placar = CTFMain.getInstance().getMatch().getPlacar();
		sender.sendMessage(Facil.chat("&3[CTF] &ePlacar: &cVermelho &f" + placar.getVermelhoPontos() + " &eX &f" + placar.getAzulPontos() + " &9Azul"));
		return true;
	}
	
}
