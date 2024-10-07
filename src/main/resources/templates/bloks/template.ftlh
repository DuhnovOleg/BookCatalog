<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">

        <title>Book catalog</title>
    </head>
    <body>

    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="/">
                <span class="navbar-brand mb-0 h1">Book catalog</span>
            </a>
            <form action="/search">
                <input type="text" name="searchText"/>
                <input type="submit" value="Искать"/>
            </form>
            <div class="dropdown text-end">
                <a href="/">Главная</a>
            </div>
            <div class="dropdown text-end">
                <a href="/author/all">Авторы</a>
            </div>
            <div class="dropdown text-end">
                <a href="/book/all">Книги</a>
            </div>
            <button class="btn btn-light my-2 my-sm-0" type="button"
                    onclick="window.location.href = '/addBook'">
                Добавить книгу
            </button>

        </div>
    </nav>

    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>
