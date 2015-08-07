Ext.define('app.view.job.JobNav', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.jobnav',
	xtype : 'jobnav',
	rootVisible : false,
	border : false,
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [{
					text : "Task",
					xtype : "taskview",
					clazz : "app.view.job.TaskView",
					leaf : true
				},{
					text : "History",
					xtype: "historyview",
					clazz : "app.view.job.HistoryView",
					leaf : true
				}]
			}
		});
		this.callParent(arguments);
	},
	listeners : [{
		cellclick : 'switchTabPanel'
	}]
});
