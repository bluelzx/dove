/**
 * TODO: 数据服务JS
 * 
 * @author ZHENFENG ZHANG
 * @since [2015-01-27]
 */
(function() {
  'use strict';
  
  angular.module('xmgl.service').factory('DataService', DataService);
  
  DataService.$inject = [ '$http', 'logger' ];
  
  function DataService($http, logger) {
    
    return {
      doGet : doGet
    };
    
    function doGet($url) {
      return $http.get($url).then(success);
      
      function success(rsp) {
        return rsp.data.success;
      }
      
      function error(rsp) {
        logger.error('XHR Failed for getAvengers.' + rsp.data.success);
      }
      
    }
  }
})();
