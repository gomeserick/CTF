package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Guerreiro extends Classe{
	public final static ItemStack[] armadura = { 
		Facil.unbreakable(Material.DIAMOND_BOOTS),
		Facil.unbreakable(Material.DIAMOND_LEGGINGS),
		Facil.unbreakable(Material.DIAMOND_CHESTPLATE),
		Facil.unbreakable(Material.DIAMOND_HELMET),
		};
	public final static ItemStack[] items = { 
		Facil.unbreakable(Material.DIAMOND_SWORD),
		new ItemStack(Material.COOKED_BEEF,3)
	};
}
