package controllers;

import auth.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.UserInformation;
import play.*;
import play.mvc.*;
import views.html.*;

public class EditDeleteController extends Controller {
    /**
     *
     *ユーザ削除部分
     * 
     */
    /**
     * 管理者かどうかを調べ、違ったら返り討ち
     * @return 
     */
    @Security.Authenticated(AdminSecured.class)
    
    /**
     * deleteへのアクセスがあった時、画面を表示する
     * @return 
     */
    public static Result deleteView() {
        List<UserInformation> list = UserInformation.find.all();
        /**
         * delete_flagが0のときのユーザをとりだす、リストに登録する
         */
        List<UserInformation> list2 =new ArrayList();
        for(UserInformation user : list){
            /**
             * delete_flag=0 リストを表示
             */
            if(user.delete_flag == 0){
                list2.add(user);
            }            
        }
        return ok(delete.render("ユーザ削除", list2));               
    }
    
    @Security.Authenticated(AdminSecured.class)
    /**
     * 「削除」ボタンが押されたときに実行する
     * 論理削除　delete_flagなるものを使う
     * 削除後は、削除したユーザの情報を画面に表示しなくする
     * @return 
     */
    public static Result doDelete(Long id){
        JFrame frame = new JFrame();
        int option = JOptionPane.showConfirmDialog(frame,
                "本当に削除しますか？", "削除の確認", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == 0) {
            UserInformation request = UserInformation.find.byId(id);
            request.delete_flag = 1;
            request.update();
        }
        return redirect(routes.Application.index());
    }
    
    /**
     * 
     * 
     * ユーザ編集部分
     * 
     */
    
    /**
    public static Result editView() {        
        return ok(edit.render("ユーザ編集"));      // edit.scala.htmlと連携する
    }
    
    public static Result doEdit(){
        return redirect(routes.Application.index());
    }
    */
    
}