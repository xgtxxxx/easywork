Ext.define('app.view.common.MainTab', {
	extend : 'Ext.tab.Panel',
	alias : 'widget.maintab',
	region: 'center',
    border : false,
    activeTab: 0,      // First tab active by default
    closeAction : 'destory',
    layout : 'fit',
    cls : 'border-none-tab'
});
