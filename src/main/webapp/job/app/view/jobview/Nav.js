Ext.define('Job.view.jobview.Nav', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.nav',
	rootVisible : false,
	border : false,
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [{
					text : "Job",
					xtype : "jobcontainer",
					clazz : "Job.view.jobview.JobContainer",
					leaf : true
				},{
					text : "Job History",
					xtype:"historycontainer",
					clazz : "Job.view.jobview.HistoryContainer",
					leaf : true
				}]
			}
		});
		this.callParent(arguments);
	}
});
