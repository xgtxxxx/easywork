Ext.define('app.model.job.JobHistoryModel', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
		'id',
		'jobName',
		'triggerName',
		'previousFireTime',
		'nextFireTime',
		'runTime',
		'refireCount',
		'message',
		'success'
    ]
});