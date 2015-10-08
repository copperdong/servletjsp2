package action;

import util.CommonsEmailSender;
import model.SmtpServer;

public class SendEmailAction {
    public void sendEmail(String smtpHost, int smtpPort,
            String userName, String password, String to, 
            String subject, String message) throws Exception {
        CommonsEmailSender sender = new CommonsEmailSender();
        SmtpServer smtpServer = new SmtpServer(smtpHost,
                smtpPort, userName, password);
        String[] toEmails = {to};
        sender.sendEmail(smtpServer, userName, 
                toEmails, /*ccEmails=*/null, /*bccEmails=*/null,
                subject, message);
    }
}
