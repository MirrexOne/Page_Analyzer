package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;

import java.util.List;
import java.util.Map;

public final class UrlsPage extends BasePage {
    private final List<Url> urls;
    private Map<Long, UrlCheck> latestCheckups;

    public UrlsPage(List<Url> urls, Map<Long, UrlCheck> latestCheckups) {
        this.urls = urls;
        this.latestCheckups = latestCheckups;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public Map<Long, UrlCheck> getLatestCheckups() {
        return latestCheckups;
    }
}
