package action;

import model.FetchResponse;
import util.JavaMailUtil;

public class FetchEmailAction {

    public FetchResponse fetchEmails(String mailStoreProtocol,
            String mailStoreHost, String userName,
            String password, String folderName, int page) throws Exception {
        return JavaMailUtil.fetchEmails(mailStoreProtocol,
                mailStoreHost, userName, password,
                folderName, page);
    }
}
