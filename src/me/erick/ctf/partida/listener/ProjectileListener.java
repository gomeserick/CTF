package me.erick.ctf.partida.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;

public class ProjectileListener implements Listener{
	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		if(CTFMain.getInstance().getMatch().isFinished() 
		||!CTFMain.getInstance().getMatch().isStarted()) return;
		
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getDamager() instanceof Arrow)) return;
		Player p = (Player)e.getEntity();
		Ctfer ctfP = PlayerList.instance.getCtfer(p);
		Arrow arrow = (Arrow) e.getDamager();
		ProjectileSource src = arrow.getShooter();
		if(!(src instanceof Player))return;
		Player shooter = (Player)src;
		Ctfer cShoot = PlayerList.instance.getCtfer(shooter);
		if(ctfP.getTime()==cShoot.getTime()) {
			e.setCancelled(true);
			return;
		}	
	}
	
}
