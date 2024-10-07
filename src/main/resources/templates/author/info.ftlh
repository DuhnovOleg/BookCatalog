<#import "../bloks/template.ftlh" as templ>
<@templ.page>
    <#list books as book>
        <div>
            Имя книги: ${book.name}<br>
            Описание: ${book.description}<br>
            Жанр: ${book.genre}<br>
        </div>
    </#list>
</@templ.page>