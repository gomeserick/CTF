package me.erick.ctf.tablist;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.map.CTFMap;
import me.erick.ctf.partida.Match;
import me.erick.ctf.partida.Placar;
import me.erick.ctf.teams.Teams;

public class TabListManager {
	
	private static ArrayList<TabListManager> list = new ArrayList<>();
	private TabList playerList;
	private Player player;
	
	public TabListManager(Player player) {
		playerList = new TabList(player,TabList.SIZE_FOUR);
		PlayerList.instance.getCtfer(player).setTabListManager(this);
		playerList.initTable();
		this.player = player;
		list.add(this);
	}
	
	public static void clear() {
		for(TabListManager m : list) {
			m.playerList.clearCustomTabs();
		}
	}

	public static ArrayList<TabListManager> getLists() {
		return list;
	}

	public static void setLists(ArrayList<TabListManager> lists) {
		TabListManager.list = lists;
	}
	
	public static void updateAllLists() {
		for(TabListManager t: list) {
			Ctfer c = PlayerList.instance.getCtfer(t.player);
			updateOneLists(c);
		}
	}
	public static void updateOneLists(Ctfer c) {
		if(!CTFMain.getInstance().getMatch().isStarted()) {
			return;
		}
			TabListManager tl = c.getTabListManager();
			Match match = CTFMain.getInstance().getMatch();
			Placar placar = match.getPlacar();
			CTFMap map = match.getMap();
			StringBuilder red = new StringBuilder(ChatColor.RED+""
												  +placar.getVermelhoPontos()+"/3 > ");
			StringBuilder blue = new StringBuilder(ChatColor.BLUE+""
					  +placar.getAzulPontos()+"/3 > ");
			if(map.getBlue().isStolen()) {
				String name = CTFMain.getInstance().getMatch().getFlagManager().getBlueStealer().getName();
				if(name.length()>8) {
					String subName = name.substring(0, 7);
					red.append(ChatColor.BLUE+subName);
				} else {
					red.append(ChatColor.BLUE+name);
				}
			} else {
				red.append(ChatColor.WHITE+"Base");
			}
			if(map.getRed().isStolen()) {
				String name = CTFMain.getInstance().getMatch().getFlagManager().getRedStealer().getName();
				if(name.length()>8) {
					String subName = name.substring(0, 7);
					blue.append(ChatColor.RED+subName);
				} else {
					blue.append(ChatColor.RED+name);
				}
			} else {
				blue.append(ChatColor.WHITE+"Base");
			}
			tl.playerList.updateSlot(0,c.getTime().getChatColor() + tl.player.getName());
			tl.playerList.updateSlot(1,ChatColor.GOLD+"CTF");
			tl.playerList.updateSlot(2,ChatColor.GOLD+"=== Equipe ===");
			tl.playerList.updateSlot(3,ChatColor.RED+"Vermelho");
			tl.playerList.updateSlot(4,ChatColor.BLUE+"Azul");
			tl.playerList.updateSlot(20, ChatColor.GOLD+"=== Matou ===");
			tl.playerList.updateSlot(21, c.getKills()+" (" + c.getStreak() + ")");
			tl.playerList.updateSlot(22, ChatColor.GOLD+"=== Pontos ===");
			tl.playerList.updateSlot(23, red.toString());
			tl.playerList.updateSlot(24, blue.toString());
			tl.playerList.updateSlot(40, ChatColor.GOLD+"=== Morreu ===");
			tl.playerList.updateSlot(41, c.getDeaths()+"");
			tl.playerList.updateSlot(42, ChatColor.GOLD+"=== Online ===");
			tl.playerList.updateSlot(43, ChatColor.RED+""
								  +match.getTeamManager().getRedQuant()+"/50");
			tl.playerList.updateSlot(44, ChatColor.BLUE+""
					  +match.getTeamManager().getBlueQuant()+"/50");
			if(c.getTime().equals(Teams.BLUE)) {
				tl.playerList.updateSlot(60, ChatColor.RED+"=== Vermelho ===");
			} else {
				tl.playerList.updateSlot(60, ChatColor.BLUE+"=== AZUL ===");
			}
			tl.updatePlayer(tl.playerList);
	}
	public void updatePlayer(TabList tab) {
		int i = 0;
		for(Player p : Bukkit.getOnlinePlayers()) {
			Ctfer c1 = PlayerList.instance.getCtfer(p);
			Ctfer c2 = PlayerList.instance.getCtfer(this.player);
			if(c1.getTime().equals(c2.getTime())&&!c2.equals(c1)) {
				if(i%20>4) {
					tab.updateSlot(i, c1.getTime().getChatColor()+""+p.getName());
				} else {
					i+=5;
					tab.updateSlot(i, c1.getTime().getChatColor()+""+p.getName());
				}
					i++;
			}
		}
		i = 61;
		for(Player p : Bukkit.getOnlinePlayers()) {
			Ctfer c1 = PlayerList.instance.getCtfer(p);
			Ctfer c2 = PlayerList.instance.getCtfer(this.player);
			if(!c1.getTime().equals(c2.getTime())) {
				tab.updateSlot(i, c1.getTime().getChatColor()+""+p.getName());
				i++;
			}
		}
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public static void removeFromList(Player p) {
		for(TabListManager t: list) {
			if(t.getPlayer().equals(p)) {
				list.remove(t);
				break;
			}
		}
	}

	public TabList getPlayerList() {
		return playerList;
	}

	public void setPlayerList(TabList playerList) {
		this.playerList = playerList;
	}
	
	
	
	
	
	
	
}
