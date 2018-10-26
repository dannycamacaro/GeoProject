package com.srb.project.enumConstans;

public enum EnumOperation {
    DELETE_DEVICE("1", "Eliminar dispositivo"),
    ADD_USER("2", "Agregar usuario"),
    DELETE_USER("3", "Eliminar usuario"),
    EDIT_USER("4", "Editar usuario");

    EnumOperation(String idOperation, String operationName) {
        this.idOperation = idOperation;
        this.operationName = operationName;
    }

    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    String idOperation;
    String operationName;

}
