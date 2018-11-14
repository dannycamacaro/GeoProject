package com.srb.project.enumConstans;

public enum EnumMessages {
    MESSAGE_REQUIRED_FIELD ("Debe introducir todos los campos requeridos"),
    MESSAGE_SUCESS_DELETE("Eliminado exitosamente!"),
    MESSAGES_ERROR_SAVE("Error al Guardar!"),
    MESSAGES_SAVE("Agregado exitosamente"),
    MESSAGES_EDIT("Editado exitosamente"),
    SELECT_ROL("Debe seleccionar un rol"),
    MESSAGES_LOGIN("Ingresar");

    EnumMessages(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
