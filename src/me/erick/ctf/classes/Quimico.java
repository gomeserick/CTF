package me.erick.ctf.classes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.erick.ctf.utils.Facil;

public class Quimico extends Classe{
	public static final ItemStack[] armadura = {
		Facil.unbreakable(Material.LEATHER_BOOTS),
		Facil.unbreakable(Material.GOLD_LEGGINGS),
		Facil.unbreakable(Material.GOLD_CHESTPLATE),
		Facil.unbreakable(Material.LEATHER_HELMET),
	};
	@SuppressWarnings("deprecation")
	public static final ItemStack[] items = {
		Facil.unbreakable(Material.IRON_SWORD),
		new ItemStack(new Potion(PotionType.INSTANT_DAMAGE, 2, true).toItemStack(5)),
		new ItemStack(new Potion(PotionType.POISON, 2, true).toItemStack(10)),
		new ItemStack(new Potion(PotionType.REGEN, 2, true).toItemStack(5)),
		new ItemStack(new Potion(PotionType.FIRE_RESISTANCE, 2, true).toItemStack(4)),
		new ItemStack(new Potion(PotionType.INSTANT_HEAL, 2, true).toItemStack(10)),
		new ItemStack(new Potion(PotionType.STRENGTH, 2, true).toItemStack(2)),
		new ItemStack(new Potion(PotionType.SPEED, 2, true).toItemStack(2)),
		new ItemStack(new Potion(PotionType.SLOWNESS, 2, true).toItemStack(2)),
		new ItemStack(new Potion(PotionType.WEAKNESS, 2, true).toItemStack(2))
	};
	
}
