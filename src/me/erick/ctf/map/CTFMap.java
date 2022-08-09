package me.erick.ctf.map;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import me.erick.ctf.flag.Flag;
import me.erick.ctf.sql.MySqlManager;
import me.erick.ctf.sql.MySqlTables;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Logger;

public class CTFMap {
	
	
	//Define os spawns
	protected Location preMatchSpawn;
	protected Location blueSpawn;
	protected Location redSpawn;
	//Define os blocos do spawn
	protected Material blueBlock;
	protected Material redBlock;
	//Define as bandeiras
	protected Location blueFlag;
	protected Location redFlag;
	//Define se o mapa está pronto ou não
	protected boolean ready;
	
	protected Flag blue;
	protected Flag red;
	
	private World world;
	private String name;
	private int id;
	private int temporaryID;
	
	public Location getSpecificFlag(Teams team) {
		if(team.equals(Teams.RED)) {
			return redFlag;
		} else {
			return blueFlag;
		}
	}
	
	public CTFMap(World w) {
		this.world = w;
		this.name = w.getName();
	}
	
	public CTFMap(int id, String nome, boolean pronto) {
		MySqlManager manager = new MySqlManager();
		this.id = id;
		this.name = nome;
		Logger.sendLog("Tentando inicializar o mapa " + nome);
		WorldCreator wc = new WorldCreator(nome);
		this.world = Bukkit.createWorld(wc);
		this.ready = pronto;
		ResultSet[] results = {
							   manager.loadData(MySqlTables.BLUE_FLAG, id),
							   manager.loadData(MySqlTables.BLUE_SPAWN, id),
							   manager.loadData(MySqlTables.SPAWN, id),
							   manager.loadData(MySqlTables.RED_SPAWN, id),
							   manager.loadData(MySqlTables.RED_FLAG, id)
							   };
		if(this.ready) {
			try {
				
				for (int i = 0; i < results.length; i++) {
					ResultSet result = results[i];
					if(result != null && result.next()) {
						switch(i) {
						case 0:
							this.blueFlag = new Location(
								this.world,
								result.getDouble(1),
								result.getDouble(2),
								result.getDouble(3));
							break;
						case 1:
							this.blueSpawn = new Location(
								this.world,
								result.getDouble(1),
								result.getDouble(2),
								result.getDouble(3),
								(float) result.getDouble(4),
								0f);
							this.blueBlock = ValidSpawnBlocks.getBlockByID(result.getInt("bloco")).getBloco();
							break;
						case 2:
							this.preMatchSpawn = new Location(
								this.world,
								result.getDouble(1),
								result.getDouble(2),
								result.getDouble(3),
								(float) result.getDouble(4),
								0f);
							break;
						case 3:
							this.redSpawn = new Location(
								this.world,
								result.getDouble(1),
								result.getDouble(2),
								result.getDouble(3),
								(float) result.getDouble(4),
								0f);
							this.redBlock = ValidSpawnBlocks.getBlockByID(result.getInt("Bloco")).getBloco();
							break;
						case 4:
							this.redFlag = new Location(
								this.world,
								result.getDouble(1),
								result.getDouble(2),
								result.getDouble(3));
							break;
						}
					}
				}
				manager.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			this.red = new Flag(Teams.RED);
			this.blue = new Flag(Teams.BLUE);
			this.red.setStolen(false);
			this.blue.setStolen(false);
		}
		MapList.getInstance().addMap(this);
	}
	
	
	
	public CTFMap(World w, Location blueFlag,
						   Location blueSpawn,
						   Location preMatchSpawn,
						   Location redSpawn,
						   Location redFlag,
						   Material blueBlock,
						   Material redBlock,
						   int id) {
		this.world = w;
		this.name = w.getName();
		this.preMatchSpawn = preMatchSpawn;
		this.blueSpawn = blueSpawn;
		this.redSpawn = redSpawn;
		this.blueFlag = blueFlag;
		this.redFlag = redFlag;
		this.blueBlock = blueBlock;
		this.redBlock = redBlock;
		this.ready = true;
		this.setId(id);
		this.red = new Flag(Teams.RED);
		this.blue = new Flag(Teams.BLUE);
		this.red.setStolen(false);
		this.blue.setStolen(false);
	}
	
	public Location getPreMatchSpawn() {
		return preMatchSpawn;
	}
	public Location getBlueSpawn() {
		return blueSpawn;
	}
	public Location getRedSpawn() {
		return redSpawn;
	}
	public Location getBlueFlag() {
		return blueFlag;
	}
	public Location getRedFlag() {
		return redFlag;
	}
	public boolean isReady() {
		return ready;
	}
	public World getWorld() {
		return world;
	}
	public void setPreMatchSpawn(Location preMatchSpawn) {
		this.preMatchSpawn = preMatchSpawn;
	}
	public void setBlueSpawn(Location blueSpawn) {
		this.blueSpawn = blueSpawn;
	}
	public void setRedSpawn(Location redSpawn) {
		this.redSpawn = redSpawn;
	}
	public void setBlueFlag(Location blueFlag) {
		this.blueFlag = blueFlag;
	}
	public void setRedFlag(Location redFlag) {
		this.redFlag = redFlag;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Material getBlueBlock() {
		return blueBlock;
	}

	public Material getRedBlock() {
		return redBlock;
	}

	public void setBlueBlock(Material blueBlock) {
		this.blueBlock = blueBlock;
	}

	public void setRedBlock(Material redBlock) {
		this.redBlock = redBlock;
	}

	public Flag getBlue() {
		return blue;
	}

	public Flag getRed() {
		return red;
	}

	public void setBlue(Flag blue) {
		this.blue = blue;
	}

	public void setRed(Flag red) {
		this.red = red;
	}
	public Flag getFlag(Teams team) {
		if(team.equals(Teams.RED)) {
			return this.red;
		} else {
			return this.blue;
		}
	}
	public Location getFlagLocation(Teams team) {
		if(team.equals(Teams.RED)) {
			return this.redFlag;
		} else {
			return this.blueFlag;
		}
	}
	public Location getSpawn(Teams team) {
		if(team.equals(Teams.RED)) {
			return this.redSpawn;
		} else {
			return this.blueSpawn;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTemporaryID() {
		return temporaryID;
	}

	public void setTemporaryID(int temporaryID) {
		this.temporaryID = temporaryID;
	}	
	
	public enum ValidSpawnBlocks{
		LAPIS(Material.LAPIS_BLOCK,1),
		NETHERRACK(Material.NETHERRACK,2),
		REDSTONE_BLOCK(Material.REDSTONE_BLOCK,3);
		
		private Material bloco;
		private int ID;
		
		ValidSpawnBlocks(Material m,int i) {
			this.bloco = m;
			this.ID = i;
		}

		public Material getBloco() {
			return bloco;
		}
		public int getID() {
			return ID;
		}
		
		public static ValidSpawnBlocks getBlockByID(int i) {
			switch(i) {
			case 1:
				return LAPIS;
			case 2:
				return NETHERRACK;
			case 3:
				return REDSTONE_BLOCK;
			default:
				return null;
			}
		}
		public static int getIDByBlock(Material m) {
			switch(m) {
			case LAPIS_BLOCK:
				return 1;
			case NETHERRACK:
				return 2;
			case REDSTONE_BLOCK:
				return 3;
			default:
				return -1;
			}
		}
		
	}
}




