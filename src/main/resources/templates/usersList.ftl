<#import "components/common.ftl" as e>

<@e.page>

List Of Users


<table>

    <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>...</th>
        </tr>
    </thead>

    <tbody>
    <#list allUsers as u>
        <tr>
            <td>${u.username}</td>
            <td><#list u.roles as r>${r}<#sep>, </#list></td>
            <td><a href="user/${u.id}">Edit user</a></td>
        </tr>
    </#list>
    </tbody>



</table>




</@e.page>