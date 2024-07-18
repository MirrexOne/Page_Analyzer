package hexlet.code.rout;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    public static String pathToSites() {
        return "/urls";
    }

    public static String pathToSite(Long id) {
        return pathToSite(String.valueOf(id));
    }
    public static String pathToSite(String id) {
        return "/urls/" + id;
    }

    public static String pathToCheck(Long id) {
        return pathToCheck(String.valueOf(id));
    }

    public static String pathToCheck(String id) {
        return "/urls/" + id + "/checks";
    }
}
