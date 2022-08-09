package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Soldado extends Classe{
	public static final ItemStack[] armadura = { 
			Facil.unbreakable(Material.IRON_BOOTS),
			Facil.unbreakable(Material.IRON_LEGGINGS),
			Facil.unbreakable(Material.IRON_CHESTPLATE),
			Facil.unbreakable(Material.IRON_HELMET),
		};
	public static final ItemStack[] items = { 
			Facil.unbreakable(Material.IRON_SWORD),
			new ItemStack(Material.COOKED_BEEF,4)
		};
}
