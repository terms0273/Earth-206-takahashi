package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.*;
import play.db.ebean.Model;
import play.mvc.*;

/**
 * データベースに@Idの中身を登録する
 * @author r-takahashi
 */
@Entity
public class UserInformation extends Model {
    @Id
    public Long id;
    public String userId;
    public String userName;
    public String password;
    public String master; 

    public static Finder<Long, UserInformation> find = new Finder<Long, UserInformation>(Long.class, UserInformation.class);
}