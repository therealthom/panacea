<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav nav-list">
    
    <li>
        <a href="<c:url value="/setup/init" />" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
           <i class="icon-wrench"></i>
            <span class="menu-text"> System settings </span>
        </a>
    </li>

    <li>
        <a href="<c:url value="/project/listProjects" />" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
           <i class="icon-briefcase"></i>
            <span class="menu-text"> Projects </span>
        </a>
    </li>

    <li>
        <a href="<c:url value="/promotion/list" />" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
            <i class="icon-thumbs-up"></i>
            <span class="menu-text"> Promotions </span>
        </a>
    </li>

    <li>
        <a href="<c:url value="/log/list" />" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
           <i class="icon-group"></i>
            <span class="menu-text"> Log </span>
        </a>
    </li>

    <li>
        <a href="userAdmin.html" onclick="jQuery.blockUI({ message: '<h4><img src=\'../assets/img/busy.gif\' /> Please wait</h4>' });">
            <i class="icon-group"></i>
            <span class="menu-text"> User admin </span>
        </a>
    </li>

</ul><!-- /.nav-list -->