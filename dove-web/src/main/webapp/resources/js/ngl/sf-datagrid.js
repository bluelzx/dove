/**
 * Search form datagrid-ngl
 * 
 * <p>
 * example： SfDatagrid.paging(...);
 * </p>
 * 
 * @version 0.0.1
 * @since 2015-06-20
 */
(function() {
  'use strict';
  
  angular.module('comm.service').factory('SfDatagrid', SfDatagrid);
  
  function SfDatagrid() {
    /**
     * Default parameter
     */
    ns._defaults = {};
    
    /**
     * messages
     */
    ns.messages = {
      remoteErr : '远程请求异常！'
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
      var url = window.location.href.toString();
      return '/' + url.split('/')[3];
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
    
    ns.paging = function() {
      // draw element
      ns.drawSearchForm();
      ns.drawDatagrid();
      ns.drawPagingBar();
    };
    
    ns.drawSearchForm = function() {
      
    };
    
    ns.drawDatagrid = function() {
      
    };
    
    ns.drawPagingBar = function($commPaging) {
      
    };
  }
})();
