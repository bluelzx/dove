/**
 * TODO: 应用菜单列表JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
var ListAppMenu = {
  
  init : function() {
    // 加载扩展的JS
    // ListAppMenu.loadExtJs();
    // 执行查询列表
    ListAppMenu.list();
    // 绑定事件
    ListAppMenu.bindFunc();
  },
  
  bindFunc : function() {
    // 批量删除
    ListAppMenu.batDel();
    // 添加之前
    ListAppMenu.prepAdd();
    // 发布菜单
    ListAppMenu.prepDeploy();
  },
  
  /**
   * List data
   */
  list : function() {
    var _url = ListAppMenu.getActionUri() + "/list";
    WcsbBase.doPaging(null, _url, null);
  },
  
  loadBoxs : function() {
    // 所属应用
    var _acCliAppName = FuncBinder.getPostJson(WebBase.getCxtPath() + "/app/clientApp/getActiCliAppName", null);
    if (_acCliAppName && _acCliAppName.success) {
      WcsbBase.loadSelectbox("cliAppCode_sebox_", _acCliAppName.data, $("#cliAppCode_").val());
    }
  },
  
  del : function(id) {
    var _url = ListAppMenu.getActionUri() + "/delete";
    WcsbBase.del(null, id, _url, null);
  },
  
  batDel : function() {
    WcsbBase.batDel(null, ListAppMenu.del, null);
  },
  
  // TODO
  config : function(id) {
  },
  
  prepDeploy : function() {
    var _url = ListAppMenu.getActionUri() + "/deployAppMenu?id=";
    WcsbBase.bindSelBoxBtn(_url, "deploy_btn", "deploy_dlg", null);
  },
  
  prepAdd : function() {
    // 提交保存
    var _saveUrl = ListAppMenu.getActionUri() + '/save';
    WcsbBase.prepAdd(null, _saveUrl, ListAppMenu.checkEditForm, null, function() {
      // reset form
      WebBase.resetForm("edit_fm");
      $("#id_").val("");
      // 
      ListAppMenu.filterForm("add");
    });
  },
  
  edit : function(id) {
    WebBase.resetForm("edit_fm");
    // 查看
    var _getUrl = ListAppMenu.getActionUri() + '/get?id=' + id;
    // 提交保存
    var _updateUrl = ListAppMenu.getActionUri() + '/update';
    WcsbBase.edit(null, _getUrl, _updateUrl, ListAppMenu.checkEditForm, null, null);
    // 
    ListAppMenu.filterForm("edit");
  },
  
  filterForm : function(type) {
    var flag = true;
    if (type == 'add') {
      flag = false;
    }
    ListAppMenu.loadBoxs();
    // JSON内容
    WebBase.maxlength("content_", 3000);
    if (flag) {
      // 所属应用
      $("#cliAppCode_sebox_ dt span").unbind("click");
    }
    
  },
  
  checkEditForm : function() {
    $("#cliAppCode_").val(WcsbBase.getSelectDlVal("cliAppCode_sebox_", "cliAppCode"));
    if (!DataValidator.notEmpty('cliAppCode_', '所属应用不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('appMenuName_', '菜单名称不能为空！')) {
      return false;
    }
    if (!DataValidator.notEmpty('content_', '菜单内容不能为空！')) {
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
    return WcsbBase.getThemesUri() + "menu/appMenu";
  },
  
  /**
   * Get local action URI
   * 
   * @returns /XX
   */
  getActionUri : function() {
    return WebBase.getCxtPath() + "/menu/appMenu";
  },
  
  loadExtJs : function() {
  }

};
$(document).ready(function() {
  ListAppMenu.init();
});
