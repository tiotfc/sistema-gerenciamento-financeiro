package br.com.sada.gerenciamento.financeiro.exception;

public class LimiteExcedidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LimiteExcedidoException(String message) {
		super(message);
	}

}
