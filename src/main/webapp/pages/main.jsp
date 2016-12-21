<!DOCTYPE html>
<html ng-app="addrassApp">
<head>
    <title>AddrAss</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/user-list.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/user-profile.css">

    <script type="text/javascript" src="webjars/angularjs/1.5.9/angular.min.js"></script>
    <script type="text/javascript" src="webjars/angularjs/1.5.9/angular-resource.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular/app.module.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/core/user/user.module.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/core/user/user.service.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/core/hexify/hexify.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/user-list/user-list.module.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/user-list/user-list.component.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/user-profile/user-profile.module.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/user-profile/user-profile.component.js"></script>

    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/user-part/user-part.module.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/angular/user-part/user-part.controller.js"></script>


</head>
<body>
<div ng-controller="userPart">
    <div ng-switch on="selected">
        <user-list ng-switch-when="friends"></user-list>
        <user-profile ng-switch-when="profile"></user-profile>
    </div>
</div>
</body>
</html>
