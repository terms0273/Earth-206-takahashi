package controllers;

import auth.*;
import dto.findForm;
import models.UserInformation;
import play.*;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController extends Controller {
   
    
    /**
     * loginへのアクセスがあったら、routeからLoginControllerのloginViewがよばれる
     * loginViewは、login.scala.htmlと連携し、連携が正常に完了したことを返す
     * @return 
     */
    public static Result loginView() {
        Form<findForm> form = new Form(findForm.class);
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
                 * ユーザ情報が削除されていないか確認する
                 */
                if(user.delete_flag == 0){
                    /**
                     * passwordを確認する
                     * 入力されたパスワードと、DBのパスワードが一致するか調べる
                     */
                    if(BCrypt.checkpw(ff.password, user.password)){
                        /**
                         * セッションに入れる
                         */
                        session("userId", user.userId);
                        /**
                         * ログイン後の画面に移動
                         */                    
                        return ok(index.render("ログイン完了"));
                    }
                }
            }
        }
    return redirect(controllers.routes.LoginController.loginView());
    }
    /**
     * セッションを消すところ
     * ログアウトの時に必要、logoutが実行されると動く
     */
    public static void clearSession(){
        session().clear();
        
    }
    
    /**
     * ログアウトするところ
     * セッションを消した後、ログイン画面へ戻る
     * @return 
     */
    @Security.Authenticated(Secured.class)
    public static Result logout(){
        clearSession();
        return redirect(routes.LoginController.loginView());
    }
}