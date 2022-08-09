package me.erick.ctf.core;

import org.bukkit.Bukkit;

import me.erick.ctf.classes.listener.ArqueiroListener;
import me.erick.ctf.classes.listener.AssassinoListener;
import me.erick.ctf.classes.listener.CuradorListener;
import me.erick.ctf.classes.listener.IncendiarioListener;
import me.erick.ctf.classes.listener.LadraoListener;
import me.erick.ctf.classes.listener.MagoListener;
import me.erick.ctf.classes.listener.NinjaListener;
import me.erick.ctf.classes.listener.QuimicoListener;
import me.erick.ctf.classes.listener.SoldadoListener;
import me.erick.ctf.ctfer.listener.DeathListener;
import me.erick.ctf.ctfer.listener.JoinListener;
import me.erick.ctf.ctfer.listener.QuitListener;
import me.erick.ctf.flag.CaptureSystem;
import me.erick.ctf.flag.listener.FlagListener;
import me.erick.ctf.partida.listener.BeefListener;
import me.erick.ctf.partida.listener.BlockBreakListener;
import me.erick.ctf.partida.listener.BlockPlacementListener;
import me.erick.ctf.partida.listener.ChatListener;
import me.erick.ctf.partida.listener.DamageListener;
import me.erick.ctf.partida.listener.DropListener;
import me.erick.ctf.partida.listener.HungerListener;
import me.erick.ctf.partida.listener.MovementListener;
import me.erick.ctf.partida.listener.ProjectileListener;
import me.erick.ctf.partida.listener.RespawnListener;
import me.erick.ctf.partida.listener.WeatherListener;

public class EventRegister {
	public static void register() {
		Bukkit.getServer().getPluginManager().registerEvents(new FlagListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new CaptureSystem(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new HungerListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new BlockPlacementListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new WeatherListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new RespawnListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new DropListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new DamageListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new BeefListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new MovementListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new ProjectileListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new ArqueiroListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new CuradorListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new SoldadoListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new LadraoListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new IncendiarioListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new NinjaListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new QuimicoListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new AssassinoListener(), CTFMain.getInstance());
		Bukkit.getServer().getPluginManager().registerEvents(new MagoListener(), CTFMain.getInstance());
	}
}
