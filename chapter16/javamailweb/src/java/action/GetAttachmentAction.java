package action;

import java.io.InputStream;
import util.JavaMailUtil;

public class GetAttachmentAction {
    public InputStream getAttachment(String mailStoreProtocol,
            String mailStoreHost, String userName, 
            String password, String folderName, int messageId,
            String attachmentFileName) throws Exception {
        return JavaMailUtil.getAttachment(mailStoreProtocol,
                mailStoreHost, userName, password, folderName,
                messageId, attachmentFileName);
    }
}
