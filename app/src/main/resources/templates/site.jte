@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.rout.NamedRoutes
@import static hexlet.code.util.Utils.formatTimestamp
@param UrlPage page

@template.root(
content = @`
    <div class="container-lg mt-5">
        <h1>Website: ${page.getUrl().getName()}</h1>

        <table class="table table-bordered table-hover mt-3">
            <tbody>
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Name</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Date of Creation</td>
                <td>${formatTimestamp(page.getUrl().getCreatedAt())}</td>
            </tr>
            </tbody>
        </table>

        <h2 class="mt-5">Checks</h2>
        <form method="post" action="">
            <button type="submit" class="btn btn-primary">Run check</button>
        </form>

        <table class="table table-bordered table-hover mt-3">
            <thead>
            <th class="col-1">ID</th>
            <th class="col-1">Response code</th>
            <th>title</th>
            <th>h1</th>
            <th>description</th>
            <th class="col-2">Date of check</th>
            </thead>
            <tbody>

            <tr>
                <td>

                </td>
                <td>

                </td>
                <td>

                </td>
                <td>

                </td>
                <td>

                </td>
                <td>

                </td>
            </tr>

            </tbody>
        </table>
    </div>
`
)