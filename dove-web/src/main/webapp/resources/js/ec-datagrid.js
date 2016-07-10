/**
 * EC datagrid
 * 
 * <pre>
 * example： EcDatagrid.paging(...);
 * </pre>
 * 
 * @since 2015-01-26
 */
var EcDatagrid = {};
(function($, ns) {
  
  ns.version = '0.0.1';
  
  /**
   * Default parameter
   */
  ns._defaults = {
    dFirstPage : 'd_first_page_',
    dPrevPage : 'd_prev_page_',
    dNextPage : 'd_next_page_',
    dEndPage : 'd_end_page_',
    dLimitRecord : 'd_limit_record_',
    dTotalPageLab : 'd_total_page_lab',
    dTotalRecordLab : 'd_total_record_lab',
    dPageBar : 'd_page_bar_',
    uFirstPage : 'u_first_page_',
    uPrevPage : 'u_prev_page_',
    uNextPage : 'u_next_page_',
    uEndPage : 'u_end_page_',
    uLimitRecord : 'u_limit_record_',
    uTotalPageLab : 'u_total_page_lab',
    uTotalRecordLab : 'u_total_record_lab',
    uPageBar : 'u_page_bar_'
  };
  
  /**
   * User parameter
   */
  ns.userParameter = {
    indexId : 'index',
    dataTableId : 'dataTable',
    pageDivId : 'pageDiv',
    searchBtn : 'search_btn',
    resetBtn : 'reset_btn',
    searchRowId : 'searchRow',
    pageSizeName : 'pageSize',
    currPageName : 'currPage',
    dataHeadId : 'dataHead',
    exportFormId : 'exportForm_',
    dataTrName : 'dataTr',
    tempBodyId : 'tempBody',
    noneDataId : 'noneData_',
    nameExtAttr : '_name',
    typeExtAttr : '_type',
    optExtAttr : '_opt',
    limitExtAttr : '_limit',
    tipExtAttr : '_tip',
    pageNum : 1, // 页数
    recordNum : 0, // 记录数
    opeType : 'ope',
    selectType : 'selectbox',
    numType : 'number',
    noneType : 'none',
    dateType : 'date',
    dateTimeType : 'datetime',
    multDateType : 'mult-date',
    multDateTimeType : 'mult-datetime',
    multMonthType : 'mult-month'
  };
  
  /**
   * messages
   */
  ns.messages = {
    remoteErr : '远程请求异常！'
  };
  
  ns._prefix = '';
  
  ns._getId = function(key) {
    if (ns._prefix == null) {
      ns._prefix = '';
    }
    return ns._prefix + key;
  };
  
  ns._getTarget = function(key) {
    return $('#' + ns._getId(key));
  };
  
  ns._bind = function(key, callback) {
    var _tg = ns._getTarget(key);
    _tg.removeAttr('onclick');
    _tg.unbind('click').click(function() {
      callback();
    });
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
  
  ns.doErr = function(msg) {
    alert('Init [' + msg + '] is fail!');
    return;
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
    return '/' + window.location.pathname.split('/')[1];
  };
  
  ns.isNumber = function(id) {
    return (/^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/.test($('#' + id).val()));
  };
  
  ns.filterErrChar = function(val) {
    if (val == null || val == '') {
      return val;
    }
    var newVal = '';
    var _patt = new RegExp("[`~!#^&*()=|{}':%;,\\[\\]<>?～！……—｛｝【】‘；：”“｀。，、？％]");
    for (var i = 0, len = val.length; i < len; i++) {
      newVal += val.substr(i, 1).replace(_patt, '');
    }
    return newVal;
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
  
  ns.getPostJson = function(url, data) {
    var _rs = null;
    $.ajax({
      url : url,
      data : data,
      type : 'post',
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
  
  /**
   * Get paging for start record
   * 
   * @param currPage
   * @param limit
   */
  ns.getStartPaging = function(currPage, limit) {
    currPage = parseInt(currPage);
    limit = parseInt(limit);
    if (currPage < 1) {
      currPage = 1;
    }
    if (limit < 1) {
      limit = 1;
    }
    return parseInt((currPage - 1) * limit + 1);
  };
  
  /**
   * Get total page
   * 
   * @param count
   * @param limit
   */
  ns.getTotalPage = function(count, limit) {
    count = parseInt(count);
    limit = parseInt(limit);
    if (count < 1) {
      count = 1;
    }
    if (limit < 1) {
      limit = 1;
    }
    if ((count % limit == 0)) {
      return parseInt((count / limit));
    } else {
      return parseInt((count / limit) + 1);
    }
  };
  
  /**
   * Paging
   * 
   * @param prefix
   * @param url
   * @param currPage
   * @param pageSize
   * @param callback
   */
  ns.paging = function(prefix, url, currPage, pageSize, callback) {
    $(document.body).css("display", "none");
    try {
      if (prefix == null) {
        prefix = "";
      }
      // draw search
      ns.drawSearchForm(prefix);
      // 查询回车键的事件
      $(document).keydown(function(e) {
        var currKey = e.which;
        if (currKey == 13) {
          // 弹出遮罩层时取消查询事件
          if ($('#cover').css('visibility') == 'hidden') {
            $('#' + prefix + '' + ns.userParameter.searchBtn + '').click();
          }
          return false;
        }
      });
      // --------- 解决关闭对话框后查询、重置按钮失效 ---------
      $('#' + prefix + '' + ns.userParameter.searchBtn + '').unbind('click').click(function() {
        ns.initDatagrid(url, null, 1, prefix, callback);
      });
      $('#' + prefix + '' + ns.userParameter.resetBtn + '').unbind('click').click(function() {
        $('#' + prefix + '' + ns.userParameter.searchRowId + '').find('input,select').filter(function() {
          return !jQuery(this).is(":disabled");
        }).each(function(i, elem) {
          jQuery(this).val("");
        });
        $(this).parent().parent().find("dd").each(function() {
          var _ddFirst = $(this).children()[0];
          if (_ddFirst != null) {
            _ddFirst.click();
          }
        });
        ns.initDatagrid(url, $('#' + prefix + '' + ns.userParameter.pageSizeName + '').val(), 1, prefix, callback);
      });
      // --------- 解决关闭对话框后查询、重置按钮失效 ---------
      var _urlTg = $("#" + prefix + "_url");
      if (_urlTg != null && _urlTg.val() != null) {
        _urlTg.val(url);
        ns.initDatagrid(url, pageSize, currPage, prefix, callback);
      } else {
        // hidden current page
        var s = '';
        s += '<input type="hidden" name ="' + ns.userParameter.currPageName + '" id="' + prefix + ''
            + ns.userParameter.currPageName + '">';
        s += '<input type="hidden" name ="' + ns.userParameter.pageSizeName + '" id="' + prefix + ''
            + ns.userParameter.pageSizeName + '">';
        $('#' + prefix + ns.userParameter.dataTableId).parent().append(s);
        $('#' + prefix + ns.userParameter.dataTableId).parent().append(
            "<input type='hidden' name='" + prefix + "_url' id='" + prefix + "_url'/>");
        $('#' + prefix + "_url").val(url);
        ns.initDatagrid(url, pageSize, currPage, prefix, callback);
      }
    } catch (e) {
      alert("Paging " + e);
    } finally {
      $(document.body).css("display", "");
    }
  };
  
  /**
   * Refresh paging
   * 
   * @param prefix
   * @param callback
   */
  ns.refreshPage = function(prefix, callback) {
    if (prefix == null) {
      prefix = "";
    }
    ns.initDatagrid($("#" + prefix + "_url").val(), null, $('#' + prefix + '' + ns.userParameter.currPageName + '').val(),
        prefix, callback);
  };
  
  /**
   * Init datagrid
   * 
   * @param url
   * @param pageSize
   * @param currPage
   * @param prefix
   * @param callback
   */
  ns.initDatagrid = function(url, pageSize, currPage, prefix, callback) {
    var _searchRow = $('#' + prefix + '' + ns.userParameter.searchRowId + '');
    if (currPage != null) {
      $('#' + prefix + '' + ns.userParameter.currPageName + '').val(currPage);
    }
    if (pageSize != null) {
      $('#' + prefix + '' + ns.userParameter.pageSizeName + '').val(pageSize);
    }
    currPage = $('#' + prefix + '' + ns.userParameter.currPageName + '').val();
    pageSize = $('#' + prefix + '' + ns.userParameter.pageSizeName + '').val();
    var _data = {
      "start" : ns.getStartPaging(currPage, pageSize),
      "limit" : pageSize,
      "countall" : true
    };
    _searchRow.find('input,select').filter(function() {
      return !jQuery(this).is(":disabled");
    }).each(function(i, elem) {
      var _val = jQuery(this).val();
      if (_val == null || _val == '') {
        return;
      }
      _data['sf.' + elem.name] = _val.replace(/(^\s+)|(\s+$)/g, "");
    });
    jQuery.ajax({
      url : url,
      data : _data,
      dataType : "json",
      type : "post",
      async : false,
      cache : false,
      success : function(data) {
        // 初始化分页信息
        var _totalPage = 1;
        var _count = parseInt(data["total"]);
        if (_count != null && _count != 0) {
          _totalPage = ns.getTotalPage(_count, $('#' + prefix + '' + ns.userParameter.pageSizeName + '').val());
          ns.userParameter.recordNum = _count;
          ns.userParameter.pageNum = _totalPage;
        } else {
          _totalPage = 0;
          ns.userParameter.recordNum = 0;
          ns.userParameter.pageNum = 0;
        }
        // alert(_count + "--------------");
        // 如果当前页大于总页数，则返回上一页
        currPage = parseInt(currPage);
        _totalPage = parseInt(_totalPage);
        if (currPage > _totalPage && _totalPage > 0) {
          ns.initDatagrid($("#" + prefix + "_url").val(), null, _totalPage, prefix, callback);
        } else {
          // 初始化页面数据
          ns.drawDatagrid(data, prefix, callback);
          ns.drawPagingBar(ns.userParameter.pageNum, ns.userParameter.recordNum, currPage, prefix, callback);
        }
      }
    });
  };
  
  /**
   * Draw search form
   * 
   * @param prefix
   */
  ns.drawSearchForm = function(prefix) {
    var _head = $('#' + prefix + '' + ns.userParameter.dataHeadId + '');
    if (_head == null) {
      return;
    }
    var _fields = _head.children();
    var _tmpSrow = $('#' + prefix + '' + ns.userParameter.searchRowId + '');
    if (_tmpSrow != null && _tmpSrow.html() != null) {
      _tmpSrow.remove();
    } else {
      return;
    }
    if (_fields != null) {
      var s = '';
      s += '<tr id="' + prefix + '' + ns.userParameter.searchRowId + '">';
      var _chbox = _head.find("th:eq(0)").find("input[type=checkbox]");
      if (_chbox != null && _chbox.attr("name") != null) {
        s += '<td colspan="2">&nbsp;</td>';
      } else {
        s += '<td>&nbsp;</td>';
      }
      _fields.each(function(idx, td) {
        var _name = $(td).attr('' + ns.userParameter.nameExtAttr + '');
        if (_name == ns.userParameter.opeType) {
          s += '<td>';
          s += '<a href="javascript:;" class="bt_blue mr20" id="' + prefix + '' + ns.userParameter.searchBtn
              + '"><span>查询</span></a>';
          s += '<a href="javascript:;" class="bt_blue" id="' + prefix + '' + ns.userParameter.resetBtn + '"><span>重置</span></a>';
          s += '</td>';
        } else if (_name != null && _name != ns.userParameter.indexId) {
          // tip message
          var _tip = $(td).attr('' + ns.userParameter.tipExtAttr + '');
          if (_tip == null || _tip == '') {
            _tip = '请输入查询条件';
          }
          var _type = $(td).attr('' + ns.userParameter.typeExtAttr + '');
          if (_type == ns.userParameter.selectType) {
            var _opt = $(td).attr('' + ns.userParameter.optExtAttr + '');
            if (!_opt) {
              alert("请指定'" + _name + "'列的options！");
            }
            s += '<td class="pl5 pr5">';
            s += '<dl class="selectbox selectbox_3" style="z-index: 1;margin:0 auto" id="search_' + prefix + _name + '">';
            s += '<dt><span class="fleft" id="search_' + _name + '" name="' + _name
                + '">&nbsp;&nbsp;全部&nbsp;&nbsp;</span><span class="fright">&nbsp;</span></dt>';
            s += '<dd><p class="sel" name="">&nbsp;&nbsp;全部&nbsp;&nbsp;</p>';
            if (_opt.indexOf('{') == -1) {
              var _optArray = _opt.split(';');
              var _vkey = null;
              for (var i = 0; i < _optArray.length; i++) {
                _vkey = _optArray[i].split(':');
                s += '<p name="' + _vkey[0] + '">' + _vkey[1] + '</p>';
              }
            } else {
              var _json = WebBase.getDatas();
              if (_json != null) {
                _opt = _json[_opt.substring(1, _opt.length - 1)];
                for ( var i in _opt) {
                  s += '<p name="' + i + '">' + _opt[i] + '</p>';
                }
              }
            }
            s += '</dd></dt></dl></td>';
          } else if (_type == ns.userParameter.noneType) {
            s += '<td class="pl5 pr5">&nbsp;</td> ';
          } else if (_type == ns.userParameter.dateType) {
            s += '<td class="pl5 pr5"><input id="search_' + _name + '" name="' + _name + '" class="Wdate" ';
            s += ' readonly="readonly" onclick="EcDatagrid.fmtWdateDate()" style="width:80px;" /> ';
            s += '</td>';
          } else if (_type == ns.userParameter.dateTimeType) {
            s += '<td class="pl5 pr5">';
            s += '<input id="search_' + _name + '" name="' + _name + '" class="Wdate" ';
            s += ' style="width: 130px;" readonly="readonly" onclick="EcDatagrid.fmtWdateDtime()" /> ';
            s += '</td>';
          } else if (_type == ns.userParameter.multDateType) { // TODO
            s += '<td class="pl5 pr5">';
            s += '<input id="search_' + _name + 'Begin" name="' + _name + 'Begin" class="Wdate" ';
            s += ' onclick="EcDatagrid.fmtMinWdateDate(\'search_' + _name + 'End\')" ';
            s += ' readonly="readonly" style="width:100px;" /> ';
            s += '<span> 至 </span>';
            s += '<input id="search_' + _name + 'End" name="' + _name + 'End" class="Wdate" ';
            s += ' onclick="EcDatagrid.fmtMaxWdateDate(\'search_' + _name + 'Begin\')" ';
            s += ' readonly="readonly" style="width:100px;" /> ';
            s += '</td>';
          } else if (_type == ns.userParameter.multMonthType) { // TODO
            s += '<td class="pl5 pr5">';
            s += '<input id="search_' + _name + 'Begin" name="' + _name + 'Begin" class="Wdate" ';
            s += ' onclick="EcDatagrid.fmtMinWdateMonth(\'search_' + _name + 'End\')" ';
            s += ' readonly="readonly" style="width:80px;" /> ';
            s += '<span> 至 </span>';
            s += '<input id="search_' + _name + 'End" name="' + _name + 'End" class="Wdate" ';
            s += ' onclick="EcDatagrid.fmtMaxWdateMonth(\'search_' + _name + 'Begin\')" ';
            s += ' readonly="readonly" style="width:80px;" /> ';
            s += '</td>';
          } else if (_type == ns.userParameter.multDateTimeType) { // TODO
            s += '<td class="pl5 pr5">';
            s += '<input id="search_' + _name + 'Begin" name="' + _name + 'Begin" class="Wdate" ';
            s += ' onclick="EcDatagrid.fmtMinWdateDtime(\'search_' + _name + 'End\')" ';
            s += ' style="width: 130px;" readonly="readonly" /> ';
            s += '<span> 至 </span>';
            s += '<input id="search_' + _name + 'End" name="' + _name + 'End" class="Wdate" ';
            s += ' onclick="EcDatagrid.fmtMaxWdateDtime(\'search_' + _name + 'Begin\')" ';
            s += ' readonly="readonly" style="width: 130px;" /> ';
            s += '</td>';
          } else if (_type == ns.userParameter.numType) {
            s += '<td class="pl5 pr5"><input placeholder="' + _tip + '" id="search_' + _name + '" name="' + _name + '" ';
            s += ' onblur="$(this).val(EcDatagrid.isNumber(this.id)?this.value:\'\');" ';
            s += ' style="width:100px;" maxlength="8" /> ';
            s += '</td>';
          } else {
            s += '<td class="pl5 pr5"><input placeholder="' + _tip + '" id="search_' + _name + '" name="' + _name;
            s += '" onkeyup="$(this).val(EcDatagrid.filterErrChar(this.value));" style="width:130px;" maxlength="20" /> ';
            s += '</td>';
          }
        }
      });
      s += '</tr>';
      $(s).insertBefore('#' + prefix + '' + ns.userParameter.dataHeadId + '');
    }
  };
  
  ns.fmtWdateDtime = function() {
    WdatePicker({
      dateFmt : 'yyyy-MM-dd HH:mm:ss'
    });
  };
  
  ns.fmtWdateDate = function() {
    WdatePicker({
      dateFmt : 'yyyy-MM-dd'
    });
  };
  
  ns.fmtWdateMonth = function() {
    WdatePicker({
      dateFmt : 'yyyy-MM-01'
    });
  };
  
  ns.fmtMinWdateDate = function(maxId) {
    EcDatagrid.fmtMinWdate('yyyy-MM-dd', maxId);
  };
  
  ns.fmtMaxWdateDate = function(minId) {
    EcDatagrid.fmtMaxWdate('yyyy-MM-dd', minId);
  };
  
  ns.fmtMinWdateDtime = function(maxId) {
    EcDatagrid.fmtMinWdate('yyyy-MM-dd HH:mm:ss', maxId);
  };
  
  ns.fmtMaxWdateDtime = function(minId) {
    EcDatagrid.fmtMaxWdate('yyyy-MM-dd HH:mm:ss', minId);
  };
  
  ns.fmtMinWdateMonth = function(maxId) {
    EcDatagrid.fmtMinWdate('yyyy-MM-01', maxId);
  };
  
  ns.fmtMaxWdateMonth = function(minId) {
    EcDatagrid.fmtMaxWdate('yyyy-MM-01', minId);
  };
  
  ns.fmtMaxWdate = function(patt, minId) {
    // max
    WdatePicker({
      dateFmt : patt,
      minDate : '#F{$dp.$D(\'' + minId + '\')}'
    });
  };
  
  ns.fmtMinWdate = function(patt, maxId) {
    // min
    WdatePicker({
      dateFmt : patt,
      onpicked : function() {
        $('#' + maxId).focus();
      },
      maxDate : '#F{$dp.$D(\'' + maxId + '\')}'
    });
  };
  
  /**
   * Draw datagrid
   * 
   * @param data
   * @param prefix
   * @param callback
   */
  ns.drawDatagrid = function(data, prefix, callback) {
    var _success = eval(data["success"]);
    var jsonList = data["list"];
    var _table = $('#' + prefix + ns.userParameter.dataTableId);
    // remove dataTr
    $(_table.find("[name='" + prefix + "" + ns.userParameter.dataTrName + "']")).remove();
    var _noneTr = $('#' + prefix + '' + ns.userParameter.noneDataId + '');
    if (_noneTr != null) {
      _noneTr.remove();
    }
    var _head = $('#' + prefix + '' + ns.userParameter.dataHeadId + '');
    if (_success && jsonList != null && jsonList.length > 0) {
      var _fields = _head.children(); // td
      _table.css("display", "none"); // hidden table
      $('#' + prefix + '' + ns.userParameter.tempBodyId + '').css("display", "");
      // 数据重新加载的时候，把html模版中的checked设置为false
      $('#' + prefix + '' + ns.userParameter.tempBodyId + '').find("input[type=checkbox]").each(function() {
        $(this).attr("checked", false);
      });
      var htmlTemp = $('#' + prefix + '' + ns.userParameter.tempBodyId + '');
      var css = htmlTemp.attr("class");
      var s = "";
      $('#' + prefix + '' + ns.userParameter.tempBodyId + '').css("display", "none");
      
      // load data
      var _limitMap = new FuncBinder.HashMap();
      for (var i = 0; i < jsonList.length; i++) {
        s += '<tr ';
        if (css) {
          s += 'class="' + css + '"';
        }
        s += ' id="' + prefix + '' + ns.userParameter.dataTrName + '' + (i + 1) + '" name = "' + prefix + ''
            + ns.userParameter.dataTrName + '">';
        // HTML template
        var _html = htmlTemp.html();
        if (_html == null) {
          alert("Init data table error!");
          return;
        }
        // index
        _html = _html.replace(new RegExp("{index}", "gm"), (i + 1));
        var _tmp = null;
        for (j in jsonList[i]) {
          _tmp = jsonList[i][j];
          if (_tmp == null || _tmp == 'null') {
            _tmp = '&nbsp;';
          }
          // ingored sub text
          if (i == 0) {
            $(_fields).each(function(jx, jn) {
              if (j == $(jn).attr(ns.userParameter.nameExtAttr)) {
                _limitVal = $(jn).attr('' + (j + ns.userParameter.limitExtAttr).toLowerCase() + '');
                if ((/^\d+$/.test(_limitVal)) && _limitVal > 0) {
                  _limitMap.put(j, _limitVal);
                }
                return false;
              }
            });
          }
          // substr text
          var _tmpLimit = _limitMap.get(j);
          if (_tmpLimit > 0 && (_tmp.length > _tmpLimit)) {
            _tmp = _tmp.substr(0, _tmpLimit) + " ...";
          }
          // replace text
          _html = _html.replace(new RegExp("{" + j + "}", "gm"), _tmp);
        }
        s += _html;
        s += '</tr>';
      }
      // clear map
      _limitMap.clear();
      
      // append to head
      $(s).insertAfter(_head);
      // load all checkbox
      ns.setChildChkbox(_table);
      _table.css("display", ""); // display table
    } else {
      if (_head != null) {
        var _tbLength = _head.children("th").length;
        if (_tbLength == 0) {
          _tbLength = _head.children("td").length;
          if (_tbLength == 0) {
            _tbLength = 1;
          }
        }
        // remove tempBody
        $("#" + prefix + '' + ns.userParameter.tempBodyId + '').css("display", "none");
        _table.append("<tr id='" + prefix + "" + ns.userParameter.noneDataId
            + "'><td height='30px' style='color:red;font-weight:bold;' colspan='" + _tbLength + "'>暂无有效数据！</td></tr>");
      }
    }
    // callback func
    if (ns.isFunc(callback)) {
      callback(jsonList);
    }
    
  };
  
  /**
   * Draw paging bar
   * 
   * @param pageNum
   * @param recordNum
   * @param currPage
   * @param prefix
   * @param callback
   */
  ns.drawPagingBar = function(pageNum, recordNum, currPage, prefix, callback) {
    var _pageDiv = $("#" + prefix + ns.userParameter.pageDivId);
    if (_pageDiv != null) {
      pageNum = parseInt(pageNum);
      recordNum = parseInt(recordNum);
      currPage = parseInt(currPage);
      var s = '';
      // first page
      if (currPage == 1) {
        s += "<label>首页</label>";
      } else {
        s += "<label class='hand' onclick=\"EcDatagrid.doPage(" + 1 + ",'" + prefix + "'," + callback + ")\">首页</label>";
      }
      // up page
      s += ns.addUpBtn(currPage, prefix, callback);
      if (pageNum <= 5) {
        for (var i = 1; i <= pageNum; i++) { // 1 page
          s += ns.addPageBtn(i, currPage, prefix, callback);
        }
      } else if (currPage <= 2) { // 2 page
        for (var i = 1; i <= 5; i++) {
          s += ns.addPageBtn(i, currPage, prefix, callback);
        }
      } else if (currPage >= pageNum - 2) {
        for (var i = pageNum - 4; i <= pageNum; i++) {
          s += ns.addPageBtn(i, currPage, prefix, callback);
        }
      } else if (currPage > 2 || currPage < pageNum - 2) {
        for (var i = currPage - 2; i <= currPage + 2; i++) {
          s += ns.addPageBtn(i, currPage, prefix, callback);
        }
      }
      s += ns.addNextBtn(currPage, pageNum, prefix, callback); // next page
      if (currPage == pageNum) {
        s += "<label>尾页</label>";
      } else {
        s += "<label class='hand' onclick=\"EcDatagrid.doPage(" + pageNum + ",'" + prefix + "'," + callback + ")\">尾页</label>";
      }
      s += '<label>共&nbsp;' + (pageNum == null ? ns.userParameter.pageNum : pageNum) + '&nbsp;页</label>';
      s += '<label>&nbsp;&nbsp;共&nbsp;' + (recordNum == null ? ns.userParameter.recordNum : recordNum)
          + '&nbsp;条记录&nbsp;&nbsp;&nbsp;每页显示</label>';
      s += ns.addPageSize(prefix, callback);
      _pageDiv.html(s);
      
      // init select box
      initSelectBox();
    }
  };
  
  /**
   * Add up button
   * 
   * @param currPage
   * @param prefix
   * @param callback
   */
  ns.addUpBtn = function(currPage, prefix, callback) {
    currPage = parseInt(currPage);
    var s = "";
    if (currPage == 1) {
      s += '<label>上一页</label>';
    } else {
      var up = --currPage;
      s += "<label class='hand' onclick=\"EcDatagrid.doPage(" + up + ",'" + prefix + "'," + callback + ")\" >上一页</label>";
    }
    return s;
  };
  
  /**
   * Add next button
   * 
   * @param currPage
   * @param pageNum
   * @param prefix
   * @param callback
   */
  ns.addNextBtn = function(currPage, pageNum, prefix, callback) {
    currPage = parseInt(currPage);
    pageNum = parseInt(pageNum);
    var s = "";
    if (currPage == pageNum) {
      s += '<label>下一页</label>';
    } else {
      var next = ++currPage;
      s += "<label class='hand' onclick=\"EcDatagrid.doPage(" + next + ",'" + prefix + "'," + callback + ")\" >下一页</label>";
    }
    return s;
  };
  
  /**
   * Add page size
   * 
   * @param prefix
   * @param callback
   */
  ns.addPageSize = function(prefix, callback) {
    var s = '';
    s += '<label><dl class="selectbox selectbox_2" style="z-index: 1;" name="pageSizeSelbox">';
    s += '<dt><span class="fleft">' + $('#' + prefix + 'pageSize').val() + '</span><span class="fright">&nbsp;</span></dt>';
    s += '<dd>';
    s += "<p name=\"10\" onclick=\"EcDatagrid.chgPageSize(10,'" + prefix + "'," + callback + ");\">10</p>";
    s += "<p name=\"50\" onclick=\"EcDatagrid.chgPageSize(50,'" + prefix + "'," + callback + ");\">50</p>";
    s += "<p name=\"100\" onclick=\"EcDatagrid.chgPageSize(100,'" + prefix + "'," + callback + ");\">100</p>";
    s += '</dd>';
    s += '</dl></label><label>条记录</label>';
    return s;
  };
  
  /**
   * Change page size
   * 
   * @param pageSize
   * @param prefix
   * @param callback
   */
  ns.chgPageSize = function(pageSize, prefix, callback) {
    ns.initDatagrid($("#" + prefix + "_url").val(), pageSize, 1, prefix, callback);
  };
  
  /**
   * Do page
   * 
   * @param currPage
   * @param prefix
   * @param callback
   */
  ns.doPage = function(currPage, prefix, callback) {
    ns.initDatagrid($("#" + prefix + "_url").val(), null, currPage, prefix, callback);
  };
  
  /**
   * Add page button
   * 
   * @param indexPage
   * @param currPage
   * @param prefix
   * @param callback
   */
  ns.addPageBtn = function(indexPage, currPage, prefix, callback) {
    indexPage = parseInt(indexPage);
    currPage = parseInt(currPage);
    var s = "";
    if (indexPage == currPage) {
      s += '<label style="color:gray;">' + currPage + '</label>';
    } else {
      s += "<label class='hand' onclick=\"EcDatagrid.doPage(" + indexPage + ",'" + prefix + "'," + callback + ")\" >" + indexPage
          + "</label>";
    }
    return s;
  };
  
  /**
   * Set child checkbox
   * 
   * @param table
   */
  ns.setChildChkbox = function(table) {
    var _name = table.find(".checkedAll").attr("name");
    table.find(".checkedAll").attr("checked", false);
    var childCheck = table.find("input[name=" + _name + "][class!=checkedAll]");
    childCheck.unbind('click').bind('click', function() {
      if (this.checked && (ns.getCkboxVals(_name).length >= childCheck.length - 1)) {
        table.find(".checkedAll").attr("checked", true);
      } else {
        table.find(".checkedAll").attr("checked", false);
      }
    });
  };
  
  /**
   * Get checkbox values
   * 
   * @param name
   */
  ns.getCkboxVals = function(name) {
    var _vals = new Array();
    var _val = null;
    $("input[name='" + name + "']:checked").each(function() {
      _val = $(this).val();
      if (_val != null && _val != "" && _val != 'on' && _val.indexOf('{') == -1) {
        _vals.push(_val);
      }
    });
    return _vals;
  };
  
  /**
   * export datagrid
   * 
   * @param prefix
   * @param url
   */
  ns.exportDatagrid = function(prefix, url) {
    if (prefix == null) {
      prefix = "";
    }
    var _searchRow = $('#' + prefix + '' + ns.userParameter.searchRowId + '');
    var formId = prefix + '' + ns.userParameter.exportFormId + '';
    if ($("#" + formId) == null || $("#" + formId).html() == null) {
      var _form = "<form method='post' action='" + url + "' id='" + formId + "' style='display:none'></form>";
      $("#" + prefix + ns.userParameter.dataTableId).parent().append(_form);
    } else {
      $("#" + formId).prop("action", url);
    }
    _searchRow.find('input,select').filter(function() {
      return !jQuery(this).is(":disabled");
    }).each(
        function(i, elem) {
          var val = jQuery(this).val();
          if (val == null || val == '') {
            return;
          }
          $("#" + formId).append(
              "<input type='hidden' name='sf." + elem.name + "' value='" + val.replace(/(^\s+)|(\s+$)/g, "") + "'/>");
        });
    $("#" + formId).submit();
    $("#" + formId).html("");
  };
  
})(jQuery, EcDatagrid);
