<#import "components/common.ftl" as e>

<@e.page>
Edit Users

<form action="/user" method="post">

    <input type="text" name="username" value="${userToEdit.username}">
    <input type="hidden" name="userId" value="${userToEdit.id}">
    <#list userRoles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${userToEdit.roles?seq_contains(role)?string("checked","")}>${role}</label>
    </div>
    </#list>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" name="Edit User">

</form>


</@e.page>