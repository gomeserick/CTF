package me.erick.ctf.commands.interfaces;

import org.bukkit.entity.Player;

public interface Command {
	
	public boolean execute(Player sender, String name, String[] args);
	
}
