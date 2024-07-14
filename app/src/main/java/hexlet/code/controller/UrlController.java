package hexlet.code.controller;

import io.javalin.http.Context;

public class UrlController {

    public static void root(Context context) {
        context.render("search.jte");
    }

    public static void show(Context context) {
        context.render("sites.jte");
    }
}
