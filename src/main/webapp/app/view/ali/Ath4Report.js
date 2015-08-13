Ext.define('app.view.ali.Ath4Report', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.ath4-report',
	requires : ['app.view.ali.AliController'],
	uses : ['app.store.ali.Ath4ReportStore'],
	controller : 'ali',
	selModel: {
        selType: 'checkboxmodel'
    },
	initComponent : function() {
		var me = this;
		var store = Ext.create('app.store.ali.Ath4ReportStore');
		this.store = store;
		this.columns = [{
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
			width : 50
		}, {
			header : '花名',
			dataIndex : 'name',
			width : 50
		}, {
			header : '一级类目',
			dataIndex : 'subject1'
		}, {
			header : '二级类目',
			dataIndex : 'subject2'
		}, {
			header : '三级类目',
			dataIndex : 'subject3'
		}, {
			header : '总咨询量',
			dataIndex : 'count',
			width : 50,
            field: {
                xtype: 'numberfield'
            }
		}, {
			header : '平均通话时长',
			dataIndex : 'duration',
			width : 100,
            field: {
                xtype: 'numberfield'
            },
			formatter:'round(2)'
		}];
		var groupField = Ext.create('Ext.form.field.ComboBox',{
			labelAlign : 'right',
			fieldLabel : '分类项目',
			labelWidth : 60,
			width : 150,
			editable : false,
			store : [[ 'subject3','三级类目'],[ 'subject2','二级类目'],[ 'subject1','一级类目']],
			value : 'subject3',
			queryMode : 'local',
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                    	searchBtn.fireEvent('click',searchBtn);
                    }
                }
			}
		});
		var countField = Ext.create('Ext.form.field.Number',{
			labelAlign : 'right',
			fieldLabel : '总咨询量',
			labelWidth : 60,
			value : 0,
			width : 150,
			listeners : {
				specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                    	searchBtn.fireEvent('click',searchBtn);
                    }
                }
			}
		});
		var durationField = Ext.create('Ext.form.field.Number',{
			labelAlign : 'right',
			fieldLabel : '通话时长',
			labelWidth : 60,
			value : 0,
			width : 150,
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
        		click : 'reloadDataByGroup'
        	}
		});
		
		var clearBtn = Ext.create('Ext.button.Button',{
			text : '重置',
        	iconCls : 'glyphicon glyphicon-erase',
        	listeners : {
        		click : function(){
            		groupField.reset();
            		countField.reset();
            		durationField.reset();
            	}
        	}
		});
		
		this.tbar = Ext.create('Ext.toolbar.Toolbar',{
			items : [{
				text: '饼图',
				iconCls : 'icon-pie',
				handler: 'showPieChart'
            },'-',{
				text: '柱图',
				iconCls : 'icon-bar',
				handler: 'showColumnChart'
            },'-',{
				text: '线图',
				iconCls : 'icon-line',
				handler: 'showLineChart'
            },'-',
            	groupField,'-',
            	countField,'-',
            	durationField,'-',
            	searchBtn,'-',
            	clearBtn,
            '->', {
				text : '导出',
				iconCls : 'icon-export',
				handler : "doExport"
			},'-', {
				text : '返回',
				iconCls : 'icon-back',
				handler : "goBack"
			}]
		});
		this.getGroupField = function(){
			return groupField;
		};
		this.getCountField = function(){
			return countField;
		};
		this.getDurationField = function(){
			return durationField;
		};
		this.getSearchBtn = function(){
			return searchBtn;
		};
		this.reset = function(){
			clearBtn.fireEvent('click',clearBtn);
		};
//		this.listeners = {
//			afterrender : function(){
//				searchBtn.fireEvent('click',searchBtn);
//			}
//		}
		this.viewConfig = {
			getRowClass : function(record,rowIndex,rowParams,store){
				if(record.data.duration>270){
					return 'grid-row-bgcolor-red';
				}
			}
		};
		this.callParent(arguments);
		Ext.QuickTips.init();
	},
	reload : function(params){
		Ext.apply(this.store.proxy.extraParams,params);
		var searchBtn = this.getSearchBtn();
		searchBtn.fireEvent('click',searchBtn);
	}
});
