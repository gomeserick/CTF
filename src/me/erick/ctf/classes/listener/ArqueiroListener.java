package me.erick.ctf.classes.listener;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.ctfer.util.Kill;

public class ArqueiroListener implements Listener {
	
	private double hsDistance = 25;
	
	@EventHandler
	public void headShot(EntityDamageByEntityEvent e) {
		if(CTFMain.getInstance().getMatch().isFinished() 
	    ||!CTFMain.getInstance().getMatch().isStarted()) {
			e.setCancelled(true);
			return;
		}
		if(!(e.getEntity() instanceof Player)) return;
		if(!(e.getDamager() instanceof Arrow)) return;
		Player p = (Player)e.getEntity();
		Ctfer ctfP = PlayerList.instance.getCtfer(p);
		Arrow arrow = (Arrow) e.getDamager();
		ProjectileSource src = arrow.getShooter();
		if(!(src instanceof Player))return;
		Player shooter = (Player)src;
		Ctfer cShoot = PlayerList.instance.getCtfer(shooter);
		if(!(cShoot.getClasse().equals(Classes.Arqueiro))) return;
		if(cShoot.getTime().equals(ctfP.getTime())) {
			e.setCancelled(true);
			return;
		}
		Location shootedLocation = p.getLocation();
		Location shooterLocation = shooter.getLocation();
		if(shootedLocation.distance(shooterLocation)<hsDistance) return;
		p.setHealth(0);
		Kill.kill(cShoot, ctfP);
	}
}
