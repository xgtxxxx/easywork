Ext.define('app.model.ali.Ath4ReportModel', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],
    fields: [
		'id',
		'insertTime',
		'businessMonth',
		'businessDate',
		'skillGroup',
		'family',
		'name',
		'subject1',
		'subject2',
		'subject3',
		'count',
		'duration'
    ]
});