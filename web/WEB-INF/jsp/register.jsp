<%-- 
    Document   : register
    Created on : 2018-5-17, 22:15:10
    Author     : hasee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>Register</title>
        <base href="${pageContext.request.contextPath}/"/>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="resource/css/css/register.css" />
        <link rel="stylesheet" href="resource/css/edbox.css"/>
    </head>

    <body>
        <div class="title">
            <div id="to-main" style="cursor: pointer;">
                <img src="resource/imags/logo.png" width="80px" height="80px" alt="">
                <span>DonkeyKong</span> 
            </div>
        </div>
        <div class="login">
            <form action="users/register" method="POST" id="valid-form">
                <span class="welcome-login">Register</span>
                <br>          
                <input id="id" name="userId" onchange="checkid(this)"  class="id-input" type="text" placeholder="Enter your ID" />
                <br>
                <span class="id-error error"></span>
                <br/>
                <input id="pass" name="password" onchange="checkpass(this)" class="pass-input"  type="password"  placeholder="Enter your Password" />
                <br>
                <span class="pass-error error"></span>
                <br/>
                <input id="tel" name="tel" onchange="checkTel(this)"  class="tel-input" type="text" placeholder="Enter your phone number" />
                <br>
                <span class="tel-error error"></span>
                <br/>
                <input id="mail" name="email" onchange="checkMail(this)" class="mail-input" type="text" placeholder="Enter your e-mail" />           
                <br>
                <span class="mail-error error"></span>
                <br/>
                <button type="submit" class="btn-regist" id="register">Register</button>
                <button type="reset" class="btn-reset">Reset</button><br/>
                <span class="regist-error" style="color: red;">${message}</span>
            </form>
        </div>
    </body>
    <script src="resource/js/jquery-2.1.0.js"></script>
    <script src="resource/js/jquery.edbox.js"></script>
    <script>

                    var unameRegex = /^\w{3,15}$/;
                    var upassRegex = /^\w{4,12}$/;
                    var telRegex = /^1[34578]\d{9}$/;
                    var mailRegex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

                    $('.error').hide();

                    $(function (node) {
                        checkid(node);
                        checkpass(node);
                        checkTel(node);
                        checkMail(node);
                    });

                    function checkid(node) {
                        var uname = node.value;
                        var idErr = $('.id-error');
                        if (uname === '') {
                            idErr.hide();
                        } else {
                            if (!unameRegex.test(uname)) {
                                idErr.show();
                                idErr.html('Invalid Id!');
                            } else {
                                idErr.hide();
                            }
                        }

                    }
                    function checkpass(node) {
                        var upass = node.value;
                        var passErr = $('.pass-error');
                        if (upass === '') {
                            passErr.hide();
                        } else {
                            if (!upassRegex.test(upass)) {
                                passErr.show();
                                passErr.html('Invalid Password!');
                            } else {
                                passErr.hide();
                            }
                        }
                    }
                    function checkTel(node) {
                        var utel = node.value;
                        var telErr = $('.tel-error');
                        if (utel != null)
                            if (!telRegex.test(utel)) {
                                telErr.show();
                                telErr.html('Invalid Telephone!');
                            } else {
                                telErr.hide();
                            }
                    }
                    function checkMail(node) {
                        var umail = node.value;
                        var mailErr = $('.mail-error');
                        if (umail != null)
                            if (!mailRegex.test(umail)) {
                                mailErr.show();
                                mailErr.html('Invalid E-mail!');
                            } else {
                                mailErr.hide();
                            }
                    }
                    //阻止表单提交
                    $('#register').click(function (check) {
                        var idval = $('.id-input').val();
                        var pval = $('.pass-input').val();
                        var tval = $('.tel-input').val();
                        var mval = $('.mail-input').val();
                        var idErr = $('.id-error');
                        var passErr = $('.pass-error');
                        var telErr = $('.tel-error');
                        var mailErr = $('.mail-error');
                        if (idval == "") {
                            $.edbox({warning:"input should not be blank!"});
                            $('.id-input').focus();
                            check.preventDefault();
                        }
                        if (pval == "") {
                            $.edbox({warning:"input should not be blank!"});
                            $('.pass-input').focus();
                            check.preventDefault();
                        }
                        if (tval == "") {
                            $.edbox({warning:"input should not be blank!"});
                            $('.tel-input').focus();
                            check.preventDefault();
                        }
                        if (mval == "") {
                           $.edbox({warning:"input should not be blank!"});
                            $('.mail-input').focus();
                            check.preventDefault();
                        }

                        if (!unameRegex.test(idval)) {
                            idErr.show();
                            idErr.html('Invalid ID');
                            $('.id-input').focus();
                            check.preventDefault();
                        } else {
                            idErr.hide();
                        }
                        if (!upassRegex.test(pval)) {
                            passErr.show();
                            passErr.html('Invalid Password');
                            $('.pass-input').focus();
                            check.preventDefault();
                        } else {
                            passErr.hide();
                        }
                        if (tval != null)
                            if (!telRegex.test(tval)) {
                                telErr.show();
                                telErr.html('Invalid Telephone!');
                                $('.tel-input').focus();
                                check.preventDefault();
                            } else {
                                telErr.hide();
                            }
                        if (mval != null)
                            if (!mailRegex.test(mval)) {
                                mailErr.show();
                                mailErr.html('Invalid E-mail!');
                                $('.mail-input').focus();
                                check.preventDefault();
                            } else {
                                mailErr.hide();
                            }

                    });
                    $('#to-main').click(function () {
                        window.location.href = 'redirect/main';
                    });
    </script>
</html>
