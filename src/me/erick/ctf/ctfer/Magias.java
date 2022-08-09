package me.erick.ctf.ctfer;

public enum Magias {
	MAGIA_DANO(5),
	MAGIA_FOGO(100),
	MAGIA_RAIO(100),
	MAGIA_GELO(200),
	MAGIA_CURA(80),
	MAGIA_TP(240);
	
	private int cooldown;
	
	private Magias(int cooldown) {
		this.cooldown = cooldown;
	}
	public int getCooldown() {
		return this.cooldown;
	}
}
