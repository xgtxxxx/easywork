Ext.define('app.view.ali.AliBodyView', {
	extend: 'Ext.panel.Panel',
	requires : ['app.view.ali.Ath4DetailGrid','app.view.ali.Ath4Report'],
	uses : [],
    alias : 'widget.alibodyview',
    controller: 'ali',
    layout: 'card',
    border : false,
    activeItem : 0,
    items: [{
            xtype : 'ath4-detail'
    },{
            xtype : 'ath4-report'
    }]
});