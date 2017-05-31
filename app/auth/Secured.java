package auth;

import play.mvc.*;
import views.html.*;
import controllers.*;

/**
 *セッションに値が入っているか確認
 * @author r-takahashi
 */
public class Secured extends Security.Authenticator{
    @Override
    public String getUsername(Http.Context ctx){
        return ctx.session().get("userId");
    }
    
    @Override
    public Result onUnauthorized(Http.Context ctx){
        return redirect(routes.LoginController.loginView());
    }
}
