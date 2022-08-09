package me.erick.ctf.tablist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;
 
public class TabList {
 
   private final static String[] colorcodeOrder = { "0", "1", "2", "3", "4",
       "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
   private List<PacketPlayOutPlayerInfo.PlayerInfoData> datas = new ArrayList<PacketPlayOutPlayerInfo.PlayerInfoData>();
   private Player player = null;
 
   public static int SIZE_ONE = 20;
   public static int SIZE_TWO = 40;
   public static int SIZE_THREE = 60;
   public static int SIZE_FOUR = 80;
 
   private String[] tabs;
   private int size = 0;
 
   public TabList(Player player, int size) {
     this.player = player;
     tabs = new String[80];
     this.size = size;
   }
 
   /**
    * This is here to remind you that you MUST call either this method or the
    * "clearCustomTabs" method. If you do not, the player will continue to see
    * the custom tabs until they relog.
    */
   public void CALL_THIS_BEFORE_RESTARTING() {
     clearCustomTabs();
   }
 
   /**
    * Returns the name of the tab at ID "id".
    *
    * @param id
    * @return
    */
   public String getTabName(int id) {
     return tabs[id];
   }
 
   /**
    * Clears all players from the player's tablist.
    */
   @SuppressWarnings("unchecked")
   public void clearPlayers() {
     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
     List<PacketPlayOutPlayerInfo.PlayerInfoData> players = null;
     try {
       players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(
           packet, "b");
     } catch (Exception e) {
       e.printStackTrace();
     }
 
     for (Player player2 : Bukkit.getOnlinePlayers()) {
       GameProfile gameProfile = ((CraftPlayer) player2).getProfile();
       PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(
           gameProfile, 1, EnumGamemode.NOT_SET,
           CraftChatMessage.fromString(player2.getName())[0]);
       players.add(data);
     }
     sendPackets(player, packet, players,
         PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
   }
 
   /**
    * Clears all the custom tabs from the player's tablist.
    */
   @SuppressWarnings("unchecked")
   public void clearCustomTabs() {
     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
     List<PacketPlayOutPlayerInfo.PlayerInfoData> players = null;
     try {
       players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(
           packet, "b");
     } catch (Exception e) {
       e.printStackTrace();
     }
 
     for (net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.PlayerInfoData player2 : new ArrayList<>(datas)) {
       tabs[fromCID(player2.a().getName())] = "";
       players.add(player2);
       datas.remove(player2);
     }
     sendPackets(player, packet, players,
         PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
   }
 
   /**
    * Clears all the values for a player's tablist. Use this whenever a player
    * first joins if you want to create your own tablist.
    *
    * This is here to remind you that you MUST call either this method or the
    * "clearCustomTabs" method. If you do not, the player will continue to see
    * the custom tabs until they relog.
    */
   public void clearAll() {
     clearPlayers();
     clearCustomTabs();
   }
 
   /**
    * Use this for changing a value at a specific tab.
    *
    * @param id
    * @param name
    */
   public void updateSlot(int id, String name) {
     removeCustomTab(id);
     addValue(id, name);
   }
 
   /**
    * removes a specific player from the player's tablist.
    *
    * @param player
    */
   @SuppressWarnings("unchecked")
   public void removePlayer(Player player) {
     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
     List<PacketPlayOutPlayerInfo.PlayerInfoData> players = null;
     try {
       players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(
           packet, "b");
     } catch (Exception e) {
       e.printStackTrace();
     }
 
     GameProfile gameProfile = ((CraftPlayer) player).getProfile();
     PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(
         gameProfile, 1, EnumGamemode.NOT_SET,
         CraftChatMessage.fromString(player.getName())[0]);
     players.add(data);
 
     sendPackets(player, packet, players,
         PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
   }
 
   /**
    * Removes a custom tab from a player's tablist.
    *
    * @param id
    */
   @SuppressWarnings("unchecked")
   public void removeCustomTab(int id) {
     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
     List<PacketPlayOutPlayerInfo.PlayerInfoData> players = null;
     try {
       players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(
           packet, "b");
     } catch (Exception e) {
       e.printStackTrace();
     }
 
     for (net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.PlayerInfoData playerdata : new ArrayList<>(datas)) {
       if (playerdata.a().getName().startsWith(getHexName(id))) {
         tabs[fromCID(playerdata.a().getName())] = "";
         players.add(playerdata);
         datas.remove(playerdata);
         break;
       }
     }
     sendPackets(player, packet, players,
         PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
   }
 
   /**
    * Use this to add a new player to the list
    *
    * DEPRECATION REASON: If all 80 slots have been taken, new values will not
    * be shown and may have the potential to go out of the registered bounds.
    * Use the "updateSlot" method to change a slot.
    *
    * @param id
    * @param name
    */
   @SuppressWarnings("unchecked")
   @Deprecated
   public void addValue(int id, String name) {
     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
     List<PacketPlayOutPlayerInfo.PlayerInfoData> players = null;
     try {
       players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(
           packet, "b");
     } catch (Exception e) {
       e.printStackTrace();
     }
     GameProfile gameProfile = new GameProfile(UUID.randomUUID(),
         getHexName(id));
     PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(
         gameProfile, 1, EnumGamemode.NOT_SET,
         CraftChatMessage.fromString(getCID(id) + name)[0]);
     tabs[fromCID(data.a().getName())] = "";
     players.add(data);
     datas.add(data);
 
     sendPackets(player, packet, players,
         PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
   }
 
   /**
    * This is used to create the table. If you want to create a custom tablist,
    * then this should be called right after the playlist instance has been created.
    */
   @SuppressWarnings("unchecked")
   public void initTable() {
     clearAll();
     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
     List<PacketPlayOutPlayerInfo.PlayerInfoData> players = null;
     try {
       players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(
           packet, "b");
     } catch (Exception e) {
       e.printStackTrace();
     }
     for (int i = 0; i < size; i++) {
       GameProfile gameProfile = new GameProfile(UUID.randomUUID(),
           getHexName(i));
       PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(
           gameProfile, 1, EnumGamemode.NOT_SET,
           CraftChatMessage.fromString(getCID(i) + "")[0]);
       tabs[fromCID(data.a().getName())] = "";
       players.add(data);
       datas.add(data);
 
     }
     sendPackets(player, packet, players,
         PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
   }
 
   private static String getCID(int id) {
     ChatColor firstChar = ChatColor.getByChar(colorcodeOrder[id
         / colorcodeOrder.length]);
     ChatColor secondChar = ChatColor.getByChar(colorcodeOrder[id
         % colorcodeOrder.length]);
     return firstChar + "" + secondChar + "" + ChatColor.RESET;
   }
 
   private static int fromCID(String id) {
     int total = 0;
     for (int i = 0; i < colorcodeOrder.length; i++) {
       if (colorcodeOrder[i].equalsIgnoreCase(id.charAt(0) + "")) {
         total = 16 * i;
         break;
       }
     }
     for (int i = 0; i < colorcodeOrder.length; i++) {
       if (colorcodeOrder[i].equalsIgnoreCase(id.charAt(1) + "")) {
         total += i;
         break;
       }
     }
     return total;
   }
 
   private static void setValue(Object instance, String field, Object value)
       throws Exception {
     Field f = instance.getClass().getDeclaredField(field);
     f.setAccessible(true);
     f.set(instance, value);
   }
 
   private static Object getValue(Object instance, String field)
       throws Exception {
     Field f = instance.getClass().getDeclaredField(field);
     f.setAccessible(true);
     return f.get(instance);
   }
 
   private static void sendPackets(Player player,
       PacketPlayOutPlayerInfo packet,
       List<PacketPlayOutPlayerInfo.PlayerInfoData> players,
       PacketPlayOutPlayerInfo.EnumPlayerInfoAction action) {
     try {
       setValue(packet, "a", action);
       setValue(packet, "b", players);
     } catch (Exception e) {
       e.printStackTrace();
     }
     ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
 
   }
   /**
    * Changes the player. NOTE: Scoreboards will not be transfered to the new player. You will have to re-set each value if the player has changed.
    * @param player
    */
   public void setPlayer(Player player) {
     this.player = player;
   }
   /**
    * Returns the player.
    * @return
    */
   public Player getPlayer() {
     return this.player;
   }
 
   /**
    * This returns the ID of a slot at [Row,Columb].
    *
    * Example of useage: The top tab on the first row is at [0,0], and will
    * return ID 0 The bottom of the first row is at [0,19], and will return ID
    * 19 The bottom of the fourth row is at [3,19], and will return ID 79
    *
    * @param row
    * @param col
    * @return
    */
   public int getID(int row, int col) {
     return col * 20 + row;
   }
 
   private static String getHexName(int id) {
     String firstletter = colorcodeOrder[id / 16];
     String secondletter = colorcodeOrder[id % 16];
     return firstletter + secondletter;
   }
}