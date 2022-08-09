package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;


public class Arqueiro extends Classe{
	public static final ItemStack[] armadura = {
		Facil.unbreakable(Material.CHAINMAIL_BOOTS),
		Facil.unbreakable(Material.CHAINMAIL_LEGGINGS),
		Facil.unbreakable(Material.CHAINMAIL_CHESTPLATE),
		Facil.unbreakable(Material.CHAINMAIL_HELMET),
	};
	public static final ItemStack[] items = {
		Facil.unbreakable(Material.STONE_SWORD),
		new ItemStack(Material.COOKED_BEEF,8),
		Facil.enchant(
				Facil.unbreakable(Material.BOW),
				Enchantment.ARROW_KNOCKBACK, 1
		),
		new ItemStack(Material.ARROW, 64),
		new ItemStack(Material.ARROW, 64)
	};
}
