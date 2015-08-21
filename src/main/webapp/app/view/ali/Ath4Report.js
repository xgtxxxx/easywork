Ext.define('app.view.ali.Ath4Report', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.ath4-report',
	requires : ['app.view.ali.AliController','app.view.ali.AliViewModel'],
	uses : ['app.store.ali.Ath4ReportStore'],
	controller : 'ali',
	viewModel : 'ali',
	selModel: {
        selType: 'checkboxmodel'
    },
	initComponent : function() {
		var me = this;
		var store = Ext.create('app.store.ali.Ath4ReportStore');
		this.store = store;
		this.columns = [{
			xtype : 'rownumberer',
			width : 35
		},{
			header : '日期',
			dataIndex : 'businessDate',
			xtype : 'datecolumn',
			format : 'Ymd',
			width : 70
		}, {
			header : '技能组',
			dataIndex : 'skillGroup',
			width : 70
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
			width : 150
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
		var groupField = Ext.create('Ext.form.field.ComboBox',{
			labelAlign : 'right',
			fieldLabel : '分类项目',
			labelWidth : 60,
			width : 150,
			editable : false,
			store : [['subject4','四级类目'],[ 'subject3','三级类目'],[ 'subject2','二级类目'],[ 'subject1','一级类目']],
			value : 'subject4',
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
			width : 120,
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
			width : 120,
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
            		startMonth.reset();
            		endMonth.reset();
            	}
        	}
		});
		
		this.tbar = Ext.create('Ext.toolbar.Toolbar',{
			items : [{
//				text: '饼图',
				iconCls : 'icon-pie',
				handler: 'showPieChart'
            },'-',{
//				text: '柱图',
				iconCls : 'icon-bar',
				handler: 'showColumnChart'
            },'-',{
//				text: '线图',
				iconCls : 'icon-line',
				handler: 'showLineChart'
            },'-',
            	startMonth,'-',
            	endMonth,'-',
            	groupField,'-',
            	countField,'-',
            	durationField,'-',
            	searchBtn,'-',
            	clearBtn,
            '->',{
            	text : '导入',
				iconCls : 'icon-import',
				handler : "showImportWin",
				bind : {
					hidden : '{btn_import}'
				}
			}, {
				text : '导出',
				iconCls : 'icon-export',
				handler : "doExport",
				bind : {
					hidden : '{btn_export}'
				}
			}]
		});
		this.getGroupField = function(){
			return groupField.getValue();
		};
		this.getCountField = function(){
			return countField.getValue();
		};
		this.getDurationField = function(){
			return durationField.getValue();
		};
		this.getStartField = function(){
			if(startMonth.getValue()){
				return startMonth.getValue();
			}else{
				return '';
			}
		};
		this.getEndField = function(){
			if(endMonth.getValue()){
				return endMonth.getValue();
			}else{
				return '';
			}
		};
		this.getSearchBtn = function(){
			return searchBtn;
		};
		this.reset = function(){
			clearBtn.fireEvent('click',clearBtn);
		};
		this.listeners = {
			afterrender : function(){
				searchBtn.fireEvent('click',searchBtn);
			}
		}
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
	search : function(filterString){
		var ignores = ['id','insertTime','businessMonth'];
		ExtUtil.filterStore(this.oriStore,this.getStore(),filterString,ignores);
	}
});
