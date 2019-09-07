<#import "components/common.ftl" as e>
<#import "components/login.ftl" as l>

<@e.page>

<div class="mb-3">REGISTRATION PAGE</div>
${MSG?ifExists}
<@l.login "/registration" true/>

</@e.page>