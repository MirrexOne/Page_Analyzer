package hexlet.code.controller;

import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.rout.NamedRoutes;
import hexlet.code.util.Utils;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

import static hexlet.code.repository.UrlRepository.findByName;
import static io.javalin.rendering.template.TemplateUtil.model;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
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

    public static void create(Context context) throws URISyntaxException {

        try {
            String urlName = context.formParamAsClass("url", String.class)
                    .check(url ->
                            findByName(url).get().getName().equals(url), "Страница уже существует")
                    .get();
            URI uri = new URL(urlName).toURI();
            Url url = new Url();
            UrlRepository.save(url);
            context.sessionAttribute("flash", "Page successfully added");
            context.sessionAttribute("flash-type", "success");
            context.redirect(NamedRoutes.pathToSites());

        } catch (SQLException | URISyntaxException | MalformedURLException e) {
            context.redirect(NamedRoutes.pathToSites());
        }
    }
}
