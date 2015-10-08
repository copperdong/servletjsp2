package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageBean implements Serializable {
    private String from;
    private String subject;
    private String text;
    private LocalDateTime receivedDate;
    private String[] attachments;

    public MessageBean() {
        
    }
    public MessageBean(String from, String subject, String text) {
        this.from = from;
        this.subject = subject;
        this.text = text;
    }
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String[] getAttachments() {
        return attachments;
    }

    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }
}