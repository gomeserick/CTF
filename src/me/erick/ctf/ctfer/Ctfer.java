package me.erick.ctf.ctfer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.erick.ctf.classes.Arqueiro;
import me.erick.ctf.classes.Assassino;
import me.erick.ctf.classes.Classes;
import me.erick.ctf.classes.Curador;
import me.erick.ctf.classes.Guerreiro;
import me.erick.ctf.classes.Incendiario;
import me.erick.ctf.classes.Ladrao;
import me.erick.ctf.classes.Mago;
import me.erick.ctf.classes.Ninja;
import me.erick.ctf.classes.Quimico;
import me.erick.ctf.classes.Soldado;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.tablist.TabListManager;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;
import me.erick.ctf.utils.Logger;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Ctfer {
	private Player player;
	private int kills;
	private int deaths;
	private int streak;
	private Classes classe;
	private Teams time;
	private boolean band;
	private Location spawnLocation;
	private boolean onSponge;
	private boolean invisivel;
	private boolean magiaDano;
	private boolean magiaFogo;
	private boolean magiaRaio;
	private boolean magiaGelo;
	private boolean magiaCura;
	private boolean magiaTp;
	private int danoCD=0;
	private int fogoCD=0;
	private int raioCD=0;
	private int geloCD=0;
	private int curaCD=0;
	private int tpCD=0;
	private TabListManager tabListManager;
	
	public Ctfer(Player p) {
		this.player = p;
		kills=0;
		deaths=0;
		streak = 0;
		classe = Classes.Guerreiro;
		band = false;
		this.invisivel=false;
		this.magiaDano = true;
		this.magiaFogo = true;
		this.magiaRaio=true;
		this.magiaGelo= true;
		this.magiaCura=true;
		this.magiaTp=true;
		setOnSponge(false);
		Logger.sendLog("Ctfer " + p.getName() + " Registrado com sucesso!");
	}
	
	
	public Player getPlayer() {
		return player;
	}
	public int getKills() {
		return kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public int getStreak() {
		return streak;
	}
	public Classes getClasse() {
		return classe;
	}
	public Teams getTime() {
		return time;
	}
	public boolean isBand() {
		return band;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public void setStreak(int streak) {
		this.streak = streak;
	}
	public void setClasse(Classes classe) {
		this.classe = classe;
	}
	public void setTime(Teams time) {
		this.time = time;
	}
	public void setBand(boolean band) {
		this.band = band;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}


	public Location getSpawnLocation() {
		return spawnLocation;
	}


	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}
	
	public void classChange(Classes classe) {
		
		this.classe = classe;
		if(CTFMain.getInstance().getMatch().isStarted()&&!CTFMain.getInstance().getMatch().isFinished()) {
			setItems(classe);
			this.player.sendMessage(Facil.chat("&3[CTF] &eSua classe agora é: "+classe.getName()));
		} else if(!CTFMain.getInstance().getMatch().isFinished()){
			this.player.sendMessage(Facil.chat("&3[CTF] &eVocê iniciará a partida com a classe: "+ classe.getName()));
		}
		
	}
	
	public void setItems(Classes classe) {
		PlayerInventory pinv = this.player.getInventory();
		Inventory inv = this.player.getInventory();
		pinv.clear();
		pinv.setItem(8, new ItemStack(Material.COMPASS));
		if(classe.equals(Classes.Guerreiro)) {
			pinv.setArmorContents(Guerreiro.armadura);
			
			for(ItemStack item : Guerreiro.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Arqueiro)) {
			pinv.setArmorContents(Arqueiro.armadura);
			for(ItemStack item : Arqueiro.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Curador)) {
			pinv.setArmorContents(Curador.armadura);
			for(ItemStack item : Curador.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Soldado)) {
			pinv.setArmorContents(Soldado.armadura);
			for(ItemStack item : Soldado.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Incendiario)) {
			pinv.setArmorContents(Incendiario.armadura);
			for(ItemStack item : Incendiario.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Mago)) {
			pinv.setArmorContents(Mago.armadura);
			for(ItemStack item : Mago.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Ninja)) {
			pinv.setArmorContents(Ninja.armadura);
			for(ItemStack item : Ninja.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Ladrao)) {
			pinv.setArmorContents(Ladrao.armadura);
			for(ItemStack item : Ladrao.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Quimico)) {
			pinv.setArmorContents(Quimico.armadura);
			for(ItemStack item : Quimico.items) {
				inv.addItem(item);
			}
		}
		if(classe.equals(Classes.Assassino)) {
			pinv.setArmorContents(Assassino.armadura);
			for(ItemStack item : Assassino.items) {
				inv.addItem(item);
			}
		}
		
	}


	public boolean isOnSponge() {
		return onSponge;
	}


	public void setOnSponge(boolean onSpoonge) {
		this.onSponge = onSpoonge;
	}


	public boolean isInvisivel() {
		return invisivel;
	}


	public void setInvisivel(boolean invisivel) {
		this.invisivel = invisivel;
	}


	public boolean isMagiaDano() {
		return magiaDano;
	}


	public boolean isMagiaFogo() {
		return magiaFogo;
	}


	public boolean isMagiaRaio() {
		return magiaRaio;
	}


	public boolean isMagiaGelo() {
		return magiaGelo;
	}


	public boolean isMagiaCura() {
		return magiaCura;
	}


	public boolean isMagiaTp() {
		return magiaTp;
	}


	public void setMagiaDano(boolean magiaDano) {
		this.magiaDano = magiaDano;
	}


	public void setMagiaFogo(boolean magiaFogo) {
		this.magiaFogo = magiaFogo;
	}


	public void setMagiaRaio(boolean magiaRaio) {
		this.magiaRaio = magiaRaio;
	}


	public void setMagiaGelo(boolean magiaGelo) {
		this.magiaGelo = magiaGelo;
	}


	public void setMagiaCura(boolean magiaCura) {
		this.magiaCura = magiaCura;
	}


	public void setMagiaTp(boolean magiaTp) {
		this.magiaTp = magiaTp;
	}
	
	public void useMagic(Magias magia) {
		switch(magia) {
		case MAGIA_DANO:
			this.magiaDano=false;
			this.danoCD=Magias.MAGIA_DANO.getCooldown();
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					magiaDano=true;
				}
			}, Magias.MAGIA_DANO.getCooldown());
			break;
		case MAGIA_FOGO:
			this.magiaFogo=false;
			this.fogoCD=Magias.MAGIA_FOGO.getCooldown();
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					magiaFogo=true;
				}
			}, Magias.MAGIA_FOGO.getCooldown());
			break;
		case MAGIA_RAIO:
			this.magiaRaio=false;
			this.raioCD=Magias.MAGIA_RAIO.getCooldown();
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					magiaRaio=true;
				}
			}, Magias.MAGIA_RAIO.getCooldown());
			break;
		case MAGIA_GELO:
			this.magiaGelo=false;
			this.geloCD=Magias.MAGIA_GELO.getCooldown();
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					magiaGelo=true;
				}
			}, Magias.MAGIA_GELO.getCooldown());
			break;
		case MAGIA_CURA:
			this.magiaCura=false;
			this.curaCD=Magias.MAGIA_CURA.getCooldown();
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					magiaCura=true;
				}
			}, Magias.MAGIA_CURA.getCooldown());
			break;
		case MAGIA_TP:
			this.magiaTp=false;
			this.tpCD=Magias.MAGIA_TP.getCooldown();
			Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable() {
				@Override
				public void run() {
					magiaTp=true;
				}
			}, Magias.MAGIA_TP.getCooldown());
			break;
		}
	}


	public int getDanoCD() {
		return danoCD;
	}


	public int getFogoCD() {
		return fogoCD;
	}


	public int getRaioCD() {
		return raioCD;
	}


	public int getGeloCD() {
		return geloCD;
	}


	public int getCuraCD() {
		return curaCD;
	}


	public int getTpCD() {
		return tpCD;
	}


	public void setDanoCD(int danoCD) {
		this.danoCD = danoCD;
	}


	public void setFogoCD(int fogoCD) {
		this.fogoCD = fogoCD;
	}


	public void setRaioCD(int raioCD) {
		this.raioCD = raioCD;
	}


	public void setGeloCD(int geloCD) {
		this.geloCD = geloCD;
	}


	public void setCuraCD(int curaCD) {
		this.curaCD = curaCD;
	}


	public void setTpCD(int tpCD) {
		this.tpCD = tpCD;
	}
	
	public void sendMessage(String message) {
		 PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
		 IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Facil.chat(message) + "\"}");
		 PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
		 con.sendPacket(packet);
		 }


	public TabListManager getTabListManager() {
		return tabListManager;
	}


	public void setTabListManager(TabListManager tabListManager) {
		this.tabListManager = tabListManager;
	}
	
}
