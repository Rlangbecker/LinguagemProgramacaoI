package service.enums;

public enum TipoPassagem {
    ECONOMICA("ECONOMICA"),
    EXECUTIVA("EXECUTIVA"),
    PRIMEIRA_CLASSE("PRIMEIRA_CLASSE");

    private final String tipoPassagem;

    TipoPassagem(String tipoPassagem) {
        this.tipoPassagem = tipoPassagem;
    }
}
