package com.srb.project.enumConstans;


public enum EnumOperation {

    //MODULO USER
    ADD_USER("1", "Add user"),
    EDIT_USER("2", "Edit user"),
    DELETE_USER("3", "Delete user"),
    //MODULO DEVICE
    ADD_DEVICE("4", "Add device"),
    EDIT_DEVICE("5", "Edit device"),
    DELETE_DEVICE("6", "Delete device"),
    //MODULO DRIVER
    ADD_DRIVER("7", "Add driver"),
    EDIT_DRIVER("8", "Edit driver"),
    DELETE_DRIVER("9", "Delete driver"),
    //MODULO LOCATION
    ADD_LOCATION("10", "Add location"),
    EDIT_LOCATION("11", "Edit location"),
    DELETE_LOCATION("12", "Delete location"),
    //MODULO ROL
    ADD_ROL("13", "Add rol"),
    EDIT_ROL("14", "Edit rol"),
    DELETE_ROL("15", "Delete rol"),
    //MODULO ROUTES
    ADD_ROUTES("16", "Add routes"),
    EDIT_ROUTES("17", "Edit routes"),
    DELETE_ROUTES("18", "Delete routes"),
    //MODULO ROUTES_DETAIL
    ADD_ROUTES_DETAIL("19", "Add routes_detail"),
    EDIT_ROUTES_DETAIL("20", "Edit routes_detail"),
    DELETE_ROUTES_DETAIL("21", "Delete routes_detail"),
    //MODULO VEHICLE
    ADD_VEHICLE("22", "Add vehicle"),
    EDIT_VEHICLE("23", "Edit vehicle"),
    DELETE_VEHICLE("24", "Delete vehicle"),
    //MODULO ASSIGNEDVEHICLE
    ADD_ASSIGNEDVEHICLE("25", "Add assignedvehicle"),
    EDIT_ASSIGNEDVEHICLE("26", "Edit assignedvehicle"),
    DELETE_ASSIGNEDVEHICLE("27", "Delete assignedvehicle"),
    //MODULO ASSIGNEDROUTES
    ADD_ASSIGNEDROUTES("28", "Add assignedroutes"),
    EDIT_ASSIGNEDROUTES("29", "Edit assignedroutes"),
    DELETE_ASSIGNEDROUTES("30", "Delete assignedroutes");

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

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    String idOperation;
    String operationName;

}
