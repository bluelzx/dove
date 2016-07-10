/**
 * TODO: Data validator
 * 
 * <p>
 * example： DataValidator.email(id,'');
 * </p>
 * 
 * @version 0.0.1
 * @since 2014-12-5
 */
var DataValidator = {};
(function($, ns) {
  
  ns.version = '0.0.1';
  
  /**
   * Default parameter
   */
  ns._defaults = {
    msgId : 'msg_'
  };
  
  ns.getMsgId = function(id, defId) {
    var _msgId = ns._defaults.msgId;
    if (defId != null && defId != '') {
      _msgId = defId;
    }
    return id + _msgId;
  };
  
  ns.getMsg = function(msg, defMsg) {
    if (msg == null || msg == '') {
      msg = defMsg;
    }
    return msg;
  };
  
  ns.getRs = function(flag, id, msgId, msg, defMsg) {
    var _msgId = ns.getMsgId(id, msgId);
    if (flag) {
      $('#' + _msgId).text('');
    } else {
      $('#' + _msgId).text(ns.getMsg(msg, defMsg));
      $('#' + id).focus();
    }
    return flag;
  };
  
  ns.formatMsg = function(vals, msg) {
    if (vals != null && msg != null && msg != "") {
      var _vals = vals.split(',');
      for (var i = 0; i < _vals.length; i++) {
        msg = msg.replace(eval('/\\{' + i + '}/g'), _vals[i]);
      }
    }
    return msg;
  };
  
  /**
   * messages
   */
  ns.messages = {
    email : '请输入有效的Email地址！',
    url : '请输入有效的网址！',
    date : '请输入有效的日期！',
    dateISO : '请输入有效的日期 (YYYY-MM-DD)！',
    validName : '请输入有效名称（由数字/26字母/下划线/中文组成）！',
    validCode : '请输入有效编码（由数字/26字母/下划线组成）！',
    integer : '只能输入正整数！',
    money : '请输入有效的金额！',
    chinaPhone : '请输入有效电话号码，格式：010-12345678',
    chinese : '只能输入中文！',
    chinaIdCard : '请输入有效的身份证号码！',
    notEmpty : '该输入项不能为空！',
    ext : '请输入有效的后缀名称！',
    remoteErr : '远程请求异常！',
    moneyRange : '请输入 {0} 至 {1} 之间的金额！',
    intRange : '请输入 {0} 至 {1} 之间的正整数！'
  
  };
  
  ns.chinaArea = {
    11 : "北京",
    12 : "天津",
    13 : "河北",
    14 : "山西",
    15 : "内蒙古",
    21 : "辽宁",
    22 : "吉林",
    23 : "黑龙江",
    31 : "上海",
    32 : "江苏",
    33 : "浙江",
    34 : "安徽",
    35 : "福建",
    36 : "江西",
    37 : "山东",
    41 : "河南",
    42 : "湖北",
    43 : "湖南",
    44 : "广东",
    45 : "广西",
    46 : "海南",
    50 : "重庆",
    51 : "四川",
    52 : "贵州",
    53 : "云南",
    54 : "西藏",
    61 : "陕西",
    62 : "甘肃",
    63 : "青海",
    64 : "宁夏",
    65 : "新疆",
    71 : "台湾",
    81 : "香港",
    82 : "澳门",
    91 : "国外"
  };
  
  /**
   * extend functions
   */
  ns.isFunc = function(func) {
    return (func != null && (typeof func) == 'function');
  };
  
  ns.msgBox = function(title, msg, callback) {
    if (ns.isFunc(callback)) {
      callback();
    } else {
      alert(title + '\n' + msg);
    }
  };
  
  ns.getUrl = function(t) {
    var ori = window.location.href.toString();
    return ori.substr(0, ori.lastIndexOf('/')) + '/' + t;
  };
  
  ns.contains = function(array, str) {
    if (str == null || str == "" || array == null || array.length < 1) {
      return false;
    }
    var len = array.length;
    while (len--) {
      if (array[len] === str) {
        return true;
      }
    }
    return false;
  };
  
  /**
   * Get context path
   */
  ns.getCxtPath = function() {
    return '/' + window.location.pathname.split('/')[1];
  };
  
  ns.getText = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : 'get',
      dataType : 'text',
      async : false,
      cache : false,
      success : function(rs) {
        _rs = rs;
      },
      error : function() {
        alert(ns.messages.remoteErr);
      }
    });
    return _rs;
  };
  
  ns.getJson = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : 'get',
      dataType : 'json',
      async : false,
      cache : false,
      success : function(rs) {
        _rs = rs;
      },
      error : function(err) {
        alert(ns.messages.remoteErr);
      }
    });
    return _rs;
  };
  
  /**
   * Email
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.email = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
        .test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.email);
  };
  
  /**
   * URL
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.url = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
        .test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.url);
  };
  
  /**
   * Date
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.date = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.date);
  };
  
  /**
   * Date ISO (YYYY-MM-DD)
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.dateISO = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.dateISO);
  };
  
  /**
   * Is integer
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.integer = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^\d+$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.integer);
  };
  
  /**
   * Is valid name
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.isValidName = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.validName);
  };
  
  /**
   * Is valid code
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.isValidCode = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^[a-zA-Z0-9_]+$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.validCode);
  };
  
  /**
   * Is money
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.isMoney = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.money);
  };
  
  /**
   * Is chinese
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.chinese = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^[\u4e00-\u9fa5](\s*[\u4e00-\u9fa5])*$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.chinese);
  };
  
  /**
   * Is china phone number
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.chinaPhone = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (/^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/.test(_val));
    return ns.getRs(flag, id, null, msg, ns.messages.chinaPhone);
  };
  
  /**
   * Is china IdCard
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.chinaIdCard = function(id, msg) {
    var flag = true;
    var Y, JYM;
    var S, M;
    var idCard = $('#' + id).val();
    var idcardArray = idCard.split("");
    // check china area
    if (ns.chinaArea[parseInt(idCard.substr(0, 2))] == null) {
      flag = false;
    } else {
      // check IdCard
      switch (idCard.length) {
      case 15:
        if ((parseInt(idCard.substr(6, 2)) + 1900) % 4 == 0
            || ((parseInt(idCard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idCard.substr(6, 2)) + 1900) % 4 == 0)) {
          ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
        } else {
          ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;
        }
        flag = ereg.test(idCard);
      break;
      case 18:
        // 18 IdCard
        // 闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
        // 平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
        if (parseInt(idCard.substr(6, 4)) % 4 == 0
            || (parseInt(idCard.substr(6, 4)) % 100 == 0 && parseInt(idCard.substr(6, 4)) % 4 == 0)) {
          ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;// 闰年出生日期的合法性正则表达式
        } else {
          ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;// 平年出生日期的合法性正则表达式
        }
        if (ereg.test(idCard)) {
          S = (parseInt(idcardArray[0]) + parseInt(idcardArray[10])) * 7 + (parseInt(idcardArray[1]) + parseInt(idcardArray[11]))
              * 9 + (parseInt(idcardArray[2]) + parseInt(idcardArray[12])) * 10
              + (parseInt(idcardArray[3]) + parseInt(idcardArray[13])) * 5
              + (parseInt(idcardArray[4]) + parseInt(idcardArray[14])) * 8
              + (parseInt(idcardArray[5]) + parseInt(idcardArray[15])) * 4
              + (parseInt(idcardArray[6]) + parseInt(idcardArray[16])) * 2 + parseInt(idcardArray[7]) * 1
              + parseInt(idcardArray[8]) * 6 + parseInt(idcardArray[9]) * 3;
          Y = S % 11;
          M = "F";
          JYM = "10X98765432";
          M = JYM.substr(Y, 1); // 判断校验位
          flag = (M == idcardArray[17]); // 检测ID的校验位
        } else {
          flag = false;
        }
      break;
      default:
        flag = false;
      break;
      }
    }
    return ns.getRs(flag, id, null, msg, ns.messages.chinaIdCard);
  };
  
  /**
   * Is not empty
   * 
   * @param id
   * @param msg
   * @returns Boolean
   */
  ns.notEmpty = function(id, msg) {
    var _val = $('#' + id).val();
    var flag = (_val != null && $.trim(_val) != "");
    return ns.getRs(flag, id, null, msg, ns.messages.notEmpty);
  };
  
  /**
   * Check file ext name
   * 
   * @param id
   * @param exts
   * @param msg
   * @returns Boolean
   */
  ns.checkFileExt = function(id, exts, msg) {
    var _val = $('#' + id).val();
    var _fileExt = ((/[.]/.exec(_val)) ? /[^.]+$/.exec(_val.toLowerCase()) : '')[0];
    var _exts = exts.split(',');
    var flag = (_fileExt != null && _fileExt != "" && ns.contains(_exts, _fileExt));
    return ns.getRs(flag, id, null, msg, ns.messages.ext);
  };
  
  /**
   * Check remote
   * 
   * @param id
   * @param url
   * @param msg
   * @returns Boolean
   */
  ns.checkRemote = function(id, url, msg) {
    var _val = $('#' + id).val();
    var flag = (_val != null && $.trim(_val) != "");
    if (flag) {
      // ajax
      var _rs = ns.getJson(url, {
        key : _val
      });
      flag = (_rs != null && _rs.success);
    }
    return ns.getRs(flag, id, null, msg, ns.messages.remoteErr);
  };
  
  /**
   * Is required extends
   * 
   * @param id
   * @param msgId
   * @param msg
   * @returns Boolean
   */
  ns.isRequiredExt = function(id, msgId, msg) {
    var _val = null;
    if (CKEDITOR != null && CKEDITOR.instances[id]) {
      _val = CKEDITOR.instances[id].getData();
    } else {
      _val = $("#" + id).val();
    }
    var flag = (_val != null && $.trim(_val) != "");
    if (flag) {
      $('#' + msgId).text('');
    } else {
      $('#' + msgId).text(msg);
      if (CKEDITOR != null && CKEDITOR.instances[id]) {
        CKEDITOR.instances[id].focus();
      } else {
        $('#' + id).focus();
      }
    }
    return !flag;
  };
  
  /**
   * Is required
   * 
   * @param opts
   * @returns Boolean
   */
  ns.isRequired = function(opts) {
    return ns.isRequiredExt(opts.id, opts.msgId, opts.msg);
  };
  
  /**
   * Integer range
   * 
   * @param id
   * @param v1
   * @param v2
   * @param msg
   * @returns Boolean
   */
  ns.intRange = function(id, v1, v2, msg) {
    var _val = $('#' + id).val();
    var flag = (/^\d+$/.test(_val));
    if (flag) {
      _val = parseInt(_val);
      flag = (_val >= parseInt(v1) && _val < parseInt(v2));
    }
    var _defMsg = ns.formatMsg(v1 + "," + v2, ns.messages.intRange);
    return ns.getRs(flag, id, null, msg, _defMsg);
  };
  
  /**
   * Money range
   * 
   * @param id
   * @param v1
   * @param v2
   * @param msg
   * @returns Boolean
   */
  ns.moneyRange = function(id, v1, v2, msg) {
    var _val = $('#' + id).val();
    var flag = (/^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/.test(_val));
    if (flag) {
      _val = parseFloat(_val);
      flag = (_val >= parseFloat(v1) && _val < parseFloat(v2));
    }
    var _defMsg = ns.formatMsg(v1 + "," + v2, ns.messages.moneyRange);
    return ns.getRs(flag, id, null, msg, _defMsg);
  };
  
})(jQuery, DataValidator);
