<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Promotion Request Details</title>

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
                            <a class="btn btn-success" href="<c:url value="/home/dashboard" />">
                                <i class="icon-desktop"></i>
                            </a>
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
                            <li class="active">
                                <i class="icon-desktop"></i>
                                &nbsp;Welcome
                            </li>
                        </ul><!-- .breadcrumb -->
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <div class="row">
                                    <div class="col-sm-10 col-sm-offset-1">
                                        <div class="widget-box transparent invoice-box">
                                            <div class="widget-header widget-header-large">
                                                <h3 class="grey lighter pull-left position-relative">
                                                    Promotion Request Details
                                                </h3>

                                                <div class="widget-toolbar no-border invoice-info">
                                                    <span class="invoice-info-label">Promotion</span>
                                                    <span class="red">${promotion.id}</span>

                                                    <br />
                                                    <span class="invoice-info-label">Date:</span>
                                                    <span class="blue">${promotion.dateCreated}</span>
                                                </div>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main padding-24">
                                                    <div class="row">
                                                        <div class="col-sm-3">
                                                            <div class="row">
                                                                <div class="col-xs-12 label label-lg label-info arrowed-in arrowed-right">
                                                                    <b>Project Info</b>
                                                                </div>
                                                            </div>

                                                            <div class="row">
                                                                <ul class="list-unstyled spaced">
                                                                    <li>
                                                                        <i class="icon-caret-right blue"></i>
                                                                        Name : ${promotion.project.name}
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div><!-- /span -->

                                                        <div class="col-sm-9">
                                                            <div class="row">
                                                                <div class="col-xs-12 label label-lg label-success arrowed-in arrowed-right">
                                                                    <b>Environmens</b>
                                                                </div>
                                                            </div>

                                                            <div>
                                                                <table class="table table-striped table-bordered">
                                                                    <thead>
                                                                        <tr>
                                                                            <td>Name</td>
                                                                            <td>Host</td>
                                                                            <td>Port</td>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>    
                                                                        <c:forEach items="${promotion.project.environments}" var="environment">
                                                                            <tr>
                                                                                <td>${environment.name}</td>
                                                                                <td>${environment.host}</td>
                                                                                <td>${environment.port}</td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div><!-- /span -->
                                                    </div><!-- row -->

                                                    <div class="space"></div>

                                                    <div class="row">
                                                        <div class="col-xs-12 label label-warning label-lg label-info">
                                                            <b>Documents</b>
                                                        </div>
                                                    </div>

                                                    <div class="space"></div>
                                                    <a class="btn btn-purple btn-sm pull-right" href="<c:url value="/document/new?promotionId=${promotion.id}" />">
                                                        <i class="icon-cloud-upload"></i>
                                                        Upload Document
                                                    </a>

                                                    <div>
                                                        <table class="table table-striped table-bordered">
                                                            <tbody>
                                                                <c:forEach items="${promotion.documents}" var="document">
                                                                    <tr>
                                                                        <td>
                                                                            <a href="<c:url value="/document/download?promotionId=${promotion.id}&documentId=${document.id}" />">
                                                                                ${document.filename}
                                                                            </a>
                                                                        </td>                                                                    
                                                                    </tr>
                                                                </c:forEach>    
                                                            </tbody>
                                                        </table>
                                                    </div>

                                                    <div class="hr hr8 hr-double hr-dotted"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- PAGE CONTENT ENDS -->                                
                            </div><!-- /.col -->
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
            window.jQuery || document.write("<script src='../assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
        </script>
        <!--[endif]-->

        <script type="text/javascript">
            if ("ontouchend" in document)
                document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/typeahead-bs2.min.js"></script>

        <!-- page specific plugin scripts -->

        <!-- ace scripts -->

        <script src="../assets/js/ace-elements.min.js"></script>
        <script src="../assets/js/ace.min.js"></script>

        <!-- inline scripts related to this page -->
    </body>
</html> 