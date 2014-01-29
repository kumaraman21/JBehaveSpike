<#ftl strip_whitespace=true>
<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>

  $(document).ready(function() {
    $('div.step').each(function() {
    	var text = $(this).text();
    	$(this).html(text.replace(/(\w+\.(xml|txt))/, '<a href="../../classes/input/$1">$1</a>')); 
	});
  });
 
  </script>
<head>
<title>${name}</title>
<style type="text/css" media="all">
@import url( "./style/jbehave-core.css" );
</style>
</head>
<body>
<#if format == "html">
${body}
<#else>
<#assign brushFormat = format> <#if format == "stats"><#assign brushFormat = "plain"> </#if>
<script type="syntaxhighlighter" class="brush: ${brushFormat}"><#if format != "txt" || !body.contains("</script>")><![CDATA[
${body}
]]><#else>
${body?html}
</#if></script>
</#if>
</body>
<#include "./sh.ftl">
</html>
