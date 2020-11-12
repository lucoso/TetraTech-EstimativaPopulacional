package br.com.lucaswagner.domain.exception;

public class ErroDoUsuarioException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public ErroDoUsuarioException(String message) {
			super(message);
		}

}
