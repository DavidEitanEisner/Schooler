package com.example.schooler;

public class Messages {
    private String messageTitle;
    private String messageContent;
    public Messages (){};
    public Messages (String messageTitle,String messageContent){
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
    }


    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
