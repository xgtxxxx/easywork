Ext.define('Job.store.JobTriggerStore', {
    extend: 'Ext.data.Store',
    model: 'Job.model.JobTriggerModel',
//    autoLoad: true,
    proxy: {
        type: 'ajax',
        url:  CTX.PATH+'/job/listJobTrigger.do',
        reader: {
            type: 'json',
            rootProperty : 'result',
            totalProperty : 'totalCount'
        }
    }
});