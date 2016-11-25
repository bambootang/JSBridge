//
//    if (typeof(__JSBridgeNative) != "undefined" && typeof(__JSBridgeNative.linkBridge) != "undefined") {
//         __JSBridgeNative.linkBridge('__JSBridgeNative');
//    }
//
//    if (typeof(__JSBridgeNative3) != "undefined" && typeof(__JSBridgeNative3.linkBridge) != "undefined") {
//         __JSBridgeNative.api=new function(){};
//         __JSBridgeNative3.linkBridge('__JSBridgeNative.api');
//    }else{
//         throw "__JSBridgeNative3 is undefined!"
//    }



// 将链接代码放在js中随网页的加载而自动加载。
    if (typeof(__JSBridgeNative4) != "undefined" && typeof(__JSBridgeNative4.linkBridge) != "undefined") {
         __JSBridge= {};
         __JSBridge.ui = {};
         __JSBridgeNative4.linkBridge('__JSBridge.ui');
    }else{
         throw "__JSBridgeNative4 is undefined!"
    }

    if (typeof(__JSBridgeNative2) != "undefined" && typeof(__JSBridgeNative2.linkBridge) != "undefined") {
         __JSBridgeNative2.linkBridge('__JSBridgeNative2');
    }else{
         throw "__JSBridgeNative2 is undefined!"
    }