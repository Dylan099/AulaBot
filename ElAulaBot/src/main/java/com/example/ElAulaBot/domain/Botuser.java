/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ElAulaBot.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mirke
 */
@Entity
@Table(name = "botuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Botuser.findAll", query = "SELECT b FROM Botuser b"),
    @NamedQuery(name = "Botuser.findByIdUser", query = "SELECT b FROM Botuser b WHERE b.idUser = :idUser"),
    @NamedQuery(name = "Botuser.findByBotUserId", query = "SELECT b FROM Botuser b WHERE b.botUserId = :botUserId"),
    @NamedQuery(name = "Botuser.findByChatUserId", query = "SELECT b FROM Botuser b WHERE b.chatUserId = :chatUserId"),
    @NamedQuery(name = "Botuser.findByConversationId", query = "SELECT b FROM Botuser b WHERE b.conversationId = :conversationId"),
    @NamedQuery(name = "Botuser.findBySubconversationId", query = "SELECT b FROM Botuser b WHERE b.subconversationId = :subconversationId"),
    @NamedQuery(name = "Botuser.findByLastMessageSent", query = "SELECT b FROM Botuser b WHERE b.lastMessageSent = :lastMessageSent"),
    @NamedQuery(name = "Botuser.findByLastMessageReceived", query = "SELECT b FROM Botuser b WHERE b.lastMessageReceived = :lastMessageReceived"),
    @NamedQuery(name = "Botuser.findByTxuser", query = "SELECT b FROM Botuser b WHERE b.txuser = :txuser"),
    @NamedQuery(name = "Botuser.findByTxhost", query = "SELECT b FROM Botuser b WHERE b.txhost = :txhost"),
    @NamedQuery(name = "Botuser.findByTxdate", query = "SELECT b FROM Botuser b WHERE b.txdate = :txdate")})
public class Botuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bot_user_id")
    private String botUserId;
    @Size(max = 50)
    @Column(name = "chat_user_id")
    private String chatUserId;
    @Column(name = "conversation_id")
    private Integer conversationId;
    @Column(name = "subconversation_id")
    private Integer subconversationId;
    @Size(max = 50)
    @Column(name = "last_message_sent")
    private String lastMessageSent;
    @Size(max = 50)
    @Column(name = "last_message_received")
    private String lastMessageReceived;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "txuser")
    private String txuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "txhost")
    private String txhost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "txdate")
    @Temporal(TemporalType.DATE)
    private Date txdate;
    @JoinColumn(name = "profesor_id_profesor1", referencedColumnName = "profesor_id_profesor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso profesorIdProfesor1;

    public Botuser() {
    }

    public Botuser(Integer idUser) {
        this.idUser = idUser;
    }

    public Botuser(Integer idUser, String botUserId, String txuser, String txhost, Date txdate) {
        this.idUser = idUser;
        this.botUserId = botUserId;
        this.txuser = txuser;
        this.txhost = txhost;
        this.txdate = txdate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    public String getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(String chatUserId) {
        this.chatUserId = chatUserId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getSubconversationId() {
        return subconversationId;
    }

    public void setSubconversationId(Integer subconversationId) {
        this.subconversationId = subconversationId;
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

    public String getTxuser() {
        return txuser;
    }

    public void setTxuser(String txuser) {
        this.txuser = txuser;
    }

    public String getTxhost() {
        return txhost;
    }

    public void setTxhost(String txhost) {
        this.txhost = txhost;
    }

    public Date getTxdate() {
        return txdate;
    }

    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    public Curso getProfesorIdProfesor1() {
        return profesorIdProfesor1;
    }

    public void setProfesorIdProfesor1(Curso profesorIdProfesor1) {
        this.profesorIdProfesor1 = profesorIdProfesor1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Botuser)) {
            return false;
        }
        Botuser other = (Botuser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ElAulaBot.domain.Botuser[ idUser=" + idUser + " ]";
    }
    
}
