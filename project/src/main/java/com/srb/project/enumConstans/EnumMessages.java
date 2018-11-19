package com.srb.project.enumConstans;

public enum EnumMessages {
    MESSAGE_REQUIRED_FIELD ("Debe introducir todos los campos requeridos"),
    MESSAGES_SUCESS_SAVE("Agregado exitosamente"),
    MESSAGES_ERROR_SAVE("Error al Guardar!"),
    MESSAGE_SUCESS_UPDATE("Actualizado exitosamente!"),
    MESSAGES_ERROR_UPDATE("No se pudo actualizar el registro"),
    MESSAGE_SUCESS_DELETE("Eliminado exitosamente!"),
    MESSAGES_ERROR_DELETE("No se pudo eliminar el registro"),
    SELECT_ROL("Debe seleccionar un rol"),
    SELECT_USER("Debe seleccionar un usuario"),
    SELECT_REGISTER("Debe seleccionar un registro"),
    SELECT_ROUTES("Debe seleccionar una ruta"),
    MESSAGES_LOGIN("Ingresar"),
    EXIST_ROL("Introduzca un nombre distinto para el rol. El nombre del rol ya existe"),
    EXIST_VEHICLE("Introduzca una matricula distinta, el vehiculo ya se encuentra registrado"),
    EXIST_DEVICE("Introduzca un IMEI distinto, el IMEI del dispositivo ya existe"),
    EXIST_ROUTES("Introduzca un nombre de ruta distinto, el nombre de la ruta ya se encuentra registrada");

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
