<#import "components/common.ftl" as e>
<#import "components/login.ftl" as l>

<@e.page>


        <div>
            <@l.logout />
            <span><a href="/user"> Users List </a></span>
        </div>

        <div>
            <form method="post">
                <input type="text" name="postText" placeholder="Write some post">
                <input type="text" name="postTag" placeholder="Post tag">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit">POST</button>
            </form>
        </div>


<div>POSTS</div>

        <div>
            <form method="get" action="/main">
                <input type="text" name="filter">
                <button>FILTER POSTS</button>
            </form>
        </div>

        <#list posts as post>
        <div>

            <b>${post.id}</b>
            <b>${post.postText}</b>
            <i>${post.postTag}</i>
            <strong>${post.authorName}</strong>

        </div>
        <#else>
            No Posts
        </#list>

</@e.page>