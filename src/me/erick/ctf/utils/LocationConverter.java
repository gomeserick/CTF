package me.erick.ctf.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class LocationConverter {
	public static Location converter(World w, ResultSet results) {
		try {
			double x = (double) results.getDouble("x");
			double y = (double) results.getDouble("y");
			double z = (double) results.getDouble("z");
			double vx = (double) results.getDouble("vx");
			double vy = (double) results.getDouble("vy");
			double vz = (double) results.getDouble("vz");
			Vector v = new Vector(vx,vy,vz);
			Location l = new Location(w,x,y,z);
			l.setDirection(v);
			results.close();
			return l;
		} catch (SQLException e) {
			Logger.sendErrorLog("Erro ao converter os dados para localização");
			e.printStackTrace();
		}
		return null;
	}
	public static Location flagConverter(World w, ResultSet results) {
		try {
			double x = (double) results.getDouble("x");
			double y = (double) results.getDouble("y");
			double z = (double) results.getDouble("z");
			Location l = new Location(w,x,y,z);
			results.close();
			return l;
		} catch (SQLException e) {
			Logger.sendErrorLog("Erro ao converter os dados para localização da bandeira");
			e.printStackTrace();
		}
		return null;
	}
}
