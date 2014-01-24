<%-- 
    Document   : createEnvironment
    Created on : 15-ene-2014, 17:58:22
    Author     : oscar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Panacea Web</title>

        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- basic styles -->

        <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="../assets/css/font-awesome.min.css" />

        <!--[if IE 7]>
          <link rel="stylesheet" href="../assets/css/font-awesome-ie7.min.css" />
        <![endif]-->

        <!-- page specific plugin styles -->

        <!-- fonts -->

        <link rel="stylesheet" href="../assets/css/ace-fonts.css" />

        <!-- ace styles -->

        <link rel="stylesheet" href="../assets/css/ace.min.css" />
        <link rel="stylesheet" href="../assets/css/ace-rtl.min.css" />
        <link rel="stylesheet" href="../assets/css/ace-skins.min.css" />

        <link rel="stylesheet" href="../assets/css/validationEngine.jquery.css" />
        <!--[if lte IE 8]>
          <link rel="stylesheet" href="../assets/css/ace-ie.min.css" />
        <![endif]-->

        <!-- inline styles related to this page -->

        <!-- ace settings handler -->

        <script src="../assets/js/ace-extra.min.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

        <!--[if lt IE 9]>
        <script src="../assets/js/html5shiv.js"></script>
        <script src="../assets/js/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <div class="navbar navbar-default" id="navbar">
            <script type="text/javascript">
                try {
                    ace.settings.check('navbar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="navbar-container" id="navbar-container">
                <div class="navbar-header pull-left">
                    <a href="#" class="navbar-brand">
                        <small>
                            <i class="icon-tasks"></i>
                            Panacea-web: Continuous Integration for everyone
                        </small>
                    </a><!-- /.brand -->
                </div><!-- /.navbar-header -->

                <div class="navbar-header pull-right" role="navigation">

                    <ul class="nav ace-nav">

                        <li class="light-blue">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <span class="user-info">
                                    <i class="icon-th"></i>
                                    Actions
                                </span>

                                <i class="icon-caret-down"></i>
                            </a>

                            <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                                <li>
                                    <a href="#">
                                        <i class="icon-user"></i>
                                        Profile
                                    </a>
                                </li>

                                <li class="divider"></li>

                                <li>
                                    <a href="#">
                                        <i class="icon-off"></i>
                                        Logout
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul><!-- /.ace-nav -->
                </div><!-- /.navbar-header -->
            </div><!-- /.container -->
        </div>

        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.check('main-container', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="main-container-inner">
                <a class="menu-toggler" id="menu-toggler" href="#">
                    <span class="menu-text"></span>
                </a>

                <div class="sidebar" id="sidebar">
                    <script type="text/javascript">
                        try {
                            ace.settings.check('sidebar', 'fixed')
                        } catch (e) {
                        }
                    </script>

                    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                            <button class="btn btn-success">
                                <i class="icon-desktop"></i>
                            </button>
                        </div>

                        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                            <span class="btn btn-success"></span>
                        </div>
                    </div><!-- #sidebar-shortcuts -->

                    <jsp:include page="menu.jsp" />

                    <div class="sidebar-collapse" id="sidebar-collapse">
                        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
                    </div>

                    <script type="text/javascript">
                        try {
                            ace.settings.check('sidebar', 'collapsed')
                        } catch (e) {
                        }
                    </script>
                </div>

                <div class="main-content">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try {
                                ace.settings.check('breadcrumbs', 'fixed')
                            } catch (e) {
                            }
                        </script>

                        <ul class="breadcrumb">
                            <li>
                                <a href="<c:url value="/home/dashboard" />">
                                    <i class="icon-desktop"></i>
                                    &nbsp;Home
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/project/listProjects" />">
                                    <i class="icon-bars"></i>
                                    &nbsp;Projects
                                </a>
                            </li>
                            <li>
                                <a href="../environment/listEnvironments?projectId=${project.id}">
                                    <i class="icon-cogs"></i>
                                    &nbsp;Environments
                                </a>
                            </li>
                            <li class="active">
                                <i class="icon-cog"></i>
                                Edit environment
                            </li>
                        </ul><!-- .breadcrumb -->
                    </div>
                    <div class="page-content">
                        <div class="page-header">
                            <h1>
                                New environment for project: ${project.name}
                                <small>
                                    <i class="icon-double-angle-right"></i>
                                </small>
                            </h1>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <form:form id="myForm" name="myForm" method="post" action="saveChangesEnvironment" modelAttribute="environment" class="form-horizontal" onsubmit="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' }); return true;">  
                                    <div class="form-group">
                                        <label for="name" class="col-sm-3 control-label no-padding-right">New environment name:</label>
                                        <div class="col-sm-9">
                                            <span class="block input-icon input-icon-right">
                                                <input type='hidden' name='projectId' id='projectId' value='${project.id}' />
                                                <input type='hidden' name='environmentId' id='projectId' value='${environment.id}' />
                                                <select id="name" name="name" path="name" class="form-control">
                                                    <option value="Development">Development</option>
                                                    <option value="UAT">UAT</option>
                                                    <option value="QA">QA</option>
                                                    <option value="Production">Production</option>
                                                </select>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="host" class="col-sm-3 control-label no-padding-right">New environment host:</label>
                                        <div class="col-sm-3">
                                            <span class="block input-icon input-icon-right">
                                                <form:input name="host" id="host" path="host" value="${environment.host}" class="validate[required,custom[ipv4]] form-control" />
                                            </span>
                                        </div>
                                        <label for="port" class="col-sm-3 control-label no-padding-right">New environment port:</label>
                                        <div class="col-sm-3">
                                            <span class="block input-icon input-icon-right">
                                                <form:input name="port" id="port" path="port" value="${environment.port}" class="validate[required,maxSize[5]] form-control" />
                                            </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="username" class="col-sm-3 control-label no-padding-right">New environment admin username:</label>
                                        <div class="col-sm-3">
                                            <span class="block input-icon input-icon-right">
                                                <form:input name="username" id="username" path="username" value="${environment.username}" class="validate[required,custom[onlyLetterNumber]] form-control" />
                                            </span>
                                        </div>
                                        <label for="password" class="col-sm-3 control-label no-padding-right">New environment admin password:</label>
                                        <div class="col-sm-3">
                                            <span class="block input-icon input-icon-right">
                                                <form:input name="password" id="$password" path="password" value="${environment.password}" class="validate[required] form-control" />
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 clearfix form-actions">
                                        <button class="btn btn-sm btn-success" type="submit">
                                            <i class="icon-ok bigger-sm"></i>
                                            Save changes
                                        </button>
                                    </div>
                                </form:form>
                                <!-- PAGE CONTENT ENDS -->
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.page-content -->
                </div><!-- /.main-content -->
            </div><!-- /.main-container-inner -->

            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="icon-double-angle-up icon-only bigger-110"></i>
            </a>
        </div><!-- /.main-container -->

        <!-- basic scripts -->

        <!--[if !IE]> -->
        <script type="text/javascript">
            window.jQuery || document.write("<script src='../assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
        </script>
        <!-- <![endif]-->

        <!--[if IE] -->
        <script type="text/javascript">
            window.jQuery || document.write("<script src='../assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
        </script>
        <!--[endif]-->

        <script type="text/javascript">
            if ("ontouchend" in document)
                document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        
        <script src="../assets/js/jquery-blockUI.js"></script>
        
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/typeahead-bs2.min.js"></script>
        <script src="../assets/js/jquery.validationEngine-es.js"></script>
        <script src="../assets/js/jquery.validationEngine.js"></script>
        
        <!-- page specific plugin scripts -->

        <!-- ace scripts -->

        <script src="../assets/js/ace-elements.min.js"></script>
        <script src="../assets/js/ace.min.js"></script>

        <!-- inline scripts related to this page -->
        <script>
            $(document).ready(function() {
                // binds form submission and fields to the validation engine
                $("#myForm").validationEngine('attach');
            });
        </script>
    </body>
</html>