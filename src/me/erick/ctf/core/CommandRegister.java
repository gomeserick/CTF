package me.erick.ctf.core;

import me.erick.ctf.commands.Commander;

public class CommandRegister {
	
	public static void register() {
		Commander commander = CTFMain.getInstance().getCommander();
		CTFMain.getInstance().getCommand("Start").setExecutor(commander);
		CTFMain.getInstance().getCommand("Classe").setExecutor(commander);
		CTFMain.getInstance().getCommand("Map").setExecutor(commander);
		CTFMain.getInstance().getCommand("MudarTime").setExecutor(commander);
		CTFMain.getInstance().getCommand("Placar").setExecutor(commander);
		CTFMain.getInstance().getCommand("Setar").setExecutor(commander);
		CTFMain.getInstance().getCommand("AddMapa").setExecutor(commander);
		CTFMain.getInstance().getCommand("SetPronto").setExecutor(commander);
	}
}
