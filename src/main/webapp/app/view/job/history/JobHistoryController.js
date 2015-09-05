Ext.define('app.view.job.history.JobHistoryController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.jobhistory',
    
    doSearch : function(){
    	var me = this.getView();
		var items = me.down('toolbar').items;
		var params = {},
		jobName=items.items[0].getValue(),
		triggerName=items.items[2].getValue(),
		startTime=items.items[4].getRawValue(),
		endTime=items.items[6].getRawValue();
		if(jobName){
			params.jobName = jobName;
		}
		if(triggerName){
			params.triggerName = triggerName;
		}
		if(startTime){
			params.startRunTime = startTime;
		}
		if(endTime){
			params.endRunTime = endTime+" 23:59:59";
		}
		Ext.apply(me.store.proxy.extraParams,params);
		me.store.load()
	}
});
