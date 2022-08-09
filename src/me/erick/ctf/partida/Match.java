package me.erick.ctf.partida;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.core.runnables.Compass;
import me.erick.ctf.core.runnables.MagoRunnable;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.flag.FlagManager;
import me.erick.ctf.map.CTFMap;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.teams.TeamManager;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class Match {
	
	private Placar placar = new Placar();
	private CTFMap map;
	private TeamManager teamManager= new TeamManager();
	private boolean finished = false;
	private boolean started = false;
	private int countDown = 10;
	private int delay = 30;
	private FlagManager flagManager;
	private Teams winner = null;
	public Match(CTFMap map) {
		this.map = map;
		flagManager = new FlagManager(map);
		preMatch();
	}
	
	public void preMatch() {
		int playerCount = PlayerList.instance.getTotalPlayers();
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			@Override
			public void run() {
				if(started) return;
				if(!started) {
					if(playerCount<4) {
						if(delay<=0) {
							Facil.allPlayers("Aguardando os jogadores entrarem na partida");
							delay = 30;
						} else {
							delay--;
						}
					
					} else {
						if(countDown<=0) {
							start();
							return;
						} else {
							Facil.allPlayers("A Partida iniciará em " + countDown + " segundos");
						}
					}
				}
				for(Entity e : map.getWorld().getEntities()) {
					if(e instanceof Item){
						e.remove();
					}
				}
			}
		},0l,20l);
	}
	
	public void start() {
		
		started = true;
		Facil.allPlayers("A partida iniciou!");
		flagManager.dropFlag(map.getBlue());
		flagManager.dropFlag(map.getRed());
		Compass.execute();
		MagoRunnable.execute();
		for(Player p : Bukkit.getOnlinePlayers()) {
			Ctfer c = PlayerList.instance.getCtfer(p);
			c.setItems(c.getClasse());
			p.setBedSpawnLocation(map.getSpawn(c.getTime()),true);
			p.teleport(map.getSpawn(c.getTime()));
			
		}
		TabListManager.updateAllLists();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if(finished) return;
				if(placar.acabou()) {
					map.getWorld().strikeLightningEffect(map.getFlagLocation(winner));
					map.getWorld().strikeLightningEffect(map.getFlagLocation(winner));
					map.getWorld().strikeLightningEffect(map.getFlagLocation(winner));
					map.getWorld().strikeLightningEffect(map.getFlagLocation(winner));
					map.getWorld().strikeLightningEffect(map.getFlagLocation(winner));
					posMatch();
					return;
				}
				
			}
		}, 0l, 1l);
	}
	public void posMatch() {
		finished = true;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CTFMain.getInstance(), new Runnable() {
			int countDown = 60;
			Location l = map.getFlagLocation(winner);
			@Override
			public void run() {
				countDown--;
				if(countDown%10==0) {
					Facil.allPlayers(Facil.chat("Server fechando em " + countDown + " segundos"));
				} else if(countDown<10) {
					Facil.allPlayers(Facil.chat("Server fechando em " + countDown + " segundos"));
				}
				if(countDown==0) {
					Bukkit.shutdown();
				}
				Firework fw = l.getWorld().spawn(l, Firework.class);
				FireworkMeta fwm = fw.getFireworkMeta();
				Builder builder = FireworkEffect.builder();
				if(winner.equals(Teams.BLUE)) fwm.addEffect(builder.withColor(Color.BLUE, Color.FUCHSIA).withFlicker().build());
				else fwm.addEffect(builder.withColor(Color.RED, Color.ORANGE).withFlicker().build());
				fwm.setPower(1);
				fw.setFireworkMeta(fwm);
			}
		}, 20l, 20l);
	}
	
	public Placar getPlacar() {
		return placar;
	}

	public void setPlacar(Placar placar) {
		this.placar = placar;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isStarted() {
		return started;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public CTFMap getMap() {
		return map;
	}

	public void setMap(CTFMap map) {
		this.map = map;
	}



	public Teams getWinner() {
		return winner;
	}

	public void setWinner(Teams winner) {
		this.winner = winner;
	}

	public TeamManager getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(TeamManager teamManager) {
		this.teamManager = teamManager;
	}

	public FlagManager getFlagManager() {
		return flagManager;
	}

	public void setFlagManager(FlagManager flagManager) {
		this.flagManager = flagManager;
	}
	
	
}
