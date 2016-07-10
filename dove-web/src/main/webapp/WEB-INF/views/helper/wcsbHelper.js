/**
 * TODO: 微信服务助手JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var WcsbHelper = {
  
  init : function() {
    // 绑定事件
    WcsbHelper.bindFunc();
  },
  
  bindFunc : function() {
    //
    WcsbHelper.loadBoxs();
  },
  
  loadBoxs : function() {
    // 所属应用
    var _acCliAppName = FuncBinder.getPostJson(WebBase.getCxtPath() + "/app/clientApp/getActiCliAppName", null);
    if (_acCliAppName && _acCliAppName.success) {
      WcsbBase.loadSelectbox("cliAppCode_sebox_", _acCliAppName.data, $("#cliAppCode_").val());
    }
  },
  
  getCliAppId : function() {
    var _id = WcsbBase.getSelectDlVal("cliAppCode_sebox_", "cliAppCode");
    if (WebBase.isEmpty(_id)) {
      $("#cliAppCode_msg_").text("请选择所属应用！");
      return;
    }
    $("#cliAppCode_msg_").text("");
    return _id;
  },
  
  getDevToken : function() {
    var _id = WcsbHelper.getCliAppId();
    if (WebBase.isNotEmpty(_id)) {
      //
      var _url = WebBase.getCxtPath() + "/helper/getDevToken?cliAppCode=" + _id;
      var _rs = FuncBinder.getJson(_url, null);
      //
      $("#devToken_").val("");
      if (_rs != null && _rs.success) {
        $("#devToken_").val(_rs.data);
      }
    }
  },
  
  getDevAesKeyt : function() {
    var _id = WcsbHelper.getCliAppId();
    if (WebBase.isNotEmpty(_id)) {
      //
      var _url = WebBase.getCxtPath() + "/helper/getDevAesKeyt?cliAppCode=" + _id;
      var _rs = FuncBinder.getJson(_url, null);
      //
      $("#devAesKeyt_").val("");
      if (_rs != null && _rs.success) {
        $("#devAesKeyt_").val(_rs.data);
      }
    }
  },
  
  getCliAppVo : function() {
    var _id = WcsbHelper.getCliAppId();
    if (WebBase.isNotEmpty(_id)) {
      //
      var _url = WebBase.getCxtPath() + "/helper/getCliAppVo?cliAppCode=" + _id;
      var _rs = FuncBinder.getJson(_url, null);
      //
      $("#cliAppCodeExt_").val("");
      $("#cliAppPwd_").val("");
      $("#devAcCode_").val("");
      if (_rs != null && _rs.success && _rs.data) {
        $("#cliAppCodeExt_").val($("#cliAppCode_").val());
        $("#cliAppPwd_").val(_rs.data.cliAppPwd);
        $("#devAcCode_").val(_rs.data.accountCode);
      }
    }
  }

};
$(document).ready(function() {
  WcsbHelper.init();
});
