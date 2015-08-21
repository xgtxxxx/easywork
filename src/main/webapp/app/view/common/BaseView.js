Ext.define('app.view.common.BaseView', {
	requires : [
	            'app.view.common.Nav',
	            'app.view.common.NavController',
	            'app.view.common.MainTab'],
    extend: 'Ext.panel.Panel',
    alias : 'widget.baseview',
    controller: 'nav',
    layout: 'border',
    border : false,
    initComponent : function() {
		var me = this;
		this.items = [{
	        title : 'JobNav',
	        xtype : 'nav',
	        store : me.navStore
	    }, {
	        xtype: 'maintab',
	        items: []
	    }];
		this.callParent(arguments);
	}
});