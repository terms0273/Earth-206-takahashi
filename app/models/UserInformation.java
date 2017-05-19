package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.*;
import play.db.ebean.Model;
import play.mvc.*;

@Entity
public class UserInformation extends Model {
    @Id
    public Long id;
    public String userId;
    public String userName;
    public String password;

    
}