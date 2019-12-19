package com.example.sqllite.model;


//modelo del contacto con modificadores de acceso.
public class Contact {

    //atributos
    private int id;
    private String name;
    private String phone;

    //contructor vacio
    public Contact(){}

    //contructor
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    //Sobrecarga de metodo contructor
    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }


    //Geters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
