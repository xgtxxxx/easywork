Ext.define('app.view.ali.AliNav', {
	extend : 'app.view.common.Nav',
	alias : 'widget.alinav',
	initComponent : function() {
		this.store = Ext.create('Ext.data.TreeStore', {
			root : {
				expanded : true,
				children : [{
					text : "ATH4 List",
					xtype : "ath4-detail",
					clazz : "app.view.ali.Ath4DetailGrid",
					iconCls  : 'icon-leaf',
					leaf : true
				},{
					text : "ATH4 Report",
					xtype: "ath4-report",
					clazz : "app.view.ali.Ath4Report",
					iconCls  : 'icon-leaf',
					leaf : true
				}]
			}
		});
		this.callParent(arguments);
	}
});
