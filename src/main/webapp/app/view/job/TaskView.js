Ext.define('app.view.job.TaskView', {
	requires : ['app.view.job.task.TaskList','app.view.job.task.TaskTrigger'],
	extend : 'Ext.panel.Panel',
	alias : 'widget.taskview',
	layout : 'card',
	border : false,
	items  : [{
		xtype : 'tasklist'
	},{
		xtype : 'tasktrigger'
	}]
});