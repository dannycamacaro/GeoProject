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
