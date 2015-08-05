Ext.define('Job.view.jobview.HistoryContainer', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.historycontainer',
	layout : 'fit',
	border : false,
	items  : [{
		xtype : 'jobhistory'
	}],
	reload : function(){
		this.down('jobhistory').reload();
	}
});