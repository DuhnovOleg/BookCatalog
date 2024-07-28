<#import "bloks/template.ftlh" as templ>
<@templ.page>
    <#list allAuthor as author>
        <div>
            <a href="/author/info/${author.id}" >${author.firstName} ${author.lastName}</a><br>
        </div>
    <#else>
        <h3>Авторов нет</h3>
    </#list>
</@templ.page>