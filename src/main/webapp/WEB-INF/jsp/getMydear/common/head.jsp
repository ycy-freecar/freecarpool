<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta name="format-detection" content="telephone=no"/>
    <meta name="format-detection" content="email=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="author" content="FreeCarPool">
    <link href="/public/stylesheets/weui.min.css" rel="stylesheet" media="screen">
    <link href="/public/stylesheets/mydear/basic.css" rel="stylesheet" media="screen">
</head>
<body>
<!--信息弹层 错误信息加类名error-->
<div class="message text-center" id="alert-messenger">
</div>