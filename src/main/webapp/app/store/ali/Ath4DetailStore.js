Ext.define('app.store.ali.Ath4DetailStore', {
    extend: 'Ext.data.Store',
    requires : ['app.model.ali.Ath4DetailModel'],
    model: 'app.model.ali.Ath4DetailModel',
    alias : 'store.ath4-detail',
    sortOnLoad : true,
    sorters: {property: 'subject3', direction: 'ASC'},
    groupField: 'subject3',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url:  CTX.PATH+'/ali/listAth4Detail.do',
        reader: {
            type: 'json',
            rootProperty : 'result',
            totalProperty : 'totalCount'
        }
    }
});