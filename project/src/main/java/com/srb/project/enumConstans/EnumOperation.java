package com.srb.project.enumConstans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public enum EnumOperation {
    //STATUS OPERATION
    OPERATION_SUCCESSFUL ("1","Operacion exitosa"),
    OPERATION_NOT_SUCCESSFUL ("1","Operacion exitosa"),
    //MODULO USUARIO
    ADD_USER("1", "Agregar usuario"),
    EDIT_USER("2", "Editar usuario"),
    DELETE_USER("3", "Eliminar usuario"),
    //Modulo dispositivo
    DELETE_DEVICE("", "Eliminar dispositivo"),
    ;


    @Autowired
    ApplicationContext context;

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
