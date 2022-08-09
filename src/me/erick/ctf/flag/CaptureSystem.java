package me.erick.ctf.flag;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.map.CTFMap;
import me.erick.ctf.partida.Placar;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class CaptureSystem implements Listener{
	
	@EventHandler
	public void capture(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		
		if(!c.isBand() && e.getBlock().getType().equals(Material.WOOL)) {
			e.setCancelled(true);
			return;
		}
		
		Block b = e.getBlock();
		
		if(!(b.getType().equals(Material.WOOL))) return;
		
		Teams team = c.getTime();
		
		CTFMap map = CTFMain.getInstance().getMatch().getMap();
		Location l = map.getSpecificFlag(team);
		
		double distance = l.distance(b.getLocation());
		
		if(distance>3) {
			p.sendMessage(Facil.chat("&9[CTF] &cVocê precisa colocar a bandeira roubada perto da bandeira do seu time"));
			e.setCancelled(true);
			return;
		}
		
		if(map.getFlag(team).isStolen()) {
			e.setCancelled(true);
			p.sendMessage(Facil.chat("&9[CTF] &cVocê só pode capturar quando a sua bandeira não estiver roubada"));
			return;
		}
		
		l.getWorld().strikeLightningEffect(l);
		
		Facil.allPlayers("O jogador &"+c.getTime().getHexColorCode() + e.getPlayer().getName() + 
						 "&e capturou a bandeira da equipe &" + 
						 Teams.getOtherTeam(c.getTime()).getHexColorCode()+
						 Teams.getOtherTeam(c.getTime()).getTime());
		for(Player pl : Bukkit.getOnlinePlayers()) {
			pl.playSound(pl.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
		}
		map.getBlue().setStolen(false);
		map.getRed().setStolen(false);
		c.setBand(false);
		ItemStack band = p.getItemInHand();
		p.getInventory().remove(band);
		CTFMain.getInstance().getMatch().getFlagManager().bandRefresh();
		Placar placar = CTFMain.getInstance().getMatch().getPlacar();
		placar.addPontos(team);
		Facil.allPlayers("Placar: &cVermelho &f" + placar.getVermelhoPontos() + " &eX &f" + placar.getAzulPontos() + " &9Azul");
		b.setType(Material.ENDER_STONE);
		TabListManager.updateAllLists();
		Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable(){
			@Override
			public void run(){
				b.setType(Material.AIR);
			}
		}, 60L);
	}
}
