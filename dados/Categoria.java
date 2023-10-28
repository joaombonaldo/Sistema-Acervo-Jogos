package dados;

public enum Categoria {

	ACT("Acao"),
	STR("Estrategia"),
	SIM("Simulacao");
	private String nome;

	private Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public static Categoria peloNome(String nomeCompleto) {
		if (nomeCompleto.equals("Acao")) {
			return ACT;
		} else if (nomeCompleto.equals("Estrategia")) {
			return STR;
		} else if (nomeCompleto.equals("Simulacao")) {
			return SIM;
		} else {
			return null;
		}
    }
}
