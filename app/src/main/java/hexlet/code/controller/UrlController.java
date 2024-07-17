package hexlet.code.controller;

import hexlet.code.dto.HomePage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.rout.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static hexlet.code.util.Utils.formatUrl;
import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {

    public static void root(Context context) {
        HomePage page = new HomePage();
        page.setFlash(context.consumeSessionAttribute("flash"));
        page.setFlashType(context.consumeSessionAttribute("flash-type"));
        context.render("search.jte", model("page", page));
    }

    public static void showAll(Context context) throws SQLException {
        List<Url> entities = UrlRepository.getEntities();
        UrlsPage urlsPage = new UrlsPage(entities);
        urlsPage.setFlash(context.consumeSessionAttribute("flash"));
        urlsPage.setFlashType(context.consumeSessionAttribute("flash-type"));
        context.render("sites.jte", model("page", urlsPage));
    }

    public static void show(Context context) throws SQLException {
        long id = context.pathParamAsClass("id", Long.class).get();
        Url url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url with id: " + id + " not found"));
        UrlPage urlPage = new UrlPage(url);
        urlPage.setFlash(context.consumeSessionAttribute("flash"));
        urlPage.setFlashType(context.consumeSessionAttribute("flash-type"));
        context.render("site.jte", model("page", urlPage));
    }

    public static void create(Context context) throws SQLException {
        String initialUrl = context.formParam("url");
        URL validatedUrl;

        try {
            validatedUrl = new URL(initialUrl);
        } catch (Exception exception) {
            context.sessionAttribute("flash", "Incorrect URL");
            context.sessionAttribute("flash-type", "danger");
            context.redirect(NamedRoutes.rootPath());
            return;
        }

        String formattedUrl = formatUrl(validatedUrl);

        Url url = UrlRepository.findByName(formattedUrl).orElse(null);

        if (url != null) {
            context.sessionAttribute("flash", "Page already exists");
            context.sessionAttribute("flash-type", "info");
        } else {
            Url newUrl = new Url(formattedUrl);
            UrlRepository.save(newUrl);
            context.sessionAttribute("flash", "Страница успешно добавлена");
            context.sessionAttribute("flash-type", "success");
        }

        context.redirect(NamedRoutes.pathToSites());
    }
}
