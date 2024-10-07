<#import "../bloks/template.ftlh" as templ>
<@templ.page>
    <#list authors as author>
        <div>
            <a href="/author/info/${author.id}" >Автор: ${author.firstName} ${author.lastName}</a><br>
        </div>
    <#else>
        <h3>Авторов не найдено</h3>
    </#list>

    <#list books as book>
        <div>
            <a href="/book/info/${book.id}">Название книги: ${book.name}</a>
        </div>
    <#else>
        <h3>Книг не найдено</h3>
    </#list>
</@templ.page>