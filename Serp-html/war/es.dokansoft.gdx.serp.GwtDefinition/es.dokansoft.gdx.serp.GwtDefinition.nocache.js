function es_dokansoft_gdx_serp_GwtDefinition(){
  var $intern_0 = '', $intern_35 = '" for "gwt:onLoadErrorFn"', $intern_33 = '" for "gwt:onPropertyErrorFn"', $intern_21 = '"><\/script>', $intern_10 = '#', $intern_64 = '.cache.html', $intern_12 = '/', $intern_57 = '1D738B1B7D2E337784775430EC3BE1FF', $intern_58 = '25513B8A13A69ACD9D9E8A9030E1B401', $intern_59 = '48171E47C4D8D2CDA9A53BF8DAA31200', $intern_60 = '7FD10444A40106215D5F8DC8831E8B6F', $intern_61 = '9920E4BB44201DD880DCCB279325B7DC', $intern_63 = ':', $intern_27 = '::', $intern_77 = '<script defer="defer">es_dokansoft_gdx_serp_GwtDefinition.onInjectionDone(\'es.dokansoft.gdx.serp.GwtDefinition\')<\/script>', $intern_20 = '<script id="', $intern_73 = '<script language="javascript" src="', $intern_30 = '=', $intern_11 = '?', $intern_44 = 'ActiveXObject', $intern_32 = 'Bad handler "', $intern_45 = 'ChromeTab.ChromeFrame', $intern_62 = 'D80C5245AF31C81F6E42ABE980B829F8', $intern_71 = 'DOMContentLoaded', $intern_22 = 'SCRIPT', $intern_19 = '__gwt_marker_es.dokansoft.gdx.serp.GwtDefinition', $intern_23 = 'base', $intern_15 = 'baseUrl', $intern_4 = 'begin', $intern_3 = 'bootstrap', $intern_43 = 'chromeframe', $intern_14 = 'clear.cache.gif', $intern_29 = 'content', $intern_9 = 'end', $intern_1 = 'es.dokansoft.gdx.serp.GwtDefinition', $intern_17 = 'es.dokansoft.gdx.serp.GwtDefinition.nocache.js', $intern_26 = 'es.dokansoft.gdx.serp.GwtDefinition::', $intern_51 = 'gecko', $intern_52 = 'gecko1_8', $intern_5 = 'gwt.codesvr=', $intern_6 = 'gwt.hosted=', $intern_7 = 'gwt.hybrid', $intern_65 = 'gwt/chrome/chrome.css', $intern_34 = 'gwt:onLoadErrorFn', $intern_31 = 'gwt:onPropertyErrorFn', $intern_28 = 'gwt:property', $intern_70 = 'head', $intern_55 = 'hosted.html?es_dokansoft_gdx_serp_GwtDefinition', $intern_69 = 'href', $intern_50 = 'ie6', $intern_49 = 'ie8', $intern_48 = 'ie9', $intern_36 = 'iframe', $intern_13 = 'img', $intern_37 = "javascript:''", $intern_66 = 'link', $intern_54 = 'loadExternalRefs', $intern_24 = 'meta', $intern_39 = 'moduleRequested', $intern_8 = 'moduleStartup', $intern_47 = 'msie', $intern_25 = 'name', $intern_41 = 'opera', $intern_38 = 'position:absolute;width:0;height:0;border:none', $intern_67 = 'rel', $intern_46 = 'safari', $intern_16 = 'script', $intern_56 = 'selectingPermutation', $intern_75 = 'soundmanager2-jsmin.js', $intern_76 = 'soundmanager2-jsmin.js"><\/script>', $intern_72 = 'soundmanager2-setup.js', $intern_74 = 'soundmanager2-setup.js"><\/script>', $intern_2 = 'startup', $intern_68 = 'stylesheet', $intern_18 = 'undefined', $intern_53 = 'unknown', $intern_40 = 'user.agent', $intern_42 = 'webkit';
  var $wnd = window, $doc = document, $stats = $wnd.__gwtStatsEvent?function(a){
    return $wnd.__gwtStatsEvent(a);
  }
  :null, $sessionId = $wnd.__gwtStatsSessionId?$wnd.__gwtStatsSessionId:null, scriptsDone, loadDone, bodyDone, base = $intern_0, metaProps = {}, values = [], providers = [], answers = [], softPermutationId = 0, onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_4});
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      var query = $wnd.location.search;
      return (query.indexOf($intern_5) != -1 || (query.indexOf($intern_6) != -1 || $wnd.external && $wnd.external.gwtOnLoad)) && query.indexOf($intern_7) == -1;
    }
     catch (e) {
    }
    isHostedMode = function(){
      return result;
    }
    ;
    return result;
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc.getElementById($intern_1);
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      es_dokansoft_gdx_serp_GwtDefinition = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, $intern_1, base, softPermutationId);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_9});
    }
  }

  function computeScriptBase(){
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf($intern_10);
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf($intern_11);
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf($intern_12, Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):$intern_0;
    }

    function ensureAbsoluteUrl(url){
      if (url.match(/^\w+:\/\//)) {
      }
       else {
        var img = $doc.createElement($intern_13);
        img.src = url + $intern_14;
        url = getDirectoryOfFile(img.src);
      }
      return url;
    }

    function tryMetaTag(){
      var metaVal = __gwt_getMetaProperty($intern_15);
      if (metaVal != null) {
        return metaVal;
      }
      return $intern_0;
    }

    function tryNocacheJsTag(){
      var scriptTags = $doc.getElementsByTagName($intern_16);
      for (var i = 0; i < scriptTags.length; ++i) {
        if (scriptTags[i].src.indexOf($intern_17) != -1) {
          return getDirectoryOfFile(scriptTags[i].src);
        }
      }
      return $intern_0;
    }

    function tryMarkerScript(){
      var thisScript;
      if (typeof isBodyLoaded == $intern_18 || !isBodyLoaded()) {
        var markerId = $intern_19;
        var markerScript;
        $doc.write($intern_20 + markerId + $intern_21);
        markerScript = $doc.getElementById(markerId);
        thisScript = markerScript && markerScript.previousSibling;
        while (thisScript && thisScript.tagName != $intern_22) {
          thisScript = thisScript.previousSibling;
        }
        if (markerScript) {
          markerScript.parentNode.removeChild(markerScript);
        }
        if (thisScript && thisScript.src) {
          return getDirectoryOfFile(thisScript.src);
        }
      }
      return $intern_0;
    }

    function tryBaseTag(){
      var baseElements = $doc.getElementsByTagName($intern_23);
      if (baseElements.length > 0) {
        return baseElements[baseElements.length - 1].href;
      }
      return $intern_0;
    }

    var tempBase = tryMetaTag();
    if (tempBase == $intern_0) {
      tempBase = tryNocacheJsTag();
    }
    if (tempBase == $intern_0) {
      tempBase = tryMarkerScript();
    }
    if (tempBase == $intern_0) {
      tempBase = tryBaseTag();
    }
    if (tempBase == $intern_0) {
      tempBase = getDirectoryOfFile($doc.location.href);
    }
    tempBase = ensureAbsoluteUrl(tempBase);
    base = tempBase;
    return tempBase;
  }

  function processMetas(){
    var metas = document.getElementsByTagName($intern_24);
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute($intern_25), content;
      if (name) {
        name = name.replace($intern_26, $intern_0);
        if (name.indexOf($intern_27) >= 0) {
          continue;
        }
        if (name == $intern_28) {
          content = meta.getAttribute($intern_29);
          if (content) {
            var value, eq = content.indexOf($intern_30);
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = $intern_0;
            }
            metaProps[name] = value;
          }
        }
         else if (name == $intern_31) {
          content = meta.getAttribute($intern_29);
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_32 + content + $intern_33);
            }
          }
        }
         else if (name == $intern_34) {
          content = meta.getAttribute($intern_29);
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_32 + content + $intern_35);
            }
          }
        }
      }
    }
  }

  function __gwt_getMetaProperty(name){
    var value = metaProps[name];
    return value == null?null:value;
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc.createElement($intern_36);
      iframe.src = $intern_37;
      iframe.id = $intern_1;
      iframe.style.cssText = $intern_38;
      iframe.tabIndex = -1;
      $doc.body.appendChild(iframe);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_39});
      iframe.contentWindow.location.replace(base + initialHtml);
    }
  }

  providers[$intern_40] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (function(){
      return ua.indexOf($intern_41) != -1;
    }
    ())
      return $intern_41;
    if (function(){
      return ua.indexOf($intern_42) != -1 || function(){
        if (ua.indexOf($intern_43) != -1) {
          return true;
        }
        if (typeof window[$intern_44] != $intern_18) {
          try {
            var obj = new ActiveXObject($intern_45);
            if (obj) {
              obj.registerBhoIfNeeded();
              return true;
            }
          }
           catch (e) {
          }
        }
        return false;
      }
      ();
    }
    ())
      return $intern_46;
    if (function(){
      return ua.indexOf($intern_47) != -1 && $doc.documentMode >= 9;
    }
    ())
      return $intern_48;
    if (function(){
      return ua.indexOf($intern_47) != -1 && $doc.documentMode >= 8;
    }
    ())
      return $intern_49;
    if (function(){
      var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3)
        return makeVersion(result) >= 6000;
    }
    ())
      return $intern_50;
    if (function(){
      return ua.indexOf($intern_51) != -1;
    }
    ())
      return $intern_52;
    return $intern_53;
  }
  ;
  values[$intern_40] = {gecko1_8:0, ie6:1, ie8:2, ie9:3, opera:4, safari:5};
  es_dokansoft_gdx_serp_GwtDefinition.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  es_dokansoft_gdx_serp_GwtDefinition.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_54, millis:(new Date).getTime(), type:$intern_9});
    maybeStartModule();
  }
  ;
  processMetas();
  computeScriptBase();
  var strongName;
  var initialHtml;
  if (isHostedMode()) {
    if ($wnd.external && ($wnd.external.initModule && $wnd.external.initModule($intern_1))) {
      $wnd.location.reload();
      return;
    }
    initialHtml = $intern_55;
    strongName = $intern_0;
  }
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_56});
  if (!isHostedMode()) {
    try {
      unflattenKeylistIntoAnswers([$intern_49], $intern_57);
      unflattenKeylistIntoAnswers([$intern_52], $intern_58);
      unflattenKeylistIntoAnswers([$intern_48], $intern_59);
      unflattenKeylistIntoAnswers([$intern_46], $intern_60);
      unflattenKeylistIntoAnswers([$intern_41], $intern_61);
      unflattenKeylistIntoAnswers([$intern_50], $intern_62);
      strongName = answers[computePropValue($intern_40)];
      var idx = strongName.indexOf($intern_63);
      if (idx != -1) {
        softPermutationId = Number(strongName.substring(idx + 1));
        strongName = strongName.substring(0, idx);
      }
      initialHtml = strongName + $intern_64;
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded[$intern_65]) {
        var l = $doc.createElement($intern_66);
        __gwt_stylesLoaded[$intern_65] = l;
        l.setAttribute($intern_67, $intern_68);
        l.setAttribute($intern_69, base + $intern_65);
        $doc.getElementsByTagName($intern_70)[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener($intern_71, onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener($intern_71, function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_9});
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_54, millis:(new Date).getTime(), type:$intern_4});
  if (!__gwt_scriptsLoaded[$intern_72]) {
    __gwt_scriptsLoaded[$intern_72] = true;
    document.write($intern_73 + base + $intern_74);
  }
  if (!__gwt_scriptsLoaded[$intern_75]) {
    __gwt_scriptsLoaded[$intern_75] = true;
    document.write($intern_73 + base + $intern_76);
  }
  $doc.write($intern_77);
}

es_dokansoft_gdx_serp_GwtDefinition();
