package me.erick.ctf.sponge;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class Sponge{
	
	private Location l;
	private Vector v;
	private String Name;
	
	
	
	public Sponge(World w,String nome, double... d) {
		this.l = new Location(w,d[0],d[1],d[2]);
		this.v = new Vector(d[3],d[4],d[5]);
		this.Name = nome;
	}
	
	
	
	
	
	
	
	
	
	public Location getL() {
		return l;
	}
	public Vector getV() {
		return v;
	}
	public void setL(Location l) {
		this.l = l;
	}
	public void setV(Vector v) {
		this.v = v;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
