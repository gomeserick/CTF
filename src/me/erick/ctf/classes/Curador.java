package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Curador extends Classe{
	
	
	
	public static final ItemStack[] armadura = { 
			Facil.unbreakable(Material.GOLD_BOOTS),
			Facil.unbreakable(Material.GOLD_LEGGINGS),
			Facil.unbreakable(Material.GOLD_CHESTPLATE),
			Facil.unbreakable(Material.GOLD_HELMET),
		};
	public static final ItemStack[] items = { 
			Facil.unbreakable(Material.GOLD_SWORD),
			new ItemStack(Material.COOKED_BEEF,6),
			new ItemStack(Material.WEB, 10)
		};
}
