package com.example.ElAulaBot.dto;

public enum Status {
    ACTIVE(1), INACTIVE(2);
    Status(int status){
        this.status=status;
    }
    private int status;

    public int getStatus(){return status;}
}