package br.com.todolist.model;

public enum TaskStatus {

    NAO_INICIADA("Não Iniciada"),
    EM_PROGRESSO("Em Progresso"),
    PAUSADA("Pausada"),
    FINALIZADA("Finalizada");

    private String desc;

    TaskStatus(String descricao) {
        this.desc = descricao;
    }
}
