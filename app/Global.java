/**
 * import必要
 */
import play.*;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;

/**
 *
 * @author r-takahashi
 */
public class Global extends GlobalSettings{
    /**
     * POSTにフィルターをかける
     * CSRF対策 
     * @param <T>
     * @return 
     */
    //@Override
    public <T extends EssentialFilter> Class<T>[] filters(){
        return new Class[]{CSRFFilter.class};
    }
}
