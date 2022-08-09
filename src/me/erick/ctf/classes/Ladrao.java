package me.erick.ctf.classes;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.erick.ctf.utils.Facil;

public class Ladrao extends Classe{
	public static final ItemStack[] armadura = {
			Facil.color(
						Facil.unbreakable(
								Material.LEATHER_BOOTS),
						Color.ORANGE),
			Facil.color(
						Facil.unbreakable(
								Material.LEATHER_LEGGINGS
								),
				Color.ORANGE), 
			Facil.color(
						Facil.unbreakable(
								Material.LEATHER_CHESTPLATE
								),
				Color.ORANGE),
			Facil.color(
					Facil.unbreakable(
							Material.LEATHER_HELMET
							),
			Color.ORANGE),
		};
	public static final ItemStack[] items = {
		Facil.unbreakable(Material.STONE_SWORD),
		Facil.setName(Facil.unbreakable(Material.STICK), Facil.chat("&aBastão de roubo &f1")),
		Facil.setName(Facil.unbreakable(Material.STICK), Facil.chat("&aBastão de roubo &f2")),
		Facil.setName(Facil.unbreakable(Material.STICK), Facil.chat("&aBastão de roubo &f3")),
		new ItemStack(Material.COOKED_BEEF,3)
	};
}
