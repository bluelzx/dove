/**
 * TODO: 回复语列表JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var ListReplyWord = {
  
  init : function() {
    // 加载扩展的JS
    // ListReplyWord.loadExtJs();
    // 执行查询列表
    ListReplyWord.list();
    // 绑定事件
    ListReplyWord.bindFunc();
  },
  
  bindFunc : function() {
    // 批量删除
    ListReplyWord.batDel();
    // 添加之前
    ListReplyWord.prepAdd();
    // 刷新字典信息
    ListSysDict.refreshDict();
  },
  
  /**
   * List data
   */
  list : function() {
    var _url = ListReplyWord.getActionUri() + "/list";
    WcsbBase.doPaging(null, _url, null);
  },
  
  del : function(id) {
    var _url = ListReplyWord.getActionUri() + "/delete";
    WcsbBase.del(null, id, _url, null);
  },
  
  batDel : function() {
    WcsbBase.batDel(null, ListReplyWord.del, null);
  },
  
  prepAdd : function() {
    // 提交保存
    var _saveUrl = ListReplyWord.getActionUri() + '/save';
    WcsbBase.prepAdd(null, _saveUrl, ListReplyWord.checkEditForm, null, function() {
      // reset form
      WebBase.resetForm("edit_fm");
      $("#id_").val("");
    });
  },
  
  edit : function(id) {
    WebBase.resetForm("edit_fm");
    // 查看
    var _getUrl = ListReplyWord.getActionUri() + '/get?id=' + id;
    // 提交保存
    var _updateUrl = ListReplyWord.getActionUri() + '/update';
    WcsbBase.edit(null, _getUrl, _updateUrl, ListReplyWord.checkEditForm, null);
  },
  
  checkEditForm : function() {
    var _id = $('#id_').val();
    if (!DataValidator.notEmpty('groupCode_', '组别编码不能为空！')) {
      return false;
    } else {
      var _groupCode = WebBase.encodeBase64("groupCode_");
      var _url = ListReplyWord.getActionUri() + '/isExistGc?id=' + _id + "&groupCode=" + _groupCode;
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
      var _url2 = ListReplyWord.getActionUri() + '/isExistGname?id=' + _id + "&groupName=" + _groupName;
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
    return WcsbBase.getThemesUri() + "reply/replyWord";
  },
  
  /**
   * Get local action URI
   * 
   * @returns /XX
   */
  getActionUri : function() {
    return WebBase.getCxtPath() + "/reply/replyWord";
  },
  
  loadExtJs : function() {
  }

};
$(document).ready(function() {
  ListReplyWord.init();
});
