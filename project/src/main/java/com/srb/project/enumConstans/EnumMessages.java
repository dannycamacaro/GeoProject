package com.srb.project.enumConstans;

public enum EnumMessages {
    MESSAGE_REQUIRED_FIELD ("Debe introducir todos los campos requeridos"),
    MESSAGES_SUCESS_SAVE("Agregado exitosamente"),
    MESSAGES_ERROR_SAVE("Error al Guardar!"),
    MESSAGE_SUCESS_UPDATE("Actualizado exitosamente!"),
    MESSAGES_ERROR_UPDATE("No se pudo actualizar el registro"),
    MESSAGE_SUCESS_DELETE("Eliminado exitosamente!"),
    MESSAGES_ERROR_DELETE("No se pudo eliminar el registro"),
    MESSAGES_EDIT("Editado exitosamente"),
    SELECT_ROL("Debe seleccionar un rol"),
    SELECT_USER("Debe seleccionar un usuario"),
    SELECT_REGISTER("Debe seleccionar un registro"),
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
