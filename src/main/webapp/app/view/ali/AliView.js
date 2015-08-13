Ext.define('app.view.ali.AliView', {
	extend: 'Ext.panel.Panel',
	requires : ['app.view.ali.AliNav',
	            'app.view.ali.AliController',
	            'app.view.common.MainTab',
	            ,'app.view.ali.Ath4Report'],
	uses : [],
    alias : 'widget.aliview',
    controller: 'ali',
    layout: 'border',
    border : false,
    items: [{
        title : '菜单',
        xtype : 'alinav'
    }, {
        xtype : 'maintab',
        items: [{
            xtype : 'ath4-report',
            title : 'AHT4'
        }]
    }]
});