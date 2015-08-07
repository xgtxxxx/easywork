Ext.define('app.store.job.TaskStore', {
    extend: 'Ext.data.Store',
    requires : ['app.model.job.TaskModel'],
    model: 'app.model.job.TaskModel',
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