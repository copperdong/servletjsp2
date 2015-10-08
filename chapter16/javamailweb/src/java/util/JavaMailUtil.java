package util;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import model.FetchResponse;
import model.FolderBean;
import model.MessageBean;

public class JavaMailUtil {

    public void sendEmail() {
        String to = "...";//to email
        String from = "[...]@gmail.com";//from email
        final String username = "username";
        final String password = "password";

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("You got mail");
            message.setText("This email was sent with JavaMail.");
            Transport.send(message);
            System.out.println("Email sent successfully...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static FetchResponse fetchEmails(String protocol,
            String host,
            String userName, String password,
            String currentFolder, int page) throws Exception {
        FetchResponse fetchResponse = new FetchResponse();
        List<MessageBean> messageBeans = new ArrayList<>();
        fetchResponse.setMessageBeans(messageBeans);
        List<FolderBean> folderBeans = new ArrayList<>();
        fetchResponse.setFolders(folderBeans);
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", protocol);
            Session session = Session.getDefaultInstance(
                    properties);
            Store store = session.getStore(protocol);
            store.connect(host, userName, password);

            // get list of folders
            Folder defaultFolder = store.getDefaultFolder();
            Folder[] folders = defaultFolder.list();
            for (Folder folder : folders) {
                Folder[] subFolders = folder.list();
                if (subFolders.length == 0) {
                    folderBeans.add(new FolderBean(
                            folder.getName(), folder.
                            getFullName()));
                } else {
                    for (Folder subFolder : subFolders) {
                        folderBeans.add(new FolderBean(
                                subFolder.getName(), subFolder.
                                getFullName()));
                    }
                }
            }
            Folder folder = store.getFolder(currentFolder);
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            fetchResponse.setMessageCount(messages.length);

            int length = messages.length;
            int maxNumber = length - Constants.ROWS_PER_PAGE
                    * (page - 1);
            int minNumber = Math.max(0, length
                    - Constants.ROWS_PER_PAGE * page);
            for (int i = maxNumber - 1; i >= minNumber; i--) {
                MessageBean msgBean = new MessageBean();
                Message message = messages[i];
                msgBean.setId(i);
                msgBean.setSubject(message.getSubject());
                msgBean.setFrom(message.getFrom()[0].toString());
                LocalDateTime rd = LocalDateTime.ofInstant(
                        message.getReceivedDate().toInstant(),
                        ZoneId.systemDefault());
                msgBean.setReceivedDate(rd);
                messageBeans.add(msgBean);
            }

            //close the store and folder objects
            folder.close(false);
            store.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return fetchResponse;
    }

    public static FetchResponse getMessage(String protocol,
            String host,
            String userName, String password,
            String currentFolder, int id) throws Exception {
        FetchResponse fetchResponse = new FetchResponse();
        List<MessageBean> messageBeans = new ArrayList<>();
        fetchResponse.setMessageBeans(messageBeans);
        List<FolderBean> folderBeans = new ArrayList<>();
        fetchResponse.setFolders(folderBeans);
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", protocol);
            Session session = Session.getDefaultInstance(
                    properties);
            Store store = session.getStore(protocol);
            store.connect(host, userName, password);

            // get list of folders
            Folder defaultFolder = store.getDefaultFolder();
            Folder[] folders = defaultFolder.list();
            for (Folder folder : folders) {
                Folder[] subFolders = folder.list();
                if (subFolders.length == 0) {
                    folderBeans.add(new FolderBean(
                            folder.getName(), folder.
                            getFullName()));
                } else {
                    for (Folder subFolder : subFolders) {
                        folderBeans.add(new FolderBean(
                                subFolder.getName(), subFolder.
                                getFullName()));
                    }
                }
            }
            Folder folder = store.getFolder(currentFolder);
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            fetchResponse.setMessageCount(messages.length);

            MessageBean msgBean = new MessageBean();
            fetchResponse.setMessageBean(msgBean);
            Message message = messages[id];
            msgBean.setId(id);
            msgBean.setSubject(message.getSubject());
            msgBean.setFrom(message.getFrom()[0].toString());
            LocalDateTime rd = LocalDateTime.ofInstant(
                    message.getReceivedDate().toInstant(),
                    ZoneId.systemDefault());
            msgBean.setReceivedDate(rd);

            String contentType = message.getContentType().
                    toLowerCase();
            if (contentType.contains("multipart")) {
                // this message may contain attachment
                Multipart multiPart = (Multipart) message.
                        getContent();
                List<String> attachments = new ArrayList<>();
                for (int i = 0; i < multiPart.getCount(); i++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.
                            getBodyPart(i);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.
                            getDisposition())) {
                        attachments.add(part.getFileName());
                    } else {
                        // a part that is not an attachment may 
                        // contain text, HTML or 
                        // multipart/alternative
                        String partContentType = part.
                                getContentType().toLowerCase();
                        if (partContentType.
                                contains("text/plain")
                                || partContentType.contains(
                                        "text/html")) {
                            msgBean.setText(part.getContent().
                                    toString());
                        } else if (partContentType.toLowerCase()
                                .contains("multipart/alternative")) {
                            // if a part's content type is multipart/alternative, 
                            // the part may contain multiparts that contain
                            // text or HTML or both
                            if (part.getContent() instanceof MimeMultipart) {
                                MimeMultipart multiPart2 = (MimeMultipart) part.
                                        getContent();
                                for (int j = 0; j < multiPart2.
                                        getCount(); j++) {
                                    // may contain both plain text and HTML
                                    MimeBodyPart part2 = (MimeBodyPart) multiPart2.
                                            getBodyPart(j);
                                    String contentType2 = part2.
                                            getContentType().
                                            toLowerCase();
                                    if (contentType2.contains(
                                            "text/plain")
                                            || contentType2.
                                            contains("text/html")) {
                                        // we will get the latter of plain text or html
                                        msgBean.setText(part2.
                                                getContent().
                                                toString());
                                    }
                                }
                            }
                        }
                    }
                }
                if (attachments.size() > 0) {
                    msgBean.setAttachments(attachments.toArray(
                            new String[0]));
                }
            } else if (contentType.contains("text/plain")
                    || contentType.contains("text/html")) {
                Object content = message.getContent();
                if (content != null) {
                    msgBean.setText(content.toString());
                }
            }

            //close the folder and store
            folder.close(false);
            store.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return fetchResponse;
    }

    public static InputStream getAttachment(String protocol,
            String host,
            String userName, String password, String folderName,
            int messageId,
            String attachmentFileName)
            throws Exception {
        try {
            Properties properties = new Properties();
            properties.put("mail.store.protocol", protocol);
            Session session = Session.getDefaultInstance(
                    properties);
            Store store = session.getStore(protocol);
            store.connect(host, userName, password);

            // get list of folders
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            Message message = messages[messageId];

            String contentType = message.getContentType().
                    toLowerCase();
            if (contentType.contains("multipart")) {
                // this message may contain attachment
                Multipart multiPart = (Multipart) message.
                        getContent();
                for (int i = 0; i < multiPart.getCount(); i++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.
                            getBodyPart(i);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.
                            getDisposition())) {
                        if (attachmentFileName.equals(part.
                                getFileName())) {
                            return part.getInputStream();
                        }
                    }
                }
            }
            //close the folder and store
            folder.close(false);
            store.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return null;
    }
}
