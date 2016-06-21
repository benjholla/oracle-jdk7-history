

package java.util.spi;

import java.util.Locale;


public abstract class LocaleServiceProvider {

    
    protected LocaleServiceProvider() {
    }

    
    public abstract Locale[] getAvailableLocales();
}
