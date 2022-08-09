package me.erick.ctf.flag;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.bukkit.util.Vector;

import me.erick.ctf.map.CTFMap;
import me.erick.ctf.teams.Teams;


public class FlagManager {

	
	
	private CTFMap map;
	private Player redStealer;
	private Player blueStealer;
	public FlagManager(CTFMap m) {
		this.map = m;
	}
	
	public void dropFlag(Flag flag) {
		Wool wool;
		if(flag.isStolen()) {
			wool = new Wool(DyeColor.WHITE);
		} else {
			wool = new Wool(flag.getTeam().getColor());
		}
		Location l = map.getFlagLocation(flag.getTeam());
		l.setX(l.getX());
		l.setZ(l.getZ());
		l.setY(l.getY());
		ItemStack item = wool.toItemStack();
		item.setAmount(1);
		Item i = l.getWorld().dropItem(l,item);
		i.setPickupDelay(0);
		i.setVelocity(new Vector(0,0,0));
		
	}
	public void bandRefresh() {
		for(Entity e: map.getWorld().getEntities()) {
			if(e instanceof Item) {
				Item i = (Item) e;
				i.remove();
			}
		}
		dropFlag(map.getBlue());
		dropFlag(map.getRed());
	}
	/**
	 * Testa se a bandeira está no local adequado
	 * 
	 * @param flag Bandeira a ser testada
	 * @return true se a bandeira está lá
	 * 		   false se a bandeira não está lá
	 */
	public boolean flagTest(Flag flag) {
		Wool wool = new Wool(flag.getTeam().getColor());
		ItemStack item = wool.toItemStack();
		if(!flag.isStolen()) {
			Location l = map.getFlagLocation(flag.getTeam());
			for(Entity e : l.getWorld().getNearbyEntities(l, 1, 1, 1)) {
				if(e.getType().equals(EntityType.DROPPED_ITEM)) {
					Item i = (Item)e;
					if(i.getItemStack().equals(item)) {
						return true;
					}
				}
			}
		} else {
			Location l = map.getFlagLocation(flag.getTeam());
			for(Entity e : l.getWorld().getNearbyEntities(l, 1, 1, 1)) {
				if(e.getType().equals(EntityType.DROPPED_ITEM)) {
					Item i = (Item)e;
					if(i.getItemStack().getType().equals(Material.WOOL)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public CTFMap getMap() {
		return this.map;
	}

	public Player getRedStealer() {
		return redStealer;
	}

	public Player getBlueStealer() {
		return blueStealer;
	}

	public void setRedStealer(Player redStealer) {
		this.redStealer = redStealer;
	}

	public void setBlueStealer(Player blueStealer) {
		this.blueStealer = blueStealer;
	}
	public Player getStealer(Teams team) {
		return (team.equals(Teams.BLUE)) ? this.blueStealer:this.redStealer;
	}
}
