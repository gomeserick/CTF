package me.erick.ctf.classes.listener;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.bukkit.FireworkEffect.Builder;

import me.erick.ctf.CustomEntityFirework;
import me.erick.ctf.classes.Classes;
import me.erick.ctf.core.CTFMain;
import me.erick.ctf.ctfer.Ctfer;
import me.erick.ctf.ctfer.Magias;
import me.erick.ctf.ctfer.PlayerList;
import me.erick.ctf.teams.Teams;
import me.erick.ctf.utils.Facil;

public class MagoListener implements Listener{
	@EventHandler
	public void staffs(PlayerInteractEvent e) {
		if(!CTFMain.getInstance().getMatch().isStarted()
		||CTFMain.getInstance().getMatch().isFinished()) return;
		Action a = e.getAction();
		Player p = e.getPlayer();
		Ctfer c = PlayerList.instance.getCtfer(p);
		ItemStack item = p.getItemInHand();
		Material m = item.getType();
		if(!c.getClasse().equals(Classes.Mago)) return;
		if(a.equals(Action.LEFT_CLICK_AIR) || a.equals(Action.LEFT_CLICK_BLOCK)) return;
		switch(m) {
		case DIAMOND_HOE:
			if(c.isMagiaDano()){
				Dano(p);
				c.useMagic(Magias.MAGIA_DANO);
			}
			break;
		case WOOD_HOE:
			if(c.isMagiaFogo()){
				Fogo(p);
				c.useMagic(Magias.MAGIA_FOGO);
			}
			break;
		case STONE_HOE:
			if(c.isMagiaRaio()){
				Raio(p);
				c.useMagic(Magias.MAGIA_RAIO);
			}
			break;
		case IRON_HOE:
			if(c.isMagiaGelo()){
				Gelo(p);
				c.useMagic(Magias.MAGIA_GELO);
			}
			break;
		case GOLD_HOE:
			if(c.isMagiaCura()){
				Cura(p);
				c.useMagic(Magias.MAGIA_CURA);
			}
			break;
		case BLAZE_ROD:
			if(a.equals(Action.RIGHT_CLICK_BLOCK)) {
				if(c.isMagiaTp()){
					TP(p);
					c.useMagic(Magias.MAGIA_TP);
				}
			}
			
			break;
		default:
			break;
		}
	}
	@EventHandler
	public void projectile(ProjectileHitEvent e) {
		if(CTFMain.getInstance().getMatch().isFinished() 
		||!CTFMain.getInstance().getMatch().isStarted()) return;
		if(!(e.getEntity().getShooter() instanceof Player))	return;
		
		Entity en = e.getEntity();
		Player p = (Player) e.getEntity().getShooter();
		Ctfer c = PlayerList.instance.getCtfer(p);	
		if(!c.getClasse().equals(Classes.Mago)) return;
		if(e.getEntityType().equals(EntityType.ARROW)) {
			Builder builder = FireworkEffect.builder();
			FireworkEffect effect;
			if(c.getTime()==Teams.BLUE) {
				effect = builder.withColor(Color.BLUE).withColor(Color.FUCHSIA).build();
			} else {
				effect = builder.withColor(Color.RED).withColor(Color.ORANGE).build();
			}
			
			for(Player p2 : Bukkit.getOnlinePlayers()) {
				CustomEntityFirework.spawn(en.getLocation(), effect,p2);
			}
		}
		if(e.getEntityType().equals(EntityType.FIREBALL)) {
			Fireball f = (Fireball) e.getEntity();
			Location l = f.getLocation();
			for(Entity ent : l.getWorld().getNearbyEntities(l, 5, 5, 5)) {
				if(ent instanceof Player) {
					Player pl = (Player) ent;
					Ctfer ct = PlayerList.instance.getCtfer(pl);
					if(ct.getTime()!=c.getTime()) {
						pl.damage(30/Math.round(1+(l.distance(pl.getLocation()))));
						pl.setFireTicks(100);
						Facil.logger(""+30/Math.round(1+(l.distance(pl.getLocation()))));
						Facil.logger("Dano");
					}
					
				}
			}
		} else if(e.getEntityType().equals(EntityType.EGG)) {Egg egg = (Egg) e.getEntity();
			Location l = egg.getLocation();
			egg.getWorld().strikeLightningEffect(l);
			for(Entity ent : l.getWorld().getNearbyEntities(l, 3, 3, 3)) {
				if(ent instanceof Player) {
					Player pl = (Player) ent;
					Ctfer ct = PlayerList.instance.getCtfer(pl);
					if(ct.getTime()!=c.getTime()) {
						Location lp = pl.getLocation();
						Vector vector = lp.toVector().subtract(l.toVector());
						vector.multiply(3);
						vector.setY(0.3);
						pl.setVelocity(vector);
					}
				}
			}
		} else if(e.getEntityType().equals(EntityType.SNOWBALL)) {
			Snowball snowball = (Snowball) e.getEntity();
			Location l = snowball.getLocation();
			World world = l.getWorld();
			for(Entity ent : l.getWorld().getNearbyEntities(l, 3, 3, 3)) {
				if(ent instanceof Player) {
					Player pl = (Player) ent;
					Ctfer ct = PlayerList.instance.getCtfer(pl);
					if(ct.getTime()!=c.getTime()) {
						Location lp = pl.getLocation();
						Vector v = new Vector();
						v.setX(0);
						v.setY(0);
						v.setZ(0);
						
						if(lp.getX()>0) {
							lp.setX(Math.floor(lp.getX())+0.5);
						} else {
							lp.setX(Math.floor(lp.getX())-0.5);
						}
						if(lp.getZ()>0) {
							lp.setZ(Math.floor(lp.getZ())+0.5);
						} else {
							lp.setZ(Math.floor(lp.getZ())-0.5);
						}
						lp.setY(Math.floor(lp.getY()));
						
						pl.teleport(lp);
						Block[] b = new Block[10];
						b[0] = world.getBlockAt(lp.getBlockX(), lp.getBlockY()+2, lp.getBlockZ());
						b[1] = world.getBlockAt(lp.getBlockX()+1, lp.getBlockY()+1, lp.getBlockZ());
						b[2] = world.getBlockAt(lp.getBlockX(), lp.getBlockY()+1, lp.getBlockZ()-1);
						b[3] = world.getBlockAt(lp.getBlockX()-1, lp.getBlockY()+1, lp.getBlockZ());
						b[4] = world.getBlockAt(lp.getBlockX(), lp.getBlockY()+1, lp.getBlockZ()+1);
						b[5] = world.getBlockAt(lp.getBlockX()+1, lp.getBlockY(), lp.getBlockZ());
						b[6] = world.getBlockAt(lp.getBlockX(), lp.getBlockY(), lp.getBlockZ()-1);
						b[7] = world.getBlockAt(lp.getBlockX()-1, lp.getBlockY(), lp.getBlockZ());
						b[8] = world.getBlockAt(lp.getBlockX(), lp.getBlockY(), lp.getBlockZ()+1);
						b[9] = world.getBlockAt(lp.getBlockX(), lp.getBlockY()-1, lp.getBlockZ());
						for(Block bl : b) {
							if(bl.getType().equals(Material.AIR)) {
								bl.setType(Material.ICE);
							}
						}
						Bukkit.getScheduler().scheduleSyncDelayedTask(CTFMain.getInstance(), new Runnable(){
							@Override
							public void run(){
								for(Block bl : b) {
									if(bl.getType().equals(Material.ICE)) {
										bl.setType(Material.AIR);
									}
								}
							}
						}, 100L);
					}
				}
			}
		}
	}
	private void Dano(Player p) {
		Arrow a = p.launchProjectile(Arrow.class);
		a.setVelocity(a.getVelocity().multiply(2));
	}
	
