@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.Utils
@param UrlsPage page

@template.root(
content = @`
    <section>

        <div class="container-lg mt-5">
            <h1>Сайты</h1>

            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th>Имя</th>
                    <th class="col-2">Последняя проверка</th>
                    <th class="col-1">Код ответа</th>
                </tr>
                </thead>
                <tbody>
                @for(var url : page.getUrls())
                <tr>
                    <td>
                        ${url.getId()}
                    </td>
                    <td>
                        <a>${url.getName()}</a>
                    </td>
                    <td>
                        ${Utils.formatTimestamp(url.getCreatedAt())}
                    </td>
                    <td>

                    </td>

                </tr>

                </tbody>
                @endfor
            </table>
        </div>

    </section>
`
)
