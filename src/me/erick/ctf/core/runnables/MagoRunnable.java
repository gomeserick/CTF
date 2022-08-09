package me.erick.ctf.core.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.erick.ctf.classes.Classes;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.Magias;
import me.erick.ctf.ctfer.PlayerList;

public class MagoRunnable implements IRun{
	
	public static void execute() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Ctfer c = PlayerList.instance.getCtfer(p);
					if(!c.getClasse().equals(Classes.Mago)) continue;
					Material m = p.getItemInHand().getType();
					float xpDano = 0f;
					float xpFogo = 0f;
					float xpRaio = 0f;
					float xpGelo = 0f;
					float xpCura = 0f;
					float xpTP = 0f;
					StringBuilder sb = new StringBuilder();
					if(!c.isMagiaDano()){
							xpDano = (float)(Magias.MAGIA_DANO.getCooldown()-c.getDanoCD())/Magias.MAGIA_DANO.getCooldown();
							c.setDanoCD(c.getDanoCD()-1);
							sb.append("&0█   ");
						} else {
							xpDano = 0.9999999f;
							sb.append("&5█   ");
						}
					if(!c.isMagiaFogo()){
							xpFogo = (float)(Magias.MAGIA_FOGO.getCooldown()-c.getFogoCD())/Magias.MAGIA_FOGO.getCooldown();
							c.setFogoCD(c.getFogoCD()-1);
							sb.append("&0█   ");
						} else {
							xpFogo = 0.9999999f;
							sb.append("&c█   ");
						}
					if(!c.isMagiaRaio()){
							xpRaio= (float)(Magias.MAGIA_RAIO.getCooldown()-c.getRaioCD())/Magias.MAGIA_RAIO.getCooldown();
							c.setRaioCD(c.getRaioCD()-1);
							sb.append("&0█   ");
						} else {
							xpRaio= 0.9999999f;
							sb.append("&7█   ");
						}
					if(!c.isMagiaGelo()){
							xpGelo= (float)(Magias.MAGIA_GELO.getCooldown()-c.getGeloCD())/Magias.MAGIA_GELO.getCooldown();
							c.setGeloCD(c.getGeloCD()-1);
							sb.append("&0█   ");
						} else {
							xpGelo= 0.9999999f;
							sb.append("&f█   ");
						}
					if(!c.isMagiaCura()){
							xpCura= (float)(Magias.MAGIA_CURA.getCooldown()-c.getCuraCD())/Magias.MAGIA_CURA.getCooldown();
							c.setCuraCD(c.getCuraCD()-1);
							sb.append("&0█   ");
						} else {
							xpCura= 0.9999999f;
							sb.append("&e█   ");
						}
					if(!c.isMagiaTp()){
							xpTP= (float)(Magias.MAGIA_TP.getCooldown()-c.getTpCD())/Magias.MAGIA_TP.getCooldown();
							c.setTpCD(c.getTpCD()-1);
							sb.append("&0█   ");
						} else {
							xpTP= 0.9999999f;
							sb.append("&6█   ");
						}
					c.sendMessage(sb.toString());
					switch(m) {
					case DIAMOND_HOE:
						p.setExp(xpDano);
						break;
					case WOOD_HOE:
						p.setExp(xpFogo);
						break;
					case STONE_HOE:
						p.setExp(xpRaio);
						break;
					case IRON_HOE:
						p.setExp(xpGelo);
						break;
					case GOLD_HOE:
						p.setExp(xpCura);
						break;
					case BLAZE_ROD:
						p.setExp(xpTP);
						break;
					default:
						p.setExp(0);
						break;
					}
				}
			}
		}, 0, 1);
	}
}
