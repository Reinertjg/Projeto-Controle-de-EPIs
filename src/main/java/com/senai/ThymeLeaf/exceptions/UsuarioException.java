package com.senai.ThymeLeaf.exceptions;

public class UsuarioException {

    public static class EmailJaCadastradoException extends RuntimeException {
        public EmailJaCadastradoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class SenhaInvalidaException extends RuntimeException {
        public SenhaInvalidaException(String mensagem) {
            super(mensagem);
        }
    }

}
