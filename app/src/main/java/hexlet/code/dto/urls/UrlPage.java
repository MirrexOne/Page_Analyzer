package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;

import java.util.List;

public class UrlPage extends BasePage {
    private final Url url;
    private List<UrlCheck> urlCheckups;

    public UrlPage(Url url, List<UrlCheck> urlCheckups) {
        this.url = url;
        this.urlCheckups = urlCheckups;
    }

    /**
     * Get some URL.
     * @return url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * Get all checkups.
     * @return checkups list
     */
    public List<UrlCheck> getUrlCheckups() {
        return urlCheckups;
    }
}
