package FolderSyncTool.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by akshay on 21/06/17.
 */
public class IPValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public IPValidator() {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
    }

    /***
     * Validator to validate entered IP Address.
     * @param ip
     * @return true valid ip address, false invalid ip address
     */

    public boolean validate(final String ip) {
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
