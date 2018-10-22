package com.srb.project.enumConstans;

public enum EnumMessages {
    MESSAGE_SUCESS_DELETE("Eliminado exitosamente!"),
    MESSAGES_ERROR_SAVE("Error al Guardar!");

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
