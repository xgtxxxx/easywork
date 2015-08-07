Ext.define('app.view.job.HistoryView', {
	extend : 'Ext.panel.Panel',
	requires : ['app.view.job.history.JobHistory'],
	alias : 'widget.historyview',
	layout : 'fit',
	border : true,
	items  : [{
		xtype : 'jobhistory'
	}],
	reload : function(){
		this.down('jobhistory').reload();
	}
});