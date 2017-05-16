import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
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


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }
    
    /* 正常系 */
    /*ログイン画面処理*/
    
   // ログイン成功時、メイン画面へのリダイレクト
   @Test
    public void rogin{
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentType(html)).isEqualTo("text/html");
    }
    
    // ログイン成功時、セッションに値が入っている
    
    // パスワード不正ログイン画面に戻される
    
    // ログイン画面で、ユーザー登録へ移動できる
    
    // ホーム画面より、ユーザー登録へ移動できる
    
    //URLへのアクセス時、ログイン画面が表在される
    
    // ログイン時、正しくログインできる
    
    // id,パスワード入力欄へ入力できる
    
    // CSRF対策をしている
    
    
    /*ユーザー機能処理*/
    // パスワード変更時、既存と新規のパスワード入力項目がある
    
    // ユーザー機能より、パスワード変更画面への移動が行われる		

    // 登録成功時、アカウント作成完了を表す画面を表示する		

    // ユーザー削除時、確認を促すメッセージを表示する		

    // ユーザー登録時、専用の画面が表示される		

    // 管理ユーザがユーザ登録削除できる		

    // パスワード編集時、パスワードの入力欄が2つある		
 
    // パスワード編集時、パスワードの入力欄2つに入力することで編集完了		

    // CSRF対策をしている
    
    
    /* ログアウト処理 */
    // ログアウトボタンを押すと、確認を促すメッセージが表示される		
 
    // ログアウト成功時、完了画面へ移動する		
 
    // セッションが破棄される		
 
    // ログアウト完了画面で、再ログインできる		
 
    // CSRF対策をしている
    
    
    /* 異常系 */
    /* ログイン処理 */
    // ログインID不正ログイン画面に戻される		
 
    // ログイン失敗時エラーメッセージが表示される		
 
    // ログイン失敗時、セッションが破棄される		
 
    // ログイン失敗時、idが間違いの場合、パスワードがクリアされる		
 
    // ログイン失敗時、パスワードが間違いの場合、パスワードがクリアされる		

    // ログインしていない時、ログアウトできない
    
    /*ユーザー機能処理*/
    // 既に使用されているパスワードをはじく
    
    // 他人のユーザー編集は行えない
    
    // 登録失敗時、失敗を表す画面と再入力を促す画面を表示する
    
    // 一般ユーザがユーザ登録削除できない


}
