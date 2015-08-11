Ext.define('app.store.job.TaskTriggerStore', {
    extend: 'Ext.data.Store',
    model: 'app.model.job.TaskTriggerModel',
    alias : 'store.tasktrigger',
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