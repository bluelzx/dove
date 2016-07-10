/**
 * TODO: 账户列表JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var ListAccount = {
  
  init : function() {
    // 加载扩展的JS
    // ListAccount.loadExtJs();
    // 执行查询列表
    ListAccount.list();
    // 绑定事件
    ListAccount.bindFunc();
  },
  
  bindFunc : function() {
    // 批量删除
    ListAccount.batDel();
    // 添加之前
    ListAccount.prepAdd();
  },
  
  /**
   * List data
   */
  list : function() {
    var _url = ListAccount.getActionUri() + "/list";
    WcsbBase.doPaging(null, _url, ListAccount.filterStatus);
  },
  
  loadBoxs : function() {
    // 账户类型
    var _accType = FuncBinder.getPostJson(ListAccount.getActionUri() + "/getAccType", null);
    if (_accType != null && _accType.success) {
      WcsbBase.loadSelectbox("accountType_sebox_", _accType.data, $("#accountType_").val());
    }
  },
  
  del : function(id) {
    var _url = ListAccount.getActionUri() + "/delete";
    WcsbBase.del(null, id, _url, null, ListAccount.filterStatus);
  },
  
  batDel : function() {
    WcsbBase.batDel(null, ListAccount.del, null);
  },
  
  prepAdd : function() {
    // 提交保存
    var _saveUrl = ListAccount.getActionUri() + '/save';
    WcsbBase.prepAddX(null, _saveUrl, ListAccount.checkEditForm, null, function() {
      // reset form
      WebBase.resetForm("edit_fm");
      $("#id_").val("");
      // 
      ListAccount.filterForm("add");
    }, ListAccount.filterStatus);
  },
  
  edit : function(id) {
    WebBase.resetForm("edit_fm");
    // 查看
    var _getUrl = ListAccount.getActionUri() + '/get?id=' + id;
    // 提交保存
    var _updateUrl = ListAccount.getActionUri() + '/update';
    WcsbBase.edit2(null, _getUrl, _updateUrl, ListAccount.checkEditForm, null, function() {
      // 
      ListAccount.filterForm("edit");
    }, ListAccount.filterStatus);
    
  },
  
  filterForm : function(type) {
    var flag = true;
    if (type == 'add') {
      flag = false;
    }
    ListAccount.loadBoxs();
    // 备注
    WebBase.maxlength("remarks_", 200);
    // 唯一凭证
    $("#wecAppId_").prop("disabled", flag);
    // 原始ID
    $("#srcId_div_").css("display", flag);
    if (flag) {
      // 账户类型
      $("#accountType_sebox_ dt span").unbind("click");
      // 原始ID
      $("#srcId_div_").css("display", "none");
    } else {
      // 原始ID
      $("#srcId_div_").css("display", "");
    }
    
  },
  
  checkEditForm : function() {
    var _id = $('#id_').val();
    if (!DataValidator.notEmpty('wecAppId_', '唯一凭证不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('wecAppSecret_', '凭证密钥不能为空！')) {
      return false;
    } else {
      var _wecAppSecret = WebBase.encodeBase64("wecAppSecret_");
      var _url = ListAccount.getActionUri() + '/isExistAppSecret?id=' + _id + "&wecAppSecret=" + _wecAppSecret;
      var _rs = FuncBinder.getPostJson(_url, null);
      if (_rs != null && _rs.success && _rs.data) {
        $('#wecAppSecret_msg_').text('该凭证密钥已存在！');
        return false;
      }
    }
    // 原始ID
    if ($("#srcId_div_").css("display") != "none") {
      if (!DataValidator.notEmpty('srcId_', '原始ID不能为空！')) {
        return false;
      }
    }
    if (!DataValidator.notEmpty('accountName_', '账户名称不能为空！')) {
      return false;
    }
    $("#accountType_").val(WcsbBase.getSelectDlVal("accountType_sebox_", "accountType"));
    if (!DataValidator.notEmpty('accountType_', '账户类型不能为空！')) {
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
    return WcsbBase.getThemesUri() + "account/account";
  },
  
  /**
   * Get local action URI
   * 
   * @returns /XX
   */
  getActionUri : function() {
    return WebBase.getCxtPath() + "/account/account";
  },
  
  loadExtJs : function() {
  },
  
  filterStatus : function(rs) {
    if (rs != null) {
      $(rs).each(function(i, n) {
        var _txt = '停用';
        if ('S1' == n.status) { // S1停用
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
    var _url = ListAccount.getActionUri() + "/chgStatus?id=" + id + "&status=" + status;
    WcsbBase.enabledBtn(_url, _txt, ListAccount.filterStatus);
  }

};
$(document).ready(function() {
  ListAccount.init();
});
