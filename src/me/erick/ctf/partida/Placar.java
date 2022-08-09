package me.erick.ctf.partida;

import me.erick.ctf.core.CTFMain;
import me.erick.ctf.teams.Teams;

public class Placar {
	private int azulPontos;
	private int vermelhoPontos;
	
	
	public Placar() {
		this.azulPontos = 0;
		this.vermelhoPontos = 0;
	}

	public int getAzulPontos() {
		return azulPontos;
	}


	public int getVermelhoPontos() {
		return vermelhoPontos;
	}


	public void setAzulPontos(int azul) {
		this.azulPontos = azul;
	}


	public void setVermelhoPontos(int vermelho) {
		this.vermelhoPontos = vermelho;
	}
	
	public boolean acabou() {
		if(azulPontos == 3 || vermelhoPontos == 3) {
			if(azulPontos==3) {
				CTFMain.getInstance().getMatch().setWinner(Teams.BLUE);
			} else if(vermelhoPontos==3) {
				CTFMain.getInstance().getMatch().setWinner(Teams.RED);
			}
			return true;
		}
		return false;
	}
	
	public void addPontos(Teams team) {
		if(team.equals(Teams.BLUE)) this.azulPontos++;
		else this.vermelhoPontos++;
	}
	
	public int getPontos(Teams team) {
		if(team.equals(Teams.BLUE)) return this.azulPontos;
		else return this.vermelhoPontos;
	}
	
	
	
}
