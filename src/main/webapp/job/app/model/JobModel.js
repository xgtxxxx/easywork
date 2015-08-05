Ext.define('Job.model.JobModel', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         {name:'name',mapping:'jobkey.name'},
         'clazz',
         'description'
    ]
});