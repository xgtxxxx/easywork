Ext.define('app.view.ali.AliNav', {
	extend : 'app.view.common.Nav',
	alias : 'widget.alinav',
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [{
					text : "AHT4",
					xtype : "alibodyview",
					clazz : "app.view.ali.Ath4BodyGrid",
					iconCls  : 'icon-leaf',
					leaf : true
				}]
			}
		});
		this.callParent(arguments);
	}
});
