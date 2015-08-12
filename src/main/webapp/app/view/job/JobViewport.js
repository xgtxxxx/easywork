Ext.define('app.view.job.JobViewport', {
	requires : ['app.view.job.TaskView',
	            'app.view.job.JobNav',
	            'app.view.job.JobController',
	            'app.view.common.MainTab'],
    extend: 'Ext.panel.Panel',
    alias : 'widget.jobviewport',
    controller: 'job',
    layout: 'border',
    border : false,
    items: [{
        title : 'JobNav',
        xtype : 'jobnav'
    }, {
        xtype: 'maintab',
        items: [{
            xtype : 'taskview',
            title : 'Task'
        }]
    }
    ]
});