package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class LoginController extends Controller {

    public static Result loginView() {
        return ok(login.render());
    }

}