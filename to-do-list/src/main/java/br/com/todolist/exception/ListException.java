package br.com.todolist.exception;

import java.io.Serial;

public class ListException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ListException(String mensagem) {
        super(mensagem);
    }

}
