package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;


public class Ninja extends Classe{
	public static final ItemStack[] armadura = {
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR),
			new ItemStack(Material.AIR)
	};
	public static final ItemStack[] items = {
			Facil.enchant(
					Facil.unbreakable(
							Material.IRON_SWORD), 
					Enchantment.DAMAGE_ALL, 4
			),
			new ItemStack(Material.ENDER_PEARL, 10),
			new ItemStack(Material.REDSTONE, 64),
			new ItemStack(Material.COOKED_BEEF, 4),
			new ItemStack(Material.EGG, 10)
	};
}
