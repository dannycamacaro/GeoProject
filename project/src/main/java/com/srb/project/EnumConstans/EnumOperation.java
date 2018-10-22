package com.srb.project.enumConstans;

public enum EnumOperation {
    DELETE_DEVICE("1", "Eliminar dispositivo");

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
