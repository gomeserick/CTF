package me.erick.ctf.ctfer.util;

import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.utils.Facil;

public class Kill {
	
	
	public static void kill(Ctfer killer, Ctfer killed) {
		killed.setDeaths(killed.getDeaths()+1);
		killer.setKills(killer.getKills()+1);
		killer.setStreak(killer.getStreak()+1);
		killed.setStreak(0);
		killer.getPlayer().sendMessage(Facil.chat("&9[CTF] &aVocê matou: &7[" 
												  + killed.getClasse().getName()
												  +"] &" + killed.getTime().getHexColorCode()
												  +""+killed.getPlayer().getName()));
		killed.getPlayer().sendMessage(Facil.chat("&9[CTF] &bVocê foi morto por: &7[" 
				  								  + killer.getClasse().getName()
				  								  +"] &" + killer.getTime().getHexColorCode()
				  								  +""+killer.getPlayer().getName()));
		TabListManager.updateOneLists(killed);
		TabListManager.updateOneLists(killer);
	}
	
	public static void kill(Ctfer killed) {
		killed.setDeaths(killed.getDeaths()+1);
		killed.setStreak(0);
		killed.getPlayer().sendMessage(Facil.chat("&9[CTF] &cVocê se matou!"));
	}
}
