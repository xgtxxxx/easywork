Ext.define('app.view.common.Nav', {
	extend : 'Ext.tree.Panel',
	rootVisible : false,
	region: 'west',
    collapsible : true,
	collapsed : false,
	split : true,
    width : 200,
    listeners : [{
		cellclick : 'switchTabPanel'
	}]
});
