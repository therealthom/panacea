<%-- 
    Document   : taskTray
    Created on : 19-ene-2014, 23:58:02
    Author     : oscar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                                    <a href="<c:url value="/login/exit" />" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
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
                            <li class="active">
                                <i class="icon-bars"></i>
                                Tasks
                            </li>
                        </ul><!-- .breadcrumb -->
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <div class="widget-box">
                                    <div class="widget-header">
                                        <h4 class="lighter"><i class="icon-bars"></i> Task list </h4>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead class="thin-border-bottom">
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Type</th>
                                                        <th>Created On</th>
                                                        <th>Start</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="taskSummary" items="${tareas}" varStatus="status">
                                                        <tr>
                                                            <td>${taskSummary.id}</td>
                                                            <td>${taskSummary.processId}</td>
                                                            <td><fmt:formatDate value="${taskSummary.createdOn.toGregorianCalendar().getTime()}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                                                            <td style="text-align: center;">
                                                                <c:if test="${taskSummary.processId.contains('CISetupProcess')}">
                                                                    <a href="../project/createProject?taskId=${taskSummary.id}" class="btn btn-info btn-minier" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
                                                                        <i class="icon-plus-sign"></i> Set project name
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${taskSummary.processId.contains('CIBuildProcess')}">
                                                                    <a href="../project/executeBuildProject?taskId=${taskSummary.id}&projectId=${project.id}" class="btn btn-inverse btn-minier" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
                                                                        <i class="icon-plus-sign"></i> Construir
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${taskSummary.processId.contains('CIPromocionProcess')}">
                                                                    <c:if test="${firstPromotion}">
                                                                        <c:if test="${project != null}">
                                                                            <a href="../promotion/generateFirstWithProject?taskId=${taskSummary.id}&projectId=${project.id}" class="btn btn-warning btn-minier" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
                                                                                <i class="icon-plus-sign"></i> Promocionar
                                                                            </a>
                                                                        </c:if>
                                                                        <c:if test="${project == null}">
                                                                            <a href="../promotion/generateFirst?taskId=${taskSummary.id}&processId=${taskSummary.processInstanceId}" class="btn btn-warning btn-minier" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
                                                                                <i class="icon-plus-sign"></i> Promocionar
                                                                            </a>
                                                                        </c:if>
                                                                    </c:if>
                                                                    <c:if test="${!firstPromotion}">
                                                                        <a href="../promotion/evaluatePromotion?taskId=${taskSummary.id}&processId=${taskSummary.processInstanceId}" class="btn btn-success btn-minier" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
                                                                            <i class="icon-plus-sign"></i> Promocionar
                                                                        </a>
                                                                    </c:if>
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
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

        <!-- page specific plugin scripts -->

        <!-- ace scripts -->

        <script src="../assets/js/ace-elements.min.js"></script>
        <script src="../assets/js/ace.min.js"></script>

        <!-- inline scripts related to this page -->
    </body>
</html>