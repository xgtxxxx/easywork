Ext.define('Job.view.jobview.JobContainer', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.jobcontainer',
	layout : 'card',
	border : false,
	items  : [{
		xtype : 'joblist'
	},{
		xtype : 'jobtrigger'
	}]
});