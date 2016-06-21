

package javax.security.sasl;

import javax.security.auth.callback.ChoiceCallback;


public class RealmChoiceCallback extends ChoiceCallback {

    
    public RealmChoiceCallback(String prompt, String[]choices,
        int defaultChoice, boolean multiple) {
        super(prompt, choices, defaultChoice, multiple);
    }

    private static final long serialVersionUID = -8588141348846281332L;
}
