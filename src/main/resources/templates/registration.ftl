<#import "components/common.ftl" as e>
<#import "components/login.ftl" as l>

<@e.page>

REGISTRATION PAGE
${MSG?ifExists}
<@l.login "/registration" />

</@e.page>