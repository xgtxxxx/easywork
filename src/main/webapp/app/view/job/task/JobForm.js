Ext.define('app.view.job.task.JobForm', {
	extend : 'Ext.form.Panel',
	alias : 'widget.jobform',
	fieldDefaults : {
		labelAlign : "right",
		width : 300
	},
	items : [{
		xtype : 'textfield',
        fieldLabel: 'Name',
        name: 'jobName'
    }, {
    	xtype : 'textfield',
        fieldLabel: 'Class',
        name: 'clazz'
    }, {
    	xtype : 'textfield',
        fieldLabel: 'Description',
        name: 'description'
    }]
});