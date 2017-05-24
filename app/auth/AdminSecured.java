package auth;


import play.mvc.*;
import views.html.*;
import controllers.*;
/**
 *
 * @author r-takahashi
 */
public class AdminSecured extends Security.Authenticator{

    /**
     *
     * @param ctx
     * @return
     */
    @Override
    public String getUsername(Http.Context ctx){
        System.out.println("Call Here");
        
        String id = ctx.session().get("userId");
        if(id == null){
            return null;
        }
        
        String type = ctx.session().get("type");
        if(type == null){
            return null;
        }
        return(type.equals("0")) ? type : null;
    }
    
    @Override
    public Result onUnauthorized(Http.Context ctx){
        return redirect(routes.LoginController.loginView());
    }
    
}
