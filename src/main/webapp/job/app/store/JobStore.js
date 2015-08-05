Ext.define('Job.store.JobStore', {
    extend: 'Ext.data.Store',
    model: 'Job.model.JobModel',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url:  CTX.PATH+'/job/list.do',
        reader: {
            type: 'json',
            rootProperty : 'result',
            totalProperty : 'totalCount'
        }
    }
});