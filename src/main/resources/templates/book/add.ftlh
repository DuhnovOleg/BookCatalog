<#import "../bloks/template.ftlh" as templ>
<@templ.page>
    <h3>Добавить новую книгу</h3>
    <form action="/book/create" method="post" enctype="multipart/form-data">
        <#if errorNameBook>
            <div class="alert alert-danger" role="alert">Такая книга уже добавлена в библиотеку!</div>
        </#if>
        <#if bookAdded>
            <div class="alert alert-info" role="alert">Книга успешно добавлена.</div>
        </#if>
        Название книги: <input type="text" name="name"/><br><br>
        Жанр книги: <input type="text" name="genre"/><br><br>
        Описание книги: <input type="text" name="description"/><br><br>
        Имя автора: <input type="text" name="firstNameAuthor"/><br><br>
        Фамилия автора: <input type="text" name="lastNameAuthor"/><br><br>

        <input type="submit" value="Добавить книгу"/>
    </form>
</@templ.page>