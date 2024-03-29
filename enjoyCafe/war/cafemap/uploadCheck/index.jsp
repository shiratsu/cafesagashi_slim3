<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>upload Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
<hr />

<table>
<c:if test="${fn:length(dataList) > 0}">
<thead>
<tr><th>FileName</th><th>Bytes</th></tr>
</thead>
</c:if>
<tbody>
<c:forEach var="e" items="${dataList}">
<tr>
<td>${f:h(e.fileName)}</td><td>${f:h(e.length)}</td>

<c:set var="showUrl" value="show?key=${f:h(e.key)}&version=${e.version}"/>

<td><a href="${f:url(showUrl)}">Show</a></td>

</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
