package me.erick.ctf.classes;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Mago extends Classe{
	public static final ItemStack[] armadura = {
			Facil.color(
					Facil.enchant(
							Facil.unbreakable(
									Material.LEATHER_BOOTS),
							Enchantment.PROTECTION_FALL, 1),
					Color.PURPLE),
			Facil.color(
					Facil.enchant(
							Facil.unbreakable(
									Material.LEATHER_LEGGINGS),
							Enchantment.PROTECTION_FIRE, 1
							),
					Color.PURPLE), 
			Facil.color(
					Facil.enchant(
							Facil.unbreakable(
									Material.LEATHER_CHESTPLATE
									), 
							Enchantment.PROTECTION_FIRE, 2
							),
					Color.PURPLE),
			new ItemStack(Material.AIR)
		};
	public static final ItemStack[] items = {
		Facil.setName(Facil.unbreakable(Material.DIAMOND_HOE), Facil.chat("&aMagia de &5Dano")),
		Facil.setName(Facil.unbreakable(Material.WOOD_HOE), Facil.chat("&aMagia de &cFogo")),
		Facil.setName(Facil.unbreakable(Material.STONE_HOE), Facil.chat("&aMagia de &7Raio")),
		Facil.setName(Facil.unbreakable(Material.IRON_HOE), Facil.chat("&aMagia de &fGelo")),
		Facil.setName(Facil.unbreakable(Material.GOLD_HOE), Facil.chat("&aMagia de &eCura")),
		Facil.setName(Facil.unbreakable(Material.BLAZE_ROD), Facil.chat("&aMagia de &6Teleporte")),
	};
}
