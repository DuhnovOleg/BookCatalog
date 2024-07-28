<#import "bloks/template.ftlh" as templ>
<@templ.page>
    <#list listBooks as book>
        <div>
            <a href="/book/info/${book.id}">Имя книги: ${book.name}</a>
        </div>
    <#else>
        <h3>Товаров нет</h3>
    </#list>
</@templ.page>