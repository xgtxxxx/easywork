Ext.define('app.view.job.JobViewport', {
	requires : ['app.view.job.TaskView','app.view.job.JobNav','app.view.job.JobController'],
    extend: 'Ext.panel.Panel',
    alias : 'widget.jobviewport',
    controller: 'job',
    layout: 'border',
    border : false,
    items: [{
        region: 'west',
        collapsible : true,
    	collapsed : false,
    	split : true,
        width : 200,
        title : 'Nav',
        xtype : 'jobnav'
    }, {
        region: 'center',
        border : false,
        xtype: 'tabpanel', // TabPanel itself has no title
        activeTab: 0,      // First tab active by default
        closeAction : 'destory',
        layout : 'fit',
        cls : 'border-none-tab',
        items: [{
            xtype : 'taskview',
            title : 'Task'
        }]
    }
    ]
});