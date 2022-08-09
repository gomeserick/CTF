package me.erick.ctf.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.erick.ctf.utils.Facil;

public class Commander implements CommandExecutor{
	
	private Start start = new Start();
	private Classe classe = new Classe();
	private Map map = new Map();
	private MudarTime mudarTime = new MudarTime();
	private PlacarCommand placar = new PlacarCommand();
	private Setar setar = new Setar();
	private AddMapa addMapa = new AddMapa();
	private SetPronto setPronto = new SetPronto();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {

		
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Facil.chat("&cVocê precisa ser um jogador para usar esse comando"));
			return false;
		}
		Player p = (Player) sender;
		if(start.execute(p, lbl, args)) return true;
		else if(classe.execute(p, lbl, args)) return true;
		else if(map.execute(p, lbl, args)) return true;
		else if(mudarTime.execute(p, lbl, args)) return true;
		else if(placar.execute(p, lbl, args)) return true;
		else if(setar.execute(p, lbl, args)) return true;
		else if(addMapa.execute(p, lbl, args)) return true;
		else if(setPronto.execute(p, lbl, args)) return true;
		return false;
	}
	
	
}
