package me.erick.ctf.commands;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.erick.ctf.commands.interfaces.Command;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.map.CTFMap;
import me.erick.ctf.map.CTFMap.ValidSpawnBlocks;
import me.erick.ctf.sql.MySqlManager;
import me.erick.ctf.sql.MySqlTables;
import me.erick.ctf.utils.Facil;
import me.erick.ctf.utils.Logger;

public class Setar implements Command{

	private final String nome = "setar";
	private MySqlManager manager;
	@Override
	public boolean execute(Player sender, String name, String[] args) {
		
		if(!this.nome.equalsIgnoreCase(name)) return false;
		if(args.length==0) {
			sender.sendMessage(Facil.chat("&3[CTF] &cuso: /Setar [band/spawn] [azul/vermelho]"));
			return false;
		}
		manager = new MySqlManager();
		CTFMap map = CTFMain.getInstance().getMapList().getAllMaps().get(sender.getWorld().getName());
		Location l = sender.getLocation();
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Block b = sender.getTargetBlock((Set)null, 5);
		if(args.length==1) {
			int x = (int) l.getX();
			int y = (int) l.getY();
			int z = (int) l.getZ();
			if(args[0].equalsIgnoreCase("spawn")) {
				manager.updateData(MySqlTables.SPAWN, map.getId(), l.getX(),l.getY(),l.getZ(),l.getYaw());
				map.setPreMatchSpawn(l);
				Logger.sendLog("O jogador "+sender.getName()+" setou o spawn do mapa "+map.getName()+" para " +x+" "+y+" "+z);
				sender.sendMessage(Facil.chat("&3[CTF] &6Você setou o spawn do mapa "+map.getName()+" para " +x+" "+y+" "+z));
				manager.close();
				return true;
			} else {
				sender.sendMessage(Facil.chat("&3[CTF] &cuso: /Setar [band/spawn] [azul/vermelho]"));
				manager.close();
				return false;
			}
		}
		if(args.length==2) {
			int x = (int) l.getX();
			int y = (int) l.getY();
			int z = (int) l.getZ();
			Location bl = new Location(sender.getWorld(),x,(y-1),z);
			
			if(args[0].equalsIgnoreCase("Spawn")) {
				b = sender.getWorld().getBlockAt(bl);
				Material m = b.getType();
				int ID = ValidSpawnBlocks.getIDByBlock(m);
				if(args[1].equalsIgnoreCase("Azul")) {
					manager.updateData(MySqlTables.BLUE_SPAWN, map.getId(), l.getX(),l.getY(),l.getZ(),l.getYaw(),ID);
					map.setBlueSpawn(l);
					Logger.sendLog("O jogador "+sender.getName()+" setou o spawn da equipe &9Azul &6para " +x+" "+y+" "+z);
					sender.sendMessage(Facil.chat("&3[CTF] &6Você setou o spawn da equipe &9azul &6para " +x+" "+y+" "+z));
					manager.close();
					return true;
				}else if(args[1].equalsIgnoreCase("Vermelho")) {
					manager.updateData(MySqlTables.RED_SPAWN, map.getId(), l.getX(),l.getY(),l.getZ(),l.getYaw(),ID);
					map.setRedSpawn(l);
					Logger.sendLog("O jogador "+sender.getName()+" setou o spawn da equipe &cVermelha &6para " +x+" "+y+" "+z);
					sender.sendMessage(Facil.chat("&3[CTF] &6Você setou o spawn da equipe &cVermelha &6para " +x+" "+y+" "+z));
					manager.close();
					return true;
				}
			} else if(args[0].equalsIgnoreCase("Band")) {
				l = b.getLocation();
				x = (int) l.getX();
				y = (int) l.getY();
				z = (int) l.getZ();
				if(args[1].equalsIgnoreCase("Azul")) {
					manager.updateData(MySqlTables.BLUE_FLAG, map.getId(), (l.getX()),(l.getY()+2),(l.getZ()));
					map.setBlueFlag(l);
					Logger.sendLog("O jogador "+sender.getName()+" setou a bandeira da equipe &9Azul &6para " +x+" "+y+" "+z);
					sender.sendMessage(Facil.chat("&3[CTF] &6Você setou a bandeira da equipe &9azul &6para " +x+" "+y+" "+z));
					manager.close();
					return true;
				}else if(args[1].equalsIgnoreCase("Vermelho")) {
					manager.updateData(MySqlTables.RED_FLAG, map.getId(), (l.getX()+0.5),(l.getY()+2),(l.getZ()+0.5));
					map.setRedFlag(l);
					Logger.sendLog("O jogador "+sender.getName()+" setou a bandeira da equipe &cVermelha &6para " +x+" "+y+" "+z);
					sender.sendMessage(Facil.chat("&3[CTF] &6Você setou a bandeira da equipe &cVermelha &6para " +x+" "+y+" "+z));
					manager.close();
					return true;
				}
			} else {
				sender.sendMessage(Facil.chat("&3[CTF] &cuso: /Setar [band/spawn] [azul/vermelho]"));
				manager.close();
				return false;
			}
		}
		manager.close();
		return false;
	}

}
