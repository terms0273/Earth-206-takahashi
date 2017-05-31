import apps.FakeApp;
import ch.qos.logback.core.Context;
import com.avaje.ebean.Ebean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.routes;
import models.UserInformation;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static play.mvc.Http.Status.OK;
import static scalaz.NonEmptyList$class.map;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest extends FakeApp{
    
    /**
     *  正常系 
    /* ログイン画面処理
     */  
    
    /**
     * URLへのアクセス時、ログイン画面が表示される
     * viewテスト
     */   
    @Test
    public void loginTest(){
        Result result = route(fakeRequest(GET, "/login"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(charset(result)).contains("ログイン");
    }
   
    /**
     * ログイン成功時、メイン画面へのリダイレクト
     * viewテスト
     * @return 
     */   
   @Test
    public void loginSuccess(){
        Result result = route(fakeRequest(POST, "/main"));
        assertThat(status(result)).isEqualTo(303);
        assertThat(redirectLocation(result)).isEqualTo("/main");
    }
    
    /**
     * ログイン成功時、セッションに値が入っている（ログイン時、正しくログインできる　データベースの情報と合っているか調べる）
     * Controllerテスト
     */    
    @Test
    public void sessionTest() {
        Result result = route(fakeRequest(GET, "/index"));
        Http.Session temp=session (result);
        assertThat(temp.get("key")).isEqualTo("id");      
    }
   
     
    /**
     * パスワード不正で、ログイン画面に戻される 　ログイン画面への転送確認、転送先が正しいか
     * Controllerテスト
     */
    @Test
    public void reLogin(){
        Result result = route(fakeRequest(POST, "/login"));
        assertThat(status(result)).isEqualTo(303);
        assertThat(redirectLocation(result)).isEqualTo("/login");
    }
    
    /**
     * ログイン画面で、ユーザー登録へ移動できる
     * Controller、Viewテスト
     */
    @Test
     public void loginUserTest(){
        Result result = route(fakeRequest(GET, "/register"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(charset(result)).contains("ユーザー");   
     }
     
     /**
      * ホーム画面より、ユーザー登録へ移動できる
      *Controller,Viewテスト 
      */
    @Test
    public void mainUser(){
        Result result = route(fakeRequest(GET, "/user/register"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("ユーザー");   
     }  
     
    /**
     *  CSRF対策をしている
     * htmlにトークンを埋め込む？
     * Viewテスト
     */    
/*    @Test
    public void CSRFTest() throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        map
    }
*/    
    /** 
     * ユーザー機能処理
     * パスワード変更時、既存と新規のパスワード入力項目がある
     * Viewテスト
     */ 
    @Test
    public void passChange(){
        Result result = route(fakeRequest(GET, "/user/edit"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(charset(result)).contains("old_pass");   
        assertThat(charset(result)).contains("new_pass");
    }
    
    /**
     * ユーザー機能より、パスワード変更画面への移動が行われる
     * Controller、Viewテスト
     */ 		
    @Test
    public void userPass(){
        Result result = route(fakeRequest(GET, "/user"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("パスワード変更");
    }
    
    /**
     * 登録成功時、アカウント作成完了を表す画面を表示する
     * Controllerテスト
     */ 		
    @Test
    public void accountRegistered(){
        Result result = route(fakeRequest(GET, "/main"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("登録完了");
    }
    
    /**
     * ユーザー削除時、確認を促すメッセージを表示する
     * Controllerテスト
     */ 		
    @Test
    public void accountDelete(){
        Result result = route(fakeRequest(GET, "/user/delete"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("削除しますか？");
    }
    
    /**
     * ユーザー登録時、専用の画面が表示される
     * Controller、Viewテスト
     */ 		
    @Test
    public void accountRegister(){
        Result result = route(fakeRequest(GET, "/user/register"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("ユーザー");
    }
    
    /**
     * 管理ユーザがユーザ登録削除できる
     * Controllerテスト
     */ 		
    
    
    /**
     * パスワード編集時、パスワードの入力欄2つに入力することで編集完了
     * Controller、Modelテスト
     */ 		
    
    
    /**
     * CSRF対策をしている
     */ 
    
    
    /**
     * データベースから呼び出す値が正しい
     * Modelテスト
     */ 
    @Test
    public void modelTest(){
        // ユーザー情報を入力する
        Ebean.execute(Ebean.createSqlUpdate("insert into user_information(id, user_name, password)values(1, 'r-takahashi', '1234');"));
        UserInformation user = UserInformation.find.byId(1L);
        
        // 値の正誤判断
        assertThat(user.userName).isEqualTo("r-takahashi");
        assertThat(user.password).isEqualTo("1234");
    }
    
    /**
     * 呼び出す値がデータベースに入る
     * Modelテスト
     */ 
    @Test
    public void modelTest2(){
        // 値を入力し、データベースに入る
        start(fakeApplication(inMemoryDatabase()));
        UserInformation user_info = new UserInformation();
        user_info.id = 1L;
        user_info.userName = "r-takahashi";
        user_info.password = "1234";
        user_info.save();
        
        // 値の正誤判断
        Ebean.execute(Ebean.createSqlUpdate("insert into UserInformation(id, user_name, password)values(1, 'r-takahashi', '1234');"));
        UserInformation user = UserInformation.find.byId(1L);
        assertThat(user.userName).isEqualTo("r-takahashi");
        assertThat(user.password).isEqualTo("1234");
    }
    
    /**
     * ログアウト処理
     * ログアウトボタンを押すと、確認を促すメッセージが表示される
     */
     		
    @Test
    public void accountLogout(){
        Result result = route(fakeRequest(GET, "/main"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("ログアウト");
    }
    
    /**
     * ログアウト成功時、完了画面へ移動する
     */ 		
    @Test
    public void accountLogoutSuccess(){
        Result result = route(fakeRequest(GET, "/logout"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("ログアウトしました");
    }
    
    /**
     * セッションが破棄される	insert
     */ 	

     
    /**
     * ログアウト完了画面で、再ログインできる
     */ 		

            
    /**
     * CSRF対策をしている
     */ 

            
    
    /* 異常系 */
    /* ログイン処理 */
    // ログインID不正ログイン画面に戻される		

            
    // ログイン失敗時エラーメッセージが表示される		
/**
 * 
    bindRequest
    Map<String, String> map = new HashMap<String, String>();
*/    
    // ログイン失敗時、セッションが破棄される		

            
    // ログイン失敗時、idが間違いの場合、パスワードがクリアされる		

            
    // ログイン失敗時、パスワードが間違いの場合、パスワードがクリアされる		

            
    // ログインしていない時、ログアウトできない

            
    /*ユーザー機能処理*/
    // 既に使用されているパスワードをはじく

    
    // 他人のユーザー編集は行えない

    
    // 登録失敗時、失敗を表す画面と再入力を促す画面を表示する

   
    public void accountUnregistered(){
        Result result = route(fakeRequest(GET, "/register"));
        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(charset(result)).contains("入力内容確認");
    }
    
    // 一般ユーザがユーザ登録削除できない
    
        
    }
