/**
 * TODO: 数据字典组别的列表JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var ListDictGroup = {
  
  init : function() {
    // 加载扩展的JS
    ListDictGroup.loadExtJs();
    // 执行查询列表
    ListDictGroup.list();
    // 绑定事件
    ListDictGroup.bindFunc();
  },
  
  bindFunc : function() {
    // 批量删除
    ListDictGroup.batDel();
    // 添加之前
    ListDictGroup.prepAdd();
    // 刷新字典信息
    ListDict.refreshDict();
    //
    ListDictGroup.loadForm();
  },
  
  loadForm : function() {
    WebBase.maxlength("remarks_", 40);
  },
  
  /**
   * List data
   */
  list : function() {
    var _url = ListDictGroup.getActionUri() + "/list";
    WcsbBase.doPaging(null, _url, null);
  },
  
  del : function(id) {
    var _url = ListDictGroup.getActionUri() + "/delete";
    WcsbBase.del(null, id, _url, null, null);
  },
  
  batDel : function() {
    WcsbBase.batDel(null, ListDictGroup.del, null);
  },
  
  prepAdd : function() {
    // 提交保存
    var _saveUrl = ListDictGroup.getActionUri() + '/save';
    WcsbBase.prepAdd(null, _saveUrl, ListDictGroup.checkEditForm, null, function() {
      // reset form
      WebBase.resetForm("edit_fm");
      $("#id_").val("");
      $("#groupCode_").prop("readonly", "");
      
    });
  },
  
  edit : function(id) {
    WebBase.resetForm("edit_fm");
    $("#groupCode_").prop("readonly", "readonly");
    // 查看
    var _getUrl = ListDictGroup.getActionUri() + '/get?id=' + id;
    // 提交保存
    var _updateUrl = ListDictGroup.getActionUri() + '/update';
    WcsbBase.edit(null, _getUrl, _updateUrl, ListDictGroup.checkEditForm, null, null);
  },
  
  checkEditForm : function() {
    var _id = $('#id_').val();
    if (!DataValidator.isValidCode('groupCode_', '组别编码只能含数字、字母、下划线！')) {
      return false;
    } else {
      var _groupCode = WebBase.encodeBase64("groupCode_");
      var _url = ListDictGroup.getActionUri() + '/isExistGc?id=' + _id + "&groupCode=" + _groupCode;
      var _rs = FuncBinder.getPostJson(_url, null);
      if (_rs != null && _rs.success && _rs.data) {
        $('#groupCode_msg_').text('该组别编码已存在！');
        return false;
      }
    }
    if (!DataValidator.notEmpty('groupName_', '组别名称不能为空！')) {
      return false;
    } else {
      var _groupName = WebBase.encodeBase64("groupName_");
      var _url2 = ListDictGroup.getActionUri() + '/isExistGname?id=' + _id + "&groupName=" + _groupName;
      var _rs2 = FuncBinder.getPostJson(_url2, null);
      if (_rs2 != null && _rs2.success && _rs2.data) {
        $('#groupName_msg_').text('该组别名称已存在！');
        return false;
      }
    }
    
    return true;
  },
  
  /**
   * Get local themes URI
   * 
   * @returns /XX
   */
  getThemesUri : function() {
    return WcsbBase.getThemesUri() + "dict/dictGroup";
  },
  
  /**
   * Get local action URI
   * 
   * @returns /XX
   */
  getActionUri : function() {
    return WebBase.getCxtPath() + "/dict/dictGroup";
  },
  
  loadExtJs : function() {
    WebBase.appendJsE(true, "/views/dict/dict/listDict.js");
  }

};
$(document).ready(function() {
  ListDictGroup.init();
});
