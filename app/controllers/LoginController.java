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
    public static class findForm{
        public String userId;
        public String password;
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
     * ブラウザ上で、ログインボタンが押されたときに実施
     * POST    /doLogin              controllers.LoginController.doLogin()
     * 
     * フォームから値を持ってくる
     * @return 戻り値　returnで戻ってきた値が、メソッドの呼び出し側に送られる
     */
    public static Result doLogin(){
        // 値の照合
        Form<findForm> form = new Form(findForm.class).bindFromRequest();
        if(!form.hasErrors()){
            findForm ff =form.get();
            /**
             * 入力されたユーザーIDが、UserInformationに存在することを確認
             * eq : モデルの情報とfindFormの情報が一致するか調べて、DBの情報を持ってきてくれる
             */
            UserInformation user = UserInformation.find.where().eq("userId", ff.userId).findUnique();
            /**
             * IDの確認をする
             * 入力されたIDがDBになかったときは、ログインできない。
             */
            if(user != null){
                /**
                 * passwordを確認する
                 * 入力されたパスワードと、DBのパスワードが一致するか調べる
                 */
                if(user.password == ff.password){
                    /**
                     * ログイン後の画面に移動
                     */
                    return ok(index.render("ログイン完了"));
                }
            }
        }
    return redirect(controllers.routes.LoginController.loginView());
    }
}