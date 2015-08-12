Ext.define('app.view.job.JobNav', {
	extend : 'app.view.common.Nav',
	alias : 'widget.jobnav',
	xtype : 'jobnav',
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [{
					text : "Task",
					xtype : "taskview",
					clazz : "app.view.job.TaskView",
					iconCls  : 'icon-leaf',
					leaf : true
				},{
					text : "History",
					xtype: "historyview",
					clazz : "app.view.job.HistoryView",
					iconCls  : 'icon-leaf',
					leaf : true
				}]
			}
		});
		this.callParent(arguments);
	}
});
