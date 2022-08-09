package me.erick.ctf.commands;

import org.bukkit.World;
import org.bukkit.entity.Player;

import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.sql.MySqlManager;
import me.erick.ctf.utils.Facil;

public class AddMapa implements Command{

	private final String nome = "addMapa";
	private MySqlManager manager = new MySqlManager();
	
	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!this.nome.equalsIgnoreCase(name)) {
			return false;
		} else {
			World w = sender.getWorld();
			if(manager.mapExists(w.getName())) {
				sender.sendMessage(Facil.chat("&3[CTF] &eMapa já Cadastrado"));
				return false;
			} else {
				manager.createMap(w.getName());
			}
		}
		return false;
	}
	
}
