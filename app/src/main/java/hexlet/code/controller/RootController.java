package hexlet.code.controller;

import io.javalin.http.Context;

public class RootController {
    public static void root(Context context) {
        context.render("root.jte");
    }
}
