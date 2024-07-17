package hexlet.code.controller;

import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.rout.NamedRoutes;
import io.javalin.http.Context;
import static io.javalin.rendering.template.TemplateUtil.model;

import java.sql.SQLException;
import java.util.List;

public class UrlController {

    public static void root(Context context) {
        context.render("search.jte");
    }

    public static void showAll(Context context) throws SQLException {
        List<Url> entities = UrlRepository.getEntities();
        UrlsPage urlsPage = new UrlsPage(entities);
        urlsPage.setFlash(context.consumeSessionAttribute("flash"));
        urlsPage.setFlashType(context.consumeSessionAttribute("flash-type"));
        context.render("sites.jte", model("page", urlsPage));
    }

    public static void create(Context context) throws SQLException {

            String name = context.formParamAsClass("url", String.class).get();
            Url url = new Url(name);
            UrlRepository.save(url);
            context.sessionAttribute("flash", "Page successfully added");
            context.sessionAttribute("flash-type", "success");
            context.redirect(NamedRoutes.pathToSites());

    }
}
