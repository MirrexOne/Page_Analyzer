@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.rout.NamedRoutes
@import hexlet.code.util.Utils
@param UrlsPage page

@template.root(
page = page,
content = @`
    <section>

        <div class="container-lg mt-5">
            <h1>Websites</h1>

            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th>Name</th>
                    <th class="col-2">Lash check</th>
                    <th class="col-1">Response code</th>
                </tr>
                </thead>
                <tbody>
                @for(var url : page.getUrls())
                !{var check = page.getLatestCheckups().get(url.getId());}
                <tr>
                    <td>
                        ${url.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.pathToSite(url.getId())}">${url.getName()}</a>
                    </td>
                    <td>
                        ${check == null ? "" : Utils.formatTimestamp(check.getCreatedAt())}
                    </td>
                    <td>
                        ${check == null ? "" : String.valueOf(check.getStatusCode())}
                    </td>

                </tr>

                </tbody>
                @endfor
            </table>
        </div>

    </section>
`
)
