package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Assassino extends Classe{
	public static final ItemStack[] armadura = { 
		Facil.unbreakable(Material.GOLD_BOOTS),
		new ItemStack(Material.AIR),
		new ItemStack(Material.AIR),
		new ItemStack(Material.AIR)
	};
	public static final ItemStack[] items = {
		Facil.enchant(
				Facil.unbreakable(Material.IRON_SWORD),
				Enchantment.DAMAGE_ALL, 1
			),
		new ItemStack(Material.COOKED_BEEF, 8),
		new ItemStack(Material.REDSTONE, 4),
		new ItemStack(Material.SUGAR, 4),
	};
}
