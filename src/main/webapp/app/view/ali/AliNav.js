Ext.define('app.view.ali.AliNav', {
	extend : 'app.view.common.Nav',
	alias : 'widget.alinav',
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [{
					text : "AHT4",
					xtype : "ath4-report",
					clazz : "app.view.ali.Ath4Report",
					iconCls  : 'icon-leaf',
					leaf : true
				}]
			}
		});
		this.callParent(arguments);
	}
});
