package me.erick.ctf.classes;


public enum Classes {
	Guerreiro("Guerreiro"),
	Arqueiro("Arqueiro"),
	Curador("Curador"),
	Soldado("Soldado"),
	Incendiario("Incendiario"),
	Mago("Mago"),
	Ninja("Ninja"),
	Ladrao("Ladrao"),
	Quimico("Quimico"),
	Assassino("Assassino"),;
	
	private String name;
	Classes(String string) {
		this.name=string;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public static Classes getClassFromName(String s) {
		switch(s.toLowerCase()) {
		case"guerreiro":
			return Guerreiro;
		case"arqueiro":
			return Arqueiro;
		case"curador":
			return Curador;
		case"soldado":
			return Soldado;
		case"incendiario":
			return Incendiario;
		case"mago":
			return Mago;
		case"ninja":
			return Ninja;
		case"ladrao":
			return Ladrao;
		case"quimico":
			return Quimico;
		case"assassino":
			return Assassino;
		default:
			return null;
		}
	}
	public static boolean isClasse(String s) {
		switch(s.toLowerCase()) {
		case"guerreiro":
		case"arqueiro":
		case"curador":
		case"soldado":
		case"incendiario":
		case"mago":
		case"ninja":
		case"ladrao":
		case"quimico":
		case"assassino":
			return true;
		default:
			return false;
		}
	}
}
