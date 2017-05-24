package controllers;

import models.UserInformation;
import play.*;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;
import views.html.*;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterController extends Controller {

    /**
    * form用の内部クラス
    */
    public class registerForm{
        public String userName;
        public String userId;
        public String password;
    }
    
    /**
     * registerへのアクセスがあったら、routeからLoginControllerのloginViewがよばれる
     * loginViewは、login.scala.htmlと連携し、連携が正常に完了したことを返す
     * @return 
     */
    public static Result registerView() {
        Form<UserInformation> form = new Form(UserInformation.class);
        return ok(register.render("ユーザ登録", form));      // register.scala.htmlと連携する
    }
    
    /**
     * ブラウザ上で、新規登録ボタンが押されたときに実施
     * POST    /doRegister              controllers.RegisterController.doRegister()
     * 
     * フォームから値を持ってくる
     * @return 戻り値　returnで戻ってきた値が、メソッドの呼び出し側に送られる
     */
    
    public static Result doRegister(){
        //Form<registerForm> form = new Form(registerForm.class).bindFromRequest();
        //if(!form.hasErrors()){
            registerForm ff = (registerForm) new Form(registerForm.class).bindFromRequest().get();
            /**
             * 入力されたユーザーIDが、UserInformationに存在することを確認
             * ne : モデルの情報とregisterFormの情報が一致するか調べて、DBの情報に一致するものを持ってきてくれる
             */
            UserInformation checkUser = UserInformation.find.where().eq("userId", ff.userId).findUnique();
            
            
            /**
             * IDを確認する
             * 入力されたIDが、DBにないとき登録できる
             */
            if(checkUser==null){
                UserInformation user = new UserInformation();
                user.userId = ff.userId;
                user.userName = ff.userName;
                /**
                 * 暗号化する
                 */
                user.password = BCrypt.hashpw(ff.password, BCrypt.gensalt());
                user.delete_flag = 0;
                user.save();
                return ok(index.render("登録完了"));
            //}
        } 
        /**
         * エラーか、ID登録済みの時にもう一度登録画面を表示する
         */
        return redirect(routes.RegisterController.registerView());
    }  
}