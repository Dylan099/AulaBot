package com.example.ElAulaBot.dto;


import com.example.ElAulaBot.domain.Usuario;

public class UsuarioDto {
    private int userId;
    private String chatId;
    private String lastMessageSent;
    private String lastMessageReceived;

    public UsuarioDto (){}

    public UsuarioDto (Usuario usuario)
    {
        this.userId = usuario.getIdUser();
        this.chatId = usuario.getChatId();
        this.lastMessageSent = usuario.getLastMessageSent();
        this.lastMessageReceived = usuario.getLastMessageReceived();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getLastMessageSent() {
        return lastMessageSent;
    }

    public void setLastMessageSent(String lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
    }

    public String getLastMessageReceived() {
        return lastMessageReceived;
    }

    public void setLastMessageReceived(String lastMessageReceived) {
        this.lastMessageReceived = lastMessageReceived;
    }
}
