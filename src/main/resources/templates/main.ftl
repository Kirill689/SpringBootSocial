<#import "components/common.ftl" as e>

<@e.page>

        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/main" class="form-inline">
                    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Filter Posts By Tag">
                    <button type="submit" class="btn btn-primary ml-3">FILTER</button>
                </form>
            </div>
        </div>

        <a class="btn btn-primary mb-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Post Something On Wall
        </a>

        <div class="collapse <#if post??>show</#if>" id="collapseExample">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">

                <div class="form-group">
                <input class="form-control ${(postTextError??)?string('is-invalid', '')}" type="text" name="postText" placeholder="Write some post" value="<#if post??>${post.postText}</#if>">
                    <#if postTextError??>
                    <span class="invalid-feedback">
                        ${postTextError}
                    </span>
                    </#if>
                </div>


                <div class="form-group">
                <input class="form-control ${(postTagError??)?string('is-invalid', '')}" type="text" name="postTag" placeholder="Post tag" value="<#if post??>${post.postTag}</#if>">
                    <#if postTagError??>
                    <span class="invalid-feedback">
                        ${postTagError}
                    </span>
                    </#if>
                </div>

                <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                <button class="btn btn-primary" type="submit">POST</button>
                </div>

            </form>
        </div>
        </div>


<div>POSTS</div>


    <div class="card-columns">
        <#list posts as post>
        <div class="card my-2">

            <#if post.filename?exists>
                <img src="/img/${post.filename}" class="card-img-top">
            </#if>

            <div class="m-2">
            <b>${post.postText}</b>
            <i>${post.postTag}</i>
            </div>

            <div class="card-footer text-muted">
            ${post.authorName}
            </div>

        </div>
        <#else>

            No Posts Available

        </#list>
    </div>

</@e.page>