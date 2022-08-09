package me.erick.ctf.utils;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Facil {
	
	public static String chat(String s) {
		
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static void allPlayers(String s) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(Facil.chat("&3[CTF] &e"+s));
		}
	}
	
	public static double distance(Location l1, Location l2) {
		
		
		double distance = Math.sqrt(Math.pow((l1.getX()-l2.getX()), 2)
								  + Math.pow((l1.getY()-l2.getY()), 2) 
								  + Math.pow((l1.getZ()-l2.getZ()), 2));
		
		return distance;
	}
	
	
	public static ItemStack createItemStack(Material m) {
		
		ItemStack item = new ItemStack(m);
		
		return item;
	}
	
	public static ItemStack generateFromMeta(ItemMeta i, Material m) {
		ItemStack item = new ItemStack(m);
		item.setItemMeta(i);
		return item;
	}
	
	public static ItemMeta meta(ItemStack item) {
		
		ItemMeta itemMeta = item.getItemMeta();
		
		return itemMeta;
	}
	
	public static ItemMeta meta(Material m) {
		
		return meta(createItemStack(m));
		
	}
	public static void logger(String s) {
		Bukkit.getConsoleSender().sendMessage(Facil.chat("&3[CTF] &f" + s));
	}
	
	public static ItemStack unbreakable(Material m) {
		ItemStack item = createItemStack(m);
		ItemMeta itemMeta = meta(item);
		itemMeta.spigot().setUnbreakable(true);
		item.setItemMeta(itemMeta);
		
		return item;
		
	}
	
	public static void giveArmour(Player p, ItemStack[] i) {
		
		p.getInventory().setArmorContents(i);
		
	}
	
	public static void giveItems(Material m, Player p) {
		
		ItemStack item = unbreakable(m);
		p.getInventory().addItem(item);
	}
	public static void giveItems(Material m, Player p, boolean unbreakable) {
		
		ItemStack item = unbreakable(m);
		if(unbreakable) {
			p.getInventory().addItem(item);
		}
		
	}
	
	public static void giveItems(Material m, Player p, int i) {
		
		ItemStack item = new ItemStack(m, i);
		p.getInventory().addItem(item);
		
	}
	
	public static void giveItems(Material m, Player p, String s) {
		
		ItemStack i = setName(new ItemStack(m),s);
		
		p.getInventory().addItem(i);
		
	}
	
	public static ItemStack setName(ItemStack i, String s) {
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(s);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack setName(Material m, String s) {
		ItemStack i = new ItemStack(m);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(s);
		i.setItemMeta(meta);
		return i;
	}
	
	public static void giveItems(ItemStack i, Player p) {
		
		p.getInventory().addItem(i);
	}
	public static void giveItems(ItemStack i, Player p, String s) {
		
		
	}
	
	
	public static void kill(Player killer, Player killed) {
		
		killed.damage(0);
		killed.setHealth(20);
		killed.teleport(killed.getBedSpawnLocation());
		killer.sendMessage(Facil.chat("&avocê matou " + killed.getName()));
		
	}

	public static ItemStack enchant(ItemStack i, Enchantment e, int level) {
		ItemMeta meta = i.getItemMeta();
		meta.addEnchant(e, level, false);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack color(ItemStack i, Color c) {
		LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
		meta.setColor(c);
		i.setItemMeta(meta);
		return i;
	}
	
	
}
