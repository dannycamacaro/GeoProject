package com.srb.project.enumConstans;

public enum EnumLabel {
    REGISTRAR_LABEL("Registrar"),
    EDITAR_LABEL("Editar"),
    ELIMINAR_LABEL("Eliminar"),
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
