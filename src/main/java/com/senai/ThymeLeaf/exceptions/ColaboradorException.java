package com.senai.ThymeLeaf.exceptions;

public class ColaboradorException {

    public static class EmailJaCadastradoException extends RuntimeException {
        public EmailJaCadastradoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class DataInvalidaException extends RuntimeException {
        public DataInvalidaException(String mensagem) {
            super(mensagem);
        }
    }

}
