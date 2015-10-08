package action;

import model.FetchResponse;
import util.JavaMailUtil;

public class GetMessageAction {
    public FetchResponse getMessage(String mailStoreProtocol,
            String mailStoreHost, String userName, 
            String password, String folderName, int id) throws Exception {
        return JavaMailUtil.getMessage(mailStoreProtocol, 
                mailStoreHost, 
                userName, password, folderName, id);
    }
}
