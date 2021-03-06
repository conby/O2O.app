﻿// 有关“空白”模板的简介，请参阅以下文档:
// http://go.microsoft.com/fwlink/?LinkID=397704
// 若要在 Ripple 或 Android 设备/仿真程序中调试代码: 启用你的应用程序，设置断点，
// 然后在 JavaScript 控制台中运行 "window.location.reload()"。
(function () {
    "use strict";

    document.addEventListener( 'deviceready', onDeviceReady.bind( this ), false );

    function onDeviceReady() {
        // 处理 Cordova 暂停并恢复事件
        document.addEventListener( 'pause', onPause.bind( this ), false );
        document.addEventListener( 'resume', onResume.bind( this ), false );
        
        // TODO: Cordova 已加载。在此处执行任何需要 Cordova 的初始化。
        var parentElement = document.getElementById('deviceready');
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');
        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

		// delete window.open;
        //var targetUrl = "http://o2o.conby.com/wap/index.php";
        var targetUrl = "http://o2o.conby.com";
        var bkpLink = document.getElementById("bkpLink");
        bkpLink.setAttribute("href", targetUrl);
        bkpLink.text = "连接中...";
        window.location.replace(targetUrl);

        //var ref = cordova.InAppBrowser.open(targetUrl, '_self', 'hidden=yes,location=no');
        /*
        var ref = window.open(targetUrl, '_self', 'hidden=yes,location=no');
        ref.addEventListener('loadstart', function (event) {
            bkpLink.text = "加载中...";
        });
        ref.addEventListener('loadstop', function (event) {
            bkpLink.text = "加载完成";
            ref.show();
        });
        ref.addEventListener('loaderror', function (event) {
            bkpLink.text = "加载错误: 代码 " + event.code.toString() + ", 描述 " + event.message;
        });
        ref.addEventListener('exit', function (event) {
            navigator.app.exitApp();
        });*/
    };

    function onPause() {
        // TODO: 此应用程序已挂起。在此处保存应用程序状态。
    };

    function onResume() {
        // TODO: 此应用程序已重新激活。在此处还原应用程序状态。
    };
} )();