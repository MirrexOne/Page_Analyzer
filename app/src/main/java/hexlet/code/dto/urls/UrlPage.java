package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;

import java.util.List;

public final class UrlPage extends BasePage {

    private final Url url;
    private List<UrlCheck> urlCheckups;

    public UrlPage(Url url, List<UrlCheck> urlCheckups) {
        this.url = url;
        this.urlCheckups = urlCheckups;
    }

    public Url getUrl() {
        return url;
    }

    public List<UrlCheck> getUrlCheckups() {
        return urlCheckups;
    }
}
