<#import "../bloks/template.ftlh" as templ>
<@templ.page>
    Имя книги: ${book.name}<br>
    Автор: ${book.firstNameAuthor} ${book.lastNameAuthor}<br>
    Описание: ${book.description}<br>
    Жанр: ${book.genre}
</@templ.page>