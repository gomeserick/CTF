package me.erick.ctf.utils;

import org.bukkit.Bukkit;

public class Logger {
	public static void sendLog(String s) {
		Bukkit.getConsoleSender().sendMessage(Facil.chat("&3[CTF] &6"+s));
	}
	public static void sendErrorLog(String s) {
		Bukkit.getConsoleSender().sendMessage(Facil.chat("&4[CTF] &c"+s));
	}
}
