package me.erick.ctf.commands;

import org.bukkit.entity.Player;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.utils.Facil;

public class Classe implements Command{
	
	private final String name = "classe";

	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!(this.name.equalsIgnoreCase(name))) return false;
		Ctfer c = PlayerList.instance.getCtfer(sender);
		if(args.length==0) {
			sender.sendMessage(Facil.chat("&6Guerreiro\n"
										  +"Arqueiro\n"
										  +"Curador\n"
										  +"Soldado\n"
										  +"Incendiario\n"
										  +"Mago\n"
										  +"Ninja\n"
										  +"Ladrao\n"
										  +"Quimico\n"
										  +"Assassino\n"
										  + "Sua Classe atual é: "+c.getClasse().getName()));
			return true;
		} else if(!(Classes.isClasse(args[0]))) {
			sender.sendMessage(Facil.chat("&cClasse inválida"));
			return false;
		} else if(Classes.isClasse(args[0])) {
			if(CTFMain.getInstance().getMatch().isStarted() && !CTFMain.getInstance().getMatch().isFinished())sender.setHealth(0);
			c.classChange(Classes.getClassFromName(args[0]));
			return true;
		}
		return false;
	}
	
	
	
}
