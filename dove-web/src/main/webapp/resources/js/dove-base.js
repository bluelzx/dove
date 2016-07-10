/**
 * Wechat sbus base JS
 * 
 * @version 0.0.1
 * @since 2014-11-18
 */
var WcsbBase = {
  
  addPrefix : '',
  
  addPrefix2 : '',
  
  batDelPrefix : '',
  
  batDelPrefix2 : '',
  
  /**
   * Change/Replace themes URI
   * 
   * @param rs
   * @returns
   */
  chgThemesUri : function(rs) {
    return WebBase.chgThemesUri(2, rs, false);
  },
  
  forward : function(url) {
    document.location.href = WebBase.getCxtPath() + url;
  },
  
  doErr : function() {
    // alert("你访问的资源不存在或禁止访问！");
    WcsbBase.forward("/alert/err/error.html");
  },
  
  authFail : function() {
    WcsbBase.forward("/alert/fail/auth-fail.html");
  },
  
  delErr : function() {
    alert("该记录被使用，不能直接删除！");
  },
  
  saveErr : function() {
    alert("保存数据失败！");
  },
  
  downloadErr : function() {
    alert("该文件已不存在，无法下载！");
  },
  
  /**
   * Get resources page URI
   * 
   * @returns /cxtPath/resources/
   */
  getResourcesUri : function() {
    return WebBase.getCxtPath() + "/resources/";
  },
  
  /**
   * Get resources js path
   * 
   * @returns /cxtPath/resources/js/
   */
  getResJsPath : function() {
    return WebBase.getCxtPath() + "/resources/js/";
  },
  
  /**
   * Get resources jslib path
   * 
   * @returns /cxtPath/resources/jslib/
   */
  getResJslibPath : function() {
    return WebBase.getCxtPath() + "/resources/jslib/";
  },
  
  /**
   * Get themes page URI
   * 
   * @returns /cxtPath/themes/
   */
  getThemesUri : function() {
    return WebBase.getCxtPath() + "/themes/";
  },
  
  /**
   * Get views JS URI
   * 
   * @returns /cxtPath/views/
   */
  getViewsUri : function() {
    return WebBase.getCxtPath() + "/views/";
  },
  
  getSelectDlVal : function(id, name) {
    var _input = $("#" + id).find("input[name=" + name + "]");
    if (_input != null && _input != undefined) {
      _input.attr("id", name + "_");
      var _val = _input.val();
      if (_val != null && _val != undefined) {
        return _val;
      }
    }
    return '';
  },
  
  loadChboxAll : function(ptId) {
    var _chbox = $("#" + ptId);
    if (_chbox != null && _chbox.prop("type") == "checkbox") {
      _chbox.unbind("click").click(function() {
        $("input[name='" + _chbox.prop("name") + "']").each(function(i, n) {
          n.checked = _chbox.prop("checked");
        });
      });
    }
  },
  
  loadUnChboxAll : function(ptId) {
    var _chbox = $('#' + ptId);
    if (_chbox != null && _chbox.prop('type') == 'checkbox') {
      $('input[name=' + _chbox.prop('name') + ']').each(function() {
        $(this).checked = false;
      });
      _chbox.prop('checked', false);
    }
  },
  
  rmTimeImg : function() {
    var _beg = $("#beg_time_img_");
    var _end = $("#end_time_img_");
    if (_beg != null) {
      _beg.remove();
    }
    if (_end != null) {
      _end.remove();
    }
  },
  
  createCkChbox : function(id, busiId, json) {
    var _tg = $("#" + id);
    var _busiTg = $("#" + busiId);
    if (_tg != null && _busiTg != null && json != null) {
      var _str = null;
      if (json.success) {
        _tg.empty();
        var _busiVals = null;
        $.each(json.data, function(key, value) {
          _str = "<label class='ml10'>";
          _str += "<input type='checkbox' id='chbox_" + key + "' name='chbox_" + busiId + "' value='" + key + "' ";
          _str += " onclick=\"javascript:WcsbBase.setChboxVal(this.id,'" + busiId + "');\" />";
          _str += "<span>" + value + "</span></label>";
          _tg.append(_str);
          if (key != null && key != '' && _busiTg.val() != null) {
            _busiVals = _busiTg.val().split(",");
            for (var j = 0; j < _busiVals.length; j++) {
              if (key == _busiVals[j]) {
                $("#chbox_" + key).prop("checked", true);
                break;
              }
            }
          }
        });
        // message
        _tg.append("<span id='" + busiId + "msg_' class='red ml10'></span>");
      }
    }
  },
  
  setChboxVal : function(id, busiId) {
    var _vals = WebBase.getCkboxVals(id);
    if (_vals != null) {
      $("#" + busiId).val(_vals + '');
    }
  },
  
  createCkRabox : function(id, busiId, json) {
    var _tg = $("#" + id);
    var _busiTg = $("#" + busiId);
    if (_tg != null && _busiTg != null && json != null) {
      var _str = null;
      if (json.success) {
        _tg.empty();
        $.each(json.data, function(key, value) {
          _str = "<label class='ml10'>";
          _str += "<input type='radio' id='rabox_" + key + "' name='rabox_" + busiId + "' ";
          _str += " value='" + key + "' />";
          _str += "<span>" + value + "</span></label>";
          _tg.append(_str);
          if (key != null && key != '' && _busiTg.val() != null && (key == _busiTg.val())) {
            $("#rabox_" + key).prop("checked", true);
          }
        });
      }
    }
  },
  
  createRabox : function(prefix, id, json) {
    var _raboxName = prefix + "rabox";
    var _tg = $("#" + id);
    if (_tg != null && json != null) {
      var _str = null;
      if (json.success) {
        _tg.empty();
        var i = 0;
        $.each(json.data, function(key, value) {
          _str = "<label class='ml10'>";
          _str += "<input type='radio' id='rabox_" + key + "' name='" + _raboxName + "' ";
          if (i == 0) {
            _str += " checked='checked' ";
          }
          _str += " value='" + key + "' />";
          _str += "<span>" + value + "</span></label>";
          _tg.append(_str);
          i++;
        });
      }
    }
    return _raboxName;
  },
  
  /**
   * Do paging
   * 
   * @param prefix
   * @param url
   * @param callback
   */
  doPaging : function(prefix, url, callback) {
    EcDatagrid.paging(prefix, url, 1, 10, callback);
  },
  
  /**
   * Prompt dialog
   * 
   * @param btnId
   * @param dlgId
   * @param ptDlgId
   *          parent dialog id
   */
  promptDlg : function(btnId, dlgId, ptDlgId) {
    FuncBinder.bindBtn(null, null, btnId, function() {
      hide('cover', dlgId);
      WcsbBase.showPtDialog(ptDlgId);
    });
  },
  
  /**
   * Show parent dialog
   * 
   * @param ptDlgId
   */
  showPtDialog : function(ptDlgId) {
    if (ptDlgId != null && ptDlgId != '') {
      show('cover', ptDlgId);
    }
  },
  
  /**
   * Hidden parent dialog
   * 
   * @param ptDlgId
   */
  hidePtDialog : function(ptDlgId) {
    if (ptDlgId != null && ptDlgId != '') {
      hide('cover', ptDlgId);
    }
  },
  
  /**
   * Delete data
   * 
   * @param prefix
   * @param id
   * @param url
   * @param ptDlgId
   * @param callback
   */
  del : function(prefix, id, url, ptDlgId, callback) {
    if (prefix == null) {
      prefix = '';
    }
    if (id != null && id != '') {
      var _delDlg = FuncBinder._defaults.delDlg;
      WcsbBase.hidePtDialog(ptDlgId);
      show('cover', _delDlg);
      // 确定删除
      FuncBinder.bindBtn(null, null, FuncBinder._defaults.yDelBtn, function() {
        FuncBinder.del(null, url, {
          id : id + ''
        }, function(rs) {
          FuncBinder.unChboxAll(prefix);
          hide('cover', _delDlg);
          if (rs && rs.success) { // success
            EcDatagrid.refreshPage(prefix, callback);
            var _delOkDlg = FuncBinder._defaults.delOkDlg;
            show('cover', _delOkDlg);
            WcsbBase.promptDlg('t_del_ok_btn', _delOkDlg, ptDlgId);
            WcsbBase.promptDlg('del_ok_btn', _delOkDlg, ptDlgId);
          } else {
            WcsbBase.delErr();
          }
        });
      });
      // 取消删除
      WcsbBase.promptDlg('n_del_btn', _delDlg, ptDlgId);
      WcsbBase.promptDlg('tn_del_btn', _delDlg, ptDlgId);
    }
  },
  
  /**
   * Batch delete data
   * 
   * @param batDelPrefix
   * @param callback
   * @param ptDlgId
   */
  batDel : function(batDelPrefix, callback, ptDlgId) {
    if (batDelPrefix == null) {
      batDelPrefix = '';
    }
    WcsbBase.batDelPrefix = batDelPrefix;
    FuncBinder.batDel(WcsbBase.batDelPrefix, null, function(rs) {
      if (rs != null) {
        if (FuncBinder.isFunc(callback)) {
          callback((rs + ''));
        }
      } else {
        // 选择提示
        var _selDlg = FuncBinder._defaults.selDlg;
        WcsbBase.hidePtDialog(ptDlgId);
        show("cover", _selDlg);
        WcsbBase.promptDlg('t_select_btn', _selDlg, ptDlgId);
        WcsbBase.promptDlg('select_btn', _selDlg, ptDlgId);
      }
    });
  },
  
  /**
   * Batch delete2 data
   * 
   * @param batDelPrefix2
   * @param callback
   * @param ptDlgId
   */
  batDel2 : function(batDelPrefix2, callback, ptDlgId) {
    if (batDelPrefix2 == null) {
      batDelPrefix2 = '';
    }
    WcsbBase.batDelPrefix2 = batDelPrefix2;
    FuncBinder.batDel(WcsbBase.batDelPrefix2, null, function(rs) {
      if (rs != null) {
        if (FuncBinder.isFunc(callback)) {
          callback((rs + ''));
        }
      } else {
        // 选择提示
        var _selDlg = FuncBinder._defaults.selDlg;
        WcsbBase.hidePtDialog(ptDlgId);
        show("cover", _selDlg);
        WcsbBase.promptDlg('t_select_btn', _selDlg, ptDlgId);
        WcsbBase.promptDlg('select_btn', _selDlg, ptDlgId);
      }
    });
  },
  
  /**
   * View data
   * 
   * @param prefix
   * @param suffix
   * @param url
   * @param ptDlgId
   * @param callback
   */
  view : function(prefix, suffix, url, ptDlgId, callback) {
    if (prefix == null) {
      prefix = '';
    }
    if (suffix == null) {
      suffix = '';
    }
    var _rs = FuncBinder.getPostJson(url, null);
    if (_rs != null && _rs.success && _rs.data != null) {
      $.each(_rs.data, function(i, n) {
        $('#' + prefix + i + suffix).text(WebBase.fmtBlank(n));
      });
      if (FuncBinder.isFunc(callback)) {
        callback(_rs);
      }
    }
    WcsbBase.hidePtDialog(ptDlgId);
    show('cover', prefix + 'view_dlg');
    // 取消
    WcsbBase.promptDlg(prefix + 'n_view_btn', prefix + 'view_dlg', ptDlgId);
    WcsbBase.promptDlg(prefix + 'tn_view_btn', prefix + 'view_dlg', ptDlgId);
  },
  
  /**
   * Edit data
   * 
   * @param prefix
   * @param getUrl
   * @param updateUrl
   * @param checkEditForm
   * @param ptDlgId
   * @param callback
   */
  edit : function(prefix, getUrl, updateUrl, checkEditForm, ptDlgId, callback) {
    WcsbBase.edit2(prefix, getUrl, updateUrl, checkEditForm, ptDlgId, callback, null);
  },
  
  /**
   * Edit data
   * 
   * @param prefix
   * @param getUrl
   * @param updateUrl
   * @param checkEditForm
   * @param ptDlgId
   * @param callback
   * @param pagingFunc
   */
  edit2 : function(prefix, getUrl, updateUrl, checkEditForm, ptDlgId, callback, pagingFunc) {
    if (prefix == null) {
      prefix = '';
    }
    // view data
    var _rs = FuncBinder.getPostJson(getUrl, null);
    if (_rs != null && _rs.success && _rs.data != null) {
      // 查看
      $.each(_rs.data, function(i, n) {
        $('#' + prefix + i + '_').val(WebBase.fmtBlank(n));
      });
      if (FuncBinder.isFunc(callback)) {
        callback(_rs);
      }
      WcsbBase.hidePtDialog(ptDlgId);
      $('#' + prefix + 'edit_fm_lab').text('编辑');
      show('cover', prefix + 'edit_dlg');
      // 提交保存
      FuncBinder.edit(prefix, null, updateUrl, checkEditForm, function(rs) {
        if (rs != null && rs.success) {
          EcDatagrid.refreshPage(prefix, pagingFunc);
          hide('cover', prefix + 'edit_dlg');
          var _saveOkDlg = prefix + FuncBinder._defaults.saveOkDlg;
          show('cover', _saveOkDlg);
          WcsbBase.promptDlg(prefix + 't_save_ok_btn', _saveOkDlg, ptDlgId);
          WcsbBase.promptDlg(prefix + 'save_ok_btn', _saveOkDlg, ptDlgId);
        } else {
          WcsbBase.saveErr();
        }
      });
      // 取消
      WcsbBase.promptDlg(prefix + 'n_edit_btn', prefix + 'edit_dlg', ptDlgId);
      WcsbBase.promptDlg(prefix + 'tn_edit_btn', prefix + 'edit_dlg', ptDlgId);
    }
  },
  
  /**
   * Prepare add data
   * 
   * @param addPrefix
   * @param saveUrl
   * @param checkEditForm
   * @param ptDlgId
   * @param resetFunc
   */
  prepAdd : function(addPrefix, saveUrl, checkEditForm, ptDlgId, resetFunc) {
    WcsbBase.prepAddX(addPrefix, saveUrl, checkEditForm, ptDlgId, resetFunc, null);
  },
  
  /**
   * Prepare add data
   * 
   * @param addPrefix
   * @param saveUrl
   * @param checkEditForm
   * @param ptDlgId
   * @param resetFunc
   * @param pagingFunc
   */
  prepAddX : function(addPrefix, saveUrl, checkEditForm, ptDlgId, resetFunc, pagingFunc) {
    if (addPrefix == null) {
      addPrefix = '';
    }
    WcsbBase.addPrefix = addPrefix;
    FuncBinder.bindBtn(WcsbBase.addPrefix, null, 'prep_add_btn', function() {
      // do reset callback
      if (WebBase.isFunc(resetFunc)) {
        resetFunc();
      }
      WcsbBase.hidePtDialog(ptDlgId);
      show('cover', WcsbBase.addPrefix + 'edit_dlg');
      $('#' + WcsbBase.addPrefix + 'edit_fm_lab').text('新增');
      // 提交保存
      FuncBinder.edit(WcsbBase.addPrefix, null, saveUrl, checkEditForm, function(rs) {
        if (rs != null && rs.success) {
          EcDatagrid.refreshPage(WcsbBase.addPrefix, pagingFunc);
          hide('cover', WcsbBase.addPrefix + 'edit_dlg');
          var _saveOkDlg = WcsbBase.addPrefix + FuncBinder._defaults.saveOkDlg;
          show('cover', _saveOkDlg);
          WcsbBase.promptDlg(WcsbBase.addPrefix + 't_save_ok_btn', _saveOkDlg, ptDlgId);
          WcsbBase.promptDlg(WcsbBase.addPrefix + 'save_ok_btn', _saveOkDlg, ptDlgId);
        } else {
          WcsbBase.saveErr();
        }
      });
      // 取消
      WcsbBase.promptDlg(WcsbBase.addPrefix + 'n_edit_btn', WcsbBase.addPrefix + 'edit_dlg', ptDlgId);
      WcsbBase.promptDlg(WcsbBase.addPrefix + 'tn_edit_btn', WcsbBase.addPrefix + 'edit_dlg', ptDlgId);
    });
  },
  
  /**
   * Prepare add2 data
   * 
   * @param addPrefix2
   * @param saveUrl
   * @param checkEditForm
   * @param ptDlgId
   * @param resetFunc
   */
  prepAdd2 : function(addPrefix2, saveUrl, checkEditForm, ptDlgId, resetFunc) {
    if (addPrefix2 == null) {
      addPrefix2 = '';
    }
    WcsbBase.addPrefix2 = addPrefix2;
    FuncBinder.bindBtn(WcsbBase.addPrefix2, null, 'prep_add_btn', function() {
      // do reset callback
      if (WebBase.isFunc(resetFunc)) {
        resetFunc();
      }
      WcsbBase.hidePtDialog(ptDlgId);
      show('cover', WcsbBase.addPrefix2 + 'edit_dlg');
      $('#' + WcsbBase.addPrefix2 + 'edit_fm_lab').text('新增');
      // 提交保存
      FuncBinder.edit(WcsbBase.addPrefix2, null, saveUrl, checkEditForm, function(rs) {
        if (rs != null && rs.success) {
          EcDatagrid.refreshPage(WcsbBase.addPrefix2, null);
          hide('cover', WcsbBase.addPrefix2 + 'edit_dlg');
          var _saveOkDlg = WcsbBase.addPrefix2 + FuncBinder._defaults.saveOkDlg;
          show('cover', _saveOkDlg);
          WcsbBase.promptDlg(WcsbBase.addPrefix2 + 't_save_ok_btn', _saveOkDlg, ptDlgId);
          WcsbBase.promptDlg(WcsbBase.addPrefix2 + 'save_ok_btn', _saveOkDlg, ptDlgId);
        } else {
          WcsbBase.saveErr();
        }
      });
      // 取消
      WcsbBase.promptDlg(WcsbBase.addPrefix2 + 'n_edit_btn', WcsbBase.addPrefix2 + 'edit_dlg', ptDlgId);
      WcsbBase.promptDlg(WcsbBase.addPrefix2 + 'tn_edit_btn', WcsbBase.addPrefix2 + 'edit_dlg', ptDlgId);
    });
  },
  
  loadSelectbox : function(id, dataMap, defaultVal) {
    var txt = '<p name="">--- 请选择 ---</p>';
    for ( var i in dataMap) {
      txt += '<p name="' + i + '">' + dataMap[i] + '</p>';
    }
    $('#' + id + " dd ").html(txt);
    initSelectBox();
    if (defaultVal != null && defaultVal != "") {
      $("#" + id).find("p").each(function() {
        if ($(this).attr("name") == defaultVal) {
          $(this).click();
        }
      });
    }
  },
  
  loadSelectboxVal : function(id, defaultVal) {
    if (defaultVal != null && defaultVal != "") {
      $("#" + id).find("p").each(function() {
        if ($(this).attr("name") == defaultVal) {
          $(this).click();
        }
      });
    }
  },
  
  /**
   * Append yyyy-MM-dd WdatePicker E
   * 
   * @param id
   */
  appendWdateYmd : function(id) {
    WcsbBase.appendWdate(id, 'yyyy-MM-dd');
  },
  
  /**
   * Append yyyy-MM-dd WdatePicker E
   * 
   * @param id
   */
  appendWdateYmdAddCallFun : function(id, callFun) {
    WcsbBase.appendWdateAddCallFun(id, 'yyyy-MM-dd', callFun);
  },
  
  /**
   * Append yyyy-MM-dd HH:mm:00 WdatePicker E
   * 
   * @param id
   */
  appendWdateYmdhm0 : function(id) {
    WcsbBase.appendWdate(id, 'yyyy-MM-dd HH:mm:00');
  },
  
  /**
   * Append WdatePicker E
   * 
   * @param id
   * @param partten
   *          yyyy-MM-dd HH:mm:00
   */
  appendWdate : function(id, partten) {
    if (partten == null || partten == '') {
      partten = 'yyyy-MM-dd HH:mm:00';
    }
    if (id != null) {
      $('#' + id).unbind("click").click(function() {
        WdatePicker({
          dateFmt : partten
        });
      });
      $('#' + id).attr("class", "Wdate");
      // remove image
      var _img = $('#' + id + 'img_');
      if (_img != null) {
        _img.remove();
      }
    }
  },
  
  appendWdateAddCallFun : function(id, partten, callFun) {
    if (partten == null || partten == '') {
      partten = 'yyyy-MM-dd HH:mm:00';
    }
    if (id != null) {
      $('#' + id).unbind("click").click(function() {
        WdatePicker({
          dateFmt : partten,
          onpicked : callFun,
          oncleared : callFun
        });
      });
      $('#' + id).attr("class", "Wdate");
      // remove image
      var _img = $('#' + id + 'img_');
      if (_img != null) {
        _img.remove();
      }
    }
  },
  
  /**
   * print page
   */
  doPrint : function() {
    var startFlag = "<!-- begin print -->";
    var endFlag = "<!-- end print -->";
    var bdhtml = window.document.body.innerHTML;
    var prnhtml = bdhtml.substr(bdhtml.indexOf(startFlag) + startFlag.length);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(endFlag));
    window.document.body.innerHTML = prnhtml;
    window.print();
  },
  
  /**
   * Do upload file
   * 
   * @param prefix
   * @param url
   * @param btnId
   * @param fileEleNames
   * @param busiId
   * @param callback
   */
  doUploadFile : function(prefix, url, btnId, fileEleNames, busiId, callback) {
    WebBase.uploadFile(prefix, url, btnId, fileEleNames, busiId, callback);
  },
  
  bindSelBoxBtn : function(url, btnId, dlgId, callback) {
    FuncBinder.bindBtn(null, null, btnId, function() {
      var _ids = FuncBinder.getCheckboxVals(null);
      if (_ids == null || _ids.length == 0) {
        // 选择提示
        var _selDlg = FuncBinder._defaults.selDlg;
        show("cover", _selDlg);
        WcsbBase.promptDlg('t_select_btn', _selDlg, null);
        WcsbBase.promptDlg('select_btn', _selDlg, null);
        return;
      }
      show("cover", dlgId);
      // 确定
      FuncBinder.bindBtn(null, null, "y_" + btnId, function() {
        hide('cover', dlgId);
        var _rs = FuncBinder.getPostJson(url + _ids, null);
        var _tmpTxt = "操作成功！";
        if (_rs == null || WebBase.isNotEmpty(_rs.err_msg)) {
          WcsbBase.doErr();
          return;
        }
        if (_rs != null && !_rs.success) {
          _tmpTxt = "[ 执行失败 ]  " + _rs.data;
        }
        EcDatagrid.refreshPage(null, callback);
        $("#txt_ok_dlg").text(_tmpTxt);
        show('cover', 'ok_dlg');
        // 提示框标题
        WcsbBase.promptDlg("t_ok_btn", "ok_dlg", null);
        // 提示框确定
        WcsbBase.promptDlg("ok_btn", "ok_dlg", null);
      });
      // 取消-标题
      WcsbBase.promptDlg("tn_" + btnId, dlgId, null);
      // 取消
      WcsbBase.promptDlg("n_" + btnId, dlgId, null);
    });
  },
  
  refreshBtn : function(url) {
    WcsbBase.bindTipBoxBtn(url, "refresh_btn", "refresh_dlg");
  },
  
  bindTipBoxBtn : function(url, btnId, dlgId) {
    FuncBinder.bindBtn(null, null, btnId, function() {
      show("cover", dlgId);
      // 确定
      FuncBinder.bindBtn(null, null, "y_" + btnId, function() {
        hide('cover', dlgId);
        var _rs = FuncBinder.getPostJson(url, null);
        if (_rs == null || !_rs.success || WebBase.isNotEmpty(_rs.err_msg)) {
          WcsbBase.doErr();
        } else {
          $("#txt_ok_dlg").text("操作成功！");
          show('cover', 'ok_dlg');
          // 操作成功-标题
          WcsbBase.promptDlg("t_ok_btn", "ok_dlg", null);
          // 操作成功
          WcsbBase.promptDlg("ok_btn", "ok_dlg", null);
        }
      });
      // 取消-标题
      WcsbBase.promptDlg("tn_" + btnId, dlgId, null);
      // 取消
      WcsbBase.promptDlg("n_" + btnId, dlgId, null);
    });
  },
  
  enabledBtn : function(url, text, callback) {
    WcsbBase.tipBoxBtn(url, "enabled_btn", "enabled_dlg", text, callback);
  },
  
  tipBoxBtn : function(url, btnId, dlgId, text, callback) {
    $("#txt_" + dlgId).text(text);
    show("cover", dlgId);
    // 确定
    FuncBinder.bindBtn(null, null, "y_" + btnId, function() {
      hide('cover', dlgId);
      var _rs = FuncBinder.getPostJson(url, null);
      var _tmpTxt = "操作成功！";
      if (_rs == null || WebBase.isNotEmpty(_rs.err_msg)) {
        WcsbBase.doErr();
        return;
      }
      if (_rs != null && !_rs.success) {
        _tmpTxt = "[ 执行失败 ]  " + _rs.data;
      }
      EcDatagrid.refreshPage(null, callback);
      $("#txt_ok_dlg").text(_tmpTxt);
      show('cover', 'ok_dlg');
      // 提示框标题
      WcsbBase.promptDlg("t_ok_btn", "ok_dlg", null);
      // 提示框确定
      WcsbBase.promptDlg("ok_btn", "ok_dlg", null);
    });
    // 取消-标题
    WcsbBase.promptDlg("tn_" + btnId, dlgId, null);
    // 取消
    WcsbBase.promptDlg("n_" + btnId, dlgId, null);
  }

};

/**
 * Session expired
 */
// $.ajaxSetup({
// complete : function(xhr, txtStatus) {
// try {
// var _status = xhr.getResponseHeader('status');
// if (_status == 'logout') {
// window.location.replace(xhr.getResponseHeader('loginUrl'));
// } else if (txtStatus == 'error') {
// WcsbBase.doErr();
// }
// } catch (e) {
// console.info(e);
// }
// }
// });
