package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Incendiario extends Classe{
	public static final ItemStack[] armadura = { 
			Facil.unbreakable(Material.LEATHER_BOOTS),
			Facil.unbreakable(Material.LEATHER_LEGGINGS),
			Facil.unbreakable(Material.LEATHER_CHESTPLATE),
			Facil.unbreakable(Material.SULPHUR),
		};
	public static final ItemStack[] items = { 
			Facil.unbreakable(Material.DIAMOND_AXE),
			new ItemStack(Material.COOKED_BEEF,5),
			new ItemStack(Material.FLINT_AND_STEEL),
			Facil.unbreakable(Material.BOW),
			new ItemStack(Material.ARROW, 20)
		};
}
