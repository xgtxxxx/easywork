Ext.define('app.view.ali.AliView', {
	extend: 'Ext.panel.Panel',
	requires : ['app.view.ali.AliNav',
	            'app.view.ali.AliController',
	            'app.view.common.MainTab',
	            'app.view.ali.AliBodyView'],
	uses : [],
    alias : 'widget.aliview',
    controller: 'ali',
    layout: 'border',
    border : false,
    items: [{
        title : 'AliNav',
        xtype : 'alinav'
    }, {
        xtype : 'maintab',
        items: [{
            xtype : 'alibodyview',
            title : 'AHT4'
        }]
    }]
});