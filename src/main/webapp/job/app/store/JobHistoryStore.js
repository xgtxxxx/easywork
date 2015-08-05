Ext.define('Job.store.JobHistoryStore', {
    extend: 'Ext.data.Store',
    model: 'Job.model.JobHistoryModel',
    proxy: {
        type: 'ajax',
        url:  CTX.PATH+'/jobHistory/search.do',
        reader: {
            type: 'json',
            rootProperty : 'result',
            totalProperty : 'totalCount'
        }
    }
});