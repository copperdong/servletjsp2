package model;

import java.util.List;

public class FetchResponse {
    private long messageCount;
    private String folder = "INBOX";
    private MessageBean messageBean;

    private List<MessageBean> messageBeans;
    private List<FolderBean> folderBeans;
    

    public long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(long messageCount) {
        this.messageCount = messageCount;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public List<MessageBean> getMessageBeans() {
        return messageBeans;
    }

    public void setMessageBeans(List<MessageBean> messageBeans) {
        this.messageBeans = messageBeans;
    }
    public List<FolderBean> getFolders() {
        return folderBeans;
    }

    public void setFolders(List<FolderBean> folderBeans) {
        this.folderBeans = folderBeans;
    }
}
