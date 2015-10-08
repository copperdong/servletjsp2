package util;

import model.SmtpServer;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CommonsEmailSender {

    public void sendEmail(SmtpServer smtpServer, 
            String fromEmail, String[] toEmails, 
            String[] ccEmails, String[] bccEmails,
            String subject, String text) 
            throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(smtpServer.getHost());
        email.setSmtpPort(587);
        email.setAuthenticator(
                new DefaultAuthenticator(
                        smtpServer.getUserName(), 
                        smtpServer.getPassword()));
        email.setStartTLSEnabled(true);
        email.setFrom(fromEmail);
        email.setSubject(subject);
        email.setMsg(text);
        email.addTo(toEmails);
        if (ccEmails != null && ccEmails.length > 0) {
            email.addCc(ccEmails);
        }
        if (bccEmails != null && bccEmails.length > 0) {
            email.addBcc(bccEmails);
        }
        email.send();
    }
}