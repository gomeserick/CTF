package me.erick.ctf.core.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.flag.Flag;
import me.erick.ctf.map.CTFMap;

public class Compass implements IRun{
	public static void execute() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			@Override
			public void run() {
				CTFMap map = CTFMain.getInstance().getMatch().getMap();;
				Flag flag;
				for(Player p : Bukkit.getOnlinePlayers()) {
					Ctfer c = PlayerList.instance.getCtfer(p);
					flag = map.getFlag(c.getTime());
					if(flag.isStolen()) {
						p.setCompassTarget(CTFMain.getInstance().getMatch().getFlagManager().getStealer(flag.getTeam()).getLocation());
					} else {
						p.setCompassTarget(map.getFlagLocation(c.getTime()));
					}
				}
			}
		}, 5, 5);
	}
}
