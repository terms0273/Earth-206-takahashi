package controllers;

import models.UserInformation;
import play.*;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

public class LoginController extends Controller {
   /**
    * form用の内部クラス
    */
    public static class SampleForm{
        public String message;     // あとでメッセージクラスつくる？ 
    }
    
    /**
     * loginへのアクセスがあったら、routeからLoginControllerのloginViewがよばれる
     * loginViewは、login.scala.htmlと連携し、連携が正常に完了したことを返す
     * @return 
     */
    public static Result loginView() {
        Form<UserInformation> form = new Form(UserInformation.class);
        return ok(login.render("ログイン", form));      // login.scala.htmlと連携する
    }
    
    /**
     * 
     * @return 戻り値　returnで戻ってきた値が、メソッドの呼び出し側に送られる
     */
    public static Result doLogin(){
        //formの処理
        return redirect(controllers.routes.LoginController.loginView());
    }
    
    

}