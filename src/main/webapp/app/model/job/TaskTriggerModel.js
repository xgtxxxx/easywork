Ext.define('app.model.job.TaskTriggerModel', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         'name',
         'description',
         'priority',
         'startTime',
         'endTime',
         'nextFireTime',
         'previousFireTime',
         'repectCount',
         'repectInterval',
         'cron',
         'type',
         'state'
    ]
});