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
			dataIndex : 'totalCount',
			width : 50,
            field: {
                xtype: 'numberfield'
            }
		}, {
			header : '平均通话时长',
			dataIndex : 'avgDuration',
			width : 100,
            field: {
                xtype: 'numberfield'
            }
		}];
		this.tbar = [{
				text: '收起',
				iconCls : 'icon-collapse',
				handler: function() {
					me.getView().getFeature('group').collapseAll();
				}
            },'-',{
				text: '展开',
				iconCls : 'icon-expand',
				handler: function() {
					me.getView().getFeature('group').expandAll();
				}
            },'->', {
				text : 'Export',
				iconCls : 'icon-export',
				handler : "doExport"
			}];
		this.callParent(arguments);
		Ext.QuickTips.init();
	}
});
