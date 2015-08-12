Ext.define('app.store.ali.Ath4ReportStore', {
    extend: 'Ext.data.Store',
    requires : ['app.model.ali.Ath4ReportModel'],
    model: 'app.model.ali.Ath4ReportModel',
    alias : 'store.ath4-report',
    sortOnLoad : true,
    sorters: {property: 'businessDate', direction: 'ASC'},
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url:  CTX.PATH+'/ali/listAth4Report.do',
        reader: {
            type: 'json',
            rootProperty : 'result',
            totalProperty : 'totalCount'
        }
    }
});