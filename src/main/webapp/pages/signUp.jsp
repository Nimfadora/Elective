<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="logo-container">
        <div class="text-center">
            <img class="logo" src="../img/logo.png">
        </div>
    </div>
    <div class="background-container">
        <div class="container  form-container">
                <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>
                        </div>
                        <div class="panel-body">
                            <form id="signupform" action="/signUp/student" class="form-horizontal" role="form">

                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="col-md-3 control-label">Email</label>

                                    <div class="col-md-9">
                                        <input type="text" id="email" class="form-control" name="email" placeholder="Email Address">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="name" class="col-md-3 control-label">Full Name</label>

                                    <div class="col-md-9">
                                        <input type="text" id="name" class="form-control" name="name" placeholder="Full Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-md-3 control-label">Password</label>

                                    <div class="col-md-9">
                                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                                    </div>
                                </div>

                                <!--<div class="form-group">-->
                                <!--<label for="icode" class="col-md-3 control-label">Invitation Code</label>-->
                                <!--<div class="col-md-9">-->
                                <!--<input type="text" class="form-control" name="icode" placeholder="">-->
                                <!--</div>-->
                                <!--</div>-->

                                <div class="form-group">
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="button" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp Sign Up
                                        </button>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                            Already have an account?
                                            <a href="/">
                                                Log In Here
                                            </a>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</body>
</html>