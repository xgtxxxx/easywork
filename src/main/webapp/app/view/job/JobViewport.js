Ext.define('app.view.job.JobViewport', {
	requires : ['app.view.job.TaskView','app.view.job.JobNav','app.view.job.JobController'],
    extend: 'Ext.panel.Panel',
    alias : 'widget.jobviewport',
    controller: 'job',
    layout: 'border',
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
        xtype: 'tabpanel', // TabPanel itself has no title
        activeTab: 0,      // First tab active by default
        closeAction : 'destory',
        bodyPadding : '5',
        layout : 'fit',
        items: [{
            xtype : 'taskview',
            border : true,
            title : 'Task'
        }]
    }
    ]
});