Ext.define('app.store.job.JobHistoryStore', {
    extend: 'Ext.data.Store',
    model: 'app.model.job.JobHistoryModel',
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