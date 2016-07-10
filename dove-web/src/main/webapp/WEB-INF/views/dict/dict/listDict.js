/**
 * TODO: 数据字典的列表JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var ListDict = {
  
  prefix : 'sysDict_',
  
  groupCode : '',
  
  groupName : '',
  
  init : function() {
    // 加载扩展的JS
    // ListDict.loadExtJs();
    // 绑定事件
    ListDict.bindFunc();
  },
  
  bindFunc : function() {
    // 批量删除
    ListDict.batDel();
    // 添加之前
    ListDict.prepAdd();
    ListDict.checkedAll();
    //
    ListDict.loadForm();
  },
  
  loadForm : function() {
    WebBase.maxlength("sysDict_remarks_", 40);
  },
  
  /**
   * List data by groupCode
   * 
   * @param gcode
   * @param gname
   */
  listByGc : function(gcode, gname) {
    if (gcode != null && gcode != '') {
      ListDict.groupCode = gcode;
    }
    if (gname != null && gname != '') {
      ListDict.groupName = gname;
    }
    var _prefix = ListDict.prefix;
    var _dlgId = _prefix + FuncBinder._defaults.listDlg;
    var _url = ListDict.getThemesUri() + '/listDict.html';
    FuncBinder.loadPage(_prefix, _dlgId, _url, function() {
      // 替换名称
      $('#' + _prefix + 'groupCode_').val(ListDict.groupCode);
      $('#' + _prefix + 'groupName_').text(ListDict.groupName);
      // 初始化
      ListDict.init();
      // 列表查询
      var _url2 = ListDict.getActionUri() + '/list?sf.groupCode=' + ListDict.groupCode;
      WcsbBase.doPaging(_prefix, _url2, null);
      show('cover', _dlgId);
    });
    
  },
  
  checkedAll : function() {
    $('.checkedAll').click(function() {
      if (this.checked) {
        $(this).parent().parent().parent().find('input[type=checkbox][name=sysDict_items]').attr('checked', true);
      } else {
        $(this).parent().parent().parent().find('input[type=checkbox][name=sysDict_items]').attr('checked', false);
      }
    });
  },
  
  del : function(id) {
    var _prefix = ListDict.prefix;
    var _url = ListDict.getActionUri() + '/delete';
    WcsbBase.del(_prefix, id, _url, _prefix + 'list_dlg', null);
  },
  
  batDel : function() {
    var _prefix = ListDict.prefix;
    WcsbBase.batDel2(_prefix, ListDict.del, _prefix + 'list_dlg');
  },
  
  prepAdd : function() {
    var _prefix = ListDict.prefix;
    // 提交保存
    var _saveUrl = ListDict.getActionUri() + '/save';
    WcsbBase.prepAdd2(_prefix, _saveUrl, ListDict.checkEditForm, _prefix + 'list_dlg', function() {
      // reset form
      WebBase.resetForm(_prefix + 'edit_fm');
      $('#sysDict_groupCode_').val(ListDict.groupCode);
      // 重置单选框
      // $('#' + _prefix + 'isEncrypt_n').prop('checked', 'checked');
    });
    
  },
  
  edit : function(id) {
    var _prefix = ListDict.prefix;
    // 查看
    var _getUrl = ListDict.getActionUri() + '/get?id=' + id;
    // 提交保存
    var _updateUrl = ListDict.getActionUri() + '/update';
    WcsbBase.edit(_prefix, _getUrl, _updateUrl, ListDict.checkEditForm, _prefix + 'list_dlg', null);
    // 选中单选框
    WebBase.selectRadio(_prefix + 'isEncrypt_', 'isEncryptTmp');
    
  },
  
  checkEditForm : function() {
    var _prefix = ListDict.prefix;
    if (!DataValidator.notEmpty(_prefix + 'sn_', '序号不能为空！')) {
      return false;
    } else {
      if (!DataValidator.integer(_prefix + 'sn_', '序号必须为数字！')) {
        return false;
      }
    }
    if (!DataValidator.notEmpty(_prefix + 'dataKey_', '数据键不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty(_prefix + 'dataValue_', '数据值不能为空！')) {
      return false;
    }
    var _id = $('#sysDict_id_').val();
    var _groupCode = $('#sysDict_groupCode_').val();
    var _dataKey = $('#sysDict_dataKey_').val();
    var _url = ListDict.getActionUri() + '/isExistKey?id=' + _id + '&groupCode=' + _groupCode + '&dataKey=' + _dataKey;
    var _rs = FuncBinder.getPostJson(_url, null);
    if (_rs != null && _rs.success && _rs.data) {
      $('#sysDict_dataKey_msg_').text('该数据键已存在！');
      return false;
    }
    var _dataValue = $('#sysDict_dataValue_').val();
    var _url2 = ListDict.getActionUri() + '/isExistValue?id=' + _id + '&groupCode=' + _groupCode + '&dataValue=' + _dataValue;
    var _rs2 = FuncBinder.getPostJson(_url2, null);
    if (_rs2 != null && _rs2.success && _rs2.data) {
      $('#sysDict_dataValue_msg_').text('该数据值已存在！');
      return false;
    }
    return true;
  },
  
  /**
   * Get local themes URI
   * 
   * @returns /XX
   */
  getThemesUri : function() {
    return WcsbBase.getThemesUri() + 'dict/dict';
  },
  
  /**
   * Get local action URI
   * 
   * @returns /XX
   */
  getActionUri : function() {
    return WebBase.getCxtPath() + '/dict/dict';
  },
  
  loadExtJs : function() {
    // null
  },
  
  refreshDict : function() {
    var _url = ListDict.getActionUri() + '/refreshDict';
    WcsbBase.refreshBtn(_url);
  }

};
