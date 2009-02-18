package dominio.jogador;

import constantes.ConstantesTipoJogador;

/**
 * Classe responsavel por instanciar os varios tipos de jogadores disponiveis.
 * @author Gustavo Farias
 *
 */
public class JogadorFactory {

	private static JogadorFactory jogadorFactory;
	
	public static JogadorFactory getInstance(){
		
		if(jogadorFactory == null)
			return new JogadorFactory();
		
		return jogadorFactory;
		
	}
	
	private JogadorFactory(){
		
	}
	
	/**
	 * Instancia o tipo de jogador especificado pelo codigo recebido.
	 * 
	 * @param tipo
	 * @return
	 */
	public Jogador getJogador(int tipo, String nome){
		
		switch (tipo) {
		case ConstantesTipoJogador.JOGADOR_BUSCA_CEGA:
			return new Jogador_BuscaCega(nome);
		case ConstantesTipoJogador.JOGADOR_VAR_MENOS_RESTRITIVA:
			return new Jogador_VarMenosRestritiva(nome);
		default:
			return null;
		}
		
		
	}
	
}
