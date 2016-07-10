/**
 * Common web base JS
 * 
 * @version 0.0.1
 * @author ZHENFENG ZHANG
 * @since 2014-11-18
 */
var WebBase = {
  
  /**
   * Get JS path
   * 
   * @param js
   */
  getJsPath : function(js) {
    var _scripts = document.getElementsByTagName("script");
    var _path = "";
    for (var i = 0, len = _scripts.length; i < len; i++) {
      var src = _scripts[i].src;
      if (src.indexOf(js) != -1) {
        var ss = src.split(js);
        _path = ss[0];
        break;
      }
    }
    var _href = location.href;
    _href = _href.split("#")[0];
    _href = _href.split("?")[0];
    var ss = _href.split("/");
    ss.length = ss.length - 1;
    _href = ss.join("/");
    if (_path.indexOf("https:") == -1 && _path.indexOf("http:") == -1 && _path.indexOf("file:") == -1 && _path.indexOf("\/") != 0) {
      _path = _href + "/" + _path;
    }
    return _path;
  },
  
  /**
   * Get cookie
   * 
   * @param sName
   */
  getCookie : function(sName) {
    var _aCookie = document.cookie.split(";");
    var _lastMatch = null;
    for (var i = 0, len = _aCookie.length; i < len; i++) {
      var _aCrumb = _aCookie[i].split("=");
      if (sName == _aCrumb[0]) {
        _lastMatch = _aCrumb;
      }
    }
    if (_lastMatch) {
      var v = _lastMatch[1];
      if (v == undefined) {
        return v;
      }
      return unescape(v);
    }
    return null;
  },
  
  /**
   * DYNC write JS file
   * 
   * @param jsPath
   */
  writeJs : function(jsPath) {
    if (jsPath) {
      document.write("<script src=\"" + jsPath + "\" type=\"text/javascript\" charset=\"utf-8\" ></script>");
      document.close();
    }
  },
  
  /**
   * DYNC write css file
   * 
   * @param cssPath
   */
  writeCss : function(cssPath) {
    var _ckey = new Date().getDay().toString();
    var b = WebBase.getCookie(_ckey);
    if (cssPath && b) {
      document.write("<link href=\"" + cssPath + "\" rel=\"stylesheet\" type=\"text/css\" charset=\"utf-8\" />");
      document.close();
      jQuery.cookie(_ckey, b, {
        expires : 1
      });
    }
  },
  
  /**
   * context path
   */
  cxtPath : null,
  
  /**
   * Get context path
   */
  getCxtPath : function() {
    if (WebBase.cxtPath == null) {
      return '/' + window.location.pathname.split('/')[1];
    } else {
      return WebBase.cxtPath;
    }
  },
  
  /**
   * Set datas
   */
  datas : '',
  
  /**
   * Get datas
   */
  getDatas : function() {
    return WebBase.str2JSON(WebBase.datas);
  },
  
  /**
   * String data to JSON object
   * 
   * @param data
   * @return JSON object
   */
  str2JSON : function(data) {
    if (data) {
      data = eval('(' + data + ')');
    }
    return data;
  },
  
  /**
   * JSON object to string
   * 
   * @param json
   * @return string
   */
  json2Str : function(json) {
    return json + "";
  },
  
  /**
   * Serialize JSON
   * 
   * @param tg
   */
  serializeJSON : function(tg) {
    var _json = "{";
    var _vals = jQuery(tg).serialize();
    if (_vals) {
      var _tmps = _vals.split("&");
      if (_tmps && _tmps.length > 0) {
        for (var i = 0, len = _tmps.length; i < len; i++) {
          var _ss = _tmps[i].split("=");
          _json += '"' + _ss[0] + '":"' + _ss[1] + '",';
        }
      }
    }
    if (_json.lastIndexOf(",") > 0) {
      _json = _json.substring(0, _json.lastIndexOf(","));
    }
    _json += "}";
    return _json;
  },
  
  /**
   * Format date <br/>
   * 
   * WebBase.format("yyyy-MM-dd hh:mm:ss",val) => 2006-07-02 08:09:04
   * 
   * @param fmt
   * @param val
   */
  formatDate : function(fmt, val) {
    var d = new Date();
    d.setTime(val);
    return d.format(fmt);
  },
  
  isFunc : function(func) {
    return (func != null && (typeof func) == "function");
  },
  
  msgBox : function(title, msg, callback) {
    if (WebBase.isFunc(callback)) {
      callback();
    } else {
      alert(title + "\n" + msg);
    }
  },
  
  getUrl : function(t) {
    var ori = window.location.href + '';
    return ori.substr(0, ori.lastIndexOf("/")) + "/" + t;
  },
  
  fmtBlank : function(val) {
    if (val == null || val == "null" || val == "NULL") {
      val = "";
    }
    return val;
  },
  
  fmtZero : function(val) {
    if (val == null || val == "null" || val == "NULL") {
      val = "0";
    }
    return val;
  },
  
  isNotEmpty : function(val) {
    return (WebBase.fmtBlank(val) != "");
  },
  
  isEmpty : function(val) {
    return !(WebBase.isNotEmpty(val));
  },
  
  setText : function(id, value) {
    var _type = jQuery("input=[id='" + id + "']").prop('type');
    if (_type == undefined) {
      jQuery('#' + id).text(WebBase.fmtBlank(value));
    }
  },
  
  setVal : function(id, value) {
    var _type = jQuery("input=[id='" + id + "']").prop('type');
    if (_type != undefined && (_type == 'text' || _type == 'hidden')) {
      jQuery('#' + id).val(WebBase.fmtBlank(value));
    }
  },
  
  /**
   * Get checkbox values
   * 
   * @param id
   */
  getCkboxVals : function(id) {
    var _vals = new Array();
    var _tg = $("#" + id);
    if (_tg && _tg.prop("type") == "checkbox") {
      var _val = null;
      $("input[name='" + _tg.prop("name") + "']:checked").each(function() {
        _val = $(this).val();
        if (_val != null && _val != "" && _val != 'on' && _val.indexOf('{') == -1) {
          _vals.push(_val);
        }
      });
    }
    if (_vals.length <= 0) {
      _vals = null;
    }
    return _vals;
  },
  
  /**
   * Change/Replace themes URI
   * 
   * @param rs
   * @returns
   */
  chgThemesUriX : function(rs) {
    return WebBase.chgThemesUri(2, rs, true);
  },
  
  /**
   * Change/Replace themes URI
   * 
   * @param size
   *          [1,10]
   * @param rs
   * @param isCache
   *          true
   * @returns
   */
  chgThemesUri : function(size, rs, isCache) {
    if (rs != null) {
      size = parseInt(size);
      if (isNaN(size) || size < 1 || size > 10) {
        size = 2;
      }
      var _regexp = "";
      for (var i = 0; i < size; i++) {
        _regexp += "\\..\\/";
      }
      _regexp = eval("/" + _regexp + "/g");
      rs = rs.replace(_regexp, WebBase.getCxtPath() + '/themes/');
      if (isCache != null && !isCache) {
        // set no cache
        rs = rs.replace(/\.css/g, '.css?v=' + new Date().getTime());
        rs = rs.replace(/\.js/g, '.js?v=' + new Date().getTime());
      }
    }
    return rs;
  },
  
  /**
   * Change/Replace CSS for IE
   */
  chgCssForIE : function() {
    if (document.createStyleSheet) {
      var _href = null;
      jQuery('head').find('link').each(function(i, n) { // head CSS
        _href = jQuery(n).prop('href');
        if (_href != null && _href != "") {
          jQuery(n).remove();
          document.createStyleSheet(_href);
        }
      });
      jQuery('body').find('link').each(function(i, n) { // body CSS
        _href = jQuery(n).prop('href');
        if (_href != null && _href != "") {
          jQuery(n).remove();
          document.createStyleSheet(_href);
        }
      });
    }
  },
  
  /**
   * Append element
   * 
   * @param isCache
   * @param etype
   * @param fileName
   * @param tgObj
   */
  appendE : function(isCache, etype, fileName, tgObj) {
    if (WebBase.isEmpty(fileName)) {
      return;
    }
    var _head = tgObj;
    if (_head == null) {
      alert('html head element is undefined!');
    } else {
      fileName = WebBase.getCxtPath() + fileName;
      var _id = fileName.substr(fileName.lastIndexOf('/') + 1, fileName.length);
      if (!isCache) {
        fileName = fileName + '?_v=' + new Date().getTime();
      }
      if ('js' == etype) {
        _id = _id.replace(/\.js/g, '_js_');
        if (jQuery('#' + _id).prop('type') == null) {
          jQuery('<script id="' + _id + '" src="' + fileName + '" type="text/javascript" charset="utf-8" ></script>').appendTo(
              _head);
        }
      } else if ('css' == etype) {
        _id = _id.replace(/\.css/g, '_css_');
        if (jQuery('#' + _id).prop('type') == null) {
          jQuery('<link id="' + _id + '" href="' + fileName + '" rel="stylesheet" type="text/css" charset="utf-8" />').appendTo(
              _head);
          if (document.createStyleSheet) { // for IE
            document.createStyleSheet(fileName);
          }
        }
      }
    }
  },
  
  /**
   * Append target JS element
   * 
   * @param isCache
   * @param fileName
   * @param tgObj
   */
  appendTgJsE : function(isCache, fileName, tgObj) {
    WebBase.appendE(isCache, 'js', fileName, tgObj);
  },
  
  /**
   * Append JS element
   * 
   * @param isCache
   * @param fileName
   */
  appendJsE : function(isCache, fileName) {
    WebBase.appendE(isCache, 'js', fileName, jQuery('head')[0]);
  },
  
  /**
   * Append target CSS element
   * 
   * @param isCache
   * @param fileName
   * @param tgObj
   */
  appendTgCssE : function(isCache, fileName, tgObj) {
    WebBase.appendE(isCache, 'css', fileName, tgObj);
  },
  
  /**
   * Append CSS element
   * 
   * @param isCache
   * @param fileName
   */
  appendCssE : function(isCache, fileName) {
    WebBase.appendE(isCache, 'css', fileName, jQuery('head')[0]);
  },
  
  /**
   * Get paging for start record
   * 
   * @param currPage
   * @param limit
   */
  getStartPaging : function(currPage, limit) {
    currPage = parseInt(currPage);
    limit = parseInt(limit);
    if (currPage < 1) {
      currPage = 1;
    }
    if (limit < 1) {
      limit = 1;
    }
    return parseInt((currPage - 1) * limit + 1);
  },
  
  /**
   * Get total page
   * 
   * @param count
   * @param limit
   */
  getTotalPage : function(count, limit) {
    count = parseInt(count);
    limit = parseInt(limit);
    if (count < 1) {
      count = 1;
    }
    if (limit < 1) {
      limit = 1;
    }
    if ((count % limit == 0)) {
      return parseInt((count / limit));
    } else {
      return parseInt((count / limit) + 1);
    }
  },
  
  /**
   * Scroll ul
   * 
   * @param id
   * @param opt
   * @param callback
   */
  scrollUl : function(id, opt, callback) {
    if (!opt) {
      opt = {};
    }
    var _this = $('#' + id).eq(0).find("ul:first");
    var lineH = _this.find("li:first").height(); // get line height
    // parent line height
    var line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height() / lineH, 10);
    var speed = opt.speed ? parseInt(opt.speed, 10) : 500; // speed
    var timer = opt.timer ? parseInt(opt.timer, 10) : 3000; // unit: ms
    if (line == 0) {
      line = 1;
    }
    var upHeight = 0 - line * lineH;
    // main function
    scrollUp = function() {
      _this.animate({
        marginTop : upHeight
      }, speed, function() {
        for (var i = 1; i <= line; i++) {
          _this.find("li:first").appendTo(_this);
        }
        _this.css({
          marginTop : 0
        });
      });
    };
    _this.hover(function() {
      clearInterval(timerId);
    }, function() {
      timerId = setInterval("scrollUp()", timer);
    }).mouseout();
  },
  
  appendTr : function(trId, dataList) {
    WebBase.appendTrIndex(null, trId, dataList);
  },
  
  appendTrIndex : function(iField, trId, dataList) {
    $("tr[id='" + trId + "_']").remove();
    if (dataList != null) {
      var _trTg = $("#" + trId);
      var _trHtml = _trTg.html();
      var _index = 0;
      $.each(dataList, function(i, n) {
        _index++;
        var _tmp = _trHtml;
        $.each(n, function(j, jn) {
          if (iField != null && iField != "") {
            _tmp = _tmp.replace(new RegExp("{" + iField + "}", "gm"), _index + "");
          }
          _tmp = _tmp.replace(new RegExp("{" + j + "}", "gm"), WebBase.fmtBlank(jn));
        });
        $("<tr id='" + trId + "_' >" + _tmp + "</tr>").insertBefore(_trTg);
      });
      _trTg.css("display", "none");
    }
  },
  
  appendTrMap : function(trId, dataMap) {
    WebBase.appendTrIndexMap(null, trId, dataMap);
  },
  
  appendTrIndexMap : function(iField, trId, dataMap) {
    $("tr[id='" + trId + "_']").remove();
    if (dataMap != null) {
      var _trTg = $("#" + trId);
      var _trHtml = _trTg.html();
      var _tmp = _trHtml;
      var _index = 0;
      $.each(dataMap, function(i, n) {
        _index++;
        if (iField != null && iField != "") {
          _tmp = _tmp.replace(new RegExp("{" + iField + "}", "gm"), _index + "");
        }
        _tmp = _tmp.replace(new RegExp("{" + i + "}", "gm"), WebBase.fmtBlank(n));
      });
      $("<tr id='" + trId + "_' >" + _tmp + "</tr>").insertBefore(_trTg);
      _trTg.css("display", "none");
    }
  },
  
  /**
   * Bind button func
   * 
   * @param prefix
   * @param btnId
   * @param callback
   */
  bindBtn : function(prefix, btnId, callback) {
    var _tg = $("#" + WebBase.fmtBlank(prefix) + btnId);
    if (_tg != null) {
      _tg.attr("onclick", "");
      _tg.unbind("click").click(function() {
        if (WebBase.isFunc(callback)) {
          callback(_tg);
        }
      });
    }
  },
  
  /**
   * Unbind button func
   * 
   * @param prefix
   * @param btnId
   * @param callback
   */
  unbindBtn : function(prefix, btnId, callback) {
    var _tg = $("#" + WebBase.fmtBlank(prefix) + btnId);
    if (_tg != null) {
      _tg.attr("onclick", "");
      _tg.unbind("click");
      if (WebBase.isFunc(callback)) {
        callback(_tg);
      }
    }
  },
  
  /**
   * Upload file
   * 
   * @param prefix
   * @param url
   * @param btnId
   * @param fileEleNames
   * @param busiId
   * @param callback
   */
  uploadFile : function(prefix, url, btnId, fileEleNames, busiId, callback) {
    WebBase.bindBtn(prefix, btnId, function() {
      // check file value
      var flag = true;
      $.each(fileEleNames, function(i, n) {
        if ($("input[name='" + n + "']").val() == "") {
          $("#" + busiId + "msg_").text("请选择需要上传的附件！");
          flag = false;
          return false;
        }
      });
      if (flag) {
        // reomve click
        WebBase.unbindBtn(prefix, btnId, function() {
          $("#" + busiId + "msg_").text("正在上传附件。。。");
        });
        // do send
        jQuery.ajaxFileUpload({
          url : url,
          secureuri : false,
          fileElementId : fileEleNames,
          type : 'post',
          async : false,
          cache : false,
          success : function(rs) {
            var _text = "";
            var _rspState = (rs != null && rs.length > 0 && rs.indexOf('<pre>') == -1);
            if (_rspState) { // 1. 校验返回值
              var _rs2 = rs.split("@");
              if (WebBase.fmtBlank(_rs2[0]) != "") {
                $('#' + busiId).val(_rs2[0]); // 返回的ID
              } else {
                _rspState = false;
              }
              if (WebBase.fmtBlank(_rs2[1]) != "") {
                _text = _rs2[1]; // 返回的文本名称
              } else {
                _rspState = false;
              }
            }
            if (FuncBinder.isFunc(callback)) { // 2. 回调函数
              callback(_rspState);
            }
            if (_rspState) { // 3. 追加文本名称
              // append filename label
              $("#" + busiId + "msg_").append("<br/>" + _text);
            }
          },
          error : function(rs, status, msg) {
            if (FuncBinder.isFunc(callback)) {
              callback(false);
            }
          }
        });
        // 4. reset file input
        for ( var i in fileEleNames) {
          jQuery("input[name=" + fileEleNames[i] + "]").val("");
        }
        window.setTimeout(function() {
          // 5. rebind
          WebBase.uploadFile(prefix, url, btnId, fileEleNames, busiId, callback);
        }, 10000);
      }
      
    });
  },
  
  /**
   * Get workday number
   * 
   * @param beginId
   * @param endId
   */
  getWorkDayNum : function(beginId, endId) {
    var beginDate = $("#" + beginId).val();
    var endDate = $("#" + endId).val();
    if (WebBase.isEmpty(beginDate) || WebBase.isEmpty(endDate)) {
      return 0;
    }
    beginDate = new Date(beginDate.replace(/-/g, "/"));
    endDate = new Date(endDate.replace(/-/g, "/"));
    // 日期差值,即包含周六日、以天为单位的工时，86400000=1000*60*60*24.
    var workDayVal = (endDate - beginDate) / 86400000 + 1;
    var remainder = (workDayVal % 7); // 工时的余数
    var divisor = Math.floor(workDayVal / 7); // 工时向下取整的除数
    var weekendDay = 2 * divisor;
    // 起始日期的星期，星期取值有（1,2,3,4,5,6,0）
    var nextDay = beginDate.getDay();
    for (var tempDay = remainder; tempDay >= 1; tempDay--) {
      if (tempDay == remainder) { // 第一天不用加1
        nextDay = nextDay + 0;
      } else if (tempDay != remainder) {
        nextDay = nextDay + 1;
      }
      if (nextDay == 7) { // 周日
        nextDay = 0;
      }
      if (nextDay == 0 || nextDay == 6) { // 周六日
        weekendDay = weekendDay + 1;
      }
    }
    var _num = parseInt(workDayVal - weekendDay);
    return (_num < 0 ? 0 : isNaN(_num) ? 0 : _num);
  },
  
  resetForm : function(fmId) {
    document.getElementById(fmId).reset();
    //
    var _fmIns = $("#" + fmId).find("input[type='text']");
    $.each(_fmIns, function(i, n) {
      $(n).val("");
    });
    //
    WebBase.resetFormTip();
  },
  
  resetFormTip : function() {
    $("form span.red.ml10").text('');
  },
  
  maxlength : function(id, max) {
    jQuery("#" + id).keyup(function() {
      max = parseInt(max);
      var _val = jQuery(this).val();
      if (_val != null && _val.length > max) {
        jQuery(this).val(_val.substr(0, max));
      }
    });
  },
  
  encodeBase64 : function(id) {
    return Base64.encode($('#' + id).val()).replace(/\+/g, '%2B');
  },
  
  encodeBase64Text : function(text) {
    return Base64.encode(text).replace(/\+/g, '%2B');
  },
  
  decodeBase64 : function(id) {
    return Base64.decode($('#' + id).val());
  },
  
  selectRadio : function(id, radioName) {
    var _val = $("#" + id).val();
    $.each($("input[name='" + radioName + "']"), function(i, n) {
      if ($(this).val() == _val) {
        $(this).prop("checked", "checked");
        return false;
      }
    });
  },
  
  bindFcbk : function(id, tip, url, oncreateFunc, afterFunc, width) {
    if (width == null) {
      width = 455;
    }
    $("#" + id).fcbkcomplete({
      json_url : url,
      maxitems : 20,
      height : 6,
      width : width,
      cache : true,
      newel : false,
      delay : 600,
      input_min_size : 0,
      filter_selected : true,
      complete_text : tip,
      oncreate : function() {
        if (WebBase.isFunc(oncreateFunc)) {
          oncreateFunc();
        }
      }
    });
    // format fcbk textarea
    $("#" + id).change(function() {
      WebBase.fmtFcbkTextarea(id);
    });
    if (WebBase.isFunc(afterFunc)) {
      afterFunc();
    }
  },
  
  fmtFcbkTextarea : function(id) {
    $('.bit-box').each(function(i, n) {
      var _liTxt = $(n).text();
      if (_liTxt != null && _liTxt.indexOf("<") > 0) {
        $(n).html(_liTxt.substr(_liTxt.indexOf("<"), _liTxt.length) + '<a class="closebutton" href="#"></a>');
      }
    });
  },
  
  addFcbkItem : function(id, items) {
    if (items != null && items.length > 0 && items != "[]") {
      items = eval(items);
      $(items).each(function(i, n) {
        $("#" + id).trigger("addItem", n);
      });
    }
  },
  
  removeFcbkItem : function(id, items) {
    if (items != null && items.length > 0 && items != "[]") {
      items = eval(items);
      $(items).each(function(i, n) {
        $("#" + id).trigger("removeItem", n);
      });
    }
  },
  
  removeAllFcbkItem : function(id) {
    var _tgs = $("#" + id).children();
    if (_tgs != null && _tgs.length > 0) {
      _tgs.each(function(i, n) {
        if ($(n).prop("selected")) {
          _tgs.trigger("removeItem", n);
        }
      });
    }
  },
  
  setFcbkVals : function(id, tgId) {
    var _tgVal = "";
    var _tgs = $("#" + id).children();
    if (_tgs != null && _tgs.length > 0) {
      _tgs.each(function(i, n) {
        if ($(n).prop("selected")) {
          if (WebBase.fmtBlank($(n).val()) != "") {
            if (i > 0) {
              _tgVal += ",";
            }
            _tgVal += $(n).val();
          }
        }
      });
    }
    $("#" + tgId).val(_tgVal);
  }

};

/**
 * Format date <br/>
 * 
 * new Date().setTime(val).format("yyyy-MM-dd hh:mm:ss",str)
 * <p>
 * var d = new Date();
 * <p>
 * d.setTime(val);
 * <p>
 * d.format(fmt); => 2006-07-02 08:09:04
 * 
 * @param fmt
 * @returns
 */
Date.prototype.format = function(fmt) {
  var o = {
    "M+" : this.getMonth() + 1, // 月份
    "d+" : this.getDate(), // 日
    "h+" : this.getHours(), // 小时
    "m+" : this.getMinutes(), // 分
    "s+" : this.getSeconds(), // 秒
    "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
    // 毫秒
    "S" : this.getMilliseconds()
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for ( var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
  }
  return fmt;
};
