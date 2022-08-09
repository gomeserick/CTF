package me.erick.ctf.flag;

import me.erick.ctf.teams.Teams;

public class Flag {
	private boolean stolen;
	private Teams team;
	
	
	public Flag(Teams team) {
		this.team = team;
	}
	
	public boolean isStolen() {
		return stolen;
	}
	public Teams getTeam() {
		return team;
	}
	public void setStolen(boolean stolen) {
		this.stolen = stolen;
	}
	public void setTeam(Teams team) {
		this.team = team;
	}
	
}
