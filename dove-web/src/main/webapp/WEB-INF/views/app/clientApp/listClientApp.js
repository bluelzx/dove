/**
 * TODO: 客户端APP列表JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var ListClientApp = {
  
  init : function() {
    // 加载扩展的JS
    // ListClientApp.loadExtJs();
    // 执行查询列表
    ListClientApp.list();
    // 绑定事件
    ListClientApp.bindFunc();
  },
  
  bindFunc : function() {
    // 批量删除
    ListClientApp.batDel();
    // 添加之前
    ListClientApp.prepAdd();
    // 开启接入
    ListClientApp.prepDev();
  },
  
  /**
   * List data
   */
  list : function() {
    var _url = ListClientApp.getActionUri() + "/list";
    WcsbBase.doPaging(null, _url, ListClientApp.filterStatus);
  },
  
  loadBoxs : function() {
    // 所属账户
    var _activeAcc = FuncBinder.getPostJson(WebBase.getCxtPath() + "/account/account/getActiveAcc", null);
    if (_activeAcc != null && _activeAcc.success) {
      WcsbBase.loadSelectbox("accountCode_sebox_", _activeAcc.data, $("#accountCode_").val());
    }
  },
  
  del : function(id) {
    var _url = ListClientApp.getActionUri() + "/delete";
    WcsbBase.del(null, id, _url, null, ListClientApp.filterStatus);
  },
  
  batDel : function() {
    WcsbBase.batDel(null, ListClientApp.del, null);
  },
  
  prepDev : function() {
    var _url = ListClientApp.getActionUri() + "/chgDevStatus?ids=";
    WcsbBase.bindSelBoxBtn(_url, "dev_btn", "dev_dlg", ListClientApp.filterStatus);
  },
  
  prepAdd : function() {
    // 提交保存
    var _saveUrl = ListClientApp.getActionUri() + '/save';
    WcsbBase.prepAddX(null, _saveUrl, ListClientApp.checkEditForm, null, function() {
      // reset form
      WebBase.resetForm("edit_fm");
      $("#id_").val("");
      // 
      ListClientApp.filterForm("add");
    }, ListClientApp.filterStatus);
  },
  
  edit : function(id) {
    WebBase.resetForm("edit_fm");
    // 查看
    var _getUrl = ListClientApp.getActionUri() + '/get?id=' + id;
    // 提交保存
    var _updateUrl = ListClientApp.getActionUri() + '/update';
    WcsbBase.edit2(null, _getUrl, _updateUrl, ListClientApp.checkEditForm, null, null, ListClientApp.filterStatus);
    // 
    ListClientApp.filterForm("edit");
    // 选中单选框
    WebBase.selectRadio('isReplyMsg_', 'isReplyMsgTmp');
  },
  
  filterForm : function(type) {
    var flag = true;
    if (type == 'add') {
      flag = false;
    }
    ListClientApp.loadBoxs();
    // 服务编码集
    WebBase.maxlength("websCodes_", 200);
    if (flag) {
      // 所属账户
      $("#accountCode_sebox_ dt span").unbind("click");
    }
    
  },
  
  checkEditForm : function() {
    $("#accountCode_").val(WcsbBase.getSelectDlVal("accountCode_sebox_", "accountCode"));
    if (!DataValidator.notEmpty('accountCode_', '所属账户不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('cliAppName_', '应用名称不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('receiveUrl_', '应用地址不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('oauthCbUrl_', '授权地址不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('websCodes_', '服务编码不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('cliIpAddrs_', '客户端IP不能为空！')) {
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
    return WcsbBase.getThemesUri() + "app/clientApp";
  },
  
  /**
   * Get local action URI
   * 
   * @returns /XX
   */
  getActionUri : function() {
    return WebBase.getCxtPath() + "/app/clientApp";
  },
  
  loadExtJs : function() {
  },
  
  filterStatus : function(rs) {
    if (rs != null) {
      $(rs).each(function(i, n) {
        var _txt = '停用';
        if ('S1' == n.status || 'S99' == n.status) { // S1停用 S99接入
          _txt = '启用';
          $('#edit_btn_' + n.id).css('display', '');
          $('#del_btn_' + n.id).css('display', '');
        } else {
          $('#edit_btn_' + n.id).css('display', 'none');
          $('#del_btn_' + n.id).css('display', 'none');
        }
        $('#status_' + n.id).text(_txt);
      });
    }
  },
  
  chgStatus : function(id, status) {
    var _txt = "确定要启用吗？";
    if ("S0" == status) { // S0启用
      _txt = "确定要停用吗？";
    }
    var _url = ListClientApp.getActionUri() + "/chgStatus?id=" + id + "&status=" + status;
    WcsbBase.enabledBtn(_url, _txt, ListClientApp.filterStatus);
  }

};
$(document).ready(function() {
  ListClientApp.init();
});
