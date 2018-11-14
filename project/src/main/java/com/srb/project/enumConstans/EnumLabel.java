package com.srb.project.enumConstans;

public enum EnumLabel {
    REGISTRAR_LABEL("Registrar"),
    NUEVO_LABEL("Nuevo"),
    EDITAR_LABEL("Editar"),
    ELIMINAR_LABEL("Eliminar"),
    ACEPTAR_LABEL("Aceptar"),
    REGISTRO_SALVADO_LABEL("Registro salvado"),
    REGISTRO_ELIMINADO_LABEL("Registro eliminado"),
    ROW_COUNT_CAPTION_LABEL("%d registro(s) encontrados"),
    REFRESCAR_LABEL("Refrescar"),
    ELIMINAR_REGISTRO_LABEL("¿Desea eliminar el registro?"),
    NAME_ROL_LABEL("Nombre de rol"),
    DESCRIPTION_ROL_LABEL("Descripcion de rol"),
    USERNAME_LABEL("Nombre de usuario"),
    PASSWORD_LABEL("Contraseña"),
    FIRST_NAME_LABEL("Nombres"),
    LAST_NAME_LABEL("Apellidos"),
    IDENTITY_DOCUMENT_LABEL("Documento de identidad"),
    AGE_LABEL("Edad"),
    PHONE_NUMBER_LABEL("Numero de telefono"),
    EMAIL_LABEL("Email"),
    CANCELAR_LABEL("Cancelar");

    EnumLabel(String label) {
        this.label = label;
    }

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
