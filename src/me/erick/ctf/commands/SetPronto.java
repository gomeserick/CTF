package me.erick.ctf.commands;

import org.bukkit.entity.Player;

import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.map.CTFMap;
import me.erick.ctf.map.MapList;
import me.erick.ctf.sql.MySqlManager;
import me.erick.ctf.utils.Logger;

public class SetPronto implements Command{
	
	private final String nome = "setPronto";
	private MySqlManager manager = new MySqlManager();

	@Override
	public boolean execute(Player sender, String name, String[] args) {
		if(!this.nome.equalsIgnoreCase(name)) {
			return false;
		} else {
			CTFMap map = MapList.getInstance().getAllMaps().get(sender.getWorld().getName());
			map.setReady(map.isReady() ? false : true);
			Logger.sendLog("A conclusão do mapa " + map.getName() + " foi definida como " + map.isReady());
			manager.updateMap(map);
		}
		return false;
	}
	
}
