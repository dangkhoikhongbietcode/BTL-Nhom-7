package com.example.btl_nhom_7.Warranty.model;

public class WarrantyModel {
    private String name;
    private String phone;
    private String date;
    private String service;
    private String vehicleType;
    private String store;

    public WarrantyModel() {
        // Constructor mặc định để sử dụng khi đọc dữ liệu từ database
    }

    public WarrantyModel(String name, String phone, String date, String service, String vehicleType, String store) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.service = service;
        this.vehicleType = vehicleType;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }

    public String getService() {
        return service;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getStore() {
        return store;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
