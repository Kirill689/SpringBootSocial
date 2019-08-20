<#import "components/common.ftl" as e>
<#import "components/login.ftl" as l>

<@e.page>

<div>LOGIN PAGE</div>

<@l.login "/login" />

<div>If you not have an account you can create one here -> <a href="/registration">Registration</a></div>

</@e.page>