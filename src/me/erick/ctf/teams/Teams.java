package me.erick.ctf.teams;

import org.bukkit.DyeColor;

import net.md_5.bungee.api.ChatColor;

public enum Teams {
	BLUE("Azul",11, DyeColor.BLUE,DyeColor.PURPLE,"9","5",ChatColor.BLUE),
	RED("Vermelho",14, DyeColor.RED,DyeColor.ORANGE,"c","6",ChatColor.RED);
	
	private String time;
	private int colorCode;
	private DyeColor color;
	private DyeColor secondaryColor;
	private String hexColorCode;
	private String secondaryHexColorCode;
	private ChatColor chatColor;
	Teams(String s, int i, DyeColor color,DyeColor secondaryColor, String hexColorCode, String secondaryHexColorCode,ChatColor c) {
		this.time = s;
		this.colorCode = i;
		this.color = color;
		this.secondaryColor = secondaryColor;
		this.hexColorCode = hexColorCode;
		this.secondaryHexColorCode = secondaryHexColorCode;
		this.chatColor = c;
	}
	
	public String getTime(){
		return this.time;
	}
	public int getColorCode() {
		return this.colorCode;
	}
	public DyeColor getColor() {
		return this.color;
	}
	public String getHexColorCode() {
		return this.hexColorCode;
	}

	public DyeColor getSecondaryColor() {
		return secondaryColor;
	}

	public String getSecondaryHexColorCode() {
		return secondaryHexColorCode;
	}

	public void setSecondaryColor(DyeColor secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public void setSecondaryHexColorCode(String secondaryHexColorCode) {
		this.secondaryHexColorCode = secondaryHexColorCode;
	}
	public static Teams getOtherTeam(Teams team) {
		return (Teams.BLUE.equals(team)) ? Teams.RED : Teams.BLUE;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public void setChatColor(ChatColor chatColor) {
		this.chatColor = chatColor;
	}
}
