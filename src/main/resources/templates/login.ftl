<#import "components/common.ftl" as e>
<#import "components/login.ftl" as l>

<@e.page>

${MSG?ifExists}
<@l.login "/login" false/>

</@e.page>