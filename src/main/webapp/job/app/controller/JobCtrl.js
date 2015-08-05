Ext.define('Job.controller.JobCtrl', {
    extend: 'Ext.app.Controller',

    stores: [
        'JobStore@Job.store',
        'JobHistoryStore@Job.store',
        'JobTriggerStore@Job.store'
    ],

    models: [
        'JobModel@Job.model',
        'JobHistoryModel@Job.model',
        'JobTriggerModel@Job.model'
    ],

    views: [
        'Viewport',
        'JobContainer@Job.view.jobview',
        'HistoryContainer@Job.view.jobview',
        'TriggerForm@Job.view.jobview',
        'JobForm@Job.view.jobview',
        'JobTrigger@Job.view.jobview',
        'JobList@Job.view.jobview',
        'JobHistory@Job.view.jobview',
        'Nav@Job.view.jobview'
    ],

    init: function() {
    	
    	 this.control({
    		 'nav' : {
    			 cellclick : this.changeTab
    		 }
         });
    },
    
    changeTab : function(t, td, cellIndex, record, tr, rowIndex, e, eOpts) {
		var tab = Ext.getCmp('tabpanelid');
    	var panel = tab.child(record.data.xtype);
		if (!panel) {
			panel = Ext.create(record.data.clazz, {
				title : record.data.text,
				closable : true
			});
			panel.reload();
			tab.add(panel);
		}
		tab.setActiveTab(panel);
	}
});
