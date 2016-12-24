<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/registration.css">
    <script type="application/javascript" defer
            src="${pageContext.request.contextPath}/resources/js/registration.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.3/jquery.min.js"></script>
</head>

<body>


<div class="form">
    <div id="alreadyExists" class="err" style="display: none">
        <span>User Already Exists</span>
    </div>
    <div id="incorrectCredentials" class="err" style="display: none">
        <span>Incorrect credentials</span>
    </div>

    <div class="tab-group">
        <div class="tab" href="#signup">
            <div class="tab-name">SIGN UP</div>
        </div>
        <div id="loginTab" class="tab active" href="#login">
            <div class="tab-name">SIGN IN</div>
        </div>
    </div>


    <div class="tab-switcher">
        <div id="signup">
            <div class="tab-content">
                <div class="tab-main-name">Sign Up for Free</div>


                <div class="field-row">
                    <div class="field-wrap">
                        <label>
                            Login<span class="req">*</span>
                        </label>
                        <input id="regLogin" name="userLogin" type="text" required autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Password<span class="req">*</span>
                        </label>
                        <input id="regPassword" name="userPassword" type="password" required
                               autocomplete="new-password"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Name<span class="req">*</span>
                    </label>
                    <input id="regName" name="userName" type="text" required autocomplete="off"/>
                </div>

                <div class="button-wrapper">
                    <button type="button" class="button-little" id="extra">More</button>
                </div>

                <div id="extra-fields">
                    <div class="field-wrap">
                        <label>
                            Email Address
                        </label>
                        <input id="regEmail" name="userEmailField" type="email" autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Phone
                        </label>
                        <input id="regPhone" name="userPhoneField" type="text" autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Organization
                        </label>
                        <input id="regOrganization" name="userOrganizationField" type="text" autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Address
                        </label>
                        <input id="regAddress" name="userAddressField" type="text" autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Notes
                        </label>
                        <input id="regNotes" name="userNotesField" type="text" autocomplete="off"/>
                    </div>

                </div>
                <div class="button-wrapper">
                    <button id="registerBtn" type="submit" class="button-big">
                        GET STARTED
                    </button>
                </div>
            </div>

        </div>


        <div id="login">
            <div class="tab-content">
                <div class="tab-main-name">Welcome back!</div>

                <form id="loginForm" method="post">

                    <div class="field-wrap">
                        <label>
                            Login<span class="req">*</span>
                        </label>
                        <input id="logLogin" name="j_username" type="text" required autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Password<span class="req">*</span>
                        </label>
                        <input id="logPassword" name="j_password" type="password" required autocomplete="new-password"/>
                    </div>

                    <div class="button-wrapper">
                        <button id="loginBtn" type="submit" class="button-big">
                            LOG IN
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div><!-- tab-content -->
</body>

</html>