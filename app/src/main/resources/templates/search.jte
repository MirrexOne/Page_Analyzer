@import hexlet.code.rout.NamedRoutes
@import hexlet.code.dto.HomePage
@param HomePage page

@template.root(
page = page,
content = @`
        <div class="container-fluid bg-dark p-5">
            <div class="row">
                <div class="col-md-10 col-lg-8 mx-auto text-white">
                    <h1 class="display-3 mb-0">Page Analyzer</h1>
                    <p class="lead">Free to check websites for SEO suitability</p>
                    <form action="${NamedRoutes.pathToSites()}" method="post" class="rss-form text-body">
                        <div class="row">
                            <div class="col">
                                <div class="form-floating">
                                    <input id="url-input" autofocus type="text" required name="url" aria-label="url"
                                           class="form-control w-100" placeholder="link" autocomplete="off">
                                    <label for="url-input">Link</label>
                                </div>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="h-100 btn btn-lg btn-primary px-sm-5">Проверить</button>
                            </div>
                        </div>
                    </form>
                    <p class="mt-2 mb-0 text-muted">Pattern: https://www.example.com</p>
                </div>
            </div>
        </div>
`
)
