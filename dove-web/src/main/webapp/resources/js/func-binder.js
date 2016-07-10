/**
 * Function binder
 * 
 * <p>
 * example： FuncBinder.beginAdd({url:url,data:data,callback:cb});
 * </p>
 * 
 * @version 0.0.1
 * @since 2014-12-5
 */
var FuncBinder = {};
(function($, ns) {
  
  ns.version = '0.1.1';
  
  /**
   * Default parameter
   */
  ns._defaults = {
    okDlg : "ok_dlg",
    addDlg : "add_dlg",
    addBtn : "add_btn",
    prepAddBtn : "prep_add_btn",
    editDlg : "edit_dlg",
    editBtn : "edit_btn",
    prepEditBtn : "prep_edit_btn",
    delDlg : "del_dlg",
    delBtn : "del_btn",
    viewDlg : "view_dlg",
    viewBtn : "view_btn",
    addFm : "add_fm",
    editFm : "edit_fm",
    batDelBtn : "bat_del_btn",
    yDelBtn : "y_del_btn",
    nDelBtn : "n_del_btn",
    delOkDlg : "del_ok_dlg",
    cboxAll : "cbox_all",
    btnKey : "_btn",
    dlgKey : "_dlg",
    listDlg : "list_dlg",
    listBtn : "list_btn",
    saveOkDlg : "save_ok_dlg",
    fmKey : "_fm",
    selDlg : "select_dlg",
    cheDlg : "check_dlg"
  };
  
  ns._getId = function(key) {
    if (ns._prefix == null) {
      ns._prefix = "";
    }
    return ns._prefix + key;
  };
  
  ns._getBtnId = function(key, defv) {
    var _btnId = key + "" + ns._defaults.btnKey;
    if (key == null || key == "") {
      _btnId = defv;
    }
    return ns._getId(_btnId);
  };
  
  ns._getDlgId = function(key, defv) {
    var _dlgId = key + "" + ns._defaults.dlgKey;
    if (key == null || key == "") {
      _dlgId = defv;
    }
    return ns._getId(_dlgId);
  };
  
  ns._getFmId = function(key, defv) {
    var _fmId = key + "" + ns._defaults.fmKey;
    if (key == null || key == "") {
      _fmId = defv;
    }
    return ns._getId(_fmId);
  };
  
  ns._prefix = "";
  
  /**
   * Append extend parameter
   * 
   * @param src
   * @param dest
   */
  ns.extend = function(src, dest) {
    var name = null;
    for (name in dest) {
      src[name] = dest[name];
    }
    return src;
  };
  
  /**
   * Is function
   * 
   * @param func
   */
  ns.isFunc = function(func) {
    return (func != null && (typeof func) == "function");
  };
  
  ns.fnErr = function(xhr, status, err) {
    alert("Network busy...");
  };
  
  ns.msgBox = function(title, msg, callback) {
    if (ns.isFunc(callback)) {
      callback();
    } else {
      alert(title + "\n" + msg);
    }
  };
  
  ns.getUrl = function(t) {
    var ori = window.location.href + '';
    return ori.substr(0, ori.lastIndexOf("/")) + "/" + t;
  };
  
  /**
   * Get text result
   * 
   * @param url
   * @param data
   */
  ns.getText = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : "get",
      dataType : "text",
      async : false,
      cache : false,
      success : function(rs) {
        _rs = rs;
      },
      error : function(xhr, status, err) {
        ns.fnErr(xhr, status, err);
      }
    });
    return _rs;
  };
  
  /**
   * Do get text result
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.doGetText = function(url, data, callback) {
    var _rs = ns.getText(url, data);
    if (_rs != null && ns.isFunc(callback)) {
      // replace ../../ path
      _rs = ns.chgThemesUri(2, _rs);
      callback(_rs);
    }
  };
  
  /**
   * Get get JSON data
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.getJson = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : "get",
      dataType : "json",
      async : false,
      cache : false,
      success : function(rs) {
        _rs = rs;
      },
      error : function(xhr, status, err) {
        ns.fnErr(xhr, status, err);
      }
    });
    return _rs;
  };
  
  /**
   * Do get JSON data
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.doGetJson = function(url, data, callback) {
    var _rs = ns.getJson(url, data);
    if (_rs != null && ns.isFunc(callback)) {
      callback(_rs);
    }
  };
  
  /**
   * Do post for JSON
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.doPostJson = function(url, data, callback) {
    var _rs = ns.getPostJson(url, data);
    if (_rs != null && ns.isFunc(callback)) {
      callback(_rs);
    }
  };
  
  /**
   * Get post JSON data
   * 
   * @param url
   * @param data
   */
  ns.getPostJson = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : "post",
      dataType : "json",
      async : false,
      cache : false,
      success : function(rs) {
        _rs = rs;
      },
      error : function(xhr, status, err) {
        ns.fnErr(xhr, status, err);
      }
    });
    return _rs;
  };
  
  /**
   * Do post for JSON
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.doPost = function(url, data, callback) {
    var _rs = ns.getPost(url, data);
    if (_rs != null && ns.isFunc(callback)) {
      callback(_rs);
    }
  };
  
  /**
   * Get post text for JSON
   * 
   * @param url
   * @param data
   */
  ns.getPost = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : "post",
      dataType : "text",
      async : false,
      cache : false,
      success : function(rs) {
        _rs = rs;
      },
      error : function(xhr, status, err) {
        ns.fnErr(xhr, status, err);
      }
    });
    return _rs;
  };
  
  /**
   * Get context path
   */
  ns.getCxtPath = function() {
    return '/' + window.location.pathname.split('/')[1];
  };
  
  /**
   * Get checkbox values
   * 
   * @param prefix
   */
  ns.getCheckboxVals = function(prefix) {
    ns._prefix = prefix;
    var _vals = new Array();
    var _tg = $("#" + ns._getBtnId(null, ns._defaults.cboxAll));
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
  };
  
  /**
   * Uncheck checkbox
   * 
   * @param prefix
   */
  ns.unChboxAll = function(prefix) {
    ns._prefix = prefix;
    var _tg = $("#" + ns._getBtnId(null, ns._defaults.cboxAll));
    if (_tg && _tg.prop("type") == "checkbox") {
      $('input[name=' + _tg.prop('name') + ']').each(function() {
        $(this).checked = false;
      });
      _tg.prop('checked', false);
    }
  };
  
  /**
   * Change/Replace themes URI
   * 
   * @param size
   *          [1,10]
   * @param rs
   * @returns
   */
  ns.chgThemesUri = function(size, rs) {
    if (rs) {
      size = parseInt(size);
      if (isNaN(size) || size < 1 || size > 10) {
        size = 2;
      }
      var _regexp = "";
      for (var i = 0; i < size; i++) {
        _regexp += "\\..\\/";
      }
      _regexp = eval("/" + _regexp + "/g");
      rs = rs.replace(_regexp, ns.getCxtPath() + "/themes/");
    }
    return rs;
  };
  
  /**
   * Prepare page file
   * 
   * @param btnId
   * @param dlgId
   * @param url
   * @param callback
   */
  ns.prepPage = function(btnId, dlgId, url, callback) {
    var _tg = $("#" + btnId);
    if (_tg != null && url != null) {
      _tg.attr("onclick", "");
      _tg.unbind('click').click(function() {
        var _rs = ns.getText(url, null);
        if (_rs != null && ns.isFunc(callback)) {
          // replace ../../ path
          _rs = ns.chgThemesUri(2, _rs);
          // add HTML
          $("#" + dlgId).html(_rs);
          // call function
          callback(dlgId);
        }
      });
    } else {
      alert("Prepare page parameter[id/url] is undefined!");
    }
  };
  
  /**
   * Prepare add button func
   * 
   * @param prefix
   * @param key
   * @param url
   * @param callback
   */
  ns.prepAddPage = function(prefix, key, url, callback) {
    ns._prefix = prefix;
    ns.prepPage(ns._getBtnId(null, ns._defaults.prepAddBtn), ns._getDlgId(key, ns._defaults.addDlg), url, callback);
  };
  
  /**
   * Prepare edit button func for page
   * 
   * @param prefix
   * @param key
   * @param url
   * @param callback
   */
  ns.prepEditPage = function(prefix, key, url, callback) {
    ns._prefix = prefix;
    ns.loadPage(null, ns._getDlgId(key, ns._defaults.editDlg), url, callback);
  };
  
  /**
   * Delete button func
   * 
   * @param prefix
   * @param url
   * @param data
   * @param callback
   */
  ns.del = function(prefix, url, data, callback) {
    ns._prefix = prefix;
    ns.doPostJson(url, data, callback);
  };
  
  /**
   * Batch delete button func
   * 
   * @param prefix
   * @param key
   * @param callback
   */
  ns.batDel = function(prefix, key, callback) {
    ns._prefix = prefix;
    var _tg = $("#" + ns._getBtnId(key, ns._defaults.batDelBtn));
    if (_tg != null) {
      _tg.attr("onclick", "");
      _tg.unbind("click").click(function() {
        if (ns.isFunc(callback)) {
          callback(ns.getCheckboxVals(prefix));
        }
      });
    } else {
      alert("Batch delete parameter[id] is undefined!");
    }
  };
  
  /**
   * Load page
   * 
   * @param prefix
   * @param dlgId
   * @param url
   * @param callback
   */
  ns.loadPage = function(prefix, dlgId, url, callback) {
    ns._prefix = prefix;
    var _dlg = $("#" + dlgId);
    if (url != null && _dlg != null) {
      var _rs = ns.getText(url, null);
      if (_rs != null && ns.isFunc(callback)) {
        // replace ../../ path
        _rs = ns.chgThemesUri(2, _rs);
        // insert HTML
        _dlg.html(_rs);
        // call function
        callback();
      }
    } else {
      alert("Load page parameter[url] is undefined!");
    }
  };
  
  /**
   * View button func for page
   * 
   * @param prefix
   * @param key
   * @param url
   * @param callback
   */
  ns.viewPage = function(prefix, key, url, callback) {
    ns._prefix = prefix;
    ns.loadPage(prefix, ns._getDlgId(key, ns._defaults.viewDlg), url, callback);
  };
  
  /**
   * Edit button func
   * 
   * @param btnId
   * @param fmId
   * @param url
   * @param prepSend
   * @param callback
   */
  ns.doEdit = function(btnId, fmId, url, prepSend, callback) {
    var _tg = $("#" + btnId);
    if (_tg != null && url != null) {
      _tg.attr("onclick", "");
      _tg.unbind('click').click(function() {
        // check form callback
        if (!ns.isFunc(prepSend) || (prepSend() == true)) {
          var _form = $("#" + fmId);
          if (_form) {
            ns.doPostJson(url, _form.serialize(), callback);
          }
        }
      });
    } else {
      alert("Edit parameter[id/url] is undefined!");
    }
  };
  
  /**
   * Bind button func
   * 
   * @param prefix
   * @param key
   * @param btnId
   * @param callback
   */
  ns.bindBtn = function(prefix, key, btnId, callback) {
    ns._prefix = prefix;
    var _tg = $("#" + ns._getBtnId(key, btnId));
    if (_tg != null) {
      _tg.attr("onclick", "");
      _tg.unbind("click").click(function() {
        if (ns.isFunc(callback)) {
          callback(_tg);
        }
      });
    } else {
      alert("Bind button object is undefined!");
    }
  };
  
  /**
   * Batch checkbox button func
   * 
   * @param prefix
   * @param key
   * @param btnId
   * @param callback
   */
  ns.batChboxBtn = function(prefix, key, btnId, callback) {
    ns._prefix = prefix;
    var _tg = $("#" + ns._getBtnId(key, btnId));
    if (_tg != null) {
      _tg.attr("onclick", "");
      _tg.unbind("click").click(function() {
        if (ns.isFunc(callback)) {
          callback(ns.getCheckboxVals(prefix));
        }
      });
    } else {
      alert("Batch checkbox button object is undefined!");
    }
  };
  
  /**
   * Add button func
   * 
   * @param prefix
   * @param key
   * @param prepSend
   * @param callback
   */
  ns.add = function(prefix, key, url, prepSend, callback) {
    ns._prefix = prefix;
    ns.doEdit(ns._getBtnId(key, ns._defaults.addBtn), ns._getFmId(key, ns._defaults.addFm), url, prepSend, callback);
  };
  
  /**
   * Edit button func
   * 
   * @param prefix
   * @param key
   * @param prepSend
   * @param callback
   */
  ns.edit = function(prefix, key, url, prepSend, callback) {
    ns._prefix = prefix;
    ns.doEdit(ns._getBtnId(key, ns._defaults.editBtn), ns._getFmId(key, ns._defaults.editFm), url, prepSend, callback);
  };
  
  /**
   * Ajax post sync request
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.post = function(url, data, callback) {
    ns.doPost(url, data, callback);
  };
  
  /**
   * Ajax post sync request for json
   * 
   * @param url
   * @param data
   * @param callback
   */
  ns.postJson = function(url, data, callback) {
    ns.doPostJson(url, data, callback);
  };
  
  /**
   * HashMap
   */
  ns.HashMap = function() {
    var size = 0;
    var entry = new Object();
    /** put */
    this.put = function(key, value) {
      if (!this.containsKey(key)) {
        size++;
      }
      entry[key] = value;
    };
    /** get */
    this.get = function(key) {
      if (this.containsKey(key)) {
        return entry[key];
      } else {
        return null;
      }
    };
    /** remove */
    this.remove = function(key) {
      if (delete entry[key]) {
        size--;
      }
    };
    /** clear */
    this.clear = function() {
      for ( var key in entry) {
        if (delete entry[key]) {
          size--;
        }
      }
    };
    /** contains key */
    this.containsKey = function(key) {
      return (key in entry);
    };
    /** contains value */
    this.containsValue = function(value) {
      for ( var prop in entry) {
        if (entry[prop] == value) {
          return true;
        }
      }
      return false;
    };
    /** values */
    this.values = function() {
      var values = new Array(size);
      for ( var prop in entry) {
        values.push(entry[prop]);
      }
      return values;
    };
    /** keys */
    this.keys = function() {
      var keys = new Array(size);
      for ( var prop in entry) {
        keys.push(prop);
      }
      return keys;
    };
    /** map size */
    this.size = function() {
      return size;
    };
  };
  
})(jQuery, FuncBinder);