	private void Fogo(Player p) {
		Fireball f = p.launchProjectile(Fireball.class);
		f.setVelocity(f.getVelocity().multiply(5));
		f.setIsIncendiary(false);
	}
	private void Raio(Player p) {
		Egg egg = p.launchProjectile(Egg.class);
		egg.setVelocity(egg.getVelocity().multiply(1.2));
	}
	private void Gelo(Player p) {
		Snowball snowball = p.launchProjectile(Snowball.class);
		snowball.setVelocity(snowball.getVelocity().multiply(1.2));
	}
	private void Cura(Player p) {
		PotionEffect ef = new PotionEffect(PotionEffectType.REGENERATION, 300, 1);
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta) item.getItemMeta();
		meta.addCustomEffect(ef, true);
		item.setItemMeta(meta);
		ThrownPotion throwPotion =  p.launchProjectile(ThrownPotion.class);
		throwPotion.setItem(item);
	}
	@SuppressWarnings({"rawtypes", "unchecked" })
	private void TP(Player p) {
		Location l = p.getLocation();
		Block b = p.getTargetBlock((Set)null, 5);
		if(!b.getType().equals(Material.AIR)) {
			Block top = b.getWorld().getHighestBlockAt(b.getX(),b.getZ());
			l.setX(top.getX()+0.5);
			l.setZ(top.getZ()+0.5);
			l.setY(top.getY());
			p.teleport(l);
		}
	}
}
