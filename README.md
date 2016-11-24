# JSBridge

　　在Android开发中，常会需要在应用内展示网页内容，平时的运营活动更是会需要与native进行交互，比如：获取用户信息、调用对应的数据服务接口、控制展示页面的抬头信息等。<br/>
　　这个时候就需要我们有一套方便、快捷、可靠的方式来达到这些目的。目前常用的方式有两种：url拦截，js接口注入。JSBridge就是js注入的方式，只是它更聪明。

**JSBridge的优点:**   
    1、方便，一句代码就可以将框架注入到网页  
    2、可靠，依赖于系统支持的JavaScriptInterface注入方式进行扩展  
    3、能进行function异步回调.  

**接入步骤:**  
1.在工程的build.gradle中引入框架：  
 ` compile 'com.bamboo.talkweb:jsbridge:0.1'  `  
   
2.在需要注入JSBridge的地方加入以下代码  
 ` JSBridgeClient.registerJSBridge(webView, injectedObj, bridgeName); `  
  ps：以上代码必须要在webview.loadurl 完成之前调用  
  
3.在网页中调用  
 ` if (typeof(__JSBridgeNative) != "undefined" && typeof(__JSBridgeNative.linkBridge) != "undefined") {`    
 　　` __JSBridgeNative.linkBridge();`  
 `} `  
  
    
具体可参考[jsbridge－sample](https://github.com/kerwinT/JSBridge)
