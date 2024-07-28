<#import "bloks/template.ftlh" as templ>
<@templ.page>
    Имя книги: ${bookInfo.name}<br>
    Автор: ${bookInfo.firstNameAuthor} ${bookInfo.lastNameAuthor}<br>
    Описание: ${bookInfo.description}<br>
    Жанр: ${bookInfo.genre}
</@templ.page>