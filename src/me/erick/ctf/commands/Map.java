package me.erick.ctf.commands;

import org.bukkit.entity.Player;

import me.erick.ctf.commands.interfaces.Command;

public class Map implements Command{

	private final String name = "map";
	
	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!(this.name.equalsIgnoreCase(name))) return false;
		sender.sendMessage(sender.getWorld().getName());
		return true;
	}

}
