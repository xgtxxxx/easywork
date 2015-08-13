Ext.define('app.view.ali.Ath4DetailGrid', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.ath4-detail',
	requires : ['app.view.ali.AliController'],
	uses : ['app.store.ali.Ath4DetailStore'],
	controller : 'ali',
	initComponent : function() {
		var me = this;
		var store = Ext.create('app.store.ali.Ath4DetailStore');
		this.store = store;
		this.features = [{
	        id: 'group',
	        ftype: 'groupingsummary',
	        groupHeaderTpl: '三级类目:{name}',
	        hideGroupedHeader: true,
	        enableGroupingMenu: false
	    }];
		this.columns = [ {
			xtype : 'rownumberer',
			width : 35
		}, {
			header : '日期',
			dataIndex : 'businessDate',
			xtype : 'datecolumn',
			format : 'Ymd'
		}, {
			header : '技能组',
			dataIndex : 'skillGroup'
		}, {
			header : '客栈',
			dataIndex : 'family',
			width : 40
		}, {
			header : '花名',
			dataIndex : 'name',
			width : 40
		}, {
			header : '一级类目',
			dataIndex : 'subject1',
			width : 60
		}, {
			header : '二级类目',
			dataIndex : 'subject2'
		}, {
			header : '三级类目',
			dataIndex : 'subject3'
		}, {
			header : '四级类目',
			dataIndex : 'subject4',
			width : 150,
		}, {
			header : '咨询量',
			dataIndex : 'count',
			width : 100,
			summaryType: 'sum',
            summaryRenderer: function(value, summaryData, dataIndex) {
                return '总咨询量:'+value + '个';
            },
            field: {
                xtype: 'numberfield'
            }
		}, {
			header : '平均通话时长',
			dataIndex : 'duration',
			width : 100,
            summaryType: 'average',
            field: {
                xtype: 'numberfield'
            },
            summaryRenderer: function(value, summaryData, dataIndex) {
                return '平均时长:'+value + '秒';
            },
		}];
		
		var month = Ext.Date.format(new Date(),'Ym');
		
		var startMonth = Ext.create('Ext.form.field.Text',{
			fieldLabel : '起始月',
			labelAlign : 'right',
			labelWidth : 50,
			width : 120,
			value : month,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                    	searchBtn.fireEvent('click',searchBtn);
                    }
                }
			}
		});
		
		var endMonth = Ext.create('Ext.form.field.Text',{
			fieldLabel : '截止月',
			labelAlign : 'right',
			labelWidth : 50,
			width : 120,
			value : month,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                    	searchBtn.fireEvent('click',searchBtn);
                    }
                }
			}
		});
		
		var searchBtn = Ext.create('Ext.button.Button',{
			text : '查询',
        	iconCls : 'glyphicon glyphicon-search',
        	listeners : {
        		click : 'reloadDetailData'
        	}
		});
		
		var clearBtn = Ext.create('Ext.button.Button',{
			text : '重置',
        	iconCls : 'glyphicon glyphicon-erase',
        	handler : function(){
        		startMonth.reset();
        		endMonth.reset();
        	}
		});
		
		this.tbar = [{
				text: '收起',
				iconCls : 'glyphicon glyphicon-minus',
				handler: function() {
					me.getView().getFeature('group').collapseAll();
				}
            },'-',{
				text: '展开',
				iconCls : 'glyphicon glyphicon-plus',
				handler: function() {
					me.getView().getFeature('group').expandAll();
				}
            },'-',startMonth,
              '-',endMonth,
              '-',searchBtn,
              '-',clearBtn,
            '->', {
				text : '导入',
				iconCls : 'icon-add',
				handler : "showImportWin"
			},'-',{
				text : '报表',
				iconCls : 'icon-go',
				handler : "goReport"
			}];
		this.getStartMonth = function(){
			if(startMonth.getValue()){
				return startMonth.getValue();
			}else{
				return '';
			}
		},
		this.getEndMonth = function(){
			if(endMonth.getValue()){
				return endMonth.getValue();
			}else{
				return '';
			}
		},
		this.listeners = {
			afterrender : function(){
				searchBtn.fireEvent('click',searchBtn);
			}
		}
		this.callParent(arguments);
		Ext.QuickTips.init();
	}
});
